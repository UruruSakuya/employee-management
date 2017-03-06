package jp.ne.naokiur.em.dto;

import java.sql.Timestamp;

public class SearchCondtion {
    private String userId;
    private String firstName;
    private String lastName;
    private String postCode;
    private Integer fromAge;
    private Integer toAge;
    private Timestamp fromEnterDate;
    private Timestamp toEnterDate;

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

    public Integer getFromAge() {
        return fromAge;
    }

    public void setFromAge(Integer fromAge) {
        this.fromAge = fromAge;
    }

    public Integer getToAge() {
        return toAge;
    }

    public void setToAge(Integer toAge) {
        this.toAge = toAge;
    }

    public Timestamp getFromEnterDate() {
        return fromEnterDate;
    }

    public void setFromEnterDate(Timestamp fromEnterDate) {
        this.fromEnterDate = fromEnterDate;
    }

    public Timestamp getToEnterDate() {
        return toEnterDate;
    }

    public void setToEnterDate(Timestamp toEnterDate) {
        this.toEnterDate = toEnterDate;
    }

}
