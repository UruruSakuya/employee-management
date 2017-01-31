package jp.ne.naokiur.em.code;

public enum Site {
    LOGIN("/login", "/WEB-INF/views/login.jsp", "login"),
    MENU("/user/menu", "/WEB-INF/views/user/menu.jsp", "menu"),
    SEARCH_INIT("/user/search/init", "/WEB-INF/views/user/search/init.jsp", "search"),
    REGISTER_INIT("/user/register/init", "/WEB-INF/views/user/register/init.jsp", "register");

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
