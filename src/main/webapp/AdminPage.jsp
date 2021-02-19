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

<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Great+Vibes&display=swap"
	rel="stylesheet">

<link href="css/AdminPage.css" rel="stylesheet">

</head>
<body>
	<%  User user=(User) request.getSession().getAttribute("USER");
	  if(user!=null){ %>
		<div class="container">
			<div class="content">
				<h1>Wellcome</h1>
				<div>
					<a href="<%=request.getContextPath()%>/listFood"
						class="submit btn-solid round input-submit">Quản lý đồ ăn</a> <br>
					<a href="<%=request.getContextPath()%>/listUser"
						class="submit btn-solid round input-submit">Quản lý khách hàng</a>
					<br> <a href="#" class="submit btn-solid round input-submit">Quản
						lý nhân sự </a>
				</div>
			</div>
		</div>
		<%}else{  response.sendRedirect("Login.jsp");} %>
		
	
</body>
</html>
