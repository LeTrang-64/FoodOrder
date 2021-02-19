<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Foods Store Application</title>
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
	background: url('imgs/lunch-4.jpg');
	
}
</style>
</head>
<body class="bg-form ">
	<center>
		<h1>Foods Management</h1>
		<h2><a
				href="<%=request.getContextPath()%>/listFood">List All Foods</a>

		</h2>
	</center>

	<div class="container-fluid">
		<c:if test="${USER != null}">
			<caption >
				<h2 style="text-align: center;">
					<c:if test="${food != null}">
                        Edit Food
                    </c:if>
					<c:if test="${food == null}">
                        Add New Food
                    </c:if>
				</h2>
			</caption>
			<div class="row">
				<div class="col-md-2 col-sm-2 col-xs-12"></div>
				<div class="col-md-8 col-sm-8 col-xs-12">


					<!-- request.getContextPath():trả về đường dẫn gốc của ứng dụng
		 -->

					<c:if test="${food != null}">
						<form action="<%=request.getContextPath()%>/updateFood"
							method="post" class="form-container" style="margin-top: 10vh;">
					</c:if>
					<c:if test="${food == null}">
						<form action="<%=request.getContextPath()%>/createFood"
							method="post" class="form-container" style="margin-top: 10vh;">
					</c:if>

					<c:if test="${food != null}">
						<input type="hidden" name="id"
							value="<c:out value='${food.foodID}' />" />
					</c:if>


					<div class="form-group">
						<label for="InputName">Name</label> <input type="text" name="name" class="form-control"
							value="<c:out value='${food.foodName}' />" />
					</div>
					<div class="form-group">
						<label for="InputPrice">Price</label> <input type="text" class="form-control"
							name="price" size="5" value="<c:out value='${food.price}' />" />
					</div>


					<div class="form-group">
						<label for="InputImage">Image</label> <input type="text" class="form-control"
							name="img" value="<c:out value='${food.img}' />" />
					</div>
					<div class="form-group">
						<label for="InputInfo">Mô Tả: </label> <input type="text" class="form-control"
							name="info" value="<c:out value='${food.info}' />" />
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