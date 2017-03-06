<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jp.ne.naokiur.em.dto.PostDto"%>
<%@page import="jp.ne.naokiur.em.dto.DisplayEmployeeDto"%>
<jsp:include page="/WEB-INF/views/layouts/header.jsp"></jsp:include>
<div id="contents">
    <h2>社員検索</h2>
    <form method="post" action="./init">
        <div id="conditions">
            <table>
                <tr>
                    <th>ユーザID</th>
                    <td><input type="text" id="user_id" name="user_id" /></td>
                </tr>
                <tr>
                    <th>名前</th>
                    <td><input type="text" id="fitst_name" name="first_name" /><input
                        type="text" id="last_name" name="last_name" /></td>
                </tr>
                <tr>
                    <th>部門</th>
                    <td><label for="post_code">部門</label> <select id="post_code"
                        name="post_code">
                            <%
                                List<PostDto> postList = (List<PostDto>) session.getAttribute("postList");
                                for (PostDto dto : postList) {
                                    request.setAttribute("post", dto);
                            %>
                            <option value="${requestScope.post.postCode}">${requestScope.post.postName}</option>
                            <%
                                }
                            %>
                    </select></td>
                    <th>年齢</th>
                    <td><input type="number" id="from_age" name="from_age" />〜<input type="number"
                        id="to_age" name="to_age" /></td>
                </tr>
                <tr>
                    <th><label for="enter_date">入社年月日</label></th>
                    <td><input type="date" id="from_enter_date" name="from_enter_date" />〜<input
                        type="date" id="to_enter_date" name="to_enter_date" /></td>
                </tr>
            </table>
        </div>
        <input type="submit" value="検索" />

        <div id="results">
            <table>
                <tr>
                    <th>ユーザID</th>
                    <th>名前</th>
                    <th>部門</th>
                    <th>年齢</th>
                    <th>入社年月日</th>
                </tr>

                <%
                    List<DisplayEmployeeDto> resultList = (List<DisplayEmployeeDto>) session.getAttribute("resultList");

                    if (resultList != null && !resultList.isEmpty()) {
                        for (DisplayEmployeeDto dto : resultList) {
                            request.setAttribute("result", dto);
                %>
                <tr>
                    <td>${requestScope.result.userId}</td>
                    <td>${requestScope.result.firstName}${requestScope.result.lastName}</td>
                    <td>${requestScope.result.postName}</td>
                    <td>${requestScope.result.age}</td>
                    <td>${requestScope.result.enterDate}</td>
                </tr>

                <%
                    }
                %>

                <%
                    }
                %>

            </table>
        </div>
    </form>
</div>
<jsp:include page="/WEB-INF/views/layouts/footer.jsp"></jsp:include>
