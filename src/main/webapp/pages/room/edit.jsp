<%--
  Created by IntelliJ IDEA.
  User: elvin
  Date: 5/25/19
  Time: 1:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.elvin.aaos.core.model.enums.Status" %>
<%@ page import="com.elvin.aaos.core.model.enums.RoomType" %>
<c:set var="roomTypes" value="${RoomType.values()}"></c:set>
<c:set var="roomStatus" value="${Status.values()}"></c:set>

<jsp:include page="../common/pageHeader.jsp"></jsp:include>

<!-- Page Heading -->
<h1 class="h3 mb-4 text-gray-800"><i class="fas fa-building fa-2x text-black-50"></i> Edit Room</h1>

<!-- User Cards -->
<jsp:include page="roomCards.jsp"></jsp:include>

<!-- Form -->
<div class="row">
    <div class="col-lg-12">
        <div class="card shadow mb-4">
            <!-- Card Header - Dropdown -->
            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <h6 class="m-0 font-weight-bold text-primary">Edit Room Form</h6>
            </div>
            <!-- Card Body -->
            <div class="card-body">
                <div class="p-5">
                    <form method="post" action="${pageContext.request.contextPath}/room/edit">

                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                        <input type="hidden" name="id" value="${room.id}">

                        <div class="form-group">
                            <input type="text" class="form-control" id="name"
                                   placeholder="Room Name" name="name" required value="${room.name}">
                            <p class="para-error text-right">${error.name}</p>
                        </div>
                        <div class="form-group">
                            <select id="roomType" name="roomType" class="form-control" required>
                                <option selected disabled>Select Room Type</option>
                                <c:forEach items="${roomTypes}" var="roomType">
                                    <option value="${roomType}" <c:if test="${roomType eq room.roomType}">selected</c:if>>${roomType.value}</option>
                                </c:forEach>
                            </select>
                            <p class="para-error text-right">${error.roomType}</p>
                        </div>
                        <div class="form-group">
                            <select id="building" name="buildingId" class="form-control" required>
                                <option selected disabled>Select Building</option>
                                <c:forEach items="${buildingList}" var="building">
                                    <option value="${building.id}" <c:if test="${building.id eq room.building.id}">selected</c:if>>${building.name}</option>
                                </c:forEach>
                            </select>
                            <p class="para-error text-right">${error.building}</p>
                        </div>
                        <div class="form-group">
                            <select id="status" name="status" class="form-control" required>
                                <option selected disabled>Select Room Status</option>
                                <c:forEach items="${roomStatus}" var="status">
                                    <option value="${status}" <c:if test="${status eq room.status}">selected</c:if>>${status.value}</option>
                                </c:forEach>
                            </select>
                            <p class="para-error text-right">${error.status}</p>
                        </div>
                        <button type="submit" class="btn btn-primary btn-user btn-block">Edit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../common/pageFooter.jsp"></jsp:include>