<%--
  Created by IntelliJ IDEA.
  User: elvin
  Date: 6/6/19
  Time: 8:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../common/pageHeader.jsp"></jsp:include>

<!-- Page Heading -->
<h1 class="h3 mb-4 text-gray-800"><i class="fas fa-user-circle fa-2x text-black-50"></i> User Profile</h1>

<div class="row">
    <div class="col-xl-6 col-md-6 mb-4">
        <div class="card border-left-primary shadow h-100 py-2">
            <div class="card-body">
                <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                        <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">Registered on</div>
                        <div class="h5 mb-0 font-weight-bold text-gray-800">${user.createdAt}</div>
                    </div>
                    <div class="col-auto">
                        <i class="fas fa-calendar-check fa-2x text-gray-300"></i>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="col-xl-6 col-md-6 mb-4">
        <div class="card border-left-success shadow h-100 py-2">
            <div class="card-body">
                <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                        <div class="text-xs font-weight-bold text-success text-uppercase mb-1">Last updated</div>
                        <div class="h5 mb-0 font-weight-bold text-gray-800">
                            <c:choose>
                                <c:when test="${not empty user.lastModifiedAt}">
                                    ${user.lastModifiedAt}
                                </c:when>
                                <c:otherwise>
                                    Not updated since registered
                                </c:otherwise>
                            </c:choose>

                        </div>
                    </div>
                    <div class="col-auto">
                        <i class="fas fa-calendar-alt fa-2x text-gray-300"></i>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-lg-12">
        <div class="card shadow mb-4">
            <!-- Card Header - Dropdown -->
            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <h6 class="m-0 font-weight-bold text-primary">Your Details</h6>
            </div>
            <!-- Card Body -->
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-striped table-hover table-bordered">
                        <tbody>
                            <tr>
                                <td><h4>Full Name</h4></td>
                                <td><h4>${user.fullName}</h4></td>
                            </tr>
                            <tr>
                                <td><h4>Username</h4></td>
                                <td><h4>${user.username}</h4></td>
                            </tr>
                            <tr>
                                <td><h4>Email</h4></td>
                                <td><h4>${user.email}</h4></td>
                            </tr>
                            <tr>
                                <td><h4>User Type</h4></td>
                                <td><h4>${user.userType.value}</h4></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

    </div>
</div>

<jsp:include page="../common/pageFooter.jsp"></jsp:include>
