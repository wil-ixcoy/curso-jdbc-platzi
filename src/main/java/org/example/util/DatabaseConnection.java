package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection{

    private static String url = "jdbc:postgresql://aws-0-us-west-1.pooler.supabase.com:6543/postgres";
    private static String user = "postgres.gzttcynbsfeammiugeig";
    private static String password = "HL*#pa3uQG2Ws!B";

    private  static Connection myConn = null;


    public static Connection getInstance() throws SQLException {
        if(myConn == null){
            myConn = DriverManager.getConnection(url,user,password);
        }
        return myConn;
    }
}