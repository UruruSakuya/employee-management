package jp.ne.naokiur.em.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import jp.ne.naokiur.em.dto.EmployeeDto;
import jp.ne.naokiur.em.dto.UserDto;

public enum EmployeeAccessorImpl implements DBAccessable {
    INSTANCE;
    private final static ResourceBundle ENV = ResourceBundle.getBundle("environment");

    @Override
    public void createTable() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        String sql = "CREATE TABLE IF NOT EXISTS EM_EMPLOYEES (" + "user_id VARCHAR(16) PRIMARY KEY NOT NULL,"
                + "first_name VARCHAR(16)," + "last_name VARCHAR(16)," + "post_code CHAR(2)," + "age INTEGER,"
                + "enter_date TIMESTAMP)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.executeUpdate();

                String initialAccountUser = "admin";
                String initialAccountPassword = "admin";
                String initialAccount = UsersAccessorImpl.INSTANCE.selectUserId(initialAccountUser,
                        initialAccountPassword);

                if (initialAccount == null || "".equals(initialAccount)) {
                    UsersAccessorImpl.INSTANCE.insertUser(new UserDto(initialAccountUser, initialAccountPassword));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertUser(EmployeeDto employee) {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection(ENV.getString("db.url"), ENV.getString("db.user"),
                ENV.getString("db.password"))) {
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
}
