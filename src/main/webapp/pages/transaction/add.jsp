<%--
  Created by IntelliJ IDEA.
  User: elvin
  Date: 6/18/19
  Time: 10:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../common/pageHeader.jsp"></jsp:include>

<!-- Page Heading -->
<h1 class="h3 mb-4 text-gray-800"><i class="fas fa-file-alt fa-2x text-black-50"></i> Add Student Transaction</h1>

<!-- Form -->
<div class="row">
    <div class="col-lg-12">
        <div class="card shadow mb-4">
            <!-- Card Header - Dropdown -->
            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <h6 class="m-0 font-weight-bold text-primary">Transaction Form</h6>
            </div>
            <!-- Card Body -->
            <div class="card-body">
                <jsp:include page="../common/alertCard.jsp"></jsp:include>
                <div class="p-5">
                    <form method="post" action="${pageContext.request.contextPath}/transaction/add">

                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                        <div class="text-center">
                            <img class="img-fluid px-3 px-sm-4 mt-3 mb-4" style="width: 25rem;" src="${cp}/resources/img/transaction.svg" alt="">
                        </div>

                        <div class="form-group">
                            <div class="row">
                                <div class="col-md-10">
                                    <input type="text" id="searchStudent" class="form-control" placeholder="Search by student ID">
                                </div>
                                <div class="col-md-2">
                                    <button type="button" class="btn btn-outline-primary btn-block" onclick="getStudentById('${pageContext.request.contextPath}', $('#searchStudent').val())"><i class="fa fa-search"></i></button>
                                </div>
                            </div>
                        </div>

                        <input type="hidden" name="studentProfileId" id="studentId">

                        <div id="studentProfileContent" class="form-group" style="display:none;">
                            <table class="table table-hover table-bordered">
                                <tbody>
                                    <tr>
                                        <td>Student Name</td>
                                        <td id="studentName"></td>
                                    </tr>
                                    <tr>
                                        <td>Student Email</td>
                                        <td id="studentEmail"></td>
                                    </tr>
                                    <tr>
                                        <td>Batch</td>
                                        <td id="studentBatch"></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>

                        <div class="form-group">
                            <select id="course" name="courseId" class="form-control" required>
                                <option selected disabled>Select Course</option>
                                <c:forEach items="${courseList}" var="course">
                                    <option value="${course.id}" <c:if test="${course.id eq studentTransaction.courseId}">selected</c:if>>${course.name}</option>
                                </c:forEach>
                            </select>
                            <p class="para-error text-right">${error.course}</p>
                        </div>
                        <div class="form-group">
                            <textarea class="form-control" id="remarks" placeholder="Transaction Remarks"
                                      name="remarks" required rows="5">${studentTransaction.remarks}</textarea>
                            <p class="para-error text-right">${error.remarks}</p>
                        </div>
                        <div class="form-group">
                            <label for="isComplete">Is the transaction complete?</label>
                            <div id="isComplete">
                                <div class="form-check-inline">
                                    <label class="form-check-label">
                                        <input type="radio" class="form-check-input" name="complete" value="true" required <c:if test="${studentTransaction.complete eq 'true'}">checked</c:if>>Yes
                                    </label>
                                </div>
                                <div class="form-check-inline">
                                    <label class="form-check-label">
                                        <input type="radio" class="form-check-input" name="complete" value="false" required <c:if test="${studentTransaction.complete eq 'false'}">checked</c:if>>No
                                    </label>
                                </div>
                            </div>
                            <p class="para-error text-right">${error.isComplete}</p>
                        </div>

                        <button type="submit" class="btn btn-primary btn-user btn-block">Add</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../common/pageFooter.jsp"></jsp:include>

<script>
    function getStudentById(pageContext, studentId) {
        $('#studentId').val(undefined);
        $('#studentProfileContent').css('display','none');

        Swal.showLoading();
        try {
            let convertToNum = Number(studentId);
            $.ajax({
                    url: '/student/' + studentId,
                    type: 'post',
                    beforeSend: function (xhr) {
                        xhr.setRequestHeader(csrfHeader, csrfToken);
                    },
                    success: function (data) {
                        if (data.status != '200') {
                            setTimeout(function () {
                                Swal.fire({
                                    type: 'error',
                                    title: 'Oops...',
                                    text: data.message
                                });
                            }, 2000);
                        } else {
                            Swal.close();
                            loadStudentData(data);
                        }
                    },
                    error: function (data) {
                        setTimeout(function () {
                            Swal.fire({
                                type: 'error',
                                title: 'Oops...',
                                text: data.responseJSON.message
                            });
                        }, 2000);
                    },
                    fail: function (data) {
                        setTimeout(function () {
                            Swal.fire({
                                type: 'error',
                                title: 'Oops...',
                                text: 'Something went wrong!'
                            });
                        }, 2000);
                    }
                }
            );
        } catch(err) {
            setTimeout(function () {
                Swal.fire({
                    type: 'error',
                    title: 'Oops...',
                    text: 'Invalid Student ID!'
                });
            }, 2000);
        }

    }

    function loadStudentData(data) {
        $('#studentId').val(data.object.id);
        $('#studentName').html(data.object.fullName);
        $('#studentEmail').html(data.object.email);
        $('#studentBatch').html(data.object.batch.name);

        $('#studentProfileContent').css('display','block');
    }
</script>