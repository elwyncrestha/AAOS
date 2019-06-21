<%--
  Created by IntelliJ IDEA.
  User: elvin
  Date: 5/17/19
  Time: 10:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="auth" class="com.elvin.aaos.web.utility.auth.AuthenticationUtil"/>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="../common/pageHeader.jsp"></jsp:include>
<!-- Page Heading -->
<h1 class="h3 mb-4 text-gray-800"><i class="fas fa-university fa-2x text-black-50"></i> Display Organization Information
</h1>

<!-- User Cards -->
<jsp:include page="organizationCards.jsp"></jsp:include>

<div class="row">
    <div class="col-lg-12">
        <jsp:include page="../common/alertCard.jsp"></jsp:include>
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
                <c:choose>
                    <c:when test="${not empty organization}">
                        <h1>${organization.name}</h1>
                        <h6>Established on ${organization.establishment}</h6>
                        <hr />
                        <p>${organization.description}</p>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${fn:contains(auth.getCurrentUser().authority, 'ROLE_ADMINISTRATOR')}">
                            <a class="btn btn-info" href="${cp}/organization/edit">Add Organization Information</a>
                        </c:if>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

    </div>
</div>

<div class="row">
    <div class="col-lg-12">
        <div class="card shadow mb-4">
            <!-- Card Header - Dropdown -->
            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <h6 class="m-0 font-weight-bold text-primary">Building Information</h6>
            </div>
            <!-- Card Body -->
            <div class="card-body">
                <p>The information of the organization's building is tabulated in <a href="${cp}/building/display"><b>this
                    page.</b></a></p>
            </div>
        </div>

    </div>
</div>

<c:if test="${not empty roomScheduleList}">
<div class="row">
    <div class="col-lg-12">
        <div class="card shadow mb-4">
            <!-- Card Header - Dropdown -->
            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <h6 class="m-0 font-weight-bold text-primary">Room Usage Details</h6>
            </div>
            <!-- Card Body -->
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                        <thead>
                        <tr>
                            <th>Room</th>
                            <th>Batch</th>
                            <th>Name</th>
                            <th>Teacher</th>
                            <th>Duration</th>
                            <th>Day</th>
                        </tr>
                        </thead>
                        <tfoot>
                        <tr>
                            <th>Room</th>
                            <th>Batch</th>
                            <th>Name</th>
                            <th>Teacher</th>
                            <th>Duration</th>
                            <th>Day</th>
                        </tr>
                        </tfoot>
                        <tbody>
                        <c:forEach var="schedule" items="${roomScheduleList}">
                            <tr>
                                <td>${schedule.room.name}</td>
                                <td>${schedule.batch.name}</td>
                                <td>${schedule.name}</td>
                                <td>${schedule.teacherProfile.fullName}</td>
                                <td>${schedule.startTime} - ${schedule.endTime}</td>
                                <td>${schedule.dayOfWeek}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</c:if>

<c:if test="${not empty labRoomScheduleList}">
<div class="row">
    <div class="col-lg-12">
        <div class="card shadow mb-4">
            <!-- Card Header - Dropdown -->
            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <h6 class="m-0 font-weight-bold text-primary">Lab Room Usage Details</h6>
            </div>
            <!-- Card Body -->
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered" width="100%" cellspacing="0">
                        <thead>
                        <tr>
                            <th>Room</th>
                            <th>Batch</th>
                            <th>Name</th>
                            <th>Teacher</th>
                            <th>Duration</th>
                            <th>Day</th>
                        </tr>
                        </thead>
                        <tfoot>
                        <tr>
                            <th>Room</th>
                            <th>Batch</th>
                            <th>Name</th>
                            <th>Teacher</th>
                            <th>Duration</th>
                            <th>Day</th>
                        </tr>
                        </tfoot>
                        <tbody>
                        <c:forEach var="schedule" items="${labRoomScheduleList}">
                            <tr>
                                <td>${schedule.room.name}</td>
                                <td>${schedule.batch.name}</td>
                                <td>${schedule.name}</td>
                                <td>${schedule.teacherProfile.fullName}</td>
                                <td>${schedule.startTime} - ${schedule.endTime}</td>
                                <td>${schedule.dayOfWeek}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</c:if>

<jsp:include page="../common/pageFooter.jsp"></jsp:include>