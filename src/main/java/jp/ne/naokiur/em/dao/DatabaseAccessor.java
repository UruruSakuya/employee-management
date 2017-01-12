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

            String sql = "create table if not exists user("
                    + "id varchar(16) PRIMARY KEY NOT NULL,"
                    + "name varchar(64) NOT NULL,"
                    + "password text NOT NULL)";

            PreparedStatement stmt = conn.prepareStatement(sql);

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
