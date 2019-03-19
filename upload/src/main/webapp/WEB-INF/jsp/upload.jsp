<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 19.03.2019
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<h1>Users</h1>
<table border="1" cellpadding="8" cellspacing="0">
    <tr>
        <th>Name</th>
        <th>Email</th>
        <th>Flag</th>
    </tr>
    <c:forEach var="element" items="${users}">
        <jsp:useBean id="element" type="ru.javaops.masterjava.xml.schema.User"/>
        <tr>
            <td>
                    ${element.value}
            </td>
            <td>
                    ${element.email}
            </td>
            <td>
                    ${element.flag}
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
