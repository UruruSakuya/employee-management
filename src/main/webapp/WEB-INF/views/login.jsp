<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/layouts/header.jsp"></jsp:include>
<form method="post" action="./login">
	<input type="text" name="userId" placeholder="ユーザID" />
	<input type="password" name="password" placeholder="パスワード" />
	<input type="submit" value="login">
</form>

<jsp:include page="/WEB-INF/views/layouts/footer.jsp"></jsp:include>
