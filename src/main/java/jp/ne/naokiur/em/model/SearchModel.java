package jp.ne.naokiur.em.model;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import jp.ne.naokiur.em.dao.EmployeeAccessorImpl;
import jp.ne.naokiur.em.dto.EmployeeDto;
import jp.ne.naokiur.em.dto.SearchCondtion;

public class SearchModel {

    public List<EmployeeDto> search(Object userId, Object firstName, Object lastName, Object post, Object fromAge,
            Object toAge, Object fromEnterDate, Object toEnterDate) {

        SearchCondtion condtion = new SearchCondtion();

        condtion.setUserId(userId != null ? userId.toString() : null);
        condtion.setFirstName(firstName != null ? firstName.toString() : null);
        condtion.setLastName(lastName != null ? lastName.toString() : null);
        condtion.setPostCode(post != null ? post.toString() : null);
        condtion.setFromAge(fromAge != null && !fromAge.equals("") ? Integer.valueOf(fromAge.toString()) : null);
        condtion.setToAge(toAge != null && !toAge.equals("") ? Integer.valueOf(toAge.toString()) : null);

        SimpleDateFormat sdt = new SimpleDateFormat("yyyy-mm-dd");

        try {
            condtion.setFromEnterDate(fromEnterDate != null && !fromEnterDate.equals("") ? new Timestamp((sdt.parse(String.valueOf(fromEnterDate)).getTime())) : null);
            condtion.setToEnterDate(toEnterDate != null && !toEnterDate.equals("") ? new Timestamp((sdt.parse(String.valueOf(toEnterDate)).getTime())) : null);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return EmployeeAccessorImpl.INSTANCE.selectByCondition(condtion);
    }
}
