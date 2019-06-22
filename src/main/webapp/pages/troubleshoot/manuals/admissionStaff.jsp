<%--
  Created by IntelliJ IDEA.
  User: elvin
  Date: 6/22/19
  Time: 10:23 AM
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="exceptAdmin.jsp"/>
<div class="card">
    <div class="card-header" id="heading3">
        <h5 class="mb-0">
            <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapse3" aria-expanded="false" aria-controls="collapse3">
                How to manage student transaction?
            </button>
        </h5>
    </div>
    <div id="collapse3" class="collapse" aria-labelledby="heading3" data-parent="#accordion">
        <div class="card-body">
            <ul>
                <li>You can add student transaction from the navigation <b>Transaction</b> in the sidebar</li>
                <li>Pre-requisites:
                    <ul>
                        <li>You need a student ID.</li>
                        <li>You need a course first.</li>
                    </ul>
                </li>
                <li>After addition, you can view or delete the transaction from <b>View Transaction</b>.
                    <ul>
                        <li>Pre-requisites: You need a student ID to generate the transaction history</li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>
