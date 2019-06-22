<%--
  Created by IntelliJ IDEA.
  User: elvin
  Date: 6/8/19
  Time: 8:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../common/pageHeader.jsp"></jsp:include>

<!-- Page Heading -->
<h1 class="h3 mb-4 text-gray-800"><i class="fas fa-user-lock fa-2x text-black-50"></i> Update Password</h1>

<div class="row">
    <div class="col-lg-12">
        <div class="card shadow mb-4">
            <!-- Card Header - Dropdown -->
            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <h6 class="m-0 font-weight-bold text-primary">Update Password Form</h6>
            </div>
            <!-- Card Body -->
            <div class="card-body">
                <jsp:include page="../common/alertCard.jsp"></jsp:include>

                <div class="text-center">
                    <img class="img-fluid px-3 px-sm-4 mt-3 mb-4" style="width: 20rem;" src="${cp}/resources/img/forgotPassword.svg" alt="">
                </div>

                <div class="p-5">
                    <form method="post" action="${pageContext.request.contextPath}/user/password/update">

                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                        <div class="form-group">
                            <input type="password" class="form-control" id="oldPassword"
                                   placeholder="Old Password" name="oldPassword" required minlength="8" maxlength="30"
                                   value="${oldPassword}" autofocus>
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" id="newPassword"
                                   placeholder="New Password" name="newPassword" required minlength="8" maxlength="30"
                                   value="${newPassword}">
                        </div>
                        <button type="submit" class="btn btn-primary btn-user btn-block">Update</button>
                    </form>
                </div>
            </div>
        </div>

    </div>
</div>

<jsp:include page="../common/pageFooter.jsp"></jsp:include>
