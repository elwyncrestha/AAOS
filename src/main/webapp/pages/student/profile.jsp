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
<h1 class="h3 mb-4 text-gray-800"><i class="fas fa-user-graduate fa-2x text-black-50"></i> Student Profile</h1>

<jsp:include page="studentCards.jsp"></jsp:include>

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
                <c:choose>
                    <c:when test="${empty student}">
                        <a class="btn btn-info" href="${cp}/student/edit">Add Your Profile</a>
                    </c:when>
                    <c:otherwise>
                        <div class="table-responsive">
                            <table class="table table-striped table-hover table-bordered">
                                <tbody>
                                <tr>
                                    <td><h4>Batch</h4></td>
                                    <td><h4>${student.batch.name}</h4></td>
                                </tr>
                                <tr>
                                    <td><h4>Full Name</h4></td>
                                    <td><h4>${student.fullName}</h4></td>
                                </tr>
                                <tr>
                                    <td><h4>Email</h4></td>
                                    <td><h4>${student.email}</h4></td>
                                </tr>
                                <tr>
                                    <td><h4>Date of Birth</h4></td>
                                    <td><h4>${student.dob}</h4></td>
                                </tr>
                                <tr>
                                    <td><h4>Gender</h4></td>
                                    <td><h4>${student.gender.value}</h4></td>
                                </tr>
                                <tr>
                                    <td><h4>Address</h4></td>
                                    <td><h4>${student.address}</h4></td>
                                </tr>
                                <tr>
                                    <td><h4>Telephone Number</h4></td>
                                    <td><h4>${student.telephoneNumber}</h4></td>
                                </tr>
                                <tr>
                                    <td><h4>Mobile Number</h4></td>
                                    <td><h4>${student.mobileNumber}</h4></td>
                                </tr>
                                <tr>
                                    <td><h4>Parent Mobile Number</h4></td>
                                    <td><h4>${student.parentMobileNumber}</h4></td>
                                </tr>
                                <tr>
                                    <td><h4>Parent Email</h4></td>
                                    <td><h4>${student.parentEmail}</h4></td>
                                </tr>
                                <tr>
                                    <td><h4>Mother Name</h4></td>
                                    <td><h4>${student.motherName}</h4></td>
                                </tr>
                                <tr>
                                    <td><h4>Mother Contact Number</h4></td>
                                    <td><h4>${student.motherContactNumber}</h4></td>
                                </tr>
                                <tr>
                                    <td><h4>Father Name</h4></td>
                                    <td><h4>${student.fatherName}</h4></td>
                                </tr>
                                <tr>
                                    <td><h4>Father Contact Number</h4></td>
                                    <td><h4>${student.fatherContactNumber}</h4></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
            <c:if test="${not empty student}">
                <!-- Card Footer -->
                <div class="card-footer text-center">
                    <a class="btn btn-info btn-lg" href="${cp}/student/edit">Edit Your Profile</a>
                </div>
            </c:if>
        </div>

    </div>
</div>

<jsp:include page="../common/pageFooter.jsp"></jsp:include>
