package jp.ne.naokiur.em.dao;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import jp.ne.naokiur.em.dto.DisplayEmployeeDto;
import jp.ne.naokiur.em.dto.EmployeeDto;
import jp.ne.naokiur.em.dto.SearchCondtion;
import jp.ne.naokiur.em.exception.SystemException;

public enum EmployeeAccessorImpl implements DBAccessable {
    INSTANCE;
    private final static ResourceBundle ENV = ResourceBundle.getBundle("environment");

    private enum Encryption {
        INSTANCE;
        private final static String ALGORITHM = "AES";
        // TODO キーを公開すべきではないので、直書きすべきではない。
        private final static String KEY = "1234567890123456";

        public String encrypt(String password) {
            try {
                Cipher cipher = Cipher.getInstance(ALGORITHM);
                cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(KEY.getBytes(), ALGORITHM));
                byte[] finalCipher = cipher.doFinal(password.getBytes());

                return new String(Base64.getEncoder().encode(finalCipher));

            } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
                new SystemException(e);
            }

            return "";
        }

        public String decrpt(String encryptedPassword) {

            byte[] decodedPassword = Base64.getDecoder().decode(encryptedPassword.getBytes());

            try {
                Cipher cipher = Cipher.getInstance(ALGORITHM);
                cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(KEY.getBytes(), ALGORITHM));
                return new String(cipher.doFinal(decodedPassword));
            } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
                new SystemException(e);
            }

            return "";
        }
    }

    @Override
    public void createTable() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new SystemException(e);
        }

        String sql = "CREATE TABLE IF NOT EXISTS EM_EMPLOYEES ("
                + "user_id VARCHAR(16) PRIMARY KEY NOT NULL,"
                + "password VARCHAR(64) NOT NULL,"
                + "first_name VARCHAR(16)," + "last_name VARCHAR(16)," + "post_code CHAR(2)," + "age INTEGER,"
                + "enter_date TIMESTAMP)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.executeUpdate();


            } catch (SQLException e) {
                throw new SystemException(e);
            }

        } catch (SQLException e) {
            throw new SystemException(e);
        }
    }

    public void insertUser(EmployeeDto employee) {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new SystemException(e);
        }

        try (Connection conn = DriverManager.getConnection(ENV.getString("db.url"), ENV.getString("db.user"),
                ENV.getString("db.password"))) {
            String insertEmployeeSql = "INSERT INTO EM_EMPLOYEES VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(insertEmployeeSql)) {
                stmt.setString(1, employee.getUserId());
                stmt.setString(2, Encryption.INSTANCE.encrypt(employee.getPassword()));
                stmt.setString(3, employee.getFirstName());
                stmt.setString(4, employee.getLastName());
                stmt.setString(5, employee.getPostCode());
                stmt.setInt(6, employee.getAge());
                stmt.setTimestamp(7, employee.getEnterDate());

                stmt.execute();
            }

        } catch (SQLException e) {
            throw new SystemException(e);
        }
    }

    public String selectUserIdByUserIdAndPassword(String userId, String password) {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new SystemException(e);
        }

        try (Connection conn = DriverManager.getConnection(ENV.getString("db.url"), ENV.getString("db.user"),
                ENV.getString("db.password"))) {
            String selectEmployeeSql = "SELECT user_id FROM EM_EMPLOYEES "
                    + "WHERE user_id = '" + userId + "' AND "
                    + "password = '" + Encryption.INSTANCE.encrypt(password) + "';";

            try (PreparedStatement stmt = conn.prepareStatement(selectEmployeeSql)) {
                ResultSet resultSet = stmt.executeQuery();

                if (resultSet.next()) {
                    return resultSet.getString(1);
                }
            }
        } catch (SQLException e) {
            throw new SystemException(e);
        }

        return "";
    }

    public String selectCountByUserId(String userId) {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new SystemException(e);
        }

        try (Connection conn = DriverManager.getConnection(ENV.getString("db.url"), ENV.getString("db.user"),
                ENV.getString("db.password"))) {
            String selectEmployeeSql = "SELECT COUNT(user_id) FROM EM_EMPLOYEES WHERE user_id = '" + userId + "'";

            try (PreparedStatement stmt = conn.prepareStatement(selectEmployeeSql)) {
                ResultSet resultSet = stmt.executeQuery();

                if (resultSet.next()) {
                    return resultSet.getString(1);
                }
            }
        } catch (SQLException e) {
            throw new SystemException(e);
        }

        return "";
    }

    public List<EmployeeDto> selectByCondition(SearchCondtion condtion) {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new SystemException(e);
        }

        List<EmployeeDto> resultList = new ArrayList<EmployeeDto>();

        try (Connection conn = DriverManager.getConnection(ENV.getString("db.url"), ENV.getString("db.user"),
                ENV.getString("db.password"))) {
            String selectEmployeeSql = "SELECT e.user_id, e.first_name, e.last_name, e.post_code, e.age, e.enter_date, p.post_name FROM EM_EMPLOYEES e INNER JOIN EM_POST p ON e.post_code = p.post_code";

            boolean hasCondition = false;

            if (condtion.getUserId() != null && !"".equals(condtion.getUserId())) {
                if (!hasCondition) {
                    selectEmployeeSql = selectEmployeeSql + " WHERE";

                }

                selectEmployeeSql = selectEmployeeSql + " user_id = '" + condtion.getUserId() + "'";
                hasCondition = true;
            }

            if (condtion.getFirstName() != null && !"".equals(condtion.getFirstName())) {
                if (!hasCondition) {
                    selectEmployeeSql = selectEmployeeSql + " WHERE";

                } else {
                    selectEmployeeSql = selectEmployeeSql + " AND";
                }

                selectEmployeeSql = selectEmployeeSql + " e.first_name = '" + condtion.getFirstName() + "'";
                hasCondition = true;
            }

            if (condtion.getLastName() != null && !"".equals(condtion.getLastName())) {
                if (!hasCondition) {
                    selectEmployeeSql = selectEmployeeSql + " WHERE";

                } else {
                    selectEmployeeSql = selectEmployeeSql + " AND";
                }

                selectEmployeeSql = selectEmployeeSql + " e.last_name = '" + condtion.getLastName() + "'";
                hasCondition = true;
            }

            if (condtion.getPostCode() != null && !"".equals(condtion.getPostCode())) {
                if (!hasCondition) {
                    selectEmployeeSql = selectEmployeeSql + " WHERE";

                } else {
                    selectEmployeeSql = selectEmployeeSql + " AND";
                }

                selectEmployeeSql = selectEmployeeSql + " e.post_code = '" + condtion.getPostCode() + "'";
                hasCondition = true;
            }

            if (condtion.getFromAge() != null) {
                if (!hasCondition) {
                    selectEmployeeSql = selectEmployeeSql + " WHERE";

                } else {
                    selectEmployeeSql = selectEmployeeSql + " AND";
                }

                selectEmployeeSql = selectEmployeeSql + " e.age > " + condtion.getFromAge();
                hasCondition = true;
            }

            if (condtion.getToAge() != null) {
                if (!hasCondition) {
                    selectEmployeeSql = selectEmployeeSql + " WHERE";

                } else {
                    selectEmployeeSql = selectEmployeeSql + " AND";
                }

                selectEmployeeSql = selectEmployeeSql + " e.age < " + condtion.getToAge();
                hasCondition = true;
            }

            if (condtion.getFromEnterDate() != null) {
                if (!hasCondition) {
                    selectEmployeeSql = selectEmployeeSql + " WHERE";

                } else {
                    selectEmployeeSql = selectEmployeeSql + " AND";
                }

                selectEmployeeSql = selectEmployeeSql + " TRUNC(e.enter_date) > TRUNC('" + condtion.getFromEnterDate() + "')";
                hasCondition = true;
            }

            if (condtion.getToEnterDate() != null) {
                if (!hasCondition) {
                    selectEmployeeSql = selectEmployeeSql + " WHERE";

                } else {
                    selectEmployeeSql = selectEmployeeSql + " AND";
                }

                selectEmployeeSql = selectEmployeeSql + " TRUNC(e.enter_date) < TRUNC('" + condtion.getToEnterDate() + "')";
                hasCondition = true;
            }

            try (PreparedStatement stmt = conn.prepareStatement(selectEmployeeSql)) {
                ResultSet resultSet = stmt.executeQuery();

                while (resultSet.next()) {
                    DisplayEmployeeDto dto = new DisplayEmployeeDto();
                    dto.setUserId(resultSet.getString(1));
                    dto.setFirstName(resultSet.getString(2));
                    dto.setLastName(resultSet.getString(3));
                    dto.setPostCode(resultSet.getString(4));
                    dto.setAge(resultSet.getInt(5));
                    dto.setEnterDate(resultSet.getTimestamp(6));
                    dto.setPostName(resultSet.getString(7));

                    resultList.add(dto);
                }
            }
        } catch (SQLException e) {
            throw new SystemException(e);
        }

        return resultList;
    }
}
