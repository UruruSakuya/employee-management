package jp.ne.naokiur.em.code;

public enum AttributeKey {
    MESSAGE_LIST("messageList"),
    TITLE("title"),
    TITLE_NAME("titleName");

    private String key;

    private AttributeKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
