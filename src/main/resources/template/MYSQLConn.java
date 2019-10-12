package template;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 */
public class MYSQLConn extends Constants {
    /**
     * 连接驱动器
     */
    private final static String driverName = "com.mysql.jdbc.Driver";
    /**
     * 数据库用户名
     */
    private final static String user = "root";
    /**
     * 数据库密码
     */
    private final static String password = "123456";
    /**
     * 数据库连接地址
     */
    private final static String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    /**
     * 数据库连接
     */
    private Connection conn = null;
    /**
     * 数据库操作对象
     */
    private PreparedStatement statement = null;

    /**
     * 获取数据库连接对象
     */
    public void getConn() {
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库表注释信息
     */
    private void getTableNameByCon() {
        getConn();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SHOW CREATE TABLE " + tableName);
            if (rs != null && rs.next()) {
                String createDDL = rs.getString(2);
                tableComment = parse(createDDL);
            }
            rs.close();
            conn.close();
        } catch (Exception e) {
            try {
                conn.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    /**
     * 获取操作表数据信息
     */
    public void getMYSQLTable() {
        //获取表注释
        getTableNameByCon();
        getConn();
        String sql = "show full columns from " + tableName;
        try {
            Constants.columns = new ArrayList<String>();
            Constants.types = new ArrayList<String>();
            Constants.comments = new ArrayList<String>();
            statement = conn.prepareStatement(sql);
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                Constants.columns.add(results.getString("FIELD"));
                Constants.types.add(results.getString("TYPE"));
                Constants.comments.add(results.getString("COMMENT"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 返回注释信息
     *
     * @param all
     * @return
     */

    public String parse(String all) {
        String comment = null;
        int index = all.indexOf("COMMENT='");
        if (index < 0) {
            return "";
        }
        comment = all.substring(index + 9);
        comment = comment.substring(0, comment.length() - 1);
        return comment;
    }
}
