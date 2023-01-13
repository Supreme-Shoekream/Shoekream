package com.supreme.crawling.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconn {
    private static Connection conn;

    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        String url = "jdbc:mysql://127.0.0.1:3306/pro_test?serverTimezone=Asia/Seoul";
        String uid= "root";
        String upw = "root1234";

        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(url, uid, upw);
        return conn;
    }
}
