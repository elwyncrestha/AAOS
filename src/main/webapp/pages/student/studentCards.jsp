<%--
  Created by IntelliJ IDEA.
  User: elvin
  Date: 6/7/19
  Time: 8:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
    <div class="col-xl-6 col-md-6 mb-4">
        <div class="card border-left-primary shadow h-100 py-2">
            <div class="card-body">
                <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                        <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">Created at</div>
                        <div class="h5 mb-0 font-weight-bold text-gray-800">
                            <c:choose>
                                <c:when test="${not empty student or not empty student.createdAt}">
                                    ${student.createdAt}
                                </c:when>
                                <c:otherwise>
                                    Profile not created
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <div class="col-auto">
                        <i class="fas fa-calendar-check fa-2x text-gray-300"></i>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="col-xl-6 col-md-6 mb-4">
        <div class="card border-left-success shadow h-100 py-2">
            <div class="card-body">
                <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                        <div class="text-xs font-weight-bold text-success text-uppercase mb-1">Last updated</div>
                        <div class="h5 mb-0 font-weight-bold text-gray-800">
                            <c:choose>
                                <c:when test="${not empty student.lastModifiedAt}">
                                    ${student.lastModifiedAt}
                                </c:when>
                                <c:otherwise>
                                    Not updated since registered
                                </c:otherwise>
                            </c:choose>

                        </div>
                    </div>
                    <div class="col-auto">
                        <i class="fas fa-calendar-alt fa-2x text-gray-300"></i>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
