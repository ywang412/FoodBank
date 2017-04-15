<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>

<title>ASACS Food Pantry List</title>
</head>
<body>
	<div align="center">
		<h1>Food Pantries</h1>

		<h2>Count is: ${count}</h2>

		<table border="1">
			<c:forEach items="${lists}" var="foodpantry">
				<tr>
					<td>${foodpantry.foodPantryId}</td>
					<td>${foodpantry.descriptionString}</td>
					<td>${foodpantry.hours}</td>
					<td>${foodpantry.conditionsForUse}</td>
				</tr>
			</c:forEach>

		</table>


	</div>

</body>
</html>