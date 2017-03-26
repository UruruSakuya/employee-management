package jp.ne.naokiur.em.code;

import java.util.ResourceBundle;

public enum Messages {
    ERROR001(ResourceBundle.getBundle("messages").getString("ERROR001"));

    private String label;

    private Messages(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
