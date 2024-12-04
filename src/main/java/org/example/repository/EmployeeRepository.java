package org.example.repository;

import org.example.model.EmployeeEntity;
import org.example.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeRepository implements Repository<EmployeeEntity> {

    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getConnection();
    }


    @Override
    public List<EmployeeEntity> findAll() throws SQLException {
        List<EmployeeEntity> employees = new ArrayList<>();

        try (Connection myConn = getConnection(); Statement myStamt = myConn.createStatement(); ResultSet myRes = myStamt.executeQuery("SELECT * from employees")) {
            while (myRes.next()) {
                employees.add(createEmployee(myRes));
            }

            return employees;
        }
    }

    @Override
    public EmployeeEntity getById(Integer id) throws SQLException {

        EmployeeEntity employee = null;
        try (Connection myConn = getConnection(); PreparedStatement myStamt = myConn.prepareStatement("SELECT * FROM employees WHERE id = ?");) {

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
    public void save(EmployeeEntity employeeEntity) throws SQLException {

        String sql = "INSERT INTO employees (first_name, pa_surname, ma_surname, email, salary, curp) VALUES(?,?,?,?,?,?)";

        try (Connection myConn = getConnection(); PreparedStatement myPreStamt = myConn.prepareStatement(sql)) {

            myPreStamt.setString(1, employeeEntity.getFirst_name());
            myPreStamt.setString(2, employeeEntity.getPa_surname());
            myPreStamt.setString(3, employeeEntity.getMa_surname());
            myPreStamt.setString(4, employeeEntity.getEmail());
            myPreStamt.setFloat(5, employeeEntity.getSalary());
            myPreStamt.setString(6, employeeEntity.getCurp());

            int i = myPreStamt.executeUpdate();

        }
    }


    @Override
    public void update(Optional<EmployeeEntity> optionalEmployeeEntity, Integer id) throws SQLException {
        if (optionalEmployeeEntity.isEmpty()) {
            throw new IllegalArgumentException("El objeto EmployeeEntity no puede ser nulo");
        }

        EmployeeEntity employeeEntity = optionalEmployeeEntity.get();

        StringBuilder sqlBuilder = new StringBuilder("UPDATE employees SET ");
        boolean hasUpdates = false;

        if (employeeEntity.getFirst_name() != null) {
            sqlBuilder.append("first_name = ?, ");
            hasUpdates = true;
        }
        if (employeeEntity.getPa_surname() != null) {
            sqlBuilder.append("pa_surname = ?, ");
            hasUpdates = true;
        }
        if (employeeEntity.getMa_surname() != null) {
            sqlBuilder.append("ma_surname = ?, ");
            hasUpdates = true;
        }
        if (employeeEntity.getEmail() != null) {
            sqlBuilder.append("email = ?, ");
            hasUpdates = true;
        }
        if (employeeEntity.getSalary() != null) {
            sqlBuilder.append("salary = ?, ");
            hasUpdates = true;
        }


        if (employeeEntity.getCurp() != null) {
            sqlBuilder.append("curp = ?, ");
            hasUpdates = true;
        }

        if (!hasUpdates) {
            throw new IllegalArgumentException("No se proporcionaron campos para actualizar");
        }

        sqlBuilder.setLength(sqlBuilder.length() - 2);
        sqlBuilder.append(" WHERE id = ?");

        String sql = sqlBuilder.toString();

        try (Connection myConn = getConnection(); PreparedStatement myPreStamt = myConn.prepareStatement(sql)) {
            int index = 1;

            if (employeeEntity.getFirst_name() != null) {
                myPreStamt.setString(index++, employeeEntity.getFirst_name());
            }
            if (employeeEntity.getPa_surname() != null) {
                myPreStamt.setString(index++, employeeEntity.getPa_surname());
            }
            if (employeeEntity.getMa_surname() != null) {
                myPreStamt.setString(index++, employeeEntity.getMa_surname());
            }
            if (employeeEntity.getEmail() != null) {
                myPreStamt.setString(index++, employeeEntity.getEmail());
            }
            if (employeeEntity.getSalary() != null) {
                myPreStamt.setFloat(index++, employeeEntity.getSalary());
            }

            myPreStamt.setInt(index, id);

            EmployeeEntity getEmployee = this.getById(id);
            if (getEmployee.getId() == 0) {
                throw new Error("No existe el ID");
            }

            int rowsAffected = myPreStamt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No se pudo actualizar el empleado");
            }
        }
    }


    @Override
    public void delete(Integer id) throws SQLException {

        String sql = "DELETE FROM employees WHERE id = ?";
        try (Connection myConn = getConnection(); PreparedStatement myPreStamt = myConn.prepareStatement(sql)) {

            EmployeeEntity employee = this.getById(id);

            if (employee.getId() == 0) {
                throw new Error("No se puede eliminar por que no existe el usuario");
            }

            myPreStamt.setInt(1, id);
            myPreStamt.executeUpdate();
        }
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
