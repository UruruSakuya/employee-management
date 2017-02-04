<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/layouts/header.jsp"></jsp:include>
<div id="contents">
	<h2>社員登録</h2>
	<form method="post" action="./confirm">
		<div class="register-items">
			<div class="register-item">
				<label for="user_id">ユーザID</label><input type="text" id="user_id" name="user_id" />
			</div>
			<div class="register-item">
				<label for="name">名前</label>
				<input type="text" id="first_name" name="first_name" />
				<input type="text" id="last_name" name="last_name"/>
			</div>
			<div class="register-item">
				<label for="post_code">部門</label><select id="post_code" name="post_code"></select>
			</div>
			<div class="register-item">
				<label for="age">年齢</label><input type="number" id="age" name="age" min="0" />
			</div>
			<div class="register-item">
				<label for="entry_date">入社年月日</label><input type="date" id="entry_date" name="entry_date"/>
			</div>
			<input type="submit" value="登録確認" />
		</div>
	</form>
</div>
<jsp:include page="/WEB-INF/views/layouts/footer.jsp"></jsp:include>
