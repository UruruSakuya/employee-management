<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/layouts/header.jsp"></jsp:include>
<div id="contents">
    <h2>社員登録確認</h2>
    <form method="post" action="./complete">
        <div class="register-items">
            <div class="register-item">
                <label for="user_id">ユーザID</label><span id="user_id"><%=session.getAttribute("user_id")%></span>
            </div>
            <div class="register-item">
                <label for="name">名前</label><span id="first_name"><%=session.getAttribute("first_name")%></span>
                <span id="last_name"><%=request.getParameter("last_name")%></span>
            </div>
            <div class="register-item">
                <label for="post_code">部門</label><span id="post_name"><%=session.getAttribute("post_name")%></span>
            </div>
            <div class="register-item">
                <label for="age">年齢</label><span id="age"><%=session.getAttribute("age")%></span>
            </div>
            <div class="register-item">
                <label for="enter_date">入社年月日</label> <span
                    id="enter_date"><%=session.getAttribute("enter_date")%></span>
            </div>
            <input type="submit" value="登録" />
        </div>
    </form>
</div>
<jsp:include page="/WEB-INF/views/layouts/footer.jsp"></jsp:include>
