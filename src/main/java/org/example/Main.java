package org.example;
import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Connection myConn = null;
        Statement myStamt = null;
        PreparedStatement myPreredStamt = null;

        ResultSet myRes = null;



        try {
            myConn = DriverManager.getConnection("jdbc:postgresql://aws-0-us-west-1.pooler.supabase.com:6543/postgres", "postgres.gzttcynbsfeammiugeig", "HL*#pa3uQG2Ws!B");
            System.out.println("Genial, nos conectamos");
            myStamt = myConn.createStatement();


            String sql = "INSERT INTO employees(first_name,pa_surname) VALUES (?,?)";
            myPreredStamt = myConn.prepareStatement(sql);

            myPreredStamt.setString(1,"Johana");
            myPreredStamt.setString(2,"Dorantes");

            int rowAffected = myPreredStamt.executeUpdate();

            if (rowAffected >0){
                System.out.println("Se ha creado un nuevo empleado");
            }

        } catch (SQLException e) {
            System.out.println("Algo salió mal");
            e.printStackTrace();
        }
    }
}