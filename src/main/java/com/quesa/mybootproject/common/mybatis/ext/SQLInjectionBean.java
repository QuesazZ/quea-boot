package com.quesa.mybootproject.common.mybatis.ext;

import org.apache.ibatis.mapping.*;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SQL注入
 *
 * @author 陈双
 */
@Component
@EnableConfigurationProperties(CommonProperties.class)
public class SQLInjectionBean implements InitializingBean {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Autowired
    private CommonProperties commonProperties;
    private Configuration configuration;
    private LanguageDriver languageDriver;
    private List<EntityMapping> entityMappings = new ArrayList<EntityMapping>(100);
    private Map<String, String> mappers = new HashMap<String, String>(100);

    public void addEntityMapping(EntityMapping mapping) {
        this.entityMappings.add(mapping);
    }

    public void addMapper(String entityClass, String mapperClass) {
        this.mappers.put(entityClass, mapperClass);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("start init SQL .....");
        if (this.configuration == null) {
            this.configuration = this.sqlSessionFactory.getConfiguration();
            this.languageDriver = this.configuration.getDefaultScriptingLanuageInstance();
        }
        //开始扫描注解
        PackageScanner scanner = new PackageScanner(commonProperties, this);
        logger.info("scann package ....");
        scanner.scann();
        Method buildAllStatements = this.configuration.getClass().getDeclaredMethod("buildAllStatements", null);
        buildAllStatements.setAccessible(true);
        buildAllStatements.invoke(this.configuration, null);
        /*
         * 初始化MappedStatement
         */
        for (EntityMapping mapping : this.entityMappings) {
            createResultMap(mapping);
            addInsertMappedStatement(mapping);
            addInsertListMappedStatement(mapping);
            addUpdateMappedStatement(mapping);
            addUpdateByMapMappedStatement(mapping);
            addGetByIdMappedStatement(mapping);
            addGetCountMappedStatement(mapping);
            addGetListMappedStatement(mapping);
            addGetListByIdsMappedStatement(mapping);
            addGetIdListMappedStatement(mapping);
            addGetMappedStatement(mapping);
            addDeleteByIdMappedStatement(mapping);
            addDeleteMappedStatement(mapping);
            addExecuteDeleteBySqlMappedStatement(mapping);
            addExecuteInsertBySqlMappedStatement(mapping);
            addExecuteQueryListBySqlMappedStatement(mapping);
            addExecuteQueryOneBySqlMappedStatement(mapping);
            addExecuteUpdateBySqlMappedStatement(mapping);
        }
        logger.info("end init SQL .....");
    }

    /**
     * 创建ResultMap
     *
     * @param mapping
     * @throws ClassNotFoundException
     */
    public void createResultMap(EntityMapping mapping) throws ClassNotFoundException {
        List<ResultMapping> list = new ArrayList<ResultMapping>();
        for (PropertyMapping propertyMapping : mapping.getPropertyMappings()) {
            ResultMapping resultMapping = new ResultMapping.Builder(this.configuration, propertyMapping.getProperty(), propertyMapping.getColumn(), propertyMapping.getType()).build();
            list.add(resultMapping);
        }
        Class<?> entityClass = Class.forName(mapping.getClassName());
        ResultMap resultMap = new ResultMap.Builder(this.configuration, getResultMapId(mapping.getClassName()), entityClass, list).build();
        this.configuration.addResultMap(resultMap);
    }

    /**
     * 添加insert
     *
     * @throws ClassNotFoundException
     */
    public void addInsertMappedStatement(EntityMapping mapping) throws ClassNotFoundException {
        String id = this.mappers.get(mapping.getClassName()) + ".insert";
        if (this.configuration.hasStatement(id, false)) {
            return;
        }
        Class<?> clz = Class.forName(mapping.getClassName());
        String sql = SQLHeper.generateInsertSql(mapping);
        SqlSource sqlSource = this.languageDriver.createSqlSource(configuration, sql, clz);
        MappedStatement ms = new Builder(this.configuration, id, sqlSource, SqlCommandType.INSERT).build();
        this.configuration.addMappedStatement(ms);
    }

    /**
     * 添加insertList
     */
    public void addInsertListMappedStatement(EntityMapping mapping) {
        String id = this.mappers.get(mapping.getClassName()) + ".insertList";
        if (this.configuration.hasStatement(id, false)) {
            return;
        }
        String sql = SQLHeper.generateInsertListSql(mapping);
        SqlSource sqlSource = this.languageDriver.createSqlSource(configuration, sql, List.class);
        MappedStatement ms = new Builder(this.configuration, id, sqlSource, SqlCommandType.INSERT).build();
        this.configuration.addMappedStatement(ms);
    }

    /**
     * 添加update
     *
     * @param mapping
     * @throws ClassNotFoundException
     */
    public void addUpdateMappedStatement(EntityMapping mapping) throws ClassNotFoundException {
        String id = this.mappers.get(mapping.getClassName()) + ".update";
        if (this.configuration.hasStatement(id, false)) {
            return;
        }
        Class<?> clz = Class.forName(mapping.getClassName());
        String sql = SQLHeper.generateUpdateSql(mapping);
        SqlSource sqlSource = this.languageDriver.createSqlSource(configuration, sql, clz);
        MappedStatement ms = new Builder(this.configuration, id, sqlSource, SqlCommandType.UPDATE).build();
        this.configuration.addMappedStatement(ms);
    }

    /**
     * 添加updateByMap
     *
     * @param mapping
     */
    public void addUpdateByMapMappedStatement(EntityMapping mapping) {
        String id = this.mappers.get(mapping.getClassName()) + ".updateByMap";
        if (this.configuration.hasStatement(id, false)) {
            return;
        }
        String sql = SQLHeper.generateUpdateByMapSql(mapping);
        SqlSource sqlSource = this.languageDriver.createSqlSource(configuration, sql, Map.class);
        MappedStatement ms = new Builder(this.configuration, id, sqlSource, SqlCommandType.UPDATE).build();
        this.configuration.addMappedStatement(ms);
    }

    /**
     * 添加getById
     *
     * @param mapping
     */
    public void addGetByIdMappedStatement(EntityMapping mapping) {
        String id = this.mappers.get(mapping.getClassName()) + ".getById";
        if (this.configuration.hasStatement(id, false)) {
            return;
        }
        ArrayList<ResultMap> resultMaps = new ArrayList<>();
        //对应xml中的resultSet的id
        resultMaps.add(this.configuration.getResultMap(getResultMapId(mapping.getClassName())));
        String sql = SQLHeper.generateGetByIdSql(mapping);
        SqlSource sqlSource = this.languageDriver.createSqlSource(configuration, SQLHeper.generateGetByIdSql(mapping), Serializable.class);
        MappedStatement ms = new Builder(this.configuration, id, sqlSource, SqlCommandType.SELECT).resultMaps(resultMaps).build();
        this.configuration.addMappedStatement(ms);
    }

    /**
     * 添加get
     *
     * @param mapping
     * @throws ClassNotFoundException
     */
    public void addGetMappedStatement(EntityMapping mapping) throws ClassNotFoundException {
        String id = this.mappers.get(mapping.getClassName()) + ".get";
        if (this.configuration.hasStatement(id, false)) {
            return;
        }
        Class<?> clz = Class.forName(mapping.getClassName());
        ArrayList<ResultMap> resultMaps = new ArrayList<>();
        //对应xml中的resultSet的id
        resultMaps.add(this.configuration.getResultMap(getResultMapId(mapping.getClassName())));
        String sql = SQLHeper.generateGetSql(mapping);
        SqlSource sqlSource = this.languageDriver.createSqlSource(configuration, sql, clz);
        MappedStatement ms = new Builder(this.configuration, id, sqlSource, SqlCommandType.SELECT).resultMaps(resultMaps).build();
        this.configuration.addMappedStatement(ms);
    }

    /**
     * 添加getList
     *
     * @param mapping
     * @throws ClassNotFoundException
     */
    public void addGetListMappedStatement(EntityMapping mapping) throws ClassNotFoundException {
        String id = this.mappers.get(mapping.getClassName()) + ".getList";
        if (this.configuration.hasStatement(id, false)) {
            return;
        }
        Class<?> clz = Class.forName(mapping.getClassName());
        ArrayList<ResultMap> resultMaps = new ArrayList<>();
        //对应xml中的resultSet的id
        resultMaps.add(this.configuration.getResultMap(getResultMapId(mapping.getClassName())));
        String sql = SQLHeper.generateGetListSql(mapping);
        SqlSource sqlSource = this.languageDriver.createSqlSource(configuration, sql, clz);
        MappedStatement ms = new Builder(this.configuration, id, sqlSource, SqlCommandType.SELECT).resultMaps(resultMaps).build();
        this.configuration.addMappedStatement(ms);
    }

    /**
     * 生成getListByIds
     *
     * @param mapping
     */
    public void addGetListByIdsMappedStatement(EntityMapping mapping) {
        String id = this.mappers.get(mapping.getClassName()) + ".getListByIds";
        if (this.configuration.hasStatement(id, false)) {
            return;
        }
        ArrayList<ResultMap> resultMaps = new ArrayList<>();
        //对应xml中的resultSet的id
        resultMaps.add(this.configuration.getResultMap(getResultMapId(mapping.getClassName())));
        String sql = SQLHeper.generateGetListByIdsSql(mapping);
        SqlSource sqlSource = this.languageDriver.createSqlSource(configuration, sql, List.class);
        MappedStatement ms = new Builder(this.configuration, id, sqlSource, SqlCommandType.SELECT).resultMaps(resultMaps).build();
        this.configuration.addMappedStatement(ms);
    }

    /**
     * 生成getIdList
     *
     * @param mapping
     * @throws ClassNotFoundException
     */
    public void addGetIdListMappedStatement(EntityMapping mapping) throws ClassNotFoundException {
        String id = this.mappers.get(mapping.getClassName()) + ".getIdList";
        if (this.configuration.hasStatement(id, false)) {
            return;
        }
        Class<?> clz = Class.forName(mapping.getClassName());
        String sql = SQLHeper.generateGetIdListSql(mapping);
        SqlSource sqlSource = this.languageDriver.createSqlSource(configuration, sql, clz);
        List<ResultMap> resultMaps = new ArrayList<ResultMap>();
        //生成ResultMap
        ResultMap resultMap = new ResultMap.Builder(this.configuration, id + "-MultiLine", String.class, new ArrayList<ResultMapping>()).build();
        resultMaps.add(resultMap);
        MappedStatement ms = new Builder(this.configuration, id, sqlSource, SqlCommandType.SELECT).resultMaps(resultMaps).build();
        this.configuration.addMappedStatement(ms);
    }

    /**
     * 添加getCount
     *
     * @param mapping
     * @throws ClassNotFoundException
     */
    public void addGetCountMappedStatement(EntityMapping mapping) throws ClassNotFoundException {
        String id = this.mappers.get(mapping.getClassName()) + ".getCount";
        if (this.configuration.hasStatement(id, false)) {
            return;
        }
        Class<?> clz = Class.forName(mapping.getClassName());
        String sql = SQLHeper.generateGetCountSql(mapping);
        SqlSource sqlSource = this.languageDriver.createSqlSource(configuration, sql, clz);
        List<ResultMap> resultMaps = new ArrayList<ResultMap>();
        //生成ResultMap
        ResultMap resultMap = new ResultMap.Builder(this.configuration, id + "-Inline", Integer.class, new ArrayList<ResultMapping>()).build();
        resultMaps.add(resultMap);
        MappedStatement ms = new Builder(this.configuration, id, sqlSource, SqlCommandType.SELECT).resultMaps(resultMaps).build();
        this.configuration.addMappedStatement(ms);
    }

    /**
     * 添加deleteById
     *
     * @param mapping
     * @throws ClassNotFoundException
     */
    public void addDeleteByIdMappedStatement(EntityMapping mapping) throws ClassNotFoundException {
        String id = this.mappers.get(mapping.getClassName()) + ".deleteById";
        if (this.configuration.hasStatement(id, false)) {
            return;
        }
        String sql = SQLHeper.generateDeleteByIdSql(mapping);
        SqlSource sqlSource = this.languageDriver.createSqlSource(configuration, sql, Serializable.class);
        MappedStatement ms = new Builder(this.configuration, id, sqlSource, SqlCommandType.DELETE).build();
        this.configuration.addMappedStatement(ms);
    }

    /**
     * 添加delete
     *
     * @param mapping
     * @throws ClassNotFoundException
     */
    public void addDeleteMappedStatement(EntityMapping mapping) throws ClassNotFoundException {
        String id = this.mappers.get(mapping.getClassName()) + ".delete";
        if (this.configuration.hasStatement(id, false)) {
            return;
        }
        Class<?> clz = Class.forName(mapping.getClassName());
        String sql = SQLHeper.generateDeleteSql(mapping);
        SqlSource sqlSource = this.languageDriver.createSqlSource(configuration, sql, clz);
        MappedStatement ms = new Builder(this.configuration, id, sqlSource, SqlCommandType.DELETE).build();
        this.configuration.addMappedStatement(ms);
    }

    /**
     * 添加executeInsertBySql
     *
     * @param mapping
     */
    public void addExecuteInsertBySqlMappedStatement(EntityMapping mapping) {
        String id = this.mappers.get(mapping.getClassName()) + ".executeInsertBySql";
        if (this.configuration.hasStatement(id, false)) {
            return;
        }
        SqlSource sqlSource = this.languageDriver.createSqlSource(configuration, "${sql}", Map.class);
        MappedStatement ms = new Builder(this.configuration, id, sqlSource, SqlCommandType.INSERT).build();
        this.configuration.addMappedStatement(ms);
    }

    /**
     * 添加executeUpdateBySql
     *
     * @param mapping
     */
    public void addExecuteUpdateBySqlMappedStatement(EntityMapping mapping) {
        String id = this.mappers.get(mapping.getClassName()) + ".executeUpdateBySql";
        if (this.configuration.hasStatement(id, false)) {
            return;
        }
        SqlSource sqlSource = this.languageDriver.createSqlSource(configuration, "${sql}", Map.class);
        MappedStatement ms = new Builder(this.configuration, id, sqlSource, SqlCommandType.UPDATE).build();
        this.configuration.addMappedStatement(ms);
    }

    /**
     * 添加executeDeleteBySql
     *
     * @param mapping
     */
    public void addExecuteDeleteBySqlMappedStatement(EntityMapping mapping) {
        String id = this.mappers.get(mapping.getClassName()) + ".executeDeleteBySql";
        if (this.configuration.hasStatement(id, false)) {
            return;
        }
        SqlSource sqlSource = this.languageDriver.createSqlSource(configuration, "${sql}", Map.class);
        MappedStatement ms = new Builder(this.configuration, id, sqlSource, SqlCommandType.DELETE).build();
        this.configuration.addMappedStatement(ms);
    }

    /**
     * 添加executeQueryOneBySql
     *
     * @param mapping
     */
    public void addExecuteQueryOneBySqlMappedStatement(EntityMapping mapping) {
        String id = this.mappers.get(mapping.getClassName()) + ".executeQueryOneBySql";
        if (this.configuration.hasStatement(id, false)) {
            return;
        }
        ArrayList<ResultMap> resultMaps = new ArrayList<>();
        //对应xml中的resultSet的id
        resultMaps.add(this.configuration.getResultMap(getResultMapId(mapping.getClassName())));
        SqlSource sqlSource = this.languageDriver.createSqlSource(configuration, "${sql}", Map.class);
        MappedStatement ms = new Builder(this.configuration, id, sqlSource, SqlCommandType.SELECT).resultMaps(resultMaps).build();
        this.configuration.addMappedStatement(ms);
    }

    /**
     * 添加executeQueryListBySql
     *
     * @param mapping
     */
    public void addExecuteQueryListBySqlMappedStatement(EntityMapping mapping) {
        String id = this.mappers.get(mapping.getClassName()) + ".executeQueryListBySql";
        if (this.configuration.hasStatement(id, false)) {
            return;
        }
        ArrayList<ResultMap> resultMaps = new ArrayList<>();
        //对应xml中的resultSet的id
        resultMaps.add(this.configuration.getResultMap(getResultMapId(mapping.getClassName())));
        SqlSource sqlSource = this.languageDriver.createSqlSource(configuration, "${sql}", Map.class);
        MappedStatement ms = new Builder(this.configuration, id, sqlSource, SqlCommandType.SELECT).resultMaps(resultMaps).build();
        this.configuration.addMappedStatement(ms);
    }

    private String getResultMapId(String entityName) {
        return "Base" + entityName.substring(entityName.lastIndexOf(".") + 1) + "ResultMap";
    }
}
