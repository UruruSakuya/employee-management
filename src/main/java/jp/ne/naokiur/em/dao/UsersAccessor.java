package jp.ne.naokiur.em.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import jp.ne.naokiur.em.dto.EmployeeDto;

public enum UsersAccessor {
    INSTANCE;
    private final static ResourceBundle ENV = ResourceBundle.getBundle("environment");

    public void insertUser(EmployeeDto employee) {
        try {
            Class.forName(ENV.getString("db.driver"));
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection(ENV.getString("db.url"), ENV.getString("db.user"), ENV.getString("db.password"))) {
            String insertEmployeeSql = "INSERT INTO EM_EMPLOYEES VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(insertEmployeeSql)) {
                stmt.setString(1, employee.getUserId());
                stmt.setString(2, employee.getFirstName());
                stmt.setString(3, employee.getLastName());
                stmt.setString(4, employee.getPostCode());
                stmt.setInt(5, employee.getAge());
                stmt.setTimestamp(6, employee.getEntryDate());

                stmt.execute();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String selectUserId(String userId, String password) {
        Connection conn = null;
        PreparedStatement stmt  = null;

        try {
            Class.forName(ENV.getString("db.driver"));
            conn = DriverManager.getConnection(ENV.getString("db.url"), ENV.getString("db.user"), ENV.getString("db.password"));

            String createEmUsersSql = "SELECT user_id FROM EM_USERS WHERE user_id = ? AND password = ?";
            stmt = conn.prepareStatement(createEmUsersSql);
            stmt.setString(1, userId);
            stmt.setString(2, password);

            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                return result.getString(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException e) {
            }
        }

        return "";
    }
}
