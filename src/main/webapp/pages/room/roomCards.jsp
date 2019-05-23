<%--
  Created by IntelliJ IDEA.
  User: elvin
  Date: 5/23/19
  Time: 8:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Content Row -->
<div class="row">
    <div class="col-xl-6 col-md-6 mb-4">
        <div class="card border-left-primary shadow h-100 py-2">
            <div class="card-body">
                <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                        <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">Lecture Rooms</div>
                        <div class="h5 mb-0 font-weight-bold text-gray-800">${lectureRoomCount}</div>
                    </div>
                    <div class="col-auto">
                        <i class="fas fa-columns fa-2x text-gray-300"></i>
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
                        <div class="text-xs font-weight-bold text-success text-uppercase mb-1">Lab Rooms</div>
                        <div class="h5 mb-0 font-weight-bold text-gray-800">${labRoomCount}</div>
                    </div>
                    <div class="col-auto">
                        <i class="fas fa-columns fa-2x text-gray-300"></i>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>