<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/WEB-INF/views/layouts/header.jsp"></jsp:include>
<body>
    <form method="post" action="./LoginController">
        <input type="text" name="user" />
        <input type="password" name="password" />
        <input type="submit" value="login">
    </form>

    <jsp:include page="/WEB-INF/views/layouts/footer.jsp"></jsp:include>
</body>
</html>