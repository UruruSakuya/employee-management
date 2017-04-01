package jp.ne.naokiur.em.model;

import jp.ne.naokiur.em.code.Messages;
import jp.ne.naokiur.em.dao.EmployeeAccessorImpl;
import jp.ne.naokiur.em.dto.EmployeeDto;
import jp.ne.naokiur.em.exception.ModelValidatorException;

public class RegisterModel {
    public void register(EmployeeDto employee) {
        EmployeeAccessorImpl.INSTANCE.insertUser(employee);
    }

    public void validate(EmployeeDto employee) throws ModelValidatorException {
        validateUserId(employee.getUserId());
    }

    private void validateUserId(String userId) throws ModelValidatorException {
        if (userId == null || "".equals(userId)) {
            throw new ModelValidatorException(Messages.COMMON_MANDATORY_VALUES);
        }
    }

}
