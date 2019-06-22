<%--
  Created by IntelliJ IDEA.
  User: elvin
  Date: 3/30/19
  Time: 9:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="auth" class="com.elvin.aaos.web.utility.auth.AuthenticationUtil"/>

<jsp:include page="../common/pageHeader.jsp"></jsp:include>

<!-- Page Heading -->
<h1 class="h3 mb-4 text-gray-800"><i class="fas fa-tachometer-alt fa-2x text-black-50"></i> Dashboard</h1>

<c:if test="${fn:contains(auth.getCurrentUser().authority, 'ROLE_ADMINISTRATOR')}">
    <jsp:include page="graphs/admin.jsp"/>
</c:if>

<jsp:include page="../common/pageFooter.jsp"></jsp:include>