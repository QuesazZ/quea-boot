package com.quesa.mybootproject.common.mybatis.ext;

import com.quesa.mybootproject.common.util.ListUtil;

import java.util.Date;

/**
 * SQL生成工具
 *
 */
public class SQLHeper {
    /**
     * 生成insert SQL
     *
     * @param mapping
     * @return
     */
    public static String generateInsertSql(EntityMapping mapping) {
        StringBuffer sql = new StringBuffer("<script>insert into " + mapping.getTable());
        sql.append("\r\n");
        sql.append("<trim prefix='(' suffix=')' suffixOverrides=',' >");
        sql.append("\r\n");
        for (int i = 0; i < mapping.getPropertyMappings().size(); i++) {
            PropertyMapping property = mapping.getPropertyMappings().get(i);
            sql.append("<if test='" + property.getProperty() + "!= null'>");
            sql.append("\r\n");
            sql.append(property.getColumn());
            if (i < mapping.getPropertyMappings().size() - 1) {
                sql.append(",");
            }
            sql.append("\r\n");
            sql.append("</if>");
        }
        sql.append("\r\n");
        sql.append("</trim>");
        sql.append("\r\n");
        sql.append("<trim prefix='values (' suffix=')' suffixOverrides=',' >");
        sql.append("\r\n");
        for (int i = 0; i < mapping.getPropertyMappings().size(); i++) {
            PropertyMapping property = mapping.getPropertyMappings().get(i);
            sql.append("<if test='" + property.getProperty() + "!= null'>");
            sql.append("\r\n");
            sql.append("#{");
            sql.append(property.getProperty());
            sql.append("}");
            if (i < mapping.getPropertyMappings().size() - 1) {
                sql.append(",");
            }
            sql.append("\r\n");
            sql.append("</if>");
        }
        sql.append("\r\n");
        sql.append("</trim></script>");
        return sql.toString();
    }

    /**
     * 生成insertList SQL
     *
     * @param mapping
     * @return
     */
    public static String generateInsertListSql(EntityMapping mapping) {
        StringBuffer sql = new StringBuffer("<script>insert into " + mapping.getTable());
        sql.append("(");
        for (int i = 0; i < mapping.getPropertyMappings().size(); i++) {
            PropertyMapping property = mapping.getPropertyMappings().get(i);
            sql.append(property.getColumn());
            if (i < mapping.getPropertyMappings().size() - 1) {
                sql.append(",");
            }
        }
        sql.append(") values");
        sql.append("\r\n");
        sql.append("<foreach collection='entityList' item='entity' separator=','>");
        sql.append("\r\n");
        sql.append("(");
        for (int i = 0; i < mapping.getPropertyMappings().size(); i++) {
            PropertyMapping property = mapping.getPropertyMappings().get(i);
            sql.append("\r\n");
            sql.append("#{entity.");
            sql.append(property.getProperty());
            sql.append("}");
            if (i < mapping.getPropertyMappings().size() - 1) {
                sql.append(",");
            }
        }
        sql.append("\r\n");
        sql.append(")");
        sql.append("\r\n");
        sql.append("</foreach></script>");
        return sql.toString();
    }

    /**
     * 生成update SQL
     *
     * @param mapping
     * @return
     */
    public static String generateUpdateSql(EntityMapping mapping) {
        StringBuffer sql = new StringBuffer("<script>update ");
        sql.append(mapping.getTable());
        sql.append("\r\n");
        sql.append("<trim suffixOverrides=',' >");
        sql.append("\r\n");
        sql.append("<set>");
        for (int i = 0; i < mapping.getPropertyMappings().size(); i++) {
            PropertyMapping property = mapping.getPropertyMappings().get(i);
            if ("id".equals(property.getProperty())) {
                continue;
            }
            sql.append("\r\n");
            sql.append("<if test='" + property.getProperty() + " != null'>");
            sql.append("\r\n");
            sql.append(property.getColumn());
            sql.append("=#{");
            sql.append(property.getProperty());
            sql.append("}");
            if (i < mapping.getPropertyMappings().size() - 1) {
                sql.append(",");
            }
            sql.append("\r\n");
            sql.append("</if>");
        }
        sql.append("\r\n");
        sql.append("</set>");
        sql.append("\r\n");
        sql.append("</trim>");
        sql.append("\r\n");
        sql.append("where id_=#{id}</script>");
        return sql.toString();
    }

    /**
     * 生成updateByMap SQL
     *
     * @param mapping
     * @return
     */
    public static String generateUpdateByMapSql(EntityMapping mapping) {
        StringBuffer sql = new StringBuffer("<script>update ");
        sql.append(mapping.getTable());
        sql.append("\r\n");
        sql.append("<trim suffixOverrides=',' >");
        sql.append("\r\n");
        sql.append("<set>");
        for (int i = 0; i < mapping.getPropertyMappings().size(); i++) {
            PropertyMapping property = mapping.getPropertyMappings().get(i);
            if ("id".equals(property.getProperty())) {
                continue;
            }
            sql.append("\r\n");
            sql.append("<if test='entity." + property.getProperty() + " != null'>");
            sql.append("\r\n");
            sql.append(property.getColumn());
            sql.append("=#{entity.");
            sql.append(property.getProperty());
            sql.append("}");
            if (i < mapping.getPropertyMappings().size() - 1) {
                sql.append(",");
            }
            sql.append("\r\n");
            sql.append("</if>");
        }
        sql.append("\r\n");
        sql.append("</set>");
        sql.append("\r\n");
        sql.append("</trim>");
        sql.append("\r\n");
        sql.append("where 1=1 ");
        for (int i = 0; i < mapping.getPropertyMappings().size(); i++) {
            PropertyMapping property = mapping.getPropertyMappings().get(i);
            sql.append("\r\n");
            if (property.getType().equals(String.class)) {
                sql.append("<if test='condition." + property.getProperty() + " != null and condition." + property.getProperty() + " != \"\"'>");
            } else {
                sql.append("<if test='condition." + property.getProperty() + " != null'>");
            }

            sql.append("\r\n");
            sql.append(" and ");
            sql.append(property.getColumn());
            sql.append("=#{condition.");
            sql.append(property.getProperty());
            sql.append("}");
            sql.append("\r\n");
            sql.append("</if>");
        }
        sql.append("\r\n");
        sql.append("</script>");
        return sql.toString();
    }

    /**
     * 生成getById SQL
     *
     * @param mapping
     * @return
     */
    public static String generateGetByIdSql(EntityMapping mapping) {
        StringBuffer sql = new StringBuffer("select ");
        for (int i = 0; i < mapping.getPropertyMappings().size(); i++) {
            PropertyMapping property = mapping.getPropertyMappings().get(i);
            sql.append(property.getColumn());
            if (i < mapping.getPropertyMappings().size() - 1) {
                sql.append(",");
            }
        }
        sql.append(" from ");
        sql.append(mapping.getTable());
        sql.append(" where id_=#{id}");
        return sql.toString();
    }

    /**
     * 生成get SQL
     *
     * @param mapping
     * @return
     */
    public static String generateGetSql(EntityMapping mapping) {
        StringBuffer sql = new StringBuffer("<script>select ");
        for (int i = 0; i < mapping.getPropertyMappings().size(); i++) {
            PropertyMapping property = mapping.getPropertyMappings().get(i);
            sql.append(property.getColumn());
            if (i < mapping.getPropertyMappings().size() - 1) {
                sql.append(",");
            }
        }
        sql.append(" from ");
        sql.append(mapping.getTable());
        sql.append(" where 1=1 ");
        for (int i = 0; i < mapping.getPropertyMappings().size(); i++) {
            PropertyMapping property = mapping.getPropertyMappings().get(i);
            sql.append("\r\n");
            if (property.getType().equals(String.class)) {
                sql.append("<if test='" + property.getProperty() + " != null and " + property.getProperty() + " != \"\"'>");
            } else {
                sql.append("<if test='" + property.getProperty() + " != null'>");
            }
            sql.append("\r\n");
            sql.append(" and ");
            sql.append(property.getColumn());
            sql.append("=#{");
            sql.append(property.getProperty());
            sql.append("}");
            sql.append("\r\n");
            sql.append("</if>");
        }
        sql.append("\r\n");
        if (ListUtil.isNotEmpty(mapping.getOrderConditions())) {
            sql.append("order by ");
            for (OrderCondition oc : mapping.getOrderConditions()) {
                sql.append(oc.getOrderColumn()).append(" ").append(oc.getOrderType()).append(" ,");
            }
            if (sql.lastIndexOf(",") != -1) {
                sql = sql.deleteCharAt(sql.length() - 1);
            }
            sql.append("\r\n");
        }
        sql.append(" limit 1 </script>");
        return sql.toString();
    }

    /**
     * 生成getList SQL
     *
     * @param mapping
     * @return
     */
    public static String generateGetListSql(EntityMapping mapping) {
        StringBuffer sql = new StringBuffer("<script>select ");
        for (int i = 0; i < mapping.getPropertyMappings().size(); i++) {
            PropertyMapping property = mapping.getPropertyMappings().get(i);
            sql.append(property.getColumn());
            if (i < mapping.getPropertyMappings().size() - 1) {
                sql.append(",");
            }
        }
        sql.append(" from ");
        sql.append(mapping.getTable());
        sql.append(" where 1=1 ");
        for (int i = 0; i < mapping.getPropertyMappings().size(); i++) {
            PropertyMapping property = mapping.getPropertyMappings().get(i);
            sql.append("\r\n");
            if (property.getType().equals(String.class)) {
                sql.append("<if test='" + property.getProperty() + " != null and " + property.getProperty() + " != \"\"'>");
            } else {
                sql.append("<if test='" + property.getProperty() + " != null'>");
            }
            sql.append("\r\n");
            sql.append(" and ");
            sql.append(property.getColumn());
            sql.append("=#{");
            sql.append(property.getProperty());
            sql.append("}");
            sql.append("\r\n");
            sql.append("</if>");
            //扩展属性
            if (property.getType().equals(Date.class)) {
                //开始时间
                sql.append("\r\n");
                sql.append("<if test='ov." + property.getProperty() + "Begin != null'>");
                sql.append("\r\n");
                sql.append(" and date(");
                sql.append(property.getColumn());
                sql.append(")>=date(#{ov.");
                sql.append(property.getProperty());
                sql.append("Begin,jdbcType=TIMESTAMP})");
                sql.append("\r\n");
                sql.append("</if>");
                sql.append("\r\n");
                //结束时间
                sql.append("<if test='ov." + property.getProperty() + "End != null'>");
                sql.append("\r\n");
                sql.append(" and date(");
                sql.append(property.getColumn());
                sql.append(")&lt;=date(#{ov.");
                sql.append(property.getProperty());
                sql.append("End,jdbcType=TIMESTAMP})");
                sql.append("\r\n");
                sql.append("</if>");
            }
        }
        sql.append("\r\n");
        sql.append("<if test='sortFields != null'>");
        sql.append("\r\n");
        sql.append("order by ${sortFields} ${sortType} ");
        sql.append("\r\n");
        sql.append("</if>");
        sql.append("</script>");
        return sql.toString();
    }

    public static String generateGetListByIdsSql(EntityMapping mapping) {
        StringBuffer sql = new StringBuffer("<script>select ");
        for (int i = 0; i < mapping.getPropertyMappings().size(); i++) {
            PropertyMapping property = mapping.getPropertyMappings().get(i);
            sql.append(property.getColumn());
            if (i < mapping.getPropertyMappings().size() - 1) {
                sql.append(",");
            }
        }
        sql.append(" from ");
        sql.append(mapping.getTable());
        sql.append(" where id_ in ");
        sql.append("\r\n");
        sql.append("<if test='idList != null'>");
        sql.append("\r\n");
        sql.append("<if test='idList.size()>0'>");
        sql.append("\r\n");
        sql.append("<foreach item='id' collection='idList' open='(' separator=',' close=')'>");
        sql.append("\r\n");
        sql.append("#{id}");
        sql.append("\r\n");
        sql.append("</foreach>");
        sql.append("\r\n");
        sql.append("</if>");
        sql.append("\r\n");
        sql.append("<if test='idList.size()==0'>");
        sql.append("\r\n");
        sql.append("('')");
        sql.append("</if>");
        sql.append("\r\n");
        sql.append("</if>");
        sql.append("</script>");
        return sql.toString();
    }

    /**
     * 生成getCount SQL
     *
     * @param mapping
     * @return
     */
    public static String generateGetCountSql(EntityMapping mapping) {
        StringBuffer sql = new StringBuffer("<script>select ");
        sql.append("count(*)");
        sql.append(" from ");
        sql.append(mapping.getTable());
        sql.append(" where 1=1 ");
        for (int i = 0; i < mapping.getPropertyMappings().size(); i++) {
            PropertyMapping property = mapping.getPropertyMappings().get(i);
            sql.append("\r\n");
            if (property.getType().equals(String.class)) {
                sql.append("<if test='" + property.getProperty() + " != null and " + property.getProperty() + " != \"\"'>");
            } else {
                sql.append("<if test='" + property.getProperty() + " != null'>");
            }
            sql.append("\r\n");
            sql.append(" and ");
            sql.append(property.getColumn());
            sql.append("=#{");
            sql.append(property.getProperty());
            sql.append("}");
            sql.append("\r\n");
            sql.append("</if>");
            //扩展属性
            if (property.getType().equals(Date.class)) {
                //开始时间
                sql.append("\r\n");
                sql.append("<if test='ov." + property.getProperty() + "Begin != null'>");
                sql.append("\r\n");
                sql.append(" and date(");
                sql.append(property.getColumn());
                sql.append(")>=date(#{ov.");
                sql.append(property.getProperty());
                sql.append("Begin,jdbcType=TIMESTAMP})");
                sql.append("\r\n");
                sql.append("</if>");
                sql.append("\r\n");
                //结束时间
                sql.append("<if test='ov." + property.getProperty() + "End != null'>");
                sql.append("\r\n");
                sql.append(" and date(");
                sql.append(property.getColumn());
                sql.append(")&lt;=date(#{ov.");
                sql.append(property.getProperty());
                sql.append("End,jdbcType=TIMESTAMP})");
                sql.append("\r\n");
                sql.append("</if>");
            }
        }
        sql.append("\r\n");
        sql.append("</script>");
        return sql.toString();
    }

    /**
     * 生成getIdList SQL
     *
     * @param mapping
     * @return
     */
    public static String generateGetIdListSql(EntityMapping mapping) {
        StringBuffer sql = new StringBuffer("<script>select ");
        sql.append("id_");
        sql.append(" from ");
        sql.append(mapping.getTable());
        sql.append(" where 1=1 ");
        for (int i = 0; i < mapping.getPropertyMappings().size(); i++) {
            PropertyMapping property = mapping.getPropertyMappings().get(i);
            sql.append("\r\n");
            if (property.getType().equals(String.class)) {
                sql.append("<if test='" + property.getProperty() + " != null and " + property.getProperty() + " != \"\"'>");
            } else {
                sql.append("<if test='" + property.getProperty() + " != null'>");
            }
            sql.append("\r\n");
            sql.append(" and ");
            sql.append(property.getColumn());
            sql.append("=#{");
            sql.append(property.getProperty());
            sql.append("}");
            sql.append("\r\n");
            sql.append("</if>");
            //扩展属性
            if (property.getType().equals(Date.class)) {
                //开始时间
                sql.append("\r\n");
                sql.append("<if test='ov." + property.getProperty() + "Begin != null'>");
                sql.append("\r\n");
                sql.append(" and date(");
                sql.append(property.getColumn());
                sql.append(")>=date(#{ov.");
                sql.append(property.getProperty());
                sql.append("Begin,jdbcType=TIMESTAMP})");
                sql.append("\r\n");
                sql.append("</if>");
                sql.append("\r\n");
                //结束时间
                sql.append("<if test='ov." + property.getProperty() + "End != null'>");
                sql.append("\r\n");
                sql.append(" and date(");
                sql.append(property.getColumn());
                sql.append(")&lt;=date(#{ov.");
                sql.append(property.getProperty());
                sql.append("End,jdbcType=TIMESTAMP})");
                sql.append("\r\n");
                sql.append("</if>");
            }
        }
        sql.append("\r\n");
        sql.append("</script>");
        return sql.toString();
    }

    /**
     * 生成 deleteById SQL
     *
     * @param mapping
     * @return
     */
    public static String generateDeleteByIdSql(EntityMapping mapping) {
        StringBuffer sql = new StringBuffer("delete from ");
        sql.append(mapping.getTable());
        sql.append(" where id_=#{id}");
        return sql.toString();
    }

    /**
     * 生成delete SQL
     *
     * @param mapping
     * @return
     */
    public static String generateDeleteSql(EntityMapping mapping) {
        StringBuffer sql = new StringBuffer("<script>delete from ");
        sql.append(mapping.getTable());
        sql.append(" where 1=1 ");
        for (int i = 0; i < mapping.getPropertyMappings().size(); i++) {
            PropertyMapping property = mapping.getPropertyMappings().get(i);
            sql.append("\r\n");
            if (property.getType().equals(String.class)) {
                sql.append("<if test='" + property.getProperty() + " != null and " + property.getProperty() + " != \"\"'>");
            } else {
                sql.append("<if test='" + property.getProperty() + " != null'>");
            }
            sql.append("\r\n");
            sql.append(" and ");
            sql.append(property.getColumn());
            sql.append("=#{");
            sql.append(property.getProperty());
            sql.append("}");
            sql.append("\r\n");
            sql.append("</if>");
        }
        sql.append("\r\n");
        sql.append("</script>");
        return sql.toString();
    }
}
