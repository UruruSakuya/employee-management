<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/layouts/header.jsp"></jsp:include>
<div id="contents">
	<h2>ログイン</h2>
	<form method="post" action="./login">
		<div>
			<input type="text" name="userId" placeholder="ユーザID" />
		</div>
		<div>
			<input type="password" name="password" placeholder="パスワード" />
				<input type="submit" value="login" />
		</div>
	</form>
</div>

<jsp:include page="/WEB-INF/views/layouts/footer.jsp"></jsp:include>
