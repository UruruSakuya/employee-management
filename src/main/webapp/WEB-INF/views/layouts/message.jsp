<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jp.ne.naokiur.em.code.Messages"%>
<div class="message">
    <%
        List<Messages> messageList = (List<Messages>) request.getAttribute("messageList");

        if (messageList != null && !messageList.isEmpty()) {

            for (Messages message : messageList) {
                request.setAttribute("message", message.load());
                request.setAttribute("messageLevel", message.getLevel());
    %>
    <span class="${requestScope.messageLevel}">${requestScope.message}</span>
    <%
        }
        }
    %>
</div>