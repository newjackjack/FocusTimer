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
<link
	href="https://fonts.googleapis.com/css2?family=Edu+NSW+ACT+Foundation:wght@400..700&display=swap"
	rel="stylesheet">
</head>
<body>
	<div class="bg-image d-flex justify-content-center "
		style="background-image: url('https://images.pexels.com/photos/921294/pexels-photo-921294.png?auto=compress&amp;cs=tinysrgb&amp;w=1260&amp;h=750&amp;dpr=2'); background-size: cover; height: 100vh; width: 100vw;">
	<div class="container mx-auto">
		<h1 class="head-bar">Focus Timer</h1>
		<form:form action="/tasks/${oneTask.id }/edit" method="PUT" modelAttribute="oneTask">
			<form:hidden path="owner"/>
			<div class="input-area">
				<form:label path="title" class="input-label">Task: </form:label>
				<form:input path="title" type="text" class="form-control" />
				<form:errors path="title" class="text-danger" />
			</div>
			<div class="input-area">
				<form:label path="isDone" class="input-label">Is Done? </form:label>
				<form:radiobutton path="isDone" value="false" label="No"
					class="input-radio" />
				<form:radiobutton path="isDone" value="true" label="Yes"
					class="input-radio" />
				<form:errors path="isDone" class="text-danger" />
			</div>
			<button type="submit" class="input-btn">Edit!</button>
		</form:form>
		<div class="mt-5">
			<form action="/tasks/${oneTask.id}/delete" method="POST">
				<input type="hidden" name="_method" value="DELETE" />
				<button type="submit" class="btn btn-danger d-flex justify-content-center mx-auto">Delete</button>
			</form>
		</div>
	</div>
	</div>
</body>
</html>