package jp.ne.naokiur.em.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.ne.naokiur.em.dto.PostDto;

public enum PostAccessorImpl implements DBAccessable {
    INSTANCE;

    @Override
    public void createTable() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Create EM_EMPLOYEES table
            String createPostSql = "CREATE TABLE IF NOT EXISTS EM_POST (" + "post_code CHAR(2) PRIMARY KEY NOT NULL,"
                    + "post_name VARCHAR(255))";

            try (PreparedStatement stmt = conn.prepareStatement(createPostSql)) {
                stmt.executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (SQLException e1) {
            // TODO 自動生成された catch ブロック
            e1.printStackTrace();
        }
    }

    public void insertUser(PostDto post) {
        try {
            Class.forName(ENV.getString("db.driver"));
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection(ENV.getString("db.url"), ENV.getString("db.user"), ENV.getString("db.password"))) {
            String insertEmployeeSql = "INSERT INTO EM_POST VALUES (?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(insertEmployeeSql)) {
                stmt.setString(1, post.getPostCode());
                stmt.setString(2, post.getPostName());

                stmt.execute();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String selectPostNameByCode(String code) {
        try {
            Class.forName(ENV.getString("db.driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection(ENV.getString("db.url"), ENV.getString("db.user"), ENV.getString("db.password")); ) {
            String createEmUsersSql = "SELECT post_name FROM EM_POST WHERE post_code = ?";

            try (PreparedStatement stmt  =conn.prepareStatement(createEmUsersSql);) {
                stmt.setString(1, code);

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


    public List<PostDto> selectAllPost() {
        try {
            Class.forName(ENV.getString("db.driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        List<PostDto> resultList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(ENV.getString("db.url"), ENV.getString("db.user"), ENV.getString("db.password")); ) {
            String createEmUsersSql = "SELECT * FROM EM_POST";

            try (PreparedStatement stmt  =conn.prepareStatement(createEmUsersSql);) {

                ResultSet result = stmt.executeQuery();

                while (result.next()) {
                    resultList.add(new PostDto(result.getString(1), result.getString(2)));

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }

    public String selectPostCount() {
        try {
            Class.forName(ENV.getString("db.driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection(ENV.getString("db.url"), ENV.getString("db.user"), ENV.getString("db.password")); ) {
            String createEmUsersSql = "SELECT COUNT(*) FROM EM_POST";

            try (PreparedStatement stmt  =conn.prepareStatement(createEmUsersSql);) {

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
