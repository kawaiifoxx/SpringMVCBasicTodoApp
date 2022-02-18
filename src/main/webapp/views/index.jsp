<%@ page import="javax.swing.*" %><%--
  Created by IntelliJ IDEA.
  User: Shreyansh
  Date: 17-02-2022
  Time: 21:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>My Simple Todo App.</title>
</head>
<body>
<h1>My Todos</h1>
<form:form action="${contextPath}/todo" method="post" modelAttribute="newTodo">
    <form:label path="description">Todo Description: </form:label><form:input path="description"/>
    <form:button>Add Todo</form:button>
</form:form>
<hr>
<table>
    <thead>
    <tr>
        <th>Description</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${todos}" var="todo">
        <tr>
            <td>
                <c:if test="${todo.completed}">
                    <span style="text-decoration: line-through;">${todo.description}</span>
                </c:if>
                <c:if test="${!todo.completed}">
                    ${todo.description}
                </c:if>
            </td>
            <td>
                <a href="${contextPath}/todo/${todo.id}?action=toggleCompletionState">Toggle</a> |
                <a href="${contextPath}/todo/${todo.id}?action=delete">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
