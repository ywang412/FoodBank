<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>

<title>Login</title>
</head>
<body>
	<div align="center">
		<h1>Site Details </h1>
		<form:form action="saveSite" method="post">
		<table>
			<tr>
				<td>Name:</td>
				<td>${user.fullName}</td>
			</tr>
			<tr>
				<td>Email:</td>
				<td>${user.userEmail}</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Save"></td>
			</tr>
		</table>
		</form:form>
	</div>
</body>
</html>
