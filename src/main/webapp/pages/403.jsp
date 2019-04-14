<%--
  Created by IntelliJ IDEA.
  User: elvin
  Date: 4/14/19
  Time: 6:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="common/pageHeader.jsp"></jsp:include>
<!-- 404 Error Text -->
<div class="text-center">
    <div class="error mx-auto" data-text="403">403</div>
    <p class="lead text-gray-800 mb-5">Access Forbidden</p>
    <p class="text-gray-500 mb-0">It looks like you don't have permission to access the URL...</p>
    <a href="${pageContext.request.contextPath}/dashboard">&larr; Back to Dashboard</a>
</div>
<jsp:include page="common/pageFooter.jsp"></jsp:include>