package org.example.repository;

import org.example.model.EmployeeEntity;
import org.example.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements Repository<EmployeeEntity> {

    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }

    @Override
    public List<EmployeeEntity> findAll() throws SQLException {
        List<EmployeeEntity> employees = new ArrayList<>();

        try (Statement myStamt = getConnection().createStatement(); ResultSet myRes = myStamt.executeQuery("SELECT * from employees")) {
            while (myRes.next()) {
                employees.add(createEmployee(myRes));
            }

            return employees;
        }
    }

    @Override
    public EmployeeEntity getById(Integer id) throws SQLException {

        EmployeeEntity employee = null;
        try (PreparedStatement myStamt = getConnection().prepareStatement("SELECT * FROM employees WHERE id = ?");) {

            myStamt.setInt(1, id);

            try (ResultSet myRes = myStamt.executeQuery()) {

                if (myRes.next()) {
                    employee = createEmployee(myRes);
                }

                return employee;
            }
        }

    }

    @Override
    public void save(EmployeeEntity employeeEntity) {

    }

    @Override
    public void delete(Integer id) {

    }

    private static EmployeeEntity createEmployee(ResultSet myRes) throws SQLException {

        EmployeeEntity e = new EmployeeEntity();
        e.setId(myRes.getInt("id"));
        e.setFirst_name(myRes.getString("first_name"));
        e.setPa_surname(myRes.getString("pa_surname"));
        e.setMa_surname(myRes.getString("ma_surname"));
        e.setEmail(myRes.getString("email"));
        e.setSalary(myRes.getFloat("salary"));
        return e;
    }

}
