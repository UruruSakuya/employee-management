package jp.ne.naokiur.em.model;

import jp.ne.naokiur.em.dao.EmployeeAccessorImpl;
import jp.ne.naokiur.em.dto.EmployeeDto;

public class RegisterModel {
    public void register(EmployeeDto employee) {
        EmployeeAccessorImpl.INSTANCE.insertUser(employee);
    }
}
