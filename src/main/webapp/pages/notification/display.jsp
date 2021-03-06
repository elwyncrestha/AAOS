<%--
  Created by IntelliJ IDEA.
  User: elvin
  Date: 6/19/19
  Time: 7:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../common/pageHeader.jsp"></jsp:include>

<!-- Page Heading -->
<h1 class="h3 mb-4 text-gray-800"><i class="fas fa-bell fa-2x text-black-50"></i> View All Notifications</h1>

<!-- Form -->
<div class="row">
    <div class="col-lg-12">
        <div class="card shadow mb-4">
            <!-- Card Header - Dropdown -->
            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <h6 class="m-0 font-weight-bold text-primary">View Notifications</h6>
            </div>
            <!-- Card Body -->
            <div class="card-body">
                <jsp:include page="../common/alertCard.jsp"></jsp:include>

                <ul class="timeline">
                    <c:choose>
                        <c:when test="${empty notificationList}">
                            <h4>No Notifications</h4>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${notificationList}" var="notification">
                                <li>
                                    <label class="text-primary">${notification.title}</label>
                                    <label class="text-primary float-right">${notification.createdAt}</label>
                                    <p>${notification.description}</p>
                                    <c:if test="${notification.status eq 'ACTIVE'}">
                                        <a href="${pageContext.request.contextPath}/notification/read/${notification.id}">Mark as read</a>
                                    </c:if>
                                    <hr />
                                </li>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../common/pageFooter.jsp"></jsp:include>
