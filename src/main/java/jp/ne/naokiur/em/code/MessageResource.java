package jp.ne.naokiur.em.code;

import java.util.ResourceBundle;

public enum MessageResource {
    LOGIN_MANDATORY_VALUES("error"),
    LOGIN_UNMATCH("error"),
    COMMON_MANDATORY("error");

    private String level;

    private MessageResource(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public String load() {
        return ResourceBundle.getBundle("messages").getString(this.toString());
    }
}
