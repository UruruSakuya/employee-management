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
import jp.ne.naokiur.em.dto.SearchCondtion;
import jp.ne.naokiur.em.exception.ModelValidatorException;

public class EmployeeModel {
    private String userId;
    private String password;
    private String passwordAgain;
    private String firstName;
    private String lastName;
    private String postCode;
    private Integer age;
    private Timestamp enterDate;

    public EmployeeModel() {
    }

    public <T> EmployeeModel(T userId, T password, T passwordAgain, T firstName, T lastName, T postCode, T age, T enterDate) throws ModelValidatorException {
        this.userId = String.valueOf(userId);
        this.password = String.valueOf(password);
        this.passwordAgain = String.valueOf(passwordAgain);
        this.firstName = String.valueOf(firstName);
        this.lastName = String.valueOf(lastName);
        this.postCode = String.valueOf(postCode);

        List<Messages> messagesList = new ArrayList<>();

        try {
            this.age = Integer.valueOf(String.valueOf(age));
            this.enterDate = new Timestamp(new SimpleDateFormat("yyyy-MM-dd")
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

    public String getPassword() {
        return password;
    }

    public String getPasswordAgain() {
        return passwordAgain;
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
        dto.setPassword(this.password);
        dto.setFirstName(this.firstName);
        dto.setLastName(this.lastName);
        dto.setPostCode(this.postCode);
        dto.setAge(this.age);
        dto.setEnterDate(this.enterDate);

        EmployeeAccessorImpl.INSTANCE.insertUser(dto);
    }

    public List<EmployeeDto> search(Object userId, Object firstName, Object lastName, Object post, Object fromAge,
            Object toAge, Object fromEnterDate, Object toEnterDate) {

        SearchCondtion condtion = new SearchCondtion();

        condtion.setUserId(userId != null ? userId.toString() : null);
        condtion.setFirstName(firstName != null ? firstName.toString() : null);
        condtion.setLastName(lastName != null ? lastName.toString() : null);
        condtion.setPostCode(post != null ? post.toString() : null);
        condtion.setFromAge(fromAge != null && !fromAge.equals("") ? Integer.valueOf(fromAge.toString()) : null);
        condtion.setToAge(toAge != null && !toAge.equals("") ? Integer.valueOf(toAge.toString()) : null);

        SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");

        try {
            condtion.setFromEnterDate(fromEnterDate != null && !fromEnterDate.equals("") ? new Timestamp((sdt.parse(String.valueOf(fromEnterDate)).getTime())) : null);
            condtion.setToEnterDate(toEnterDate != null && !toEnterDate.equals("") ? new Timestamp((sdt.parse(String.valueOf(toEnterDate)).getTime())) : null);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return EmployeeAccessorImpl.INSTANCE.selectByCondition(condtion);
    }

    public void validate() throws ModelValidatorException {
        List<Messages> messagesList = new ArrayList<>();

        if (isExistText(this.userId)) {
            messagesList.add(new Messages(MessageResource.COMMON_MANDATORY, new String[]{"ユーザID"}));
        }

        if (isExistText(this.password)) {
            messagesList.add(new Messages(MessageResource.COMMON_MANDATORY, new String[]{"パスワード"}));
        }

        if (isExistText(this.passwordAgain)) {
            messagesList.add(new Messages(MessageResource.COMMON_MANDATORY, new String[]{"パスワード(再入力)"}));
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

        if (!this.password.equals(this.passwordAgain)) {
            messagesList.add(new Messages(MessageResource.EMPLOYEE_PASSWORD_UNMATCH));

        }

        if (!messagesList.isEmpty()) {
            throw new ModelValidatorException(messagesList);
        }
    }

    private boolean isExistText(String text) {
        return text == null || "".equals(text);
    }
}
