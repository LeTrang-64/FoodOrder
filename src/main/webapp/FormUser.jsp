<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/index_bg.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<title>Form</title>
<style type="text/css">
body {
	background: url('css/bg_2.jpg');
}
</style>
</head>
<body class="bg-form ">
	<div class="container-fluid">
		<c:if test="${USER != null}">
			<div class="row">
				<div class="col-md-2 col-sm-2 col-xs-12"></div>
				<div class="col-md-8 col-sm-8 col-xs-12">


					<!-- request.getContextPath():trả về đường dẫn gốc của ứng dụng
		 -->

					<form action="<%=request.getContextPath()%>/updateUser"
						method="post" class="form-container" style="margin-top: 10vh;">
						<c:if test="${user != null}">
							<input type="hidden" name="id"
								value="<c:out value='${user.user_id}' />" />
						</c:if>

						<div class="form-group">
							<label for="InputName">First Name</label> <input name="firstName"
								class="form-control" id="InputName"
								value="<c:out value='${user.first_name}' />" required>
						</div>
						<div class="form-group">
							<label for="InputName">Last Name</label> <input name="lastName"
								class="form-control" id="InputName"
								value="<c:out value='${user.last_name}'/>" required />
						</div>
						<div class="form-group">
							<label for="InputName">Phone</label> <input name="phoneNumber"
								class="form-control" id="InputName"
								value="<c:out value='${user.phone_number}' />" required />
						</div>

						<div class="form-group">
							<label for="exampleInputEmail1">Email address</label> <input
								type="email" name="email" class="form-control"
								id="exampleInputEmail1"
								value="<c:out value='${user.user_email}' />" required />
						</div>

						<div class="form-group">
							<label for="InputAddress">Address</label> <input name="address"
								class="form-control" id="InputAddress"
								value="<c:out value='${user.shipping_address}' />" required />
						</div>


						<button type="submit" class="btn btn-success btn-block">Save</button>

					</form>
				</div>
				<div class="col-md-2 col-sm-2 col-xs-12"></div>
			</div>



		</c:if>
	</div>

</body>
</html>