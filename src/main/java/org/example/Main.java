package org.example;

import org.example.model.EmployeeEntity;
import org.example.repository.EmployeeRepository;
import org.example.repository.Repository;
import org.example.util.DatabaseConnection;

import java.sql.*;

public class Main {
    public static void main(String[] args) {


        try (
                Connection myConn = DatabaseConnection.getInstance();
        ) {
            System.out.println("Genial, nos conectamos");

            Repository<EmployeeEntity> repository = new EmployeeRepository();

            repository.findAll().forEach(System.out::println);

        } catch (SQLException e) {
            System.out.println("Algo salió mal");
            e.printStackTrace();
        }
    }
}
