<%@page import="com.ty.task_management.dto.UserInfo"%>
<%@page import="java.util.List"%>
<%@page import="com.ty.task_management.dao.UserInfoDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Employee</title>
<style type="text/css">
* {
	padding: 10px;
	background-image:
		url("https://cdn.pixabay.com/photo/2023/09/30/21/16/river-8286407_1280.png");
	color: white;
}

.abc {
	margin-left: 915px;
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
	UserInfoDao dao = new UserInfoDao();
	List<UserInfo> list = dao.findAllUser();
	%>
	<a href="home.jsp" class="abc">Back</a>
	<h1>Employee Details</h1><br>
	<table border="2px solid">
		<tr>
			<td>Id</td>
			<td>Name</td>
			<td>Email</td>
			<td>Phone</td>
			<td>Status</td>

		</tr>

		<%
		for (UserInfo task : list) {
		%>
		<tr>

			<td><%=task.getId()%></td>
			<td><%=task.getName()%></td>
			<td><%=task.getEmail()%></td>
			<td><%=task.getPhone()%></td>
			<td><%=task.getRole()%></td>

		</tr>
		<%
		}
		%>
	</table>
</body>
</html>