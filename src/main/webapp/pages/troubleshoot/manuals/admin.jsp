<%--
  Created by IntelliJ IDEA.
  User: elvin
  Date: 6/22/19
  Time: 8:17 AM
  To change this template use File | Settings | File Templates.
--%>
<div class="card">
    <div class="card-header" id="heading2">
        <h5 class="mb-0">
            <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapse2" aria-expanded="false" aria-controls="collapse2">
                How to add a user?
            </button>
        </h5>
    </div>
    <div id="collapse2" class="collapse" aria-labelledby="heading2" data-parent="#accordion">
        <div class="card-body">
            <ul>
                <li>Open a form to add a user from navigation <b>User</b> on the left sidebar.</li>
                <li>Add required user details</li>
                <li>Submit the form</li>
                <li>In case of teacher or student, separate user profile will be created when they (teacher or student) add themselves.</li>
                <li>See the list of users by navigating through sidebar.</li>
                <li>Edit or delete user from display users page</li>
            </ul>
        </div>
    </div>
</div>
<div class="card">
    <div class="card-header" id="heading3">
        <h5 class="mb-0">
            <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapse3" aria-expanded="false" aria-controls="collapse3">
                How to manage organization information?
            </button>
        </h5>
    </div>
    <div id="collapse3" class="collapse" aria-labelledby="heading3" data-parent="#accordion">
        <div class="card-body">
            <ul>
                <li>To view or edit the general organization information, navigate to sidebar navigation named <b>Organization</b>.</li>
                <li>Under <b>Organization Information</b>, you can view or edit the information.</li>
            </ul>
            <hr>
            <ul>
                <li>Similarly, under <b>Building Information</b>, you can add building.</li>
                <li>After addition, you can view all the buildings. Moreover, you can edit or delete it.</li>
            </ul>
            <hr>
            <ul>
                <li>Similarly, under <b>Room Information</b>, you can add room.</li>
                <li>Pre-requisites to add a room:
                    <ul>
                        <li>You need a building first to add a room under specified building.</li>
                    </ul>
                </li>
                <li>After addition, you can view all the rooms. Moreover, you can edit or delete it.</li>
            </ul>
        </div>
    </div>
</div>
<div class="card">
    <div class="card-header" id="heading4">
        <h5 class="mb-0">
            <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapse4" aria-expanded="false" aria-controls="collapse4">
                How to manage academics related information?
            </button>
        </h5>
    </div>
    <div id="collapse4" class="collapse" aria-labelledby="heading4" data-parent="#accordion">
        <div class="card-body">
            <ul>
                <li>Under <b>Batch Information</b>
                    <ul>
                        <li>You can add a students batch</li>
                        <li>After addition, you can view, edit or delete them.</li>
                        <li>To assign a batch to the student,
                            <ul>
                                <li>Pre-requisites: a student profile must be created by the student</li>
                            </ul>
                        </li>
                        <li>To enroll batch in course,
                            <ul>
                                <li>Pre-requisites: Courses must be added first.</li>
                            </ul>
                        </li>
                        <li>
                            From the provided form you can choose the batch under which courses will be listed.
                            To enroll just check the required courses.
                        </li>
                    </ul>
                </li>
                <li>Under <b>Course Information</b>
                    <ul>
                        <li>You can add a course.</li>
                        <li>After addition, you can view, edit or delete them.</li>
                    </ul>
                </li>
                <li>Under <b>Module Information</b>
                    <ul>
                        <li>You can add a module
                            <ul>
                                <li>Pre-requisites: Courses must be added first.</li>
                            </ul>
                        </li>
                        <li>After addition, you can view, edit or delete them.</li>
                        <li>To assign a module to the teacher,
                            <ul>
                                <li>Pre-requisites: a teacher profile must be created by the teacher</li>
                            </ul>
                        </li>
                    </ul>
                </li>
                <li>Under <b>Exam Information</b>
                    <ul>
                        <li>You can view list of exams added by academic staffs.</li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>
<div class="card">
    <div class="card-header" id="heading5">
        <h5 class="mb-0">
            <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapse5" aria-expanded="false" aria-controls="collapse5">
                How to add a room schedule?
            </button>
        </h5>
    </div>
    <div id="collapse5" class="collapse" aria-labelledby="heading5" data-parent="#accordion">
        <div class="card-body">
            <ul>
                <li>Open a form to add a room schedule from navigation <b>Scheduling</b> on the left sidebar.</li>
                <li>Add required details
                    <ul>
                        <li>Pre-requisites:
                            <ul>
                                <li>Room must be added first.</li>
                                <li>Batch must be added first.</li>
                                <li>Teacher Profile must be created first.</li>
                            </ul>
                        </li>
                    </ul>
                </li>
                <li>Submit the form</li>
                <li>See the list of schedules by navigating through sidebar.</li>
                <li>Edit or delete the schedule from view schedules page</li>
            </ul>
        </div>
    </div>
</div>
<div class="card">
    <div class="card-header" id="heading6">
        <h5 class="mb-0">
            <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapse6" aria-expanded="false" aria-controls="collapse6">
                How to view troubleshoot problems?
            </button>
        </h5>
    </div>
    <div id="collapse6" class="collapse" aria-labelledby="heading6" data-parent="#accordion">
        <div class="card-body">
            <ul>
                <li>Open a problems page from navigation <b>Troubleshooting</b> on the left sidebar.</li>
                <li>See the list of problems added by different users in the timeline view.</li>
            </ul>
        </div>
    </div>
</div>
<div class="card">
    <div class="card-header" id="heading7">
        <h5 class="mb-0">
            <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapse7" aria-expanded="false" aria-controls="collapse7">
                How to view student transactions?
            </button>
        </h5>
    </div>
    <div id="collapse7" class="collapse" aria-labelledby="heading7" data-parent="#accordion">
        <div class="card-body">
            <ul>
                <li>Open a transaction page from navigation <b>Transaction</b> on the left sidebar.</li>
                <li>Enter respective student ID to view the transaction.</li>
                <li>See the list of transactions in the timeline view.</li>
            </ul>
        </div>
    </div>
</div>