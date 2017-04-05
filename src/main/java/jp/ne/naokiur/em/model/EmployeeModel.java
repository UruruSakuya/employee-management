package jp.ne.naokiur.em.model;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jp.ne.naokiur.em.code.MessageResource;
import jp.ne.naokiur.em.common.Messages;
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

        List<Messages> messagesList = new ArrayList<>();

        try {
            this.age = Integer.valueOf(String.valueOf(age));
            this.enterDate = new Timestamp(new SimpleDateFormat("yyyy-mm-dd")
                    .parse(String.valueOf(enterDate)).getTime());
        } catch (NumberFormatException e) {
            messagesList.add(new Messages(MessageResource.COMMON_MANDATORY, new String[]{"年齢"}));
            throw new ModelValidatorException(messagesList);

        } catch (ParseException e) {
            messagesList.add(new Messages(MessageResource.COMMON_MANDATORY, new String[]{"入社年月日"}));
            throw new ModelValidatorException(messagesList);
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
        List<Messages> messagesList = new ArrayList<>();

        if (isExistText(this.userId)) {
            messagesList.add(new Messages(MessageResource.COMMON_MANDATORY, new String[]{"ユーザID"}));
        }

        if (isExistText(this.firstName)) {
            messagesList.add(new Messages(MessageResource.COMMON_MANDATORY, new String[]{"名字"}));
        }

        if (isExistText(this.lastName)) {
            messagesList.add(new Messages(MessageResource.COMMON_MANDATORY, new String[]{"名前"}));
        }

        if (isExistText(this.postCode)) {
            messagesList.add(new Messages(MessageResource.COMMON_MANDATORY, new String[]{"部門"}));
        }

        if (this.age == null) {
            messagesList.add(new Messages(MessageResource.COMMON_MANDATORY, new String[]{"年齢"}));
        }

        if (!messagesList.isEmpty()) {
            throw new ModelValidatorException(messagesList);
        }
    }

    private boolean isExistText(String text) {
        return text == null || "".equals(text);
    }
}
