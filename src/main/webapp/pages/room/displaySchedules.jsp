<%--
  Created by IntelliJ IDEA.
  User: elvin
  Date: 6/15/19
  Time: 3:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="auth" class="com.elvin.aaos.web.utility.auth.AuthenticationUtil"/>

<jsp:include page="../common/pageHeader.jsp"></jsp:include>
<!-- Page Heading -->
<h1 class="h3 mb-4 text-gray-800"><i class="fas fa-calendar fa-2x text-black-50"></i> Display Room Schedules</h1>

<!-- User Cards -->
<jsp:include page="roomCards.jsp"></jsp:include>

<!-- Form -->
<div class="row">
    <div class="col-lg-12">
        <div class="card shadow mb-4">
            <!-- Card Header - Dropdown -->
            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <h6 class="m-0 font-weight-bold text-primary">Room Schedule Lists</h6>
            </div>
            <!-- Card Body -->
            <div class="card-body">
                <jsp:include page="../common/alertCard.jsp"></jsp:include>
                <div class="table-responsive">
                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                        <thead>
                        <tr>
                            <th>Batch</th>
                            <th>Name</th>
                            <th>Room</th>
                            <th>Teacher</th>
                            <th>Duration</th>
                            <th>Day</th>
                            <c:if test="${fn:contains(auth.getCurrentUser().authority, 'ROLE_ADMINISTRATOR')}">
                                <th>Action</th>
                            </c:if>
                        </tr>
                        </thead>
                        <tfoot>
                        <tr>
                            <th>Batch</th>
                            <th>Name</th>
                            <th>Room</th>
                            <th>Teacher</th>
                            <th>Duration</th>
                            <th>Day</th>
                            <c:if test="${fn:contains(auth.getCurrentUser().authority, 'ROLE_ADMINISTRATOR')}">
                                <th>Action</th>
                            </c:if>
                        </tr>
                        </tfoot>
                        <tbody>
                        <c:forEach var="schedule" items="${roomScheduleList}">
                            <tr>
                                <td>${schedule.batch.name}</td>
                                <td>${schedule.name}</td>
                                <td>${schedule.room.name}</td>
                                <td>${schedule.teacherProfile.fullName}</td>
                                <td>${schedule.startTime} - ${schedule.endTime}</td>
                                <td>${schedule.dayOfWeek}</td>
                                <c:if test="${fn:contains(auth.getCurrentUser().authority, 'ROLE_ADMINISTRATOR')}">
                                    <td>
                                        <a class="btn btn-sm btn-info text-white"
                                           href="${pageContext.request.contextPath}/room/schedule/edit/${schedule.id}"><i
                                                class="fas fa-fw fa-edit"></i></a>
                                        <a class="btn btn-sm btn-danger text-white"
                                           href="${pageContext.request.contextPath}/room/schedule/delete/${schedule.id}"
                                           onclick="return confirm('Are you sure you want to delete this room schedule?')"><i
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
