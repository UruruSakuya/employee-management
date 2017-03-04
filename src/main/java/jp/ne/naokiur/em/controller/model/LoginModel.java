package jp.ne.naokiur.em.controller.model;

import jp.ne.naokiur.em.dao.UsersAccessorImpl;

public class LoginModel {
    public boolean isMatchUser(String id, String password) {
        String matchedUserId = UsersAccessorImpl.INSTANCE.selectUserId(id, password);
        return (matchedUserId != null && !matchedUserId.equals(""));
    }
}
