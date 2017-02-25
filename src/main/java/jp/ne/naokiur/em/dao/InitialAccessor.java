package jp.ne.naokiur.em.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public enum InitialAccessor {
    INSTANCE;
    private final static ResourceBundle ENV = ResourceBundle.getBundle("environment");

    public void initialize() {
        try {
            Class.forName(ENV.getString("db.driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection(ENV.getString("db.url"), ENV.getString("db.user"), ENV.getString("db.password"));) {
            String createEmUsersSql = "CREATE TABLE IF NOT EXISTS EM_USERS ("
                    + "user_id VARCHAR(16) PRIMARY KEY NOT NULL," + "password TEXT NOT NULL)";

            try (PreparedStatement stmt = conn.prepareStatement(createEmUsersSql)) {

                stmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }

            String createEmployeeSql = "CREATE TABLE IF NOT EXISTS EM_EMPLOYEES ("
                    + "user_id VARCHAR(16) PRIMARY KEY NOT NULL," + "first_name VARCHAR(16)," + "last_name VARCHAR(16),"
                    + "post_code VARCHAR(2)," + "age INTEGER," + "enter_date TIMESTAMP)";

            try (PreparedStatement stmt = conn.prepareStatement(createEmployeeSql)) {
                stmt.executeUpdate();

                String initialAccountUser = "admin";
                String initialAccountPassword = "admin";
                String initialAccount = UsersAccessor.INSTANCE.selectUserId(initialAccountUser, initialAccountPassword);

                if (initialAccount != null && "".equals(initialAccount)) {
                    String insertDefaultUserSql = "INSERT INTO EM_USERS VALUES (" + "'admin'," + "'admin')";

                    try (PreparedStatement insertStatement = conn.prepareStatement(insertDefaultUserSql)) {
                        insertStatement.executeUpdate();
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
