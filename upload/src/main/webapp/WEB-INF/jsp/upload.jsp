<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 19.03.2019
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<% pageContext.setAttribute("users",ru.javaops.masterjava.web.PayloadForm.users); %>
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
    <logic:iterate id="user"  name="users"  >
        <tr>
            <td>
                <bean:write name="user" property="value"/>
            </td>
            <td>
                <bean:write name="user" property="email"/>
            </td>
            <td>
                <bean:write name="user" property="flag"/>
            </td>
        </tr>
    </logic:iterate>
</table>
</body>
</html>
