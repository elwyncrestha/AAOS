<%--
  Created by IntelliJ IDEA.
  User: elvin
  Date: 3/30/19
  Time: 9:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="post" modelAttribute="user" role="form">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="text" placeholder="Username" name="username" autofocus>
    <input type="password" placeholder="Password" name="password">

    <button type="submit">SIGN IN</button>
    </div>
</form>
</body>
</html>