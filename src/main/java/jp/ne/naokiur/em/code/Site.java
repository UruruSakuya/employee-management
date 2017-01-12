package jp.ne.naokiur.em.code;

public enum Site {
    LOGIN("./", "/WEB-INF/views/login.jsp", "login"),
    MENU("./menu", "/WEB-INF/views/menu.jsp", "menu");

    private String url;
    private String jspPath;
    private String title;

    private Site(String url, String jspPath, String title) {
        this.url = url;
        this.jspPath = jspPath;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public String getJspPath() {
        return jspPath;
    }

    public String getTitle() {
        return title;
    }
}
