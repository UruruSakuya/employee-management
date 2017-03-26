<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<div class="message">
    <%
        List<String> messageList = (List<String>) request.getAttribute("messageList");

        if (messageList != null && !messageList.isEmpty()) {

            for (String message : messageList) {
                request.setAttribute("message", message);
    %>
    <span>${requestScope.message}</span>
    <%
        }
        }
    %>
</div>