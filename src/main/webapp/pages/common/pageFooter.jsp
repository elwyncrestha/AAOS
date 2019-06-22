<%--
  Created by IntelliJ IDEA.
  User: elvin
  Date: 4/13/19
  Time: 7:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
</div>
<!-- /.container-fluid -->

</div>
<!-- End of Main Content -->

<!-- Footer -->
<jsp:include page="../common/footer.jsp"></jsp:include>
<!-- End of Footer -->

</div>
<!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                <a class="btn btn-primary" href="${cp}/logout">Logout</a>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<c:set var="activeNav" value="${requestScope['javax.servlet.forward.request_uri']}"/>

<!-- Bootstrap core JavaScript-->
<script src="${cp}/resources/vendors/jquery/jquery.min.js"></script>
<script src="${cp}/resources/vendors/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="${cp}/resources/vendors/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="${cp}/resources/js/sb-admin-2.min.js"></script>

<!-- DataTables -->
<script src="${cp}/resources/vendors/datatables/jquery.dataTables.min.js"></script>
<script src="${cp}/resources/vendors/datatables/dataTables.bootstrap4.min.js"></script>
<script src="${cp}/resources/js/demo/datatables-demo.js"></script>

<!-- SweetAlert2 -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@8"></script>
<script src="${cp}/resources/vendors/sweetalert2@8.js"></script>

<!-- ClockPicker script -->
<script type="text/javascript" src="${cp}/resources/vendors/clockpicker-gh-pages/dist/bootstrap-clockpicker.min.js"></script>
<script type="text/javascript">
    $('.clockpicker').clockpicker();
</script>

<!-- Chart JS -->
<script src="${cp}/resources/vendors/chart.js/Chart.min.js"></script>

<script type="text/javascript">
    var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");
    var csrfToken = $("meta[name='_csrf']").attr("content");


    $(function () {
        countNotification();
        <c:if test="${fn:contains(activeNav, '/dashboard')}">
            loadUserPieChartData();
        </c:if>
    });

    function countNotification() {
      $.ajax({
            url: '/notification/count',
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
                $('#notificationCounter').html(data.object);
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

    // Set new default font family and font color to mimic Bootstrap's default styling
    Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
    Chart.defaults.global.defaultFontColor = '#858796';

    function loadUserPieChartData() {
      $.ajax({
            url: '/user/count/pieChart',
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
                loadUserPieChart(data);
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

    function loadUserPieChart(data)
    {
      const labels = data.object['UserTypes'];
      let chartData = [];
      let colorCode = [];
      for (let i = 0; i < labels.length; i ++) {
        chartData.push(data.object['Data'][labels[i]]);
        colorCode.push(data.object['ColorCode'][labels[i]]);
      }

      // Pie Chart Example
      var ctx = document.getElementById("userPieChart");
      var userPieChart = new Chart(ctx, {
        type: 'doughnut',
        data: {
          labels: [...labels],
          datasets: [{
            data: [...chartData],
            backgroundColor: [...colorCode],
            hoverBorderColor: "rgb(101,101,105)",
          }],
        },
        options: {
          maintainAspectRatio: false,
          tooltips: {
            backgroundColor: "rgb(255,255,255)",
            bodyFontColor: "#858796",
            borderColor: '#dddfeb',
            borderWidth: 1,
            xPadding: 15,
            yPadding: 15,
            displayColors: false,
            caretPadding: 10,
          },
          legend: {
            display: true
          },
          cutoutPercentage: 50,
        },
      });
    }

</script>

</body>

</html>
