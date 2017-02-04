package jp.ne.naokiur.em.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public enum UsersAccessor {
    INSTANCE;
    private final static ResourceBundle ENV = ResourceBundle.getBundle("environment");

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
