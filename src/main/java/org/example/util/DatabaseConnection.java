package org.example.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection{

    private static String url = "jdbc:postgresql://aws-0-us-west-1.pooler.supabase.com:6543/postgres";
    private static String user = "postgres.gzttcynbsfeammiugeig";
    private static String password = "HL*#pa3uQG2Ws!B";

    private  static BasicDataSource pool = null;


    public static BasicDataSource getInstance() throws SQLException {
        if(pool == null){
            pool =  new BasicDataSource();

            pool.setUrl(url);
            pool.setUsername(user);
            pool.setPassword(password);


            pool.setInitialSize(3);
            pool.setMinIdle(3);
            pool.setMaxIdle(10);
            pool.setMaxTotal(10);
        }
        return pool;
    }

    public static Connection getConnection() throws SQLException {
        return getInstance().getConnection();
    }
}