<%--
  Created by IntelliJ IDEA.
  User: elvin
  Date: 6/20/19
  Time: 11:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="auth" class="com.elvin.aaos.web.utility.auth.AuthenticationUtil"/>

<jsp:include page="../common/pageHeader.jsp"></jsp:include>
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
                <jsp:include page="../common/alertCard.jsp"></jsp:include>
                <div class="p-5">
                    <div class="text-center">
                        <h1 class="h4 text-gray-900 mb-2">Having any problem?</h1>
                        <p class="mb-4">
                            Check the user manual below if there is any inconvenience with using the system.<br/>
                            If not found in manual, briefly describe your problem here and submit the form.
                        </p>
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
                <div id="accordion">
                    <div class="card">
                        <div class="card-header" id="heading1">
                            <h5 class="mb-0">
                                <button class="btn btn-link" data-toggle="collapse" data-target="#collapse1" aria-expanded="true" aria-controls="collapse1">
                                    Quick Start Guide
                                </button>
                            </h5>
                        </div>

                        <div id="collapse1" class="collapse show" aria-labelledby="heading1" data-parent="#accordion">
                            <div class="card-body">
                                <p>This manual includes following guidelines:</p>
                                <ul>
                                    <li><b>Access your profile</b> from top-right dropdown</li>
                                    <li><b>Change your account password</b> from top-right dropdown</li>
                                    <c:if test="${fn:contains(auth.getCurrentUser().authority, 'ROLE_ADMINISTRATOR')}">
                                        <li>How to add a user?</li>
                                        <li>How to manage organization information?</li>
                                        <li>How to manage academics related information?</li>
                                        <li>How to add a room schedule?</li>
                                        <li>How to view troubleshoot problems?</li>
                                        <li>How to view student transactions?</li>
                                    </c:if>
                                    <c:if test="${!fn:contains(auth.getCurrentUser().authority, 'ROLE_ADMINISTRATOR')}">
                                        <li>List of viewable informations</li>
                                    </c:if>
                                    <c:if test="${fn:contains(auth.getCurrentUser().authority, 'ROLE_ACADEMIC_STAFF')}">
                                        <li>How to manage examinations?</li>
                                        <li>How to assign exam to the batch?</li>
                                        <li>How to generate student report?</li>
                                    </c:if>
                                    <c:if test="${fn:contains(auth.getCurrentUser().authority, 'ROLE_ADMISSION_STAFF')}">
                                        <li>How to manage student transaction?</li>
                                    </c:if>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <c:if test="${fn:contains(auth.getCurrentUser().authority, 'ROLE_ADMINISTRATOR')}">
                        <jsp:include page="manuals/admin.jsp"/>
                    </c:if>
                    <c:if test="${fn:contains(auth.getCurrentUser().authority, 'ROLE_ACADEMIC_STAFF')}">
                        <jsp:include page="manuals/academicStaff.jsp"/>
                    </c:if>
                    <c:if test="${fn:contains(auth.getCurrentUser().authority, 'ROLE_ADMISSION_STAFF')}">
                        <jsp:include page="manuals/admissionStaff.jsp"/>
                    </c:if>
                    <c:if test="${fn:contains(auth.getCurrentUser().authority, 'ROLE_OPERATIONAL_STAFF')}">
                        <jsp:include page="manuals/operationalStaff.jsp"/>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../common/pageFooter.jsp"></jsp:include>