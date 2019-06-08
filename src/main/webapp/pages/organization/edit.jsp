<%--
  Created by IntelliJ IDEA.
  User: elvin
  Date: 5/18/19
  Time: 6:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../common/pageHeader.jsp"></jsp:include>
<!-- Page Heading -->
<h1 class="h3 mb-4 text-gray-800"><i class="fas fa-university fa-2x text-black-50"></i> Edit Organization Information
</h1>

<!-- User Cards -->
<jsp:include page="organizationCards.jsp"></jsp:include>

<div class="row">
    <div class="col-lg-12">
        <jsp:include page="../common/alertCard.jsp"></jsp:include>
    </div>
</div>

<div class="row">
    <div class="col-lg-12">
        <div class="card shadow mb-4">
            <!-- Card Header - Dropdown -->
            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <h6 class="m-0 font-weight-bold text-primary">Edit Organization Information</h6>
            </div>
            <!-- Card Body -->
            <div class="card-body">
                <c:choose>
                    <c:when test="${newOrganization}">
                        <div class="p-5">
                            <form method="post"
                                  action="${pageContext.request.contextPath}/organization/add">

                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                                <div class="form-group">
                                    <input type="text" class="form-control" id="name"
                                           placeholder="Organization Name" name="name" required
                                           value="${organization.name}">
                                    <p class="para-error text-right">${error.name}</p>
                                </div>
                                <div class="form-group">
                                    <label for="establishment">Establishment Date</label>
                                    <input type="date" class="form-control" id="establishment" name="establishment"
                                           pattern="yyyy-MM-dd" required value="${organization.establishment}">
                                    <p class="para-error text-right">${error.establishment}</p>
                                </div>
                                <div class="form-group">
                                    <textarea class="form-control" id="description"
                                              placeholder="Description of Organization" name="description"
                                              required rows="5">${organization.description}</textarea>
                                    <p class="para-error text-right">${error.description}</p>
                                </div>
                                <button type="submit" class="btn btn-primary btn-user btn-block">Add</button>
                            </form>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="p-5">
                            <form method="post" action="${pageContext.request.contextPath}/organization/edit">

                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                                <input type="hidden" name="id" value="${organization.id}">

                                <div class="form-group">
                                    <input type="text" class="form-control" id="name"
                                           placeholder="Organization Name" name="name" required
                                           value="${organization.name}">
                                    <p class="para-error text-right">${error.name}</p>
                                </div>
                                <div class="form-group">
                                    <label for="establishment">Establishment Date</label>
                                    <input type="date" class="form-control" id="establishment" name="establishment"
                                           pattern="yyyy-MM-dd" required value="${organization.establishment}">
                                    <p class="para-error text-right">${error.establishment}</p>
                                </div>
                                <div class="form-group">
                                    <textarea class="form-control" id="description"
                                              placeholder="Description of Organization" name="description"
                                              required rows="5">${organization.description}</textarea>
                                    <p class="para-error text-right">${error.description}</p>
                                </div>
                                <button type="submit" class="btn btn-primary btn-user btn-block">Edit</button>
                            </form>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

    </div>
</div>


<jsp:include page="../common/pageFooter.jsp"></jsp:include>
