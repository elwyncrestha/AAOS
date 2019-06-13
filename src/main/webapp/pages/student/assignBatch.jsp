<%--
  Created by IntelliJ IDEA.
  User: elvin
  Date: 6/9/19
  Time: 3:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="auth" class="com.elvin.aaos.web.utility.auth.AuthenticationUtil"/>

<jsp:include page="../common/pageHeader.jsp"></jsp:include>
<!-- Page Heading -->
<h1 class="h3 mb-4 text-gray-800"><i class="fas fa-users fa-2x text-black-50"></i> Assign Batch</h1>

<!-- Assign Batch Cards -->
<div class="row">
    <div class="col-xl-6 col-md-6 mb-4">
        <div class="card border-left-primary shadow h-100 py-2">
            <div class="card-body">
                <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                        <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">Total Assigned</div>
                        <div class="h5 mb-0 font-weight-bold text-gray-800">${assignedBatchCount}</div>
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
                        <div class="h5 mb-0 font-weight-bold text-gray-800">${unAssignedBatchCount}</div>
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
                <h6 class="m-0 font-weight-bold text-primary">Student Lists</h6>
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
                            <th>Batch</th>
                        </tr>
                        </thead>
                        <tfoot>
                        <tr>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Batch</th>
                        </tr>
                        </tfoot>
                        <tbody>
                            <c:forEach items="${studentProfileList}" var="sp" varStatus="sn">
                                <tr>
                                    <td>${sp.fullName}</td>
                                    <td>${sp.email}</td>
                                    <td>
                                        <select class="form-control" id="batchId${sn.index}" name="batchId" onchange="assignBatch('${pageContext.request.contextPath}', ${sp.id}, $('#batchId${sn.index}').val())">
                                            <option selected disabled>Select Batch</option>
                                            <c:forEach items="${batchList}" var="batch">
                                                <option value="${batch.id}" <c:if test="${sp.batch.id eq batch.id}">selected</c:if>>${batch.name}</option>
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
    function assignBatch(pageContext, profileId, batchId) {
        $.ajax({
                url: '/student/' + profileId + '/batch/' + batchId,
                type: 'post',
                data: {
                    'profileId':profileId,
                    'batchId':batchId
                },
                success: function (data) {
                    console.log(data);
                    setTimeout(function () {
                        Swal.fire({
                                type:data.swalType,
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
                    console.log(data);
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
