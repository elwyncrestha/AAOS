<%--
  Created by IntelliJ IDEA.
  User: elvin
  Date: 6/14/19
  Time: 11:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../common/pageHeader.jsp"></jsp:include>
<!-- Page Heading -->
<h1 class="h3 mb-4 text-gray-800"><i class="fas fa-user-tie fa-2x text-black-50"></i> Assign Module</h1>

<!-- Assign Batch Cards -->
<div class="row">
    <div class="col-xl-6 col-md-6 mb-4">
        <div class="card border-left-primary shadow h-100 py-2">
            <div class="card-body">
                <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                        <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">Total Assigned</div>
                        <div class="h5 mb-0 font-weight-bold text-gray-800">${assignedModuleCount}</div>
                    </div>
                    <div class="col-auto">
                        <i class="fas fa-user-check fa-2x text-gray-300"></i>
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
                        <div class="text-xs font-weight-bold text-success text-uppercase mb-1">Total Unassigned</div>
                        <div class="h5 mb-0 font-weight-bold text-gray-800">${unAssignedModuleCount}</div>
                    </div>
                    <div class="col-auto">
                        <i class="fas fa-user-slash fa-2x text-gray-300"></i>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Form -->
<div class="row">
    <div class="col-lg-12">
        <div class="card shadow mb-4">
            <!-- Card Header - Dropdown -->
            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <h6 class="m-0 font-weight-bold text-primary">Teacher Lists</h6>
            </div>
            <!-- Card Body -->
            <div class="card-body">
                <jsp:include page="../common/alertCard.jsp"></jsp:include>
                <div class="table-responsive">
                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Module</th>
                        </tr>
                        </thead>
                        <tfoot>
                        <tr>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Module</th>
                        </tr>
                        </tfoot>
                        <tbody>
                        <c:forEach items="${teacherProfileList}" var="tp" varStatus="sn">
                            <tr>
                                <td>${tp.fullName}</td>
                                <td>${tp.email}</td>
                                <td>
                                    <select class="form-control" id="moduleId${sn.index}" name="moduleId" onchange="assignModule('${pageContext.request.contextPath}', ${tp.id}, $('#moduleId${sn.index}').val())">
                                        <option selected disabled>Select Module</option>
                                        <c:forEach items="${moduleList}" var="module">
                                            <option value="${module.id}" <c:if test="${tp.module.id eq module.id}">selected</c:if>>${module.name}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../common/pageFooter.jsp"></jsp:include>


<script>
    function assignModule(pageContext, profileId, moduleId) {
        Swal.showLoading();
        $.ajax({
                url: '/teacher/' + profileId + '/module/' + moduleId,
                type: 'post',
                beforeSend: function (xhr) {
                    xhr.setRequestHeader(csrfHeader, csrfToken);
                },
                success: function (data) {
                    setTimeout(function () {
                        Swal.fire({
                                type:'success',
                                title:data.message,
                                showConfirmButton:true
                            },
                            function(){
                                location.reload();
                            }
                        );
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
</script>
