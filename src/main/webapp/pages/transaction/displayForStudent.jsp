<%--
  Created by IntelliJ IDEA.
  User: elvin
  Date: 6/18/19
  Time: 6:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../common/pageHeader.jsp"></jsp:include>

<!-- Page Heading -->
<h1 class="h3 mb-4 text-gray-800"><i class="fas fa-chart-area fa-2x text-black-50"></i> View Student Transaction</h1>

<!-- Form -->
<div class="row">
    <div class="col-lg-12">
        <div class="card shadow mb-4">
            <!-- Card Header - Dropdown -->
            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <h6 class="m-0 font-weight-bold text-primary">View Transactions</h6>
            </div>
            <!-- Card Body -->
            <div class="card-body">
                <jsp:include page="../common/alertCard.jsp"></jsp:include>

                <ul class="timeline" id="transactionTimeline">
                    <c:choose>
                        <c:when test="${empty transactionList}">
                            <h4>No Transactions recorded</h4>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${transactionList}" var="transaction">
                                <li>
                                    <label class="text-primary">${transaction.studentProfile.fullName}</label>
                                    <label class="text-primary float-right">${transaction.transactionDate}</label>
                                    <p>${transaction.remarks}</p>
                                    <label class="text-primary">Transaction for Course: ${transaction.course.name}</label><br />
                                    <label class="text-primary">
                                        Status: <c:choose><c:when test="${transaction.complete}">Completed</c:when><c:otherwise>In-complete</c:otherwise></c:choose>
                                    </label>
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