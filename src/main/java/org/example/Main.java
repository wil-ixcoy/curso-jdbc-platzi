package org.example;

import org.example.model.EmployeeEntity;
import org.example.repository.EmployeeRepository;
import org.example.repository.Repository;
import org.example.util.DatabaseConnection;
import org.example.view.SwingApp;

import java.sql.*;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws SQLException {
        try(Connection myConn = DatabaseConnection.getInstance()){

            if(myConn.getAutoCommit()){
                myConn.setAutoCommit(false);
            }

            try{
                Repository<EmployeeEntity> repository = new EmployeeRepository(myConn);

                System.out.println("-----------Insertar nuevo empleado-------------");
                EmployeeEntity employee = new EmployeeEntity();

                employee.setFirst_name("America");
                employee.setPa_surname("Lopez");
                employee.setMa_surname("Villa");
                employee.setEmail("analopez@gmail.com");
                employee.setSalary(1200F);
                employee.setCurp("AMERICA12345678901");


                repository.save(employee);
                myConn.commit();

            } catch (Exception e) {
                myConn.rollback();
                throw new RuntimeException(e);
            }
        }
    }
}
