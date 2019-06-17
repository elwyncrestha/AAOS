<%--
  Created by IntelliJ IDEA.
  User: elvin
  Date: 5/11/19
  Time: 4:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.elvin.aaos.core.model.enums.UserType" %>
<%@page import="com.elvin.aaos.core.model.enums.Status" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="userTypes" value="${UserType.values()}"></c:set>
<c:set var="statuses" value="${Status.values()}"></c:set>;

<jsp:include page="../common/pageHeader.jsp"></jsp:include>

<!-- Page Heading -->
<h1 class="h3 mb-4 text-gray-800"><i class="fas fa-user-plus fa-2x text-black-50"></i> Edit User</h1>

<!-- User Cards -->
<jsp:include page="userCards.jsp"></jsp:include>

<!-- Form -->
<div class="row">
    <div class="col-lg-12">
        <div class="card shadow mb-4">
            <!-- Card Header - Dropdown -->
            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <h6 class="m-0 font-weight-bold text-primary">Edit User Form</h6>
            </div>
            <!-- Card Body -->
            <div class="card-body">
                <div class="p-5">
                    <form class="user" method="post" action="${pageContext.request.contextPath}/user/edit">

                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                        <input type="hidden" name="id" value="${user.id}">

                        <div class="form-group">
                            <input type="text" class="form-control form-control-user" id="fullName"
                                   placeholder="Full Name" name="fullName" required minlength="5" maxlength="100"
                                   value="${user.fullName}">
                            <p class="para-error text-right">${error.fullName}</p>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control form-control-user" id="username"
                                   placeholder="Username" name="username" required minlength="5" maxlength="50"
                                   value="${user.username}">
                            <p class="para-error text-right">${error.username}</p>
                        </div>
                        <div class="form-group">
                            <input type="email" class="form-control form-control-user" id="email"
                                   placeholder="Email Address" name="email" required value="${user.email}">
                            <p class="para-error text-right">${error.email}</p>
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control form-control-user" id="password"
                                   placeholder="Password" name="password" required minlength="8" maxlength="30"
                                   value="${user.password}">
                            <p class="para-error text-right">${error.password}</p>
                        </div>
                        <div class="form-group">
                            <select id="userType" name="userType" class="form-control" required>
                                <c:forEach items="${userTypes}" var="userType">
                                    <c:if test="${userType.value ne 'Super Administrator'}">
                                        <option value="${userType}" <c:if test="${userType eq user.userType}">selected</c:if>>${userType.value}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                            <p class="para-error text-right">${error.userType}</p>
                        </div>
                        <div class="form-group">
                            <select id="userStatus" name="status" class="form-control" required>
                                <c:forEach items="${statuses}" var="status">
                                    <option value="${status}"
                                            <c:if test="${status eq user.status}">selected</c:if>>${status.value}</option>
                                </c:forEach>
                            </select>
                            <p class="para-error text-right">${error.userType}</p>
                        </div>
                        <button type="submit" class="btn btn-primary btn-user btn-block">Edit</button>
                    </form>
                </div>
            </div>
        </div>

    </div>
</div>

<jsp:include page="../common/pageFooter.jsp"></jsp:include>
