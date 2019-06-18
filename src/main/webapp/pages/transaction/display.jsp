<%--
  Created by IntelliJ IDEA.
  User: elvin
  Date: 6/18/19
  Time: 3:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../common/pageHeader.jsp"></jsp:include>

<!-- Page Heading -->
<h1 class="h3 mb-4 text-gray-800"><i class="fas fa-chart-area fa-2x text-black-50"></i> View Student Transaction</h1>

<!-- Form -->
<div class="row">
    <div class="col-lg-12">
        <div class="card shadow mb-4">
            <!-- Card Header - Dropdown -->
            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <h6 class="m-0 font-weight-bold text-primary">View Transactions</h6>
            </div>
            <!-- Card Body -->
            <div class="card-body">
                <jsp:include page="../common/alertCard.jsp"></jsp:include>

                <div class="row">
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="studentId" placeholder="Enter student ID to search the transaction">
                    </div>
                    <div class="col-md-4">
                        <button type="button" class="btn btn-block btn-outline-primary" onclick="getTransactions('${pageContext.request.contextPath}', $('#studentId').val())">Search</button>
                    </div>
                </div>

                <hr />

                <ul class="timeline" id="transactionTimeline">
                    <h4>No Transactions recorded</h4>
                </ul>

            </div>
        </div>
    </div>
</div>

<jsp:include page="../common/pageFooter.jsp"></jsp:include>

<script>
    function getTransactions(pageContext, studentId) {
        Swal.showLoading();
        try {
            let convertToNum = Number(studentId);
            $.ajax({
                    url: '/transaction/display/student/' + studentId,
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
                            loadStudentTransactions(data);
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
        } catch(err) {
            setTimeout(function () {
                Swal.fire({
                    type: 'error',
                    title: 'Oops...',
                    text: 'Invalid Student ID!'
                });
            }, 2000);
        }

    }

    function loadStudentTransactions(data) {

        // remove all children
        let transactionDiv = document.getElementById('transactionTimeline');
        while (transactionDiv.firstChild) {
            transactionDiv.removeChild(transactionDiv.firstChild);
        }

        let studentDetail = data.object['StudentDetail'];
        let allTransactions = data.object['Transactions'];

        let studentHeader = '<h4>Displaying transaction of ' + studentDetail.fullName + ' (Batch: ' + studentDetail.batch.name + ')</h4>';
        $('#transactionTimeline').append(studentHeader);

        if (allTransactions.length > 0) {
            for (let i = 0; i < allTransactions.length; i++) {
                let timeline =
                    '<li>' +
                    '<label class="text-primary">' + allTransactions[i].studentProfile.fullName + '</label>' +
                    '<label class="text-primary float-right">' + new Date(allTransactions[i].transactionDate).toLocaleDateString() + '</label>' +
                    '<p>' + allTransactions[i].remarks + '</p>' +
                    '<label class="text-primary">Transaction for Course: ' + allTransactions[i].course.name + '</label><br />';
                if (allTransactions[i].complete) {
                    timeline += '<label class="text-primary">Status: Completed</label>';
                } else {
                    timeline += '<label class="text-primary">Status: In-complete</label>';
                }
                timeline += '<hr /></li>';

                $('#transactionTimeline').append(timeline);
            }
        } else {
            $('#transactionTimeline').append('<h6>No transactions recorded</h6>');
        }
    }
</script>