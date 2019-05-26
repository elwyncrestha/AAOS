<%--
  Created by IntelliJ IDEA.
  User: elvin
  Date: 4/13/19
  Time: 6:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="cp" value="${pageContext.request.contextPath}"></c:set>
<c:set var="activeNav" value="${requestScope['javax.servlet.forward.request_uri']}"></c:set>
<jsp:useBean id="auth" class="com.elvin.aaos.web.utility.auth.AuthenticationUtil"/>

<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-center"
       href="${cp}/dashboard">
        <div class="sidebar-brand-icon rotate-n-15">
            <i class="fas fa-laptop-code"></i>
        </div>
        <div class="sidebar-brand-text mx-3">A A O S</div>
    </a>

    <!-- Divider -->
    <hr class="sidebar-divider my-0">

    <!-- Nav Item - Dashboard -->
    <li class="nav-item <c:if test="${fn:contains(activeNav, '/dashboard')}">active</c:if>">
        <a class="nav-link" href="${cp}/dashboard">
            <i class="fas fa-fw fa-tachometer-alt"></i>
            <span>Dashboard</span></a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider">

    <!-- Administrator only-->
    <c:if test="${fn:contains(auth.getCurrentUser().authority, 'ROLE_ADMINISTRATOR')}">
        <!-- Heading -->
        <div class="sidebar-heading">
            Admin
        </div>

        <!-- Nav Item - Users Collapse Menu -->
        <li class="nav-item <c:if test="${fn:contains(activeNav, '/user')}">active</c:if>">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUsers"
               aria-expanded="true" aria-controls="collapseUsers">
                <i class="fas fa-fw fa-cog"></i>
                <span>User</span>
            </a>
            <div id="collapseUsers" class="collapse" aria-labelledby="headingUsers" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <h6 class="collapse-header">Users Information:</h6>
                    <a class="collapse-item" href="${cp}/user/add">Add User</a>
                    <a class="collapse-item" href="${cp}/user/display">Display Users</a>
                </div>
            </div>
        </li>

        <!-- Nav Item - Organization Collapse Menu -->
        <li class="nav-item <c:if test="${fn:contains(activeNav, '/organization') or fn:contains(activeNav, '/building') or fn:contains(activeNav, '/room')}">active</c:if>">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseOrganization"
               aria-expanded="true" aria-controls="collapseOrganization">
                <i class="fas fa-fw fa-university"></i>
                <span>Organization</span>
            </a>
            <div id="collapseOrganization" class="collapse" aria-labelledby="headingOrganization"
                 data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <h6 class="collapse-header">Organization Information:</h6>
                    <a class="collapse-item" href="${cp}/organization/display">View Information</a>
                    <a class="collapse-item" href="${cp}/organization/edit">Edit Information</a>
                    <div class="collapse-divider"></div>
                    <h6 class="collapse-header">Building Information:</h6>
                    <a class="collapse-item" href="${cp}/building/add">Add Building</a>
                    <a class="collapse-item" href="${cp}/building/display">View Buildings</a>
                    <div class="collapse-divider"></div>
                    <h6 class="collapse-header">Room Information:</h6>
                    <a class="collapse-item" href="${cp}/room/add">Add Room</a>
                    <a class="collapse-item" href="${cp}/room/display">View Rooms</a>
                </div>
            </div>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider">
    </c:if>

    <!-- All users except Administrator -->
    <c:if test="${!fn:contains(auth.getCurrentUser().authority, 'ROLE_ADMINISTRATOR')}">
        <!-- Heading -->
        <div class="sidebar-heading">
            Organization
        </div>

        <!-- Nav Item - Organization Collapse Menu -->
        <li class="nav-item <c:if test="${fn:contains(activeNav, '/organization')}">active</c:if>">
            <a class="nav-link" href="${cp}/organization/display">
                <i class="fas fa-fw fa-university"></i>
                <span>View Organization Info</span></a>
        </li>
        <!-- Nav Item - Building Collapse Menu -->
        <li class="nav-item <c:if test="${fn:contains(activeNav, '/building')}">active</c:if>">
            <a class="nav-link" href="${cp}/building/display">
                <i class="fas fa-fw fa-building"></i>
                <span>View Buildings</span></a>
        </li>
        <!-- Nav Item - Room Collapse Menu -->
        <li class="nav-item <c:if test="${fn:contains(activeNav, '/room')}">active</c:if>">
            <a class="nav-link" href="${cp}/room/display">
                <i class="fas fa-fw fa-columns"></i>
                <span>View Rooms</span></a>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider">
    </c:if>

    <!-- Student only -->
    <c:if test="${!fn:contains(auth.getCurrentUser().authority, 'ROLE_STUDENT')}">

    </c:if>

    <!-- Teacher only -->
    <c:if test="${!fn:contains(auth.getCurrentUser().authority, 'ROLE_TEACHER')}">

    </c:if>

    <!-- Academic Staff only -->
    <c:if test="${!fn:contains(auth.getCurrentUser().authority, 'ROLE_ACADEMIC_STAFF')}">

    </c:if>

    <!-- Operational Staff only -->
    <c:if test="${!fn:contains(auth.getCurrentUser().authority, 'ROLE_OPERATIONAL_STAFF')}">

    </c:if>

    <!-- Sidebar Toggler (Sidebar) -->
    <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
    </div>

</ul>
