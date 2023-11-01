
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.Collection"%>
<%@page import="com.ty.task_management.dto.Task"%>
<%@page import="java.util.List"%>
<%@page import="com.ty.task_management.dto.UserInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>USER LOGIN</title>
<style type="text/css">
* {
	padding: 10px;
	background-image:
		url("https://cdn.pixabay.com/photo/2023/09/30/21/16/river-8286407_1280.png");
	color: white;
}

.abc {
	margin-left: 900px;
	text-decoration: none;
	display: flex;
	color: white;
}

h1 {
	align-content: center;
	text-align: center;
	text-transform: uppercase;
}
</style>
</head>
<body>





	<%
	String name = (String) request.getAttribute("name");
	UserInfo user = (UserInfo) request.getAttribute("user");
	List<Task> tasks = user.getTasks();

	if (!tasks.isEmpty()) {
		tasks = (List<Task>) tasks.stream().sorted((task1, task2) -> task1.getStatus().compareTo(task2.getStatus()))
		.collect(Collectors.toList());
	%>
	<h1>

		<%
		if (name != null) {
		%>
		Welcome
		<%=name%></h1>
	<%
	} else {
	%>
	<h1>Welcome</h1>
	<%
	}
	%>
	<a href="ulogin.jsp" class="abc">Logout </a>
	<br>
	<h1>Task Assigned</h1>
	<table border="2px solid">
		<tr>
			<td>Id</td>
			<td>Description</td>
			<td>Assigned Date</td>
			<td>CompletedDate</td>
			<td>Status</td>
			<td>Update Status</td>
		</tr>

		<%
		for (Task task : tasks) {
		%>
		<tr>

			<td><%=task.getId()%></td>
			<td><%=task.getDescription()%></td>
			<td><%=task.getCreationDateAndTime()%></td>
			<td><%=task.getCompletedDateAndTime()%></td>
			<td><%=task.getStatus()%></td>
			<td><a
				href="update?id=<%=task.getId()%>&userId=<%=user.getId()%>"><button>Update</button></a></td>
		</tr>
		<%
		}
		} else {
		%>
		<h1>
			Welcome
			<%=name%></h1>
		<a href="ulogin.jsp" class="abc">Logout </a>
		<br>
		<h1>No Task Has Been Assigned</h1>
		<%
		}
		%>
	</table>
</body>
</html>