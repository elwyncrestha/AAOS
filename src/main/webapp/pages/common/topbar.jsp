<%--
  Created by IntelliJ IDEA.
  User: elvin
  Date: 4/13/19
  Time: 7:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"></c:set>

<nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

    <a class="navbar-brand navbar-hide">Automated Academic Organization System</a>

    <!-- Topbar Navbar -->
    <ul class="navbar-nav ml-auto">

        <!-- Nav Item - Alerts -->
        <li class="nav-item dropdown no-arrow mx-1">
            <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button"
               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" onclick="loadNotifications()">
                <i class="fas fa-bell fa-fw"></i>
                <!-- Counter - Alerts -->
                <span class="badge badge-danger badge-counter" id="notificationCounter">0</span>
            </a>
            <!-- Dropdown - Alerts -->
            <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                 aria-labelledby="alertsDropdown">
                <h6 class="dropdown-header">
                    Notifications
                </h6>
                <div id="userNotifications">

                </div>
            </div>
        </li>

        <div class="topbar-divider d-none d-sm-block"></div>

        <!-- Nav Item - User Information -->
        <li class="nav-item dropdown no-arrow">
            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span class="mr-2 d-none d-lg-inline text-gray-600 small"><c:out value="${pageContext.request.userPrincipal.name}"></c:out></span>
                <i class="fas fa-user fa-sm fa-fw mr-2 text-black-50"></i>
            </a>
            <!-- Dropdown - User Information -->
            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                 aria-labelledby="userDropdown">
                <a class="dropdown-item" href="${cp}/user/profile">
                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                    Profile
                </a>
                <a class="dropdown-item" href="${cp}/user/password/update">
                    <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                    Change Password
                </a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                    Logout
                </a>
            </div>
        </li>

    </ul>

</nav>

<script>
    function loadNotifications() {
        $.ajax({
                url: '/notification/pageable/3',
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
                        loadPageable(data);
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

    function loadPageable(data) {
        // remove all children
        let notificationDiv = document.getElementById('userNotifications');
        while (notificationDiv.firstChild) {
            notificationDiv.removeChild(notificationDiv.firstChild);
        }

        let notifications = data.object;
        if (notifications.length > 0) {
            for (let i = 0; i < notifications.length; i++) {

                let notification =
                    '<div class="dropdown-item d-flex align-items-center">' +
                    '<div class="mr-3">' +
                    '<div class="icon-circle ' + notifications[i].background + '">' +
                    '<i class="fas ' + notifications[i].icon + ' text-white"></i>' +
                    '</div>' +
                    '</div>' +
                    '<div>' +
                    '<div class="small text-primary">' + new Date(notifications[i].createdAt).toLocaleString() + '</div>' +
                    '<span class="font-weight-bold">' + notifications[i].description + '</span><br />' +
                    '<a href="" onclick="markAsRead(' + notifications[i].id + ')"><u>Mark as read</u></a>' +
                    '</div>' +
                    '</div>';

                $('#userNotifications').append(notification);
            }

        } else {
            $('#userNotifications').append(
                '<div class="dropdown-item d-flex align-items-center">' +
                '<span class="font-weight-bold">No notifications</span>' +
                '</div>');
        }
        $('#userNotifications').append('<a class="dropdown-item text-center small text-gray-500" href="${pageContext.request.contextPath}/notification/all">Show All Alerts</a>');
    }

    function markAsRead(notificationId) {
        $.ajax({
                url: '/notification/read/' + notificationId,
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
                        countNotification();
                        loadNotifications();
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
</script>