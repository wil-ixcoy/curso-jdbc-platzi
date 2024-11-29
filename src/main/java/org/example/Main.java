package org.example;

import org.example.util.DatabaseConnection;

import java.sql.*;

public class Main {
    public static void main(String[] args) {


        try (
                Connection myConn = DatabaseConnection.getInstance();
                PreparedStatement deleteStmt = myConn.prepareStatement("DELETE FROM employees WHERE first_name = ?");
                PreparedStatement selectStmt = myConn.prepareStatement("SELECT * FROM employees ORDER BY id ASC");
        ) {
            System.out.println("Genial, nos conectamos");

            // Ejecutar DELETE
            deleteStmt.setString(1, "Johana");
            int rowsAffected = deleteStmt.executeUpdate();
            System.out.println("Filas eliminadas: " + rowsAffected);

            // Ejecutar SELECT
            try (ResultSet myRes = selectStmt.executeQuery()) {
                while (myRes.next()) {
                    System.out.println(myRes.getString("first_name") + " " + myRes.getString("email"));
                }
            }

        } catch (SQLException e) {
            System.out.println("Algo salió mal");
            e.printStackTrace();
        }
    }
}
