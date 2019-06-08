<%--
  Created by IntelliJ IDEA.
  User: elvin
  Date: 6/8/19
  Time: 3:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty flashMessage}">
    <div class="alert alert-success alert-dismissable">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        <strong>${flashMessage}</strong>
    </div>
</c:if>
<c:if test="${not empty flashErrorMessage}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        <strong>${flashErrorMessage}</strong>
    </div>
</c:if>
