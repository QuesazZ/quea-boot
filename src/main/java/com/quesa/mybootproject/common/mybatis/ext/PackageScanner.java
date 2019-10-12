package com.quesa.mybootproject.common.mybatis.ext;

import com.quesa.mybootproject.common.annotation.FieldConfig;
import com.quesa.mybootproject.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 包工具，根据package路径，加载class，查找@Table和@Column注解
 */
public class PackageScanner {

    private final static Logger log = LoggerFactory.getLogger(PackageScanner.class);
    //扫描  scanPackages 下的文件的匹配符
    private String DEFAULT_RESOURCE_PATTERN = "**/*.class";

    private CommonProperties commonProperties;

    private SQLInjectionBean sqlInjectionBean;

    public PackageScanner(CommonProperties commonProperties, SQLInjectionBean sqlInjectionBean) {
        this.commonProperties = commonProperties;
        this.sqlInjectionBean = sqlInjectionBean;
    }

    /**
     * 结合spring的类扫描方式 扫描带有@Table注解所有的Entity 可以扫描工程下的class文件及jar中的class文件
     *
     * @return
     */
    public void scann() {
        // 获取所有的类
        try {
            Set<String> entityClassSet = findPackageClass(this.commonProperties.resolveEntityPackage());
            Set<String> mapperClassSet = findPackageClass(this.commonProperties.resolveMapperPackage());
            for (String entityClass : entityClassSet) {
                Class<?> clz = Class.forName(entityClass);
                Table table = clz.getAnnotation(Table.class);
                // 扫描到注解
                if (table != null) {
                    EntityMapping entityMapping = new EntityMapping(table.name());
                    entityMapping.setClassName(clz.getName());
                    sqlInjectionBean.addEntityMapping(entityMapping);
                    Field[] fields = clz.getDeclaredFields();
                    for (Field field : fields) {
                        Column column = field.getAnnotation(Column.class);
                        if (column == null) {
                            continue;
                        }
                        PropertyMapping propertyMapping = new PropertyMapping();
                        propertyMapping.setColumn(column.name());
                        propertyMapping.setProperty(field.getName());
                        propertyMapping.setType(field.getType());
                        entityMapping.addPropertyMapping(propertyMapping);
                        FieldConfig fc = field.getAnnotation(FieldConfig.class);
                        if (null != fc && StringUtil.isNotBlank(fc.orderColumn())) {
                            OrderCondition oc = new OrderCondition();
                            oc.setOrderColumn(fc.orderColumn());
                            oc.setOrderType(fc.orderType());
                            entityMapping.addOrderCondition(oc);
                        }
                    }
                }
            }
            for (String mapperClass : mapperClassSet) {
                Class<?> clz = Class.forName(mapperClass);
                Class<?> entityClass = getEntityClass(clz);
                this.sqlInjectionBean.addMapper(entityClass.getName(), clz.getName());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

    private Set<String> findPackageClass(Resource[] resources) throws IOException {
        if (resources == null) {
            return Collections.EMPTY_SET;
        }
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourcePatternResolver);
        Set<String> clazzSet = new HashSet<String>();
        for (Resource resource : resources) {
            //检查resource，这里的resource都是class
            String clazz = loadClassName(metadataReaderFactory, resource);
            clazzSet.add(clazz);
        }
        return clazzSet;
    }

    private String loadClassName(MetadataReaderFactory metadataReaderFactory, Resource resource) throws IOException {
        try {
            if (resource.isReadable()) {
                MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
                if (metadataReader != null) {
                    return metadataReader.getClassMetadata().getClassName();
                }
            }
        } catch (Exception e) {
            log.error("根据resource获取类名称失败", e);
        }
        return null;
    }

    private Class<?> getEntityClass(Class<?> clz) {
        ParameterizedType parameterizedType = (ParameterizedType) clz.getGenericInterfaces()[0];
        Class<?> entityClass = (Class<?>) (parameterizedType.getActualTypeArguments()[0]);
        return entityClass;
    }

}