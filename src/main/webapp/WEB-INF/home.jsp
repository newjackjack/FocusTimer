<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Focus Timer</title>
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
		<div class="mt-5 d-flex justify-content-sm-between">
			<h1 class="head-bar">Focus Timer</h1>
			<div class="d-flex">
				<!-- Calendar -->
				<div class="calendar-container">
					<header class="calendar-header">
						<p class="calendar-current-date"></p>
						<div class="calendar-navigation">
							<span id="calendar-prev" class="material-symbols-rounded">
								< </span> <span id="calendar-next" class="material-symbols-rounded">
								> </span>
						</div>
					</header>
					<div class="calendar-body">
						<ul class="calendar-weekdays">
							<li>Sun</li>
							<li>Mon</li>
							<li>Tue</li>
							<li>Wed</li>
							<li>Thu</li>
							<li>Fri</li>
							<li>Sat</li>
						</ul>
						<ul class="calendar-dates"></ul>
					</div>
				</div>
				<!-- All the tasks -->
				<div class="middle-right-bar">
					<div class="upper-mid-right">
						<div class="tasks-block">
							<h3>Tasks:</h3>
							<table class="table table-hover">
								<thead>
									<tr>
										<th>Task</th>
										<th>isDone</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="eachTask" items="${ taskList}">
										<c:if
											test="${userId.equals(eachTask.owner.id) && !eachTask.isDone}">
											<tr>
												<td><a href="/tasks/${eachTask.id }/edit"><c:out value="${eachTask.title }" /></a></td>
												<td><c:out value="${eachTask.isDone ? 'Yes' : 'No' }" /></td>
											</tr>
										</c:if>

									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- form -->
						<div class="input-form">
							<h3>Add Task:</h3>
							<form:form action="/tasks/new" method="POST"
								modelAttribute="newTask">
								<form:hidden path="owner" value="${userId }" />
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
								<button type="submit" class="input-btn">Add Task</button>
							</form:form>
						</div>
					</div>
					<div class="timer">
						<!-- timer -->
						<h2>Enter start time</h2>
						<div oninput="displaymin()">
							<input type="number" id="hours" min="0" max="23"
								placeholder="hrs" class="timer-input" /> : <input type="number" id="minutes"
								min="0" max="59" placeholder="mins" class="timer-input"/> : <input type="number"
								id="seconds" min="0" max="59" placeholder="secs" class="timer-input"/>
						</div>
						<div id="display">00:00:00</div>
						<div>
							<button id="start" onclick="watch()" class="timer-btn">Start</button>

						</div>
					</div>
				</div>
			</div>
			<br>
			<p style="position: absolute; bottom: 5vh; left: 40vw;">Great job
				for the hard work! Recharge and come back tomorrow!</p>
			<a href="/logout"
				style="color: black; border: solid 1px #777; border-radius: 2px; position: absolute; bottom: 2vh; right: 45vw;">Log
				out</a>
		</div>
	</div>
	<script src="/js/app.js"></script>
	<audio src="/timer.mp3"></audio>
</body>
</html>