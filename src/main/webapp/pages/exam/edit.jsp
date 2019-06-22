<%--
  Created by IntelliJ IDEA.
  User: elvin
  Date: 6/19/19
  Time: 2:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../common/pageHeader.jsp"></jsp:include>

<!-- Page Heading -->
<h1 class="h3 mb-4 text-gray-800"><i class="fas fa-pen fa-2x text-black-50"></i> Edit Exam</h1>

<!-- Form -->
<div class="row">
    <div class="col-lg-12">
        <div class="card shadow mb-4">
            <!-- Card Header - Dropdown -->
            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <h6 class="m-0 font-weight-bold text-primary">Edit Exam Form</h6>
            </div>
            <!-- Card Body -->
            <div class="card-body">
                <div class="p-5">

                    <div class="text-center">
                        <img class="img-fluid px-3 px-sm-4 mt-3 mb-4" style="width: 20rem;" src="${cp}/resources/img/exam.svg" alt="">
                    </div>

                    <form method="post" action="${pageContext.request.contextPath}/exam/edit">

                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <input type="hidden" name="id" value="${exam.id}"/>

                        <div class="form-group">
                            <input type="text" class="form-control" id="name"
                                   placeholder="Exam Name" name="name" required value="${exam.name}">
                            <p class="para-error text-right">${error.name}</p>
                        </div>
                        <div class="form-group">
                            <label for="startDate">Start Date</label>
                            <input type="date" class="form-control" id="startDate" name="start"
                                   pattern="yyyy-MM-dd" required value="${exam.start}">
                            <p class="para-error text-right">${error.start}</p>
                        </div>
                        <div class="form-group">
                            <label for="endDate">End Date</label>
                            <input type="date" class="form-control" id="endDate" name="end"
                                   pattern="yyyy-MM-dd" required value="${exam.end}">
                            <p class="para-error text-right">${error.end}</p>
                        </div>
                        <div class="form-group">
                            <label for="startTime">Start Time</label>
                            <div class="input-group clockpicker" data-placement="left" data-align="top" data-autoclose="true">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"><i class="fa fa-fw fa-clock"></i></span>
                                </div>
                                <input type="text" class="form-control" id="startTime" name="strStartTime" required value="${exam.strStartTime}">
                            </div>
                            <p class="para-error text-right">${error.startTime}</p>
                        </div>
                        <div class="form-group">
                            <label for="endTime">End Time</label>
                            <div class="input-group clockpicker" data-placement="left" data-align="top" data-autoclose="true">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"><i class="fa fa-fw fa-clock"></i></span>
                                </div>
                                <input type="text" class="form-control" id="endTime" name="strEndTime" required value="${exam.strEndTime}">
                            </div>
                            <p class="para-error text-right">${error.endTime}</p>
                        </div>
                        <div class="form-group">
                            <select id="module" name="moduleId" class="form-control" required>
                                <option selected disabled>Select Module</option>
                                <c:forEach items="${moduleList}" var="module">
                                    <option value="${module.id}" <c:if test="${module.id eq exam.moduleId}">selected</c:if>>${module.name}</option>
                                </c:forEach>
                            </select>
                            <p class="para-error text-right">${error.module}</p>
                        </div>
                        <button type="submit" class="btn btn-primary btn-user btn-block">Edit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../common/pageFooter.jsp"></jsp:include>