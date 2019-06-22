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
<c:if test="${!fn:contains(auth.getCurrentUser().authority, 'ROLE_ADMINISTRATOR')}">
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Welcome</h6>
        </div>
        <div class="card-body">
            <div class="text-center">
                <img class="img-fluid px-3 px-sm-4 mt-3 mb-4" style="width: 25rem;" src="${cp}/resources/img/common_dashboard.svg" alt="">
            </div>
            <p>This web application is developed to give you some important information about our organization.
                You can get started by reading the <a href="${cp}/help">User Manual</a>,
                or simply start using the system with the help of navigations in the sidebar.
            </p>
        </div>
    </div>

</c:if>

<jsp:include page="../common/pageFooter.jsp"></jsp:include>