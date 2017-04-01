package jp.ne.naokiur.em.model;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import jp.ne.naokiur.em.code.Messages;
import jp.ne.naokiur.em.dao.EmployeeAccessorImpl;
import jp.ne.naokiur.em.dto.EmployeeDto;
import jp.ne.naokiur.em.exception.ModelValidatorException;

public class EmployeeModel<T> {
    private final String userId;
    private final String firstName;
    private final String lastName;
    private final String postCode;
    private final Integer age;
    private final Timestamp enterDate;

    public EmployeeModel(T userId, T firstName, T lastName, T postCode, T age, T enterDate) throws ModelValidatorException {
        this.userId = String.valueOf(userId);
        this.firstName = String.valueOf(firstName);
        this.lastName = String.valueOf(lastName);
        this.postCode = String.valueOf(postCode);

        try {
            this.age = Integer.valueOf(String.valueOf(age));
            this.enterDate = new Timestamp(new SimpleDateFormat("yyyy-mm-dd")
                    .parse(String.valueOf(enterDate)).getTime());
        } catch (NumberFormatException e) {
            throw new ModelValidatorException(e);

        } catch (ParseException e) {
            throw new ModelValidatorException(e);
        }
    }

    public String getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPostCode() {
        return postCode;
    }

    public Integer getAge() {
        return age;
    }

    public Timestamp getEnterDate() {
        return enterDate;
    }

    public void register() {
        EmployeeDto dto = new EmployeeDto();
        dto.setUserId(this.userId);
        dto.setFirstName(this.firstName);
        dto.setLastName(this.lastName);
        dto.setPostCode(this.postCode);
        dto.setAge(this.age);
        dto.setEnterDate(this.enterDate);

        EmployeeAccessorImpl.INSTANCE.insertUser(dto);
    }

    public void validate() throws ModelValidatorException {
        validateUserId();
    }

    private void validateUserId() throws ModelValidatorException {
        if (this.userId == null || "".equals(this.userId)) {
            throw new ModelValidatorException(Messages.EMPLOYEE_MANDATORY_USER_ID);
        }
    }
}
