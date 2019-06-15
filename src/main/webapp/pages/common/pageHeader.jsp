<%--
  Created by IntelliJ IDEA.
  User: elvin
  Date: 4/13/19
  Time: 6:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="meta.jsp"></jsp:include>
    <title>Automated Academic Organization System</title>

    <!-- Custom fonts for this template-->
    <link href="${cp}/resources/vendors/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
          rel="stylesheet">

    <!-- DataTables -->
    <link href="${cp}/resources/vendors/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

    <!-- ClockPicker Stylesheet -->
    <link rel="stylesheet" type="text/css" href="${cp}/resources/vendors/clockpicker-gh-pages/dist/bootstrap-clockpicker.min.css">

    <!-- Custom styles for this template-->
    <link href="${cp}/resources/css/sb-admin-2.min.css" rel="stylesheet">
    <link href="${cp}/resources/css/aaos.css" rel="stylesheet">

</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Sidebar -->
    <jsp:include page="../common/sidebar.jsp"></jsp:include>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Topbar -->
            <jsp:include page="../common/topbar.jsp"></jsp:include>
            <!-- End of Topbar -->

            <!-- Begin Page Content -->
            <div class="container-fluid">
