package template;

import java.io.IOException;
import java.util.List;

/**
 *
 */
public class Constants {
    //sql类型与java类型映射 start
    private final static String type_char = "char";
    private final static String type_date = "date";
    private final static String type_timestamp = "timestamp";
    private final static String type_int = "int";
    private final static String type_double = "double";
    private final static String type_bigint = "bigint";
    private final static String type_text = "text";
    private final static String type_bit = "bit";
    private final static String type_decimal = "decimal";
    private final static String type_blob = "blob";
    //sql类型与java类型映射 end

    //数据操作层方法   start
    public final static String insert = "insert";//新增对象
    public final static String insertList = "insertList";//批量新增对象
    public final static String deleteById = "deleteById";//真实更具ID删除数据
    public final static String delete = "delete";//根据 columnMap 条件，删除记录
    public final static String deleteByIds = "deleteByIds";//删除（根据ID 批量删除）
    public final static String update = "update";//更具实体对象修改
    public final static String updateByIds = "updateByIds";//更具ids批量修改
    public final static String updateByMap = "updateByMap";//根据 whereEntity 条件，更新记录
    public final static String getById = "getById";//更具ID获取
    public final static String getByIds = "getByIds";//查询（根据ID 批量查询）
    public final static String get = "get";//查询（根据 columnMap 条件）查询一条记录
    public final static String getList = "getList";//查询（根据 columnMap 条件）
    public final static String getCount = "getCount";//根据 columnMap 条件，查询总记录数
    public final static String getListPage = "getListPage";//更具实体对象获取集合列表，支持分页
    public final static String deleteLogic = "deleteLogic";//删除数据（逻辑删除，更新is_delete_字段为1,在表包含字段is_delete_时，可以调用此方法，将数据隐藏）
    public final static String deleteLogicById = "deleteLogicById";//删除数据（逻辑删除，更新is_delete_字段为1,在表包含字段is_delete_时，可以调用此方法，将数据隐藏）
    public final static String deleteLogicByIds = "deleteLogicByIds";//删除数据（逻辑删除，更新is_delete_字段为1,在表包含字段is_delete_时，可以调用此方法，将数据隐藏）

    //数据操作层方法   end
    public static String projectOutPath = "";//项目路径
    public static String entityPackageOutPath = "";//指定实体生成所在包的路径
    public static String mapperPackageOutPath = "";//实体数据接口路径
    public static String mapperXmlOutPath = "";//实体数据接口路径
    public static String servicePackageOutPath = "";
    public static String controllerPackageOutPath = "";//控制层路径
    public static String modulePage = "";//模块包名
    public static String tableName = "";//表名
    public static String tableComment = "";//表名注释
    public static String entityName = "";//实体名
    public static String mapperName = "";//实体数据接口名 dao Mapper
    public static String xmlName = "";//实体名
    public static String serviceName = "";//业务逻辑层类名
    public static String controllerName = "";//控制层类名
    public static String authorName = "quesa";//作者名字
    public static List<String> columns = null;// 列名数组
    public static List<String> types = null;//列名类型数组
    public static List<String> comments = null;//列名描述

    //支持覆盖已存在文件
    public static Boolean cover = false;//默认不允许直接覆盖
    //前端页面展示JS
    public static String jsPackageOutPath = "";//实体数据接口路径
    public static String jsName = "";//

    /**
     * 功能：获得列的数据类型
     *
     * @param type
     * @return
     */
    public static String processType(String type) {
        if (type.indexOf(type_char) > -1) {
            return "String";
        } else if (type.indexOf(type_bigint) > -1) {
            return "Long";
        } else if (type.indexOf(type_int) > -1) {
            return "Integer";
        } else if (type.indexOf(type_double) > -1) {
            return "Double";
        } else if (type.indexOf(type_date) > -1) {
            return "Date";
        } else if (type.indexOf(type_text) > -1) {
            return "String";
        } else if (type.indexOf(type_timestamp) > -1) {
            return "Date";
        } else if (type.indexOf(type_bit) > -1) {
            return "Boolean";
        } else if (type.indexOf(type_decimal) > -1) {
            return "BigDecimal";
        } else if (type.indexOf(type_blob) > -1) {
            return "byte[]";
        }
        return null;
    }

    /**
     * 构建类上面的注释
     *
     * @param sb
     * @param text
     * @return
     * @throws IOException
     */
    public static void createClassComment(StringBuffer sb, String text) throws IOException {
        sb.append("\r\n");
        sb.append("/**");
        sb.append("\r\n");
        sb.append(" * ");
        sb.append("\r\n");
        sb.append(" * " + text);
        sb.append("\r\n");
        sb.append(" * ");
        sb.append("\r\n");
        sb.append(" **/");
    }


    /**
     * 构建方法上面的注释
     *
     * @param sb
     * @param text
     * @return
     * @throws IOException
     */
    public static void createMethodComment(StringBuffer sb, String text) throws IOException {
        sb.append("\r\n");
        sb.append("\t/**");
        sb.append("\r\n");
        sb.append("\t * ");
        sb.append("\r\n");
        sb.append("\t * " + text);
        sb.append("\r\n");
        sb.append("\t * ");
        sb.append("\r\n");
        sb.append("\t **/");
    }
}
