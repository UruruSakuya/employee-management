<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jp.ne.naokiur.em.common.Messages"%>
<div class="messages">
    <ul type="disc">
    <%
        List<Messages> messageList = (List<Messages>) request.getAttribute("messageList");

        if (messageList != null && !messageList.isEmpty()) {

            for (Messages message : messageList) {
                request.setAttribute("message", message.getMessage());
                request.setAttribute("messageLevel", message.getMessageResource().getLevel());
    %>
    <li class="${requestScope.messageLevel}">${requestScope.message}</li>
    <%
        }
        }
    %>
    </ul>
</div>