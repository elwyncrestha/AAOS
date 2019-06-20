<%--
  Created by IntelliJ IDEA.
  User: elvin
  Date: 6/20/19
  Time: 9:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../common/pageHeader.jsp"></jsp:include>

<!-- Page Heading -->
<h1 class="h3 mb-4 text-gray-800"><i class="fas fa-file-alt fa-2x text-black-50"></i> Generate Student Report</h1>

<!-- Form -->
<div class="row">
    <div class="col-lg-12">
        <div class="card shadow mb-4">
            <!-- Card Header - Dropdown -->
            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <h6 class="m-0 font-weight-bold text-primary">Report Form</h6>
            </div>
            <!-- Card Body -->
            <div class="card-body">
                <jsp:include page="../common/alertCard.jsp"></jsp:include>
                <div class="p-5">
                    <form method="post" action="${pageContext.request.contextPath}/exam/report/generate">

                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                        <div class="form-group">
                            <div class="row">
                                <div class="col-md-10">
                                    <input type="text" id="studentId" class="form-control" name="studentId" placeholder="Generate by student ID" value="${studentId}">
                                </div>
                                <div class="col-md-2">
                                    <button type="submit" class="btn btn-outline-primary btn-block"><i class="fa fa-search"></i></button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../common/pageFooter.jsp"></jsp:include>