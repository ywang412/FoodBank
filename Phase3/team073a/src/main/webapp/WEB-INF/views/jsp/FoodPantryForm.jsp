<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

<title>ASACS Food Pantry Information</title>
</head>
<body>
	<div align="center">
		<h1>Food Pantry</h1>
		<form:form action="foodpantryedit" method="get" >
		<table>
			<tr>
				<td>Site Name:</td>
				<td>${shortName}</td>
			</tr>
			<tr>
				<td>Street Address:</td>
				<td>${StreetAddress}, ${City},  ${State}, ${zipcode} </td>
			</tr>
			<tr>
				<td>Contact Number:</td>
				<td>  ${contactNumber} </td>
			</tr>
			<tr>
				<td>Description:</td>
				<td>  ${descriptionString} </td>
			</tr>
			<tr>
				<td>Conditions For Use:</td>
				<td>  ${conditionsForUse}  </td>
			</tr>

			<tr>
				<td>Hours Of Operation:</td>
				<td>   ${hours}  </td>
			</tr>

			<tr align="center">

				<c:choose>
					<c:when test="${disabled}">
						<br>
					</c:when>
					<c:otherwise>
						<td colspan="2" align="center">

								<input type="submit" value="Edit">
								<input type="hidden" name="username" value="${username}">
								<input type="hidden" name="siteId" value="${siteId}">
								<input type="hidden" name="food_pantry_id" value="${foodPantryId}">

						</td>
					</c:otherwise>
				</c:choose>

			</tr>
		</table>
		</form:form>
	</div>

	<div align="center">
		<h1>Check In Client </h1>


		<br>

		<c:choose>
			<c:when test="${disabled}">
				<b align="center">Not Applicable</b>
			</c:when>
			<c:otherwise>

				<form method="post" action="/checkinclient" align="center">
					<button type="submit" align="center">Check In Client</button>
					<input type="hidden" name="username" value="${username}">
					<input type="hidden" name="siteId" value="${siteId}">
					<input type="hidden" name="food_pantry_id" value="${foodPantryId}">

				</form>
			</c:otherwise>
		</c:choose>



	</div>

	<div align="center">
		<h1>Request Items </h1>


			<c:choose>
				<c:when test="${disabled}">
					<b align="center">Not Applicable</b>

				</c:when>
				<c:otherwise>

					<form method="post" action="/requestitems">
						<button type="submit" align="center">Request Items</button>
						<input type="hidden" name="username" value="${username}">
						<input type="hidden" name="siteId" value="${siteId}">
						<input type="hidden" name="food_pantry_id" value="${foodPantryId}">

					</form>
				</c:otherwise>
			</c:choose>

	</div>

</body>
</html>