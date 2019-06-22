<%--
  Created by IntelliJ IDEA.
  User: elvin
  Date: 6/21/19
  Time: 10:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../common/pageHeader.jsp"></jsp:include>

<!-- Page Heading -->
<h1 class="h3 mb-4 text-gray-800"><i class="fas fa-question-circle fa-2x text-black-50"></i> View Problems</h1>

<!-- Form -->
<div class="row">
    <div class="col-lg-12">
        <div class="card shadow mb-4">
            <!-- Card Header - Dropdown -->
            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <h6 class="m-0 font-weight-bold text-primary">View Problems to Troubleshoot</h6>
            </div>
            <!-- Card Body -->
            <div class="card-body">
                <jsp:include page="../common/alertCard.jsp"></jsp:include>

                <div class="text-center">
                    <img class="img-fluid px-3 px-sm-4 mt-3 mb-4" style="width: 20rem;" src="${cp}/resources/img/troubleshoot.svg" alt="">
                </div>

                <ul class="timeline">
                    <c:choose>
                        <c:when test="${empty troubleshootList}">
                            <h4>No problems recorded</h4>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${troubleshootList}" var="ts">
                                <li>
                                    <label class="text-primary">${ts.user.fullName}</label>
                                    <label class="text-primary float-right">${ts.createdAt}</label>
                                    <p>${ts.description}</p>
                                    <a href="${pageContext.request.contextPath}/troubleshoot/delete/${ts.id}"
                                    class="btn btn-danger btn-sm float-right"
                                    onclick="return confirm('Are you sure you want to delete this problem?')">Delete</a>
                                    <div class="clearfix"></div>
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