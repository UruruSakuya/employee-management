<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/layouts/header.jsp"></jsp:include>
<div id="contents">
    <h2>社員登録完了</h2>
    <jsp:include page="/WEB-INF/views/layouts/message.jsp"></jsp:include>
    <p>登録完了しました。</p>
    <a href="${pageContext.request.contextPath}/user/menu">メニューに戻る</a>
</div>
<jsp:include page="/WEB-INF/views/layouts/footer.jsp"></jsp:include>
