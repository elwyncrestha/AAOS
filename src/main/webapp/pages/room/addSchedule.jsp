<%--
  Created by IntelliJ IDEA.
  User: elvin
  Date: 6/15/19
  Time: 10:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../common/pageHeader.jsp"></jsp:include>

<!-- Page Heading -->
<h1 class="h3 mb-4 text-gray-800"><i class="fas fa-calendar-day fa-2x text-black-50"></i> Add Room Schedule</h1>

<!-- User Cards -->
<jsp:include page="roomCards.jsp"></jsp:include>

<!-- Form -->
<div class="row">
    <div class="col-lg-12">
        <div class="card shadow mb-4">
            <!-- Card Header - Dropdown -->
            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <h6 class="m-0 font-weight-bold text-primary">Add Room Schedule Form</h6>
            </div>
            <!-- Card Body -->
            <div class="card-body">
                <div class="p-5">
                    <form method="post" action="${pageContext.request.contextPath}/room/schedule/add">

                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                        <div class="form-group">
                            <select id="dayOfWeek" name="dayOfWeek" class="form-control" required>
                                <option selected disabled>Select Day of Week</option>
                                <option value="MONDAY" <c:if test="${roomSchedule.dayOfWeek eq 'MONDAY'}">selected</c:if>>MONDAY</option>
                                <option value="TUESDAY" <c:if test="${roomSchedule.dayOfWeek eq 'TUESDAY'}">selected</c:if>>TUESDAY</option>
                                <option value="WEDNESDAY" <c:if test="${roomSchedule.dayOfWeek eq 'WEDNESDAY'}">selected</c:if>>WEDNESDAY</option>
                                <option value="THURSDAY" <c:if test="${roomSchedule.dayOfWeek eq 'THURSDAY'}">selected</c:if>>THURSDAY</option>
                                <option value="FRIDAY" <c:if test="${roomSchedule.dayOfWeek eq 'FRIDAY'}">selected</c:if>>FRIDAY</option>
                                <option value="SATURDAY" <c:if test="${roomSchedule.dayOfWeek eq 'SATURDAY'}">selected</c:if>>SATURDAY</option>
                                <option value="SUNDAY" <c:if test="${roomSchedule.dayOfWeek eq 'SUNDAY'}">selected</c:if>>SUNDAY</option>
                            </select>
                            <p class="para-error text-right">${error.dayOfWeek}</p>
                        </div>
                        <div class="form-group">
                            <select id="room" name="roomId" class="form-control" required>
                                <option selected disabled>Select Room</option>
                                <c:forEach items="${roomList}" var="room">
                                    <option value="${room.id}" <c:if test="${room.id eq roomSchedule.roomId}">selected</c:if>>${room.name}</option>
                                </c:forEach>
                            </select>
                            <p class="para-error text-right">${error.room}</p>
                        </div>
                        <div class="form-group">
                            <select id="batch" name="batchId" class="form-control" required>
                                <option selected disabled>Select Batch</option>
                                <c:forEach items="${batchList}" var="batch">
                                    <option value="${batch.id}" <c:if test="${batch.id eq roomSchedule.batchId}">selected</c:if>>${batch.name}</option>
                                </c:forEach>
                            </select>
                            <p class="para-error text-right">${error.batch}</p>
                        </div>
                        <div class="form-group">
                            <select id="teacherProfile" name="teacherProfileId" class="form-control" required>
                                <option selected disabled>Select Teacher</option>
                                <c:forEach items="${teacherProfileList}" var="teacher">
                                    <option value="${teacher.id}" <c:if test="${teacher.id eq roomSchedule.teacherProfileId}">selected</c:if>>${teacher.fullName} - (Module: ${teacher.module.name})</option>
                                </c:forEach>
                            </select>
                            <p class="para-error text-right">${error.teacher}</p>
                        </div>
                        <div class="form-group">
                            <label for="startTime">Start Time</label>
                            <div class="input-group clockpicker" data-placement="left" data-align="top" data-autoclose="true">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"><i class="fa fa-fw fa-clock"></i></span>
                                </div>
                                <input type="text" class="form-control" value="13:15" id="startTime" name="strStartTime" required value="${roomSchedule.strStartTime}">
                            </div>
                            <p class="para-error text-right">${error.startTime}</p>
                        </div>
                        <div class="form-group">
                            <label for="endTime">End Time</label>
                            <div class="input-group clockpicker" data-placement="left" data-align="top" data-autoclose="true">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"><i class="fa fa-fw fa-clock"></i></span>
                                </div>
                                <input type="text" class="form-control" value="15:15" id="endTime" name="strEndTime" required value="${roomSchedule.strEndTime}">
                            </div>
                            <p class="para-error text-right">${error.endTime}</p>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="name"
                                   placeholder="Room Schedule Name" name="name" required value="${roomSchedule.name}">
                            <p class="para-error text-right">${error.name}</p>
                        </div>

                        <button type="submit" class="btn btn-primary btn-user btn-block">Add</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../common/pageFooter.jsp"></jsp:include>
