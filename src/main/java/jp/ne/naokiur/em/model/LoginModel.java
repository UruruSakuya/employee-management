package jp.ne.naokiur.em.model;

import java.util.ArrayList;

import jp.ne.naokiur.em.code.MessageResource;
import jp.ne.naokiur.em.common.Messages;
import jp.ne.naokiur.em.dao.EmployeeAccessorImpl;
import jp.ne.naokiur.em.exception.ModelValidatorException;

public class LoginModel {
    public String authenticate(String id, String password) throws ModelValidatorException {

        if (id == null || id.equals("") || password == null || password.equals("")) {
            throw new ModelValidatorException(new ArrayList<Messages>() {
                /** Default serialize id */
                private static final long serialVersionUID = 1L;

                {
                    add(new Messages(MessageResource.LOGIN_MANDATORY_VALUES));
                }
            });
        }

        String matchedUserId = EmployeeAccessorImpl.INSTANCE.selectUserIdByUserIdAndPassword(id, password);

        if ((matchedUserId != null && !matchedUserId.equals(""))) {
            return matchedUserId;

        } else {
            throw new ModelValidatorException(new ArrayList<Messages>() {
                /** Default serialize id */
                private static final long serialVersionUID = 1L;

                {
                    add(new Messages(MessageResource.LOGIN_UNMATCH));
                }
            });

        }
    }
}
