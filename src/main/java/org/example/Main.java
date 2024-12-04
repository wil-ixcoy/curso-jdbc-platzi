package org.example;

import org.example.model.EmployeeEntity;
import org.example.repository.EmployeeRepository;
import org.example.repository.Repository;
import org.example.util.DatabaseConnection;

import java.sql.*;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {


        try (
                Connection myConn = DatabaseConnection.getInstance();
        ) {
            System.out.println("Genial, nos conectamos");

            Repository<EmployeeEntity> repository = new EmployeeRepository();

            System.out.println("----------insertando-------------");
           /*
           *
           *  EmployeeEntity employee = new EmployeeEntity();

            employee.setFirst_name("Diego");
            employee.setPa_surname("Pimentel");
            employee.setMa_surname("Guiterrez");
            employee.setEmail("diego@example.com");
            employee.setSalary(23400F);

            repository.save(employee);
            *
            * */


            System.out.println("----------actualizando-------------");
            EmployeeEntity employee = new EmployeeEntity();


            employee.setMa_surname("Mendoza");
            employee.setFirst_name("Juan");


            repository.update(Optional.of(employee),8);


            System.out.println("-----------------------");
            System.out.println(repository.getById(8));


            repository.delete(8);
            System.out.println("--------Obtener todos-------");
            repository.findAll().forEach(System.out::println);


        } catch (SQLException e) {
            System.out.println("Algo salió mal");
            e.printStackTrace();
        }
    }
}
