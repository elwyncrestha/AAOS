<%--
  Created by IntelliJ IDEA.
  User: elvin
  Date: 5/17/19
  Time: 10:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../common/pageHeader.jsp"></jsp:include>
<!-- Page Heading -->
<h1 class="h3 mb-4 text-gray-800"><i class="fas fa-university fa-2x text-black-50"></i> Display Organization Information
</h1>

<!-- User Cards -->
<jsp:include page="organizationCards.jsp"></jsp:include>

<div class="row">
    <div class="col-lg-12">
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
    </div>
</div>

<div class="row">
    <div class="col-lg-12">
        <div class="card shadow mb-4">
            <!-- Card Header - Dropdown -->
            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <h6 class="m-0 font-weight-bold text-primary">About Organization</h6>
            </div>
            <!-- Card Body -->
            <div class="card-body">


            </div>
        </div>

    </div>
</div>

<div class="row">
    <div class="col-lg-12">
        <div class="card shadow mb-4">
            <!-- Card Header - Dropdown -->
            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <h6 class="m-0 font-weight-bold text-primary">Room Usage Details</h6>
            </div>
            <!-- Card Body -->
            <div class="card-body">


            </div>
        </div>

    </div>
</div>

<div class="row">
    <div class="col-lg-12">
        <div class="card shadow mb-4">
            <!-- Card Header - Dropdown -->
            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <h6 class="m-0 font-weight-bold text-primary">Lab Room Usage Details</h6>
            </div>
            <!-- Card Body -->
            <div class="card-body">


            </div>
        </div>

    </div>
</div>

<div class="row">
    <div class="col-lg-12">
        <div class="card shadow mb-4">
            <!-- Card Header - Dropdown -->
            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <h6 class="m-0 font-weight-bold text-primary">Teacher Schedule</h6>
            </div>
            <!-- Card Body -->
            <div class="card-body">


            </div>
        </div>

    </div>
</div>

<jsp:include page="../common/pageFooter.jsp"></jsp:include>