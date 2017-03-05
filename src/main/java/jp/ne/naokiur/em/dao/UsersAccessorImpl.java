package jp.ne.naokiur.em.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.ne.naokiur.em.dto.UserDto;

public enum UsersAccessorImpl implements DBAccessable {
    INSTANCE;

    @Override
    public void createTable() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            // Create EM_USERS table
            String createEmUsersSql = "CREATE TABLE IF NOT EXISTS EM_USERS ("
                    + "user_id VARCHAR(16) PRIMARY KEY NOT NULL," + "password TEXT NOT NULL)";

            try (PreparedStatement stmt = conn.prepareStatement(createEmUsersSql)) {

                stmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertUser(UserDto user) {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String insertEmployeeSql = "INSERT INTO EM_USERS VALUES (?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(insertEmployeeSql)) {
                stmt.setString(1, user.getUserId());
                stmt.setString(2, user.getPassword());

                stmt.execute();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String selectUserId(String userId, String password) {
        try {
            Class.forName(ENV.getString("db.driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String selectEmUsersSql = "SELECT user_id FROM EM_USERS WHERE user_id = ? AND password = ?";

            try (PreparedStatement stmt = conn.prepareStatement(selectEmUsersSql);) {
                stmt.setString(1, userId);
                stmt.setString(2, password);

                ResultSet result = stmt.executeQuery();

                if (result.next()) {
                    return result.getString(1);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "";
    }
}
