<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/css/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Edu+NSW+ACT+Foundation:wght@400..700&display=swap" rel="stylesheet">
</head>
<body>
	<div class="bg-image d-flex justify-content-center "
		style="background-image: url('https://images.pexels.com/photos/317377/pexels-photo-317377.jpeg?auto=compress&amp;cs=tinysrgb&amp;w=1260&amp;h=750&amp;dpr=2');
		background-size: cover; height: 100vh; width: 100vw;">
		<div class="container mt-5">

			<h1 class="d-flex ml-3" style="font-size: 4em; color:rgb(50,72,86); ">Focus Timer</h1>

			<div class="d-flex mt-5">
				<!-- Register -->
				<div class="mx-auto" style="position: absolute; top:18vh;left: 30vw;">
					<h3 >Register</h3>
					<form:form action="/register" method="POST"
						modelAttribute="newUser">
						<div class="form-group">
							<form:label path="username">Username: </form:label>
							<form:input path="username" class="form-control" />
							<form:errors path="username" class="text-danger"></form:errors>
						</div>
						<div class="form-group">
							<form:label path="email">Email: </form:label>
							<form:input path="email" class="form-control" />
							<form:errors path="email" class="text-danger"></form:errors>
						</div>
						<div class="form-group">
							<form:label path="password">Password: </form:label>
							<form:input path="password" type="password" class="form-control" />
							<form:errors path="password" class="text-danger"></form:errors>
						</div>
						<div class="form-group ">
							<form:label path="confirmPassword">Confirmed Password: </form:label>
							<form:input path="confirmPassword" type="password"
								class="form-control" />
							<form:errors path="confirmPassword" class="text-danger"></form:errors>
						</div>
						<button type="submit" class="btn btn-primary mt-3">Register</button>
					</form:form>
				</div>
				<!-- Log in -->
				<div class="mx-auto" style="position: absolute; bottom: 20vh;right: 20vw;">
					<h3>Log in</h3>
					<form:form action="/login" method="POST" modelAttribute="newLogin">
						<div class="form-group">
							<form:label path="email">Email: </form:label>
							<form:input path="email" class="form-control" />
							<form:errors path="email" class="text-danger"></form:errors>
						</div>
						<div class="form-group">
							<form:label path="password">Password: </form:label>
							<form:input path="password" type="password" class="form-control" />
							<form:errors path="password" class="text-danger"></form:errors>
						</div>
						<button type="submit" class="btn btn-primary mt-3">Log in</button>
					</form:form>

				</div>
			</div>
		</div>

	</div>


</body>
</html>