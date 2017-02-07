package jp.ne.naokiur.em.dto;

import java.sql.Timestamp;

public class EmployeeDto {
    private String userId;
    private String firstName;
    private String lastName;
    private String postCode;
    private int age;
    private Timestamp entryDate;
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPostCode() {
        return postCode;
    }
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public Timestamp getEntryDate() {
        return entryDate;
    }
    public void setEntryDate(Timestamp entryDate) {
        this.entryDate = entryDate;
    }
}
