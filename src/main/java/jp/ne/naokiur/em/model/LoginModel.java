package jp.ne.naokiur.em.model;

import jp.ne.naokiur.em.code.Messages;
import jp.ne.naokiur.em.dao.UsersAccessorImpl;
import jp.ne.naokiur.em.exception.ModelValidatorException;

public class LoginModel {
    public String authenticate(String id, String password) throws ModelValidatorException {
        if (id == null || id.equals("") || password == null || password.equals("")) {
            throw new ModelValidatorException(Messages.LOGIN_MANDATORY_VALUES);
        }

        String matchedUserId = UsersAccessorImpl.INSTANCE.selectUserId(id, password);

        if ((matchedUserId != null && !matchedUserId.equals(""))) {
            return matchedUserId;

        } else {
            throw new ModelValidatorException(Messages.LOGIN_UNMATCH);
        }
    }
}
