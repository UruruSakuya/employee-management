package jp.ne.naokiur.em.controller.model;

import jp.ne.naokiur.em.dao.UsersAccessor;

public class LoginModel {
    public boolean isMatchUser(String id, String password) {
        String matchedUserId = UsersAccessor.INSTANCE.selectUserId(id, password);
        return  (matchedUserId != null && !matchedUserId.equals(""));
    }
}
