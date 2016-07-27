<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="login page">
<meta name="author" content="qin zheng">

<!-- Bootstrap core CSS -->
<link href="CSS-EXT/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="CSS/login.css" rel="stylesheet">

<!-- jquery lib -->
<script src="JS-EXT/jquery-1.11.1.js"></script>

<!-- bootstrap javascript lib -->
<script src="JS-EXT/bootstrap.min.js"></script>

<!-- login action -->
<script src="JS/loginAction.js"></script>

<title>login page</title>
</head>
<body>

	<div class="container">

		<form class="form-signin">
			<h2 class="form-signin-heading">Please sign in</h2>
			<label for="inputEmail" class="sr-only">Email address</label> <input
				id="inputEmail" class="form-control" placeholder="Email address"
				required autofocus> <label for="inputPassword"
				class="sr-only">Password</label> <input type="password"
				id="inputPassword" class="form-control" placeholder="Password"
				required>
			<div class="checkbox">
				<label> <input type="checkbox" value="remember-me">
					Remember me
				</label>
			</div>
			<button class="btn btn-lg btn-primary btn-block" id="login"
				type="submit">Sign in</button>
		</form>

	</div>
	<!-- /container -->

	<form action="login.action" method="post">
		<input name="name" type="text" /> <input name="password" type="text" />
		<button type="submit">login</button>
	</form>
</body>
</html>