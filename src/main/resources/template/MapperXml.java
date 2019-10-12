package template;

import com.quesa.mybootproject.common.util.FileUtil;
import com.quesa.mybootproject.common.util.StringUtil;

import java.io.IOException;

/**
 *
 */
public class MapperXml extends Constants {
    public MapperXml(Boolean flag) {
        Constants.cover = flag;//是否允许强制覆盖已存在文件
    }

    /**
     * 构建实体层xml文件
     *
     * @throws IOException
     */
    public void createMapperXml() throws IOException {
        String outClassUrl = projectOutPath + mapperXmlOutPath.replace(".", "/") + "/";//生成实体类路径
        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        sb.append("\r\n");
        sb.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" ");
        sb.append("\r\n");
        sb.append("    \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">");
        sb.append("\r\n");
        sb.append("<mapper namespace=\"" + mapperPackageOutPath + "." + mapperName + "\">");
        sb.append("\r\n");
        sb.append("\r\n");
        // 下面开始写SqlMapper中的方法
        //通过查询结果列
        all_column(sb);
        //添加通用查询
        all_where(sb);
        //增加
        //insert(sb);
        //批量增加
        //insertList(sb);
        //根据ID删除
        //deleteById(sb);
        //根据条件删除
        //delete(sb);
        //更具ID批量删除
        //deleteByIds(sb);
        //修改
        //update(sb);
        //根据Ids批量修改
        //updateByIds(sb);
        //根据条件修改
        //updateByMap(sb);
        //根据id查询
        //getById(sb);
        //根据ids查询集合
        //getByIds(sb);
        //根据实体对象查询
        //get(sb);
        //根据条件查询
        //getList(sb);
        //根据条件获取集合总数
        //getCount(sb);
        //实体类与数据库字段映射
        Column_resultMap(sb);
        sb.append("</mapper>");
        //判断生成文件是否存在，不存在则创建
        if (!FileUtil.isExists(outClassUrl + xmlName + ".xml") || Constants.cover) {
            FileUtil.writeFile((outClassUrl + xmlName + ".xml"), sb.toString());//创建一个指定的空文件
        } else {
            System.out.print(xmlName + ".xml文件已存在，不允许直接覆盖！\n");
            return;
        }
    }

    /**
     * 实体类与数据库字段映射
     *
     * @param sb
     */
    private void Column_resultMap(StringBuffer sb) {
        sb.append("\t<!-- 实体类与数据库字段映射-->");
        sb.append("\r\n");
        sb.append("\t<resultMap type=\"" + entityPackageOutPath + "." + entityName + "\" id=\"" + entityName + "ResultMap\">");
        sb.append("\r\n");
        sb.append("\t<!-- 用id属性来映射主键字段 -->");
        sb.append("<id property=\"id\" column=\"" + columns.get(0) + "\"/>");
        sb.append("\t<!-- 用result属性来映射非主键字段 -->");
        sb.append("\r\n");
        for (int i = 1; i < columns.size(); i++) {
            sb.append("\t<result property=\"" + StringUtil.toCamelCase(columns.get(i)) + "\" column=\"" + columns.get(i) + "\"/>");
            sb.append("\r\n");
        }
        sb.append("\t</resultMap>");
        sb.append("\r\n");
        sb.append("\r\n");
    }

    /**
     * 通用查询结果列
     *
     * @param sb
     */
    private void all_column(StringBuffer sb) {
        // 通用结果列
        sb.append("\t<!--" + tableComment + "-通用查询结果列-->");
        sb.append("\r\n");
        sb.append("\t<sql id=\"all_column\">");
        sb.append("\r\n");
        for (int i = 0; i < columns.size(); i++) {
            sb.append("\to." + columns.get(i));
            if (i != columns.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("\r\n");
        sb.append("\t</sql>");
        sb.append("\r\n");
        sb.append("\r\n");
    }

    /**
     * 修改-根据 ID 修改
     *
     * @param sb
     */
    private void all_where(StringBuffer sb) {
        // 通用查询列
        sb.append("\t<!--" + tableComment + "-通用查询结果列-->");
        sb.append("\r\n");
        sb.append("\t<sql id=\"all_where\">");
        sb.append("\r\n");
        String tempField = null;
        for (int i = 0; i < columns.size(); i++) {
            tempField = StringUtil.toCamelCase(columns.get(i));
            if ("String".equals(processType(types.get(i)))) {
                sb.append("\t\t\t<if test=\"" + tempField + " != null and " + tempField + " != '' \">");
            } else {
                sb.append("\t\t\t<if test=\"" + tempField + " != null \">");
            }
            sb.append("\r\n");
            sb.append("\t\t\t\t and o." + columns.get(i) + " = #{" + tempField + "}");
            sb.append("\r\n");
            sb.append("\t\t\t</if>");
            sb.append("\r\n");
        }
        sb.append("\t</sql>");
        sb.append("\r\n");
    }

    /**
     * 增加
     *
     * @param sb
     */
    private void insert(StringBuffer sb) {
        sb.append("\t<!-- 插入一条记录,添加 （匹配有值的字段）-->");
        sb.append("\r\n");
        sb.append("\t<insert id=\"" + insert + "\">");
        sb.append("\r\n");
        sb.append("\t\t INSERT INTO " + tableName);
        sb.append("\r\n");
        sb.append("\t\t <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\" >");
        sb.append("\r\n");
        String tempField = null;
        for (int i = 0; i < columns.size(); i++) {
            tempField = StringUtil.toCamelCase(columns.get(i));
            sb.append("\t\t\t<if test=\"" + tempField + " != null\">\r\n");
            sb.append("\t\t\t\t " + columns.get(i) + ",\r\n");
            sb.append("\t\t\t</if>");
            sb.append("\r\n");
        }
        sb.append("\r\n");
        sb.append("\t\t </trim>");
        sb.append("\r\n");

        sb.append("\t\t <trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\" >");
        sb.append("\r\n");

        tempField = null;
        for (int i = 0; i < columns.size(); i++) {
            tempField = StringUtil.toCamelCase(columns.get(i));
            sb.append("\t\t\t<if test=\"" + tempField + "!=null\">");
            sb.append("\r\n");
            sb.append("\t\t\t\t #{" + tempField + "},");
            sb.append("\r\n");
            sb.append("\t\t\t</if>");
            sb.append("\r\n");
        }
        sb.append("\t\t </trim>");
        sb.append("\r\n");
        sb.append("\t</insert>");
        sb.append("\r\n");
        sb.append("\r\n");
    }

    /**
     * 批量增加
     *
     * @param sb
     */
    private void insertList(StringBuffer sb) {
        sb.append("\t<!-- 批量插入多条记录,添加 （匹配有值的字段）-->");
        sb.append("\r\n");
        sb.append("\t<insert id=\"" + insertList + "\">");
        sb.append("\r\n");
        sb.append("\t\t INSERT INTO " + tableName + "(");
        sb.append("\r\n");
        sb.append("\t\t<include refid=\"all_column\"/>");
        sb.append("\r\n");
        sb.append("\t\t)VALUES");

        sb.append("\r\n");
        sb.append("\t\t<foreach collection=\"entityList\" item=\"entity\" index=\"index\" separator=\",\">");
        sb.append("\r\n");
        String tempField = null;
        sb.append("\t\t\t\t(");
        for (int i = 0; i < columns.size(); i++) {
            tempField = StringUtil.toCamelCase(columns.get(i));
            sb.append("\r\n");
            if (i == columns.size() - 1) {
                sb.append("\t\t\t\t #{entity." + tempField + "}");
            } else {
                sb.append("\t\t\t\t #{entity." + tempField + "},");
            }
        }
        sb.append("\r\n");
        sb.append("\t\t\t\t)\t\n");
        sb.append("\t\t\t</foreach>\t\n");
        sb.append("\t</insert>");
        sb.append("\r\n");
        sb.append("\r\n");
    }

    /**
     * 删除
     *
     * @param sb
     */
    private void deleteById(StringBuffer sb) {
        sb.append("\t<!--删除：根据主键ID删除-->");
        sb.append("\r\n");
        sb.append("\t<delete id=\"" + deleteById + "\">");
        sb.append("\r\n");
        sb.append("\t\t DELETE FROM " + tableName);
        sb.append("\r\n");
        sb.append("\t\t WHERE " + columns.get(0) + " = #{" + StringUtil.toCamelCase(columns.get(0)) + "}");
        sb.append("\r\n");
        sb.append("\t</delete>");
        sb.append("\r\n");
        sb.append("\r\n");
    }

    /**
     * 删除
     *
     * @param sb
     */
    private void delete(StringBuffer sb) {
        sb.append("\t<!--删除：根据 columnMap 条件，删除-->");
        sb.append("\r\n");
        sb.append("\t<delete id=\"" + delete + "\">");
        sb.append("\r\n");
        sb.append("\t\t DELETE FROM " + tableName + " ");
        sb.append("\r\n");
        sb.append("\t\tWHERE\t\n ");
        sb.append("1=1\r\n");
        String tempField = null;
        for (int i = 0; i < columns.size(); i++) {
            tempField = StringUtil.toCamelCase(columns.get(i));
            if ("String".equals(processType(types.get(i)))) {
                sb.append("\t\t\t<if test=\"" + tempField + " != null and " + tempField + " != '' \">");
            } else {
                sb.append("\t\t\t<if test=\"" + tempField + " != null \">");
            }
            sb.append("\r\n");
            sb.append("\t\t\t\t and " + columns.get(i) + " = #{" + tempField + "}");
            sb.append("\r\n");
            sb.append("\t\t\t</if>");
            sb.append("\r\n");
        }
        sb.append("\r\n");
        sb.append("\t</delete>");
        sb.append("\r\n");
        sb.append("\r\n");
    }

    /**
     * 更具Id批量删除
     *
     * @param sb
     */
    private void deleteByIds(StringBuffer sb) {
        sb.append("\t<!--删除：根据ID 批量删除-->");
        sb.append("\r\n");
        sb.append("\t<delete id=\"" + deleteByIds + "\">");
        sb.append("\r\n");
        sb.append("\t\t DELETE FROM " + tableName + " ");
        sb.append("\r\n");
        sb.append("\t\t WHERE " + columns.get(0) + " IN ");
        sb.append(" <foreach item=\"id\" index=\"index\" collection=\"idList\" open=\"(\"\n" +
                "separator=\",\" close=\")\">'${id}'\n" + "</foreach>\r\n");
        sb.append("\t</delete>");
        sb.append("\r\n");
        sb.append("\r\n");
    }

    /**
     * 修改-根据 ID 修改
     *
     * @param sb
     */
    private void update(StringBuffer sb) {
        sb.append("\t<!-- 根据 ID 修改-->");
        sb.append("\r\n");
        sb.append("\t<update id=\"" + update + "\">");
        sb.append("\r\n");
        sb.append("\t\t UPDATE " + tableName + " o");
        sb.append("\r\n");
        sb.append("\t\t <trim suffixOverrides=\",\" >");
        sb.append("\r\n");
        sb.append(" \t\t <set> ");
        sb.append("\r\n");

        String tempField = null;
        for (int i = 1; i < columns.size(); i++) {
            tempField = StringUtil.toCamelCase(columns.get(i));
            sb.append("\t\t\t<if test=\"" + tempField + " != null\">");
            sb.append("\r\n");
            sb.append("\t\t\t\t o." + columns.get(i) + " = #{" + tempField + "},");
            sb.append("\r\n");
            sb.append("\t\t\t</if>");
            sb.append("\r\n");
        }
        sb.append("\r\n");
        sb.append(" \t\t </set>");
        sb.append("\r\n");
        sb.append("\t\t </trim>");
        sb.append("\r\n");
        sb.append("\t\t WHERE o." + columns.get(0) + " = #{" + StringUtil.toCamelCase(columns.get(0)) + "}");
        sb.append("\r\n");
        sb.append("\t</update>");
        sb.append("\r\n");
        sb.append("\r\n");
    }

    /**
     * 修改-根据 Ids批量修改
     *
     * @param sb
     */
    private void updateByIds(StringBuffer sb) {
        sb.append("\t<!-- 根据 ID 修改-->");
        sb.append("\r\n");
        sb.append("\t<update id=\"" + updateByIds + "\">");
        sb.append("\r\n");
        sb.append("\t\t UPDATE " + tableName + " o");
        sb.append("\r\n");
        sb.append("\t\t <trim suffixOverrides=\",\" >");
        sb.append("\r\n");
        sb.append(" \t\t <set> ");
        sb.append("\r\n");

        String tempField = null;
        for (int i = 1; i < columns.size(); i++) {
            tempField = StringUtil.toCamelCase(columns.get(i));
            sb.append("\t\t\t<if test=\"et." + tempField + " != null\">");
            sb.append("\r\n");
            sb.append("\t\t\t\t o." + columns.get(i) + " = #{et." + tempField + "},");
            sb.append("\r\n");
            sb.append("\t\t\t</if>");
            sb.append("\r\n");
        }
        sb.append("\r\n");
        sb.append(" \t\t </set>");
        sb.append("\r\n");
        sb.append("\t\t </trim>");
        sb.append("\r\n");
        sb.append("\t\t WHERE o." + columns.get(0) + " IN ");
        sb.append(" <foreach item=\"id\" index=\"index\" collection=\"idList\" open=\"(\"\n" +
                "separator=\",\" close=\")\">'${id}'\n" + "</foreach>\r\n");
        sb.append("\r\n");
        sb.append("\t</update>");
        sb.append("\r\n");
        sb.append("\r\n");
    }

    /**
     * 修改-根据 columnMap 条件，更新记录
     *
     * @param sb
     */
    private void updateByMap(StringBuffer sb) {
        sb.append("\t<!-- 修改:根据 columnMap 条件，更新记录-->");
        sb.append("\r\n");
        sb.append("\t<update id=\"" + updateByMap + "\">");
        sb.append("\r\n");
        sb.append("\t\t UPDATE " + tableName + " o");
        sb.append("\r\n");
        sb.append("\t\t <trim suffixOverrides=\",\" >");
        sb.append("\r\n");
        sb.append(" \t\t <set> ");
        sb.append("\r\n");

        String tempField = null;
        for (int i = 1; i < columns.size(); i++) {
            tempField = StringUtil.toCamelCase(columns.get(i));
            sb.append("\t\t\t<if test=\"et." + tempField + " != null\">");
            sb.append("\r\n");
            sb.append("\t\t\t\t o." + columns.get(i) + " = #{et." + tempField + "},");
            sb.append("\r\n");
            sb.append("\t\t\t</if>");
            sb.append("\r\n");
        }
        sb.append("\r\n");
        sb.append(" \t\t </set>");
        sb.append("\r\n");
        sb.append("\t\t </trim>");
        sb.append("\r\n");
        sb.append("\t\t WHERE ");
        sb.append("\r\n");
        sb.append("1=1\r\n");
        tempField = null;
        for (int i = 0; i < columns.size(); i++) {
            tempField = StringUtil.toCamelCase(columns.get(i));
            if ("String".equals(processType(types.get(i)))) {
                sb.append("\t\t\t<if test=\"cm." + tempField + " != null and cm." + tempField + " != '' \">");
            } else {
                sb.append("\t\t\t<if test=\"cm." + tempField + " != null \">");
            }

            sb.append("\r\n");
            sb.append("\t\t\t\t and o." + columns.get(i) + " = #{cm." + tempField + "}");
            sb.append("\r\n");
            sb.append("\t\t\t</if>");
            sb.append("\r\n");
        }
        sb.append("\t</update>");
        sb.append("\r\n");
        sb.append("\r\n");
    }

    /**
     * 根据ID查找对象
     *
     * @param sb
     */
    private void getById(StringBuffer sb) {
        sb.append("\t<!-- 查询（根据主键ID查询） -->");
        sb.append("\r\n");
        sb.append("\t<select id=\"" + getById + "\" resultMap =\"" + entityName + "ResultMap\">");
        sb.append("\r\n");
        sb.append("\t\t SELECT");
        sb.append("\r\n");
        sb.append("\t\t <include refid=\"all_column\" />");
        sb.append("\r\n");
        sb.append("\t\t FROM " + tableName + " o");
        sb.append("\r\n");
        sb.append("\t\t WHERE o." + columns.get(0) + " = #{" + StringUtil.toCamelCase(columns.get(0)) + "}");
        sb.append("\r\n");
        sb.append("\t</select>");
        sb.append("\r\n");
        sb.append("\r\n");
    }

    /**
     * 查询（根据ID 批量查询）
     *
     * @param sb
     */
    private void getByIds(StringBuffer sb) {
        sb.append("\t<!-- 查询（根据ID 批量查询） -->");
        sb.append("\r\n");
        sb.append("\t<select id=\"" + getByIds + "\" resultMap =\"" + entityName + "ResultMap\">");
        sb.append("\r\n");
        sb.append("\t\t SELECT");
        sb.append("\r\n");
        sb.append("\t\t <include refid=\"all_column\" />");
        sb.append("\r\n");
        sb.append("\t\t FROM " + tableName + " o");
        sb.append("\r\n");
        sb.append("\t\t WHERE o." + columns.get(0) + " IN ");
        sb.append(" <foreach item=\"id\" index=\"index\" collection=\"idList\" open=\"(\"\n" +
                "separator=\",\" close=\")\">'${id}'\n" + "</foreach>\r\n");
        sb.append("\t</select>");
        sb.append("\r\n");
        sb.append("\r\n");
    }

    /**
     * 根据条件查找单个对象
     *
     * @param sb
     */
    private void get(StringBuffer sb) {
        sb.append("\t<!-- 查询（根据 columnMap 条件）单个对象 -->");
        sb.append("\r\n");
        sb.append("\t<select id=\"" + get + "\" resultMap =\"" + entityName + "ResultMap\">");
        sb.append("\r\n");
        sb.append("\t\t SELECT");
        sb.append("\r\n");
        sb.append("\t\t <include refid=\"all_column\" />");
        sb.append("\r\n");
        sb.append("\t\t FROM " + tableName + " o");
        sb.append("\r\n");
        sb.append("\t\tWHERE\t\n ");
        sb.append("1=1\r\n");
        String tempField = null;
        for (int i = 0; i < columns.size(); i++) {
            tempField = StringUtil.toCamelCase(columns.get(i));
            if ("String".equals(processType(types.get(i)))) {
                sb.append("\t\t\t<if test=\"" + tempField + " != null and " + tempField + " != '' \">");
            } else {
                sb.append("\t\t\t<if test=\"" + tempField + " != null \">");
            }
            sb.append("\r\n");
            sb.append("\t\t\t\t and o." + columns.get(i) + " = #{" + tempField + "}");
            sb.append("\r\n");
            sb.append("\t\t\t</if>");
            sb.append("\r\n");
        }
        sb.append("LIMIT 1\r\n");
        sb.append("\t</select>");
        sb.append("\r\n");
        sb.append("\r\n");
    }

    /**
     * 查询（根据 columnMap 条件）集合
     */
    private void getList(StringBuffer sb) {
        sb.append("\t<!-- 查询（根据 columnMap 条件）集合 -->");
        sb.append("\r\n");
        sb.append("\t<select id=\"" + getList + "\" resultMap =\"" + entityName + "ResultMap\">");
        sb.append("\r\n");
        sb.append("\t\t SELECT");
        sb.append("\r\n");
        sb.append("\t\t <include refid=\"all_column\" />");
        sb.append("\r\n");
        sb.append("\t\t FROM " + tableName + " o");
        sb.append("\r\n");
        sb.append("\t\tWHERE\t\n ");
        sb.append("1=1\r\n");
        String tempField = null;
        for (int i = 0; i < columns.size(); i++) {
            tempField = StringUtil.toCamelCase(columns.get(i));
            if ("String".equals(processType(types.get(i)))) {
                sb.append("\t\t\t<if test=\"" + tempField + " != null and " + tempField + " != '' \">");
            } else {
                sb.append("\t\t\t<if test=\"" + tempField + " != null \">");
            }
            sb.append("\r\n");
            sb.append("\t\t\t\t and o." + columns.get(i) + " = #{" + tempField + "}");
            sb.append("\r\n");
            sb.append("\t\t\t</if>");
            sb.append("\r\n");
        }
        sb.append("\t\t\t<if test=\"sortFields!= null and sortFields!= ''\">");
        sb.append("\r\n");
        sb.append("ORDER BY ${sortFields} ${sortType}");
        sb.append("\t\t\t</if>");
        sb.append("\r\n");
        sb.append("\t</select>");
        sb.append("\r\n");
        sb.append("\r\n");
    }

    /**
     * 根据 columnMap 条件，查询总记录数
     */
    private void getCount(StringBuffer sb) {
        sb.append("\t<!-- 根据 columnMap 条件，查询总记录数 -->");
        sb.append("\r\n");
        sb.append("\t<select id=\"" + getCount + "\" resultType=\"java.lang.Integer\">");
        sb.append("\r\n");
        sb.append("\t\t SELECT");
        sb.append("\r\n");
        sb.append("\t\t count(*)");
        sb.append("\r\n");
        sb.append("\t\t FROM " + tableName + " o");
        sb.append("\r\n");
        sb.append("\t\tWHERE\t\n ");
        sb.append("1=1\r\n");
        String tempField = null;
        for (int i = 0; i < columns.size(); i++) {
            tempField = StringUtil.toCamelCase(columns.get(i));
            if ("String".equals(processType(types.get(i)))) {
                sb.append("\t\t\t<if test=\"" + tempField + " != null and " + tempField + " != '' \">");
            } else {
                sb.append("\t\t\t<if test=\"" + tempField + " != null \">");
            }
            sb.append("\r\n");
            sb.append("\t\t\t\t and o." + columns.get(i) + " = #{" + tempField + "}");
            sb.append("\r\n");
            sb.append("\t\t\t</if>");
            sb.append("\r\n");
        }
        sb.append("\r\n");
        sb.append("\t</select>");
        sb.append("\r\n");
        sb.append("\r\n");
    }
}
