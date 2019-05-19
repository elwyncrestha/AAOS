<%--
  Created by IntelliJ IDEA.
  User: elvin
  Date: 3/30/19
  Time: 9:09 AM
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="../common/pageHeader.jsp"></jsp:include>

<!-- Page Heading -->
<h1 class="h3 mb-4 text-gray-800"><i class="fas fa-tachometer-alt fa-2x text-black-50"></i> Dashboard</h1>

<!-- Anonymous Users Card -->
<div class="col-xl-6 col-md-6 mb-4">
    <div class="card border-left-info shadow h-100 py-2">
        <div class="card-body">
            <div class="row no-gutters align-items-center">
                <div class="col mr-2">
                    <div class="text-xs font-weight-bold text-info text-uppercase mb-1">Anonymous Users Login (Today)</div>
                    <div class="h5 mb-0 font-weight-bold text-gray-800">20</div>
                </div>
                <div class="col-auto">
                    <i class="fas fa-user-shield fa-2x text-gray-300"></i>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../common/pageFooter.jsp"></jsp:include>