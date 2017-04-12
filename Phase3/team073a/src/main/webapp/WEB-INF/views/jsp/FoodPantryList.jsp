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


		<table>
			<c:forEach items="${lists}" var="firstname">
				<tr>
					<td>${firstname}</td>
				</tr>
			</c:forEach>

		</table>


	</div>

</body>
</html>