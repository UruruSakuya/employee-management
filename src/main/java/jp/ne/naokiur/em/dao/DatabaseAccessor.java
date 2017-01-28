package jp.ne.naokiur.em.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public enum DatabaseAccessor {
    INSTANCE;
    private final static ResourceBundle ENV = ResourceBundle.getBundle("environment");

    public void connect() {
        try {
            Class.forName(ENV.getString("db.driver"));
            Connection conn = DriverManager.getConnection(ENV.getString("db.url"), ENV.getString("db.user"), ENV.getString("db.password"));

            String createEmUsersSql = "CREATE TABLE IF NOT EXISTS EM_USERS ("
                    + "ID VARCHAR(16) PRIMARY KEY NOT NULL,"
                    + "PASSWORD TEXT NOT NULL)";
            PreparedStatement stmt = conn.prepareStatement(createEmUsersSql);
            stmt.executeUpdate();
            stmt.close();

            String createEmployeeSql = "CREATE TABLE IF NOT EXISTS EM_EMPLOYEES ("
                    + "ID VARCHAR(16) PRIMARY KEY NOT NULL,"
                    + "FIRST_NAME VARCHAR(16),"
                    + "LAST_NAME VARCHAR(16),"
                    + "POST_CODE VARCHAR(2),"
                    + "AGE INTEGER,"
                    + "ENTER_DATE TIMESTAMP)";
            stmt = conn.prepareStatement(createEmployeeSql);
            stmt.executeUpdate();
            stmt.close();

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
