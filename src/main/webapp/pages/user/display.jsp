<%--
  Created by IntelliJ IDEA.
  User: elvin
  Date: 4/14/19
  Time: 11:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../common/pageHeader.jsp"></jsp:include>
<!-- Page Heading -->
<h1 class="h3 mb-4 text-gray-800"><i class="fas fa-users fa-2x text-black-50"></i> Display Users</h1>

<!-- User Cards -->
<jsp:include page="userCards.jsp"></jsp:include>

<!-- Form -->
<div class="row">
    <div class="col-lg-12">
        <div class="card shadow mb-4">
            <!-- Card Header - Dropdown -->
            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <h6 class="m-0 font-weight-bold text-primary">User Lists</h6>
            </div>
            <!-- Card Body -->
            <div class="card-body">
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
                <div class="table-responsive">
                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Username</th>
                            <th>Email</th>
                            <th>User Type</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tfoot>
                        <tr>
                            <th>Name</th>
                            <th>Username</th>
                            <th>Email</th>
                            <th>User Type</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>
                        </tfoot>
                        <tbody>
                        <c:forEach var="user" items="${userList}">
                            <tr>
                                <td>${user.fullName}</td>
                                <td>${user.username}</td>
                                <td>${user.email}</td>
                                <td>${user.userType}</td>
                                <td>${user.status.value}</td>
                                <td><a class="btn btn-sm btn-info text-white"
                                       href="${pageContext.request.contextPath}/user/edit/${user.id}"><i
                                        class="fas fa-fw fa-user-edit"></i></a>
                                    <a class="btn btn-sm btn-danger text-white"
                                       href="${pageContext.request.contextPath}/user/delete/${user.id}"
                                       onclick="return confirm('Are you sure you want to delete this user?')"><i
                                            class="fas fa-fw fa-user-times"></i></a></td>
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