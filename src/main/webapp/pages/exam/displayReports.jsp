<%--
  Created by IntelliJ IDEA.
  User: elvin
  Date: 6/20/19
  Time: 8:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../common/pageHeader.jsp"></jsp:include>

<!-- Page Heading -->
<h1 class="h3 mb-4 text-gray-800"><i class="fas fa-file-alt fa-2x text-black-50"></i> Student Report</h1>

<!-- Form -->
<div class="row">
    <div class="col-lg-12">
        <div class="card shadow mb-4">
            <!-- Card Header - Dropdown -->
            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <h6 class="m-0 font-weight-bold text-primary">${studentReportList[0].studentProfile.fullName} Report</h6>
            </div>
            <!-- Card Body -->
            <div class="card-body">
                <jsp:include page="../common/alertCard.jsp"></jsp:include>
                <div class="p-5">
                    <div class="table-responsive">
                        <table class="table table-striped table-hover table-bordered">
                            <thead>
                                <tr>
                                    <th>S No.</th>
                                    <th>Module</th>
                                    <th>Obtained Marks</th>
                                </tr>
                            </thead>
                            <tfoot>
                            <tr>
                                <th>S No.</th>
                                <th>Module</th>
                                <th>Obtained Marks</th>
                            </tr>
                            </tfoot>
                            <tbody>
                            <c:forEach items="${studentReportList}" var="report" varStatus="sn">
                                <tr>
                                    <td>${sn.index+1}</td>
                                    <td>${report.module.name}</td>
                                    <td>${report.marksObtained}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../common/pageFooter.jsp"></jsp:include>