<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="org.trang.test.model.User"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Manager</title>

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
<link
	href="https://fonts.googleapis.com/css2?family=Great+Vibes&display=swap"
	rel="stylesheet">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Great+Vibes&family=Nunito&display=swap"
	rel="stylesheet">
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
						<div>
							<a class="navbar-brand" href="AdminPage.jsp"><i>Order
									Food</i></a>
						</div>


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
					<h1>Foods Management</h1>
					<h2>
						<a href="<%=request.getContextPath()%>/newFood">Add New Food</a>
						&nbsp;&nbsp;&nbsp; <a
							href="<%=request.getContextPath()%>/listFood">List All Foods</a>

					</h2>
				</center>


				<div align="center">

					<div class="table">
						<table border="1" cellpadding="5"
							class="table table-hover table-condensed" style="width: 100%">

							<tr class="head-col">
								<th style="width: 10%">ID</th>
								<th style="width: 50%">Name</th>
								<th style="width: 10%">Price</th>
								<th style="width: 30%">Actions</th>
							</tr>
							<c:forEach var="food" items="${listFoods}">


								<tr>
									<td><c:out value="${food.foodID}" /></td>
									<td><c:out value="${food.foodName}" /></td>
									<td><c:out value="${food.price}" /></td>
									<td><a
										href="<%=request.getContextPath() %>/editFood?id=<c:out value='${food.foodID}' />">Edit</a>
										&nbsp;&nbsp;&nbsp;&nbsp; <a
										href="<%=request.getContextPath() %>/deleteFood?id=<c:out value='${food.foodID}' />">Delete</a>
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
