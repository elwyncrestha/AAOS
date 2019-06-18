<%--
  Created by IntelliJ IDEA.
  User: elvin
  Date: 6/17/19
  Time: 6:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../common/pageHeader.jsp"></jsp:include>
<!-- Page Heading -->
<h1 class="h3 mb-4 text-gray-800"><i class="fas fa-book fa-2x text-black-50"></i> Enroll in Courses</h1>

<!-- Form -->
<div class="row">
    <div class="col-lg-12">
        <div class="card shadow mb-4">
            <!-- Card Header - Dropdown -->
            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <h6 class="m-0 font-weight-bold text-primary">Enroll Form</h6>
            </div>
            <!-- Card Body -->
            <div class="card-body">
                <jsp:include page="../common/alertCard.jsp"></jsp:include>
                <form method="post" action="${pageContext.request.contextPath}/batch/course/enroll">

                    <select class="form-control" id="batchId" name="batchId" onchange="getCoursesByBatchId('${pageContext.request.contextPath}', $('#batchId').val())">
                        <option selected disabled>Select Batch</option>
                        <c:forEach items="${batchList}" var="batch">
                            <option value="${batch.id}">${batch.name}</option>
                        </c:forEach>
                    </select>

                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                    <div class="form-group" id="courses"></div>

                    <button type="submit" class="btn btn-primary btn-user btn-block" id="batchCourseSubmit" style="display:none;">Add</button>
                </form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../common/pageFooter.jsp"></jsp:include>


<script>
    function getCoursesByBatchId(pageContext, batchId) {
        Swal.showLoading();
        $.ajax({
                url: '/batch/' + batchId + '/enroll/',
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
                        loadCourses(data);
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

    function loadCourses(data) {
        // remove all children
        let courseDiv = document.getElementById('courses');
        while (courseDiv.firstChild) {
            courseDiv.removeChild(courseDiv.firstChild);
        }

        $('#courses').append('<br><br><h4>Choose courses</h4><hr>');

        let allCourses = data.object['AllCourses'];
        let enrolledCourses = data.object['EnrolledCourses'];

        for (let i = 0; i < allCourses.length; i ++) {
            let isChecked = false;

            for (let j = 0; j < enrolledCourses.length; j ++) {
                if (allCourses[i].id === enrolledCourses[j].id) {
                    isChecked = true;
                    break;
                }
            }

            let checkbox =
                '<div class="row">' +
                '<div class="col-md-12">' +
                '<label><input type="checkbox" name="enrolledCourses" value="' + allCourses[i].id + '" ';
            if (isChecked) {
                checkbox += 'checked="checked"';
            }
            checkbox += '>    ' + allCourses[i].name + '</label>' +
                '</div>' +
                '</div>';
            $('#courses').append(checkbox);
        }

        $('#courses').css('display','block');
        $('#batchCourseSubmit').css('display','block');
    }
</script>
