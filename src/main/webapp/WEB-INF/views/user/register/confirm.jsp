<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/layouts/header.jsp"></jsp:include>
<div id="contents">
	<h2>社員登録確認</h2>
	<form method="post" action="./register/confirm">
		<div class="register-items">
			<div class="register-item">
				<label for="user_id">ユーザID</label><span id="user_id"><%=request.getParameter("user_id")%></span>
			</div>
			<div class="register-item">
				<label for="name">名前</label><span id="first_name"><%=request.getParameter("first_name")%></span>
				<span id="last_name"><%=request.getParameter("last_name")%></span>
			</div>
			<div class="register-item">
				<label for="post_code">部門</label><span id="post_code"><%=request.getParameter("post_code")%></span>
			</div>
			<div class="register-item">
				<label for="age">年齢</label><span id="age"><%=request.getParameter("age") %></span>
			</div>
			<div class="register-item">
				<label for="entry_date">入社年月日</label>
				<span id="entry_date"><%=request.getParameter("entry_date") %></span>
			</div>
			<input type="submit" value="登録" />
		</div>
	</form>
</div>
<jsp:include page="/WEB-INF/views/layouts/footer.jsp"></jsp:include>
