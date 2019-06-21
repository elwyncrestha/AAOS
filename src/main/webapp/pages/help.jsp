<%--
  Created by IntelliJ IDEA.
  User: elvin
  Date: 6/20/19
  Time: 11:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="common/pageHeader.jsp"></jsp:include>
<!-- Page Heading -->
<h1 class="h3 mb-4 text-gray-800"><i class="fas fa-question fa-2x text-black-50"></i> Help</h1>

<div class="row">
    <div class="col-lg-12">
        <div class="card shadow mb-4">
            <!-- Card Header - Dropdown -->
            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <h6 class="m-0 font-weight-bold text-primary">Troubleshooting</h6>
            </div>
            <!-- Card Body -->
            <div class="card-body">
                <jsp:include page="common/alertCard.jsp"></jsp:include>
                <div class="p-5">
                    <div class="text-center">
                        <h1 class="h4 text-gray-900 mb-2">Having any problem?</h1>
                        <p class="mb-4">Briefly describe your problem below and submit the form.</p>
                    </div>
                    <form class="user" action="${pageContext.request.contextPath}/help" method="post">

                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                        <div class="form-group">
                            <textarea class="form-control" placeholder="Brief problem definition..." rows="8"
                                      name="description" autofocus required>${troubleshoot.description}</textarea>
                        </div>
                        <button type="submit" class="btn btn-primary btn-user btn-block">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-lg-12">
        <div class="card shadow mb-4">
            <!-- Card Header - Dropdown -->
            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <h6 class="m-0 font-weight-bold text-primary">User Manual</h6>
            </div>
            <!-- Card Body -->
            <div class="card-body">


            </div>
        </div>
    </div>
</div>

<jsp:include page="common/pageFooter.jsp"></jsp:include>