package org.example;
import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Connection myConn = null;
        Statement myStamt = null;
        ResultSet myRes = null;

        try {
            myConn = DriverManager.getConnection("jdbc:postgresql://aws-0-us-west-1.pooler.supabase.com:6543/postgres", "postgres.gzttcynbsfeammiugeig", "HL*#pa3uQG2Ws!B");
            System.out.println("Genial, nos conectamos");

            myStamt = myConn.createStatement();

            myRes = myStamt.executeQuery("SELECT * FROM employees");

            while (myRes.next()){
                System.out.println(myRes.getString("first_name"));
            }

        } catch (SQLException e) {
            System.out.println("Algo salió mal");
            e.printStackTrace();
        }
    }
}