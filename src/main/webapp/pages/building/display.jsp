<%--
  Created by IntelliJ IDEA.
  User: elvin
  Date: 5/20/19
  Time: 11:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="auth" class="com.elvin.aaos.web.utility.auth.AuthenticationUtil"/>

<jsp:include page="../common/pageHeader.jsp"></jsp:include>
<!-- Page Heading -->
<h1 class="h3 mb-4 text-gray-800"><i class="fas fa-building fa-2x text-black-50"></i> Display Buildings</h1>

<!-- User Cards -->
<jsp:include page="buildingCards.jsp"></jsp:include>

<!-- Form -->
<div class="row">
    <div class="col-lg-12">
        <div class="card shadow mb-4">
            <!-- Card Header - Dropdown -->
            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <h6 class="m-0 font-weight-bold text-primary">Building Lists</h6>
            </div>
            <!-- Card Body -->
            <div class="card-body">
                <jsp:include page="../common/alertCard.jsp"></jsp:include>
                <div class="table-responsive">
                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Status</th>
                            <c:if test="${fn:contains(auth.getCurrentUser().authority, 'ROLE_ADMINISTRATOR')}">
                                <th>Action</th>
                            </c:if>
                        </tr>
                        </thead>
                        <tfoot>
                        <tr>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Status</th>
                            <c:if test="${fn:contains(auth.getCurrentUser().authority, 'ROLE_ADMINISTRATOR')}">
                                <th>Action</th>
                            </c:if>
                        </tr>
                        </tfoot>
                        <tbody>
                        <c:forEach var="building" items="${buildingList}">
                            <tr>
                                <td>${building.name}</td>
                                <td>${fn:substring(building.description, 0, 60)} <a
                                        href="${pageContext.request.contextPath}/building/display/${building.id}">readmore...</a>
                                </td>
                                <td>${building.status.value}</td>
                                <c:if test="${fn:contains(auth.getCurrentUser().authority, 'ROLE_ADMINISTRATOR')}">
                                    <td>
                                        <a class="btn btn-sm btn-info text-white"
                                           href="${pageContext.request.contextPath}/building/edit/${building.id}"><i
                                                class="fas fa-fw fa-edit"></i></a>
                                        <a class="btn btn-sm btn-danger text-white"
                                           href="${pageContext.request.contextPath}/building/delete/${building.id}"
                                           onclick="return confirm('Are you sure you want to delete this building?')"><i
                                                class="fas fa-fw fa-trash"></i></a>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../common/pageFooter.jsp"></jsp:include>
