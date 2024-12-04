package org.example;

import org.example.model.EmployeeEntity;
import org.example.repository.EmployeeRepository;
import org.example.repository.Repository;
import org.example.util.DatabaseConnection;
import org.example.view.SwingApp;

import java.sql.*;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        SwingApp app = new SwingApp();

        app.setVisible(true);
    }
}
