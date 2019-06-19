<%--
  Created by IntelliJ IDEA.
  User: elvin
  Date: 6/19/19
  Time: 3:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../common/pageHeader.jsp"></jsp:include>
<!-- Page Heading -->
<h1 class="h3 mb-4 text-gray-800"><i class="fas fa-pen fa-2x text-black-50"></i> Assign Exam</h1>

<!-- Form -->
<div class="row">
    <div class="col-lg-12">
        <div class="card shadow mb-4">
            <!-- Card Header - Dropdown -->
            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <h6 class="m-0 font-weight-bold text-primary">Assign Form</h6>
            </div>
            <!-- Card Body -->
            <div class="card-body">
                <jsp:include page="../common/alertCard.jsp"></jsp:include>
                <form method="post" action="${pageContext.request.contextPath}/batch/exam/assign">

                    <select class="form-control" id="batchId" name="batchId" onchange="getExamsByBatchId('${pageContext.request.contextPath}', $('#batchId').val())">
                        <option selected disabled>Select Batch</option>
                        <c:forEach items="${batchList}" var="batch">
                            <option value="${batch.id}">${batch.name}</option>
                        </c:forEach>
                    </select>

                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                    <div class="form-group" id="exams"></div>

                    <button type="submit" class="btn btn-primary btn-user btn-block" id="batchExamSubmit" style="display:none;">Add</button>
                </form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../common/pageFooter.jsp"></jsp:include>


<script>
    function getExamsByBatchId(pageContext, batchId) {
        Swal.showLoading();
        $.ajax({
                url: '/batch/' + batchId + '/exam/',
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
                        loadExams(data);
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
    }

    function loadExams(data) {
        // remove all children
        let examDiv = document.getElementById('exams');
        while (examDiv.firstChild) {
            examDiv.removeChild(examDiv.firstChild);
        }

        $('#exams').append('<br><br><h4>Choose exams</h4><hr>');

        let allExams = data.object['AllExams'];
        let assignedExams = data.object['AssignedExams'];

        for (let i = 0; i < allExams.length; i ++) {
            let isChecked = false;

            for (let j = 0; j < assignedExams.length; j ++) {
                if (allExams[i].id === assignedExams[j].id) {
                    isChecked = true;
                    break;
                }
            }

            let checkbox =
                '<div class="row">' +
                '<div class="col-md-12">' +
                '<label><input type="checkbox" name="assignedExams" value="' + allExams[i].id + '" ';
            if (isChecked) {
                checkbox += 'checked="checked"';
            }
            checkbox += '>    ' + allExams[i].name + '</label>' +
                '</div>' +
                '</div>';
            $('#exams').append(checkbox);
        }

        $('#exams').css('display','block');
        $('#batchExamSubmit').css('display','block');
    }
</script>
