<%--
  Created by IntelliJ IDEA.
  User: elvin
  Date: 6/6/19
  Time: 8:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../common/pageHeader.jsp"></jsp:include>

<!-- Page Heading -->
<h1 class="h3 mb-4 text-gray-800"><i class="fas fa-user-tie fa-2x text-black-50"></i> Teacher Profile</h1>

<jsp:include page="teacherCards.jsp"></jsp:include>

<div class="row">
    <div class="col-lg-12">
        <div class="card shadow mb-4">
            <!-- Card Header - Dropdown -->
            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <h6 class="m-0 font-weight-bold text-primary">Your Profile</h6>
            </div>
            <!-- Card Body -->
            <div class="card-body">
                <jsp:include page="../common/alertCard.jsp"></jsp:include>

                <div class="text-center">
                    <img class="img-fluid px-3 px-sm-4 mt-3 mb-4" style="width: 15rem;" src="${cp}/resources/img/profile.svg" alt="">
                </div>

                <c:choose>
                    <c:when test="${empty teacher}">
                        <a class="btn btn-info" href="${cp}/teacher/edit">Add Your Profile</a>
                    </c:when>
                    <c:otherwise>
                        <div class="table-responsive">
                            <table class="table table-striped table-hover table-bordered">
                                <tbody>
                                <tr>
                                    <td><h4>Module</h4></td>
                                    <td>
                                        <h4>
                                            <c:choose>
                                                <c:when test="${not empty teacher.module.name}">${teacher.module.name}</c:when>
                                                <c:otherwise>Unassigned</c:otherwise>
                                            </c:choose>
                                        </h4>
                                    </td>
                                </tr>
                                <tr>
                                    <td><h4>Full Name</h4></td>
                                    <td><h4>${teacher.fullName}</h4></td>
                                </tr>
                                <tr>
                                    <td><h4>Email</h4></td>
                                    <td><h4>${teacher.email}</h4></td>
                                </tr>
                                <tr>
                                    <td><h4>Date of Birth</h4></td>
                                    <td><h4>${teacher.dob}</h4></td>
                                </tr>
                                <tr>
                                    <td><h4>Gender</h4></td>
                                    <td><h4>${teacher.gender.value}</h4></td>
                                </tr>
                                <tr>
                                    <td><h4>Address</h4></td>
                                    <td><h4>${teacher.address}</h4></td>
                                </tr>
                                <tr>
                                    <td><h4>Mobile Number</h4></td>
                                    <td><h4>${teacher.mobileNumber}</h4></td>
                                </tr>
                                <tr>
                                    <td><h4>Education</h4></td>
                                    <td><h4>${teacher.education}</h4></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
            <c:if test="${not empty teacher}">
                <!-- Card Footer -->
                <div class="card-footer text-center">
                    <a class="btn btn-info btn-lg" href="${cp}/teacher/edit">Edit Your Profile</a>
                </div>
            </c:if>
        </div>

    </div>
</div>

<jsp:include page="../common/pageFooter.jsp"></jsp:include>
