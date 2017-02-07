package jp.ne.naokiur.em.model;

import jp.ne.naokiur.em.dao.UsersAccessor;
import jp.ne.naokiur.em.dto.EmployeeDto;

public class RegisterModel {
    public void register(EmployeeDto employee) {
        UsersAccessor.INSTANCE.insertUser(employee);
    }
}
