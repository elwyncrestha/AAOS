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

        <!-- Nav Item - Academics Collapse Menu -->
        <li class="nav-item <c:if test="${fn:contains(activeNav, '/batch') or fn:contains(activeNav, '/course')}">active</c:if>">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseAcademics"
               aria-expanded="true" aria-controls="collapseAcademics">
                <i class="fas fa-fw fa-book-open"></i>
                <span>Academics</span>
            </a>
            <div id="collapseAcademics" class="collapse" aria-labelledby="headingAcademics"
                 data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <h6 class="collapse-header">Batch Information:</h6>
                    <a class="collapse-item" href="${cp}/batch/add">Add Batch</a>
                    <a class="collapse-item" href="${cp}/batch/display">View Batches</a>
                    <a class="collapse-item" href="${cp}/batch/assign">Assign Batch</a>
                    <a class="collapse-item" href="${cp}/batch/course/enroll">Enroll Batch in Course</a>
                    <div class="collapse-divider"></div>
                    <h6 class="collapse-header">Course Information:</h6>
                    <a class="collapse-item" href="${cp}/course/add">Add Course</a>
                    <a class="collapse-item" href="${cp}/course/display">View Courses</a>
                    <div class="collapse-divider"></div>
                    <h6 class="collapse-header">Module Information:</h6>
                    <a class="collapse-item" href="${cp}/module/add">Add Module</a>
                    <a class="collapse-item" href="${cp}/module/display">View Modules</a>
                    <a class="collapse-item" href="${cp}/module/assign">Assign Module</a>
                    <div class="collapse-divider"></div>
                    <h6 class="collapse-header">Exam Information:</h6>
                    <a class="collapse-item" href="${cp}/exam/display">View Exams</a>
                    <div class="collapse-divider"></div>
                </div>
            </div>
        </li>

        <!-- Nav Item - Schedule Collapse Menu -->
        <li class="nav-item <c:if test="${fn:contains(activeNav, '/schedule')}">active</c:if>">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseSchedule"
               aria-expanded="true" aria-controls="collapseSchedule">
                <i class="fas fa-fw fa-calendar"></i>
                <span>Scheduling</span>
            </a>
            <div id="collapseSchedule" class="collapse" aria-labelledby="headingSchedule"
                 data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <h6 class="collapse-header">Room Schedule:</h6>
                    <a class="collapse-item" href="${cp}/room/schedule/add">Add Schedule</a>
                    <a class="collapse-item" href="${cp}/room/schedule/display">View Schedules</a>
                    <div class="collapse-divider"></div>
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

        <!-- Heading -->
        <div class="sidebar-heading">
            Academics
        </div>

        <!-- Nav Item - Batch Collapse Menu -->
        <li class="nav-item <c:if test="${fn:contains(activeNav, '/batch')}">active</c:if>">
            <a class="nav-link" href="${cp}/batch/display">
                <i class="fas fa-fw fa-users"></i>
                <span>View All Batch</span></a>
        </li>

        <!-- Nav Item - Course Collapse Menu -->
        <li class="nav-item <c:if test="${fn:contains(activeNav, '/course')}">active</c:if>">
            <a class="nav-link" href="${cp}/course/display">
                <i class="fas fa-fw fa-book"></i>
                <span>View Courses</span></a>
        </li>

        <!-- Nav Item - Module Collapse Menu -->
        <li class="nav-item <c:if test="${fn:contains(activeNav, '/module')}">active</c:if>">
            <a class="nav-link" href="${cp}/module/display">
                <i class="fas fa-fw fa-book"></i>
                <span>View Modules</span></a>
        </li>

        <!-- Nav Item - Exam Collapse Menu -->
        <li class="nav-item <c:if test="${fn:contains(activeNav, '/exam')}">active</c:if>">
            <a class="nav-link" href="${cp}/exam/display">
                <i class="fas fa-fw fa-pen"></i>
                <span>View Exams</span></a>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider">
    </c:if>

    <!-- Student only -->
    <c:if test="${fn:contains(auth.getCurrentUser().authority, 'ROLE_STUDENT')}">
        <!-- Nav Item - Transaction Collapse Menu -->
        <li class="nav-item <c:if test="${fn:contains(activeNav, '/exam')}">active</c:if>">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseStudentAcademic"
               aria-expanded="true" aria-controls="collapseStudentAcademic">
                <i class="fas fa-fw fa-book"></i>
                <span>Academics</span>
            </a>
            <div id="collapseStudentAcademic" class="collapse" aria-labelledby="headingStudentAcademic"
                 data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <h6 class="collapse-header">Exam Report</h6>
                    <a class="collapse-item" href="${cp}/exam/student/generate">Generate Reports</a>
                    <div class="collapse-divider"></div>
                </div>
            </div>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider">

        <!-- Nav Item - Transaction Collapse Menu -->
        <li class="nav-item <c:if test="${fn:contains(activeNav, '/transaction')}">active</c:if>">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseStudentTransaction"
               aria-expanded="true" aria-controls="collapseStudentTransaction">
                <i class="fas fa-fw fa-chart-bar"></i>
                <span>Transaction</span>
            </a>
            <div id="collapseStudentTransaction" class="collapse" aria-labelledby="headingStudentTransaction"
                 data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <h6 class="collapse-header">Your Transactions</h6>
                    <a class="collapse-item" href="${cp}/transaction/student/display">View Transactions</a>
                    <div class="collapse-divider"></div>
                </div>
            </div>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider">
    </c:if>

    <!-- Teacher only -->
    <c:if test="${fn:contains(auth.getCurrentUser().authority, 'ROLE_TEACHER')}">
        <!-- Nav Item - Academics Collapse Menu -->
        <li class="nav-item <c:if test="${fn:contains(activeNav, '/exam')}">active</c:if>">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseAcademicsss"
               aria-expanded="true" aria-controls="collapseAcademicsss">
                <i class="fas fa-fw fa-book-open"></i>
                <span>Academics</span>
            </a>
            <div id="collapseAcademicsss" class="collapse" aria-labelledby="headingAcademicsss"
                 data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <h6 class="collapse-header">Exam Information:</h6>
                    <a class="collapse-item" href="${cp}/exam/report/add">Add Student Report</a>
                    <a class="collapse-item" href="${cp}/exam/report/generate">Generate Student Report</a>
                    <div class="collapse-divider"></div>
                </div>
            </div>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider">
    </c:if>

    <!-- Academic Staff only -->
    <c:if test="${fn:contains(auth.getCurrentUser().authority, 'ROLE_ACADEMIC_STAFF')}">
        <!-- Nav Item - Academics Collapse Menu -->
        <li class="nav-item <c:if test="${fn:contains(activeNav, '/exam')}">active</c:if>">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseAcademicss"
               aria-expanded="true" aria-controls="collapseAcademicss">
                <i class="fas fa-fw fa-book-open"></i>
                <span>Academics</span>
            </a>
            <div id="collapseAcademicss" class="collapse" aria-labelledby="headingAcademicss"
                 data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <h6 class="collapse-header">Exam Information:</h6>
                    <a class="collapse-item" href="${cp}/exam/add">Add Exam</a>
                    <a class="collapse-item" href="${cp}/exam/display">View Exams</a>
                    <a class="collapse-item" href="${cp}/exam/assign">Assign Exam</a>
                    <a class="collapse-item" href="${cp}/exam/report/generate">Generate Student Report</a>
                    <div class="collapse-divider"></div>
                </div>
            </div>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider">
    </c:if>

    <!-- Operational Staff only -->
    <c:if test="${fn:contains(auth.getCurrentUser().authority, 'ROLE_OPERATIONAL_STAFF')}">

    </c:if>

    <!-- Admission Staff only -->
    <c:if test="${fn:contains(auth.getCurrentUser().authority, 'ROLE_ADMINISTRATOR') or fn:contains(auth.getCurrentUser().authority, 'ROLE_ADMISSION_STAFF')}">
        <!-- Heading -->
        <div class="sidebar-heading">
            Administration
        </div>

        <!-- Nav Item - Transaction Collapse Menu -->
        <li class="nav-item <c:if test="${fn:contains(activeNav, '/transaction')}">active</c:if>">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTransaction"
               aria-expanded="true" aria-controls="collapseTransaction">
                <i class="fas fa-fw fa-chart-bar"></i>
                <span>Transaction</span>
            </a>
            <div id="collapseTransaction" class="collapse" aria-labelledby="headingTransaction"
                 data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <h6 class="collapse-header">Transaction Management</h6>
                    <c:if test="${fn:contains(auth.getCurrentUser().authority, 'ROLE_ADMISSION_STAFF')}">
                        <a class="collapse-item" href="${cp}/transaction/add">Add Transaction</a>
                    </c:if>
                    <a class="collapse-item" href="${cp}/transaction/display">View Transactions</a>
                    <div class="collapse-divider"></div>
                </div>
            </div>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider">
    </c:if>

    <!-- Sidebar Toggler (Sidebar) -->
    <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
    </div>

</ul>
