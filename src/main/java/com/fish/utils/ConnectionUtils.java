package com.fish.utils;

import com.fish.bean.Database;

import java.sql.*;

/**
 * Created by fish on 2016/12/11.
 */
public class ConnectionUtils {

    /**
     * 获取数据库连接
     * @return
     * @throws SQLException
     */
    public static Connection getConnection(Database database) throws SQLException {
        try {

            System.out.println(database);

//    	    1. 加载驱动
            Class.forName("com.mysql.jdbc.Driver");
//	    2. 创建连接
            Connection conn = DriverManager.getConnection(database.getConnectionURL(), database.getUserId(), database.getPassword());
            conn.setAutoCommit(false);
            return conn;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("没有引入驱动包", e);
        }
    }

    /**
     * 关闭数据库连接
     * @param conn
     * @param pst
     * @param res
     * @throws SQLException
     */
    public static void close(Connection conn, PreparedStatement pst, ResultSet res) throws SQLException {
        if(res != null) {
            res.close();
        }
        if(pst != null) {
            pst.close();
        }
        if(conn != null) {
            conn.close();
        }
    }

}
