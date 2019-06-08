<%--
  Created by IntelliJ IDEA.
  User: elvin
  Date: 6/7/19
  Time: 8:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.elvin.aaos.core.model.enums.Gender" %>
<c:set var="genders" value="${Gender.values()}"></c:set>

<jsp:include page="../common/pageHeader.jsp"></jsp:include>

<!-- Page Heading -->
<h1 class="h3 mb-4 text-gray-800"><i class="fas fa-user-graduate fa-2x text-black-50"></i> Student Profile</h1>

<jsp:include page="studentCards.jsp"></jsp:include>

<!-- Form -->
<div class="row">
    <div class="col-lg-12">
        <div class="card shadow mb-4">
            <!-- Card Header - Dropdown -->
            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <h6 class="m-0 font-weight-bold text-primary">Add Profile Details</h6>
            </div>
            <!-- Card Body -->
            <div class="card-body">
                <div class="p-5">
                    <form method="post" action="${pageContext.request.contextPath}/student/edit">

                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                        <input type="hidden" name="id" value="${student.id}">
                        <input type="hidden" name="user.id" value="${student.user.id}">
                        <input type="hidden" name="fullName" value="${student.fullName}">
                        <input type="hidden" name="email" value="${student.email}">
                        <input type="hidden" name="version" value="${student.version}">

                        <div class="form-group">
                            <label for="dob">Date of Birth</label>
                            <input type="date" class="form-control" id="dob" name="dob"
                                   pattern="yyyy-MM-dd" required value="${student.dob}">
                            <p class="para-error text-right">${error.dob}</p>
                        </div>
                        <div class="form-group">
                            <label for="gender">Gender</label>
                            <div id="gender">
                                <c:forEach var="gender" items="${genders}">
                                    <div class="form-check-inline">
                                        <label class="form-check-label">
                                            <input type="radio" class="form-check-input" name="gender" value="${gender}" required <c:if test="${student.gender eq gender}">checked</c:if>>${gender.value}
                                        </label>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="address"
                                   placeholder="Address" name="address" required value="${student.address}">
                            <p class="para-error text-right">${error.address}</p>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="telephoneNumber"
                                   placeholder="Telephone Number" name="telephoneNumber" required value="${student.telephoneNumber}">
                            <p class="para-error text-right">${error.telephoneNumber}</p>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="mobileNumber"
                                   placeholder="Mobile Number" name="mobileNumber" required value="${student.mobileNumber}">
                            <p class="para-error text-right">${error.mobileNumber}</p>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="parentMobileNumber"
                                   placeholder="Parent Mobile Number" name="parentMobileNumber" required value="${student.parentMobileNumber}">
                            <p class="para-error text-right">${error.parentMobileNumber}</p>
                        </div>
                        <div class="form-group">
                            <input type="email" class="form-control" id="parentEmail"
                                   placeholder="Parent Email" name="parentEmail" required value="${student.parentEmail}">
                            <p class="para-error text-right">${error.parentEmail}</p>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="motherName"
                                   placeholder="Mother Name" name="motherName" required value="${student.motherName}">
                            <p class="para-error text-right">${error.motherName}</p>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="motherContactNumber"
                                   placeholder="Mother Contact Number" name="motherContactNumber" required value="${student.motherContactNumber}">
                            <p class="para-error text-right">${error.motherContactNumber}</p>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="fatherName"
                                   placeholder="Father Name" name="fatherName" required value="${student.fatherName}">
                            <p class="para-error text-right">${error.fatherName}</p>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="fatherContactNumber"
                                   placeholder="Father Contact Number" name="fatherContactNumber" required value="${student.fatherContactNumber}">
                            <p class="para-error text-right">${error.fatherContactNumber}</p>
                        </div>
                        <button type="submit" class="btn btn-primary btn-user btn-block">Add</button>
                    </form>
                </div>
            </div>
        </div>

    </div>
</div>

<jsp:include page="../common/pageFooter.jsp"></jsp:include>
