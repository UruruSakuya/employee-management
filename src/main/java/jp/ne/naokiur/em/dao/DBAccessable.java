package jp.ne.naokiur.em.dao;

import java.util.ResourceBundle;

public interface DBAccessable {

    public final static ResourceBundle ENV = ResourceBundle.getBundle("environment");
    public final static String URL = ENV.getString("db.url");
    public final static String USER = ENV.getString("db.user");
    public final static String PASSWORD = ENV.getString("db.password");
    public final static String DRIVER = ENV.getString("db.driver");


    public void createTable();
}
