<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="org.trang.test.model.User"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Manager</title>
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet">

<link href="https://fonts.googleapis.com/css?family=Lobster"
	rel="stylesheet" type="text/css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Great+Vibes&display=swap" rel="stylesheet">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Great+Vibes&family=Nunito&display=swap" rel="stylesheet">
<link href="css/FoodManager.css" rel="stylesheet">


</head>
<body>
	<div class="form_fix">
		
			<%
				User user = (User) request.getSession().getAttribute("USER");
					if (user != null && user.getIsManager() == 1) {
			%>
				<div class="admin-page1">
					<nav class="navbar ">
						<div class="container">
							<a class="navbar-brand" href="AdminPage.jsp">Order Food</a>


							<div class="collapse navbar-collapse" id="collapsibleNavbar">
								<ul class="navbar-nav ml-auto ">
									<!-- day cac muc sang trai-->
									<li class="nav-item "><a class="nav-link"
										href="AdminPage.jsp">Home</a></li>



									<li class="nav-item"><a
										href="<%=request.getContextPath()%>/listFood" class="nav-link"><i
											class="fa fa-bread"></i> Food Manager</a></li>

									<li class="nav-item"><a
										href="<%=request.getContextPath()%>/listUser" class="nav-link"><i
											class="fa fa-book"></i> Customer Manager</a></li>
									<li class="nav-item"><a href="#" class="nav-link"> <i
											class="fa fa-user"></i> <%=((User) session.getAttribute("USER")).getFirst_name()%>
											<%=((User) session.getAttribute("USER")).getLast_name()%>
									</a></li>

									<li class="nav-item"><a href="Logout.jsp" class="nav-link"><i
											class="fa fa-fw fa-sign-out"></i>Logout</a></li>

								</ul>
							</div>
						</div>
					</nav>


					<center>
						<h1>Users Management</h1>
						<h2>
							<a href="<%=request.getContextPath()%>/listUser">List All
								Users</a>

						</h2>
					</center>


					<div align="center">
						
						<div class="table">
							<table border="1" cellpadding="5"
								class="table table-hover table-condensed" style="width: 100%">

								<tr>
									<th style="width: 10%">ID</th>
									<th style="width: 30%">Name</th>
									<th style="width: 30%">email</th>
									<th style="width: 10%">Acc</th>
									<th style="width: 20%">Actions</th>
								</tr>
								<c:forEach var="user" items="${listUsers}">


									<tr>
										<td><c:out value="${user.user_id}" /></td>
										<td><c:out value="${user.first_name}  ${ user.last_name}" /></td>
										<td><c:out value="${user.user_email}" /></td>
										<c:if test="${user.isActive==1}">
											<td><c:out value="ACTIVE" /></td>
										</c:if>
										<c:if test="${user.isActive==0}">
											<td><c:out value="FROZEN" /></td>
										</c:if>

										<td><a
											href="<%=request.getContextPath() %>/editUser?id=<c:out value='${user.user_id}' />">Edit</a>
											&nbsp;&nbsp;&nbsp;&nbsp; <a
											href="<%=request.getContextPath() %>/activeUser?id=<c:out value='${user.user_id}'/>&is_active=<c:out value='${user.isActive}' />  " >Active</a>
										</td>
									</tr>

								</c:forEach>
							</table>
						</div>
					</div>
				</div>
			<%
				} else {
						response.sendRedirect("Login.jsp");
					}
			%>
	</div>


</body>
</html>