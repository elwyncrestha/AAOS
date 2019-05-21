<%--
  Created by IntelliJ IDEA.
  User: elvin
  Date: 5/21/19
  Time: 8:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.elvin.aaos.core.model.enums.BuildingStatus" %>
<c:set var="buildingStatus" value="${BuildingStatus.values()}"></c:set>

<jsp:include page="../common/pageHeader.jsp"></jsp:include>

<!-- Page Heading -->
<h1 class="h3 mb-4 text-gray-800"><i class="fas fa-building fa-2x text-black-50"></i> Edit Building</h1>

<!-- User Cards -->
<jsp:include page="buildingCards.jsp"></jsp:include>

<!-- Form -->
<div class="row">
    <div class="col-lg-12">
        <div class="card shadow mb-4">
            <!-- Card Header - Dropdown -->
            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <h6 class="m-0 font-weight-bold text-primary">Edit Building Form</h6>
            </div>
            <!-- Card Body -->
            <div class="card-body">
                <div class="p-5">
                    <form method="post" action="${pageContext.request.contextPath}/building/edit">

                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                        <input type="hidden" name="id" value="${building.id}">

                        <div class="form-group">
                            <input type="text" class="form-control" id="name"
                                   placeholder="Building Name" name="name" required value="${building.name}">
                        </div>
                        <div class="form-group">
                            <textarea class="form-control" id="description" placeholder="Building Description"
                                      name="description" required rows="7">${building.description}</textarea>
                        </div>
                        <div class="form-group">
                            <select id="status" name="status" class="form-control" required>
                                <option selected disabled>Select Building Status</option>
                                <c:forEach items="${buildingStatus}" var="status">
                                    <option value="${status}" <c:if test="${status eq building.status}">selected</c:if>>${status.value}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary btn-user btn-block">Edit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../common/pageFooter.jsp"></jsp:include>