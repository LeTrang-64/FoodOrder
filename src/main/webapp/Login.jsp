<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">


<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<title>Login</title>
<link href="css/index_bg.css" rel="stylesheet">
</head>
<body class="bg" >

	
	<div class="container-fluid" >
		<div class="row" >
			<div class="col-md-4 col-sm-4 col-xs-12"></div>
			<div class="col-md-4 col-sm-4 col-xs-12">
				<form action="<%=request.getContextPath()%>/loginCheck"
					method="post" class="form-container">
					<h1 class="header">Login for awesome food!</h1>
					<div class="form-group">
						<label for="InputEmail">Email</label> <input type="email"
							name="email" class="form-control" id="InputEmail"
							placeholder="email" required>
					</div>
					<div class="form-group">
						<label for="InputPassword">Password</label> <input type="password"
							name="password" class="form-control" id="InputPassword"
							placeholder="Password" required>
					</div>

					<div class="checkbox">
						<label> <input type="checkbox"> Remember Me
						</label>
					</div>
					<button type="submit" class="btn btn-success btn-block">Submit</button>
					<div align="JUSTIFY">
						New user?<a class="register" href="Register.jsp"><b> <i>Register
									here</i></b></a>
					</div>
				</form>
			</div>
			<div class="col-md-4 col-sm-4 col-xs-12"></div>
		</div>
	</div>
</body>
</html>
