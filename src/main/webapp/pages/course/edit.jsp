<%--
  Created by IntelliJ IDEA.
  User: elvin
  Date: 6/13/19
  Time: 10:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../common/pageHeader.jsp"></jsp:include>

<!-- Page Heading -->
<h1 class="h3 mb-4 text-gray-800"><i class="fas fa-book fa-2x text-black-50"></i> Edit Course</h1>

<!-- User Cards -->
<jsp:include page="courseCard.jsp"></jsp:include>

<!-- Form -->
<div class="row">
    <div class="col-lg-12">
        <div class="card shadow mb-4">
            <!-- Card Header - Dropdown -->
            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <h6 class="m-0 font-weight-bold text-primary">Edit Course Form</h6>
            </div>
            <!-- Card Body -->
            <div class="card-body">
                <div class="p-5">
                    <form method="post" action="${pageContext.request.contextPath}/course/edit">

                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                        <input type="hidden" name="id" value="${course.id}"/>

                        <div class="form-group">
                            <input type="text" class="form-control" id="name"
                                   placeholder="Course Name" name="name" required value="${course.name}">
                            <p class="para-error text-right">${error.name}</p>
                        </div>
                        <button type="submit" class="btn btn-primary btn-user btn-block">Edit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../common/pageFooter.jsp"></jsp:include>
