package jp.ne.naokiur.em.code;

import java.util.ResourceBundle;

public enum Messages {
    LOGIN_MANDATORY_VALUES("error"),
    LOGIN_UNMATCH("error");

    private String level;

    private Messages(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public String load() {
        return ResourceBundle.getBundle("messages").getString(this.toString());
    }
}
