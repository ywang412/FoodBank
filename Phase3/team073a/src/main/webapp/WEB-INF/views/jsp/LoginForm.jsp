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
		<h1>ASACS User Login </h1>
		<form:form action="login" method="post" modelAttribute="user">
		
		<form:errors />
		<table>
			<tr>
				<td>Username:</td>
				<td><form:input path="userName" /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><form:password path="password" /></td>
				<td><form:errors path="password" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Login"></td>
			</tr>
		</table>
		
		</form:form>
                    <a href="public/MealsRemaining">Meals Remaining</a>

	</div>
</body>
</html>
