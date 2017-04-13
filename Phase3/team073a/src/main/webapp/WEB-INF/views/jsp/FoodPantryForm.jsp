<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>

<title>ASACS Food Pantry Information</title>
</head>
<body>
	<div align="center">
		<h1>Site Details </h1>
		<form:form action="editSite" method="post">
		<table>
			<tr>
				<td>Site Name:</td>
				<td>${site.shortName}</td>
			</tr>
			<tr>
				<td>Street Address:</td>
				<td>${site.StreetAddress}, ${site.City},  ${site.State}, ${site.zipcode} </td>
			</tr>
			<tr>
				<td>Contact Number:</td>
				<td>  ${site.contactNumber} </td>
			</tr>
			<tr>
				<td>Description:</td>
				<td>  tbd </td>
			</tr>
			<tr>
				<td>Conditions For Use:</td>
				<td>  tbd </td>
			</tr>

			<tr>
				<td>Hours Of Operation:</td>
				<td>  tbd </td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Edit"></td>
			</tr>
		</table>
		</form:form>
	</div>

	<div align="center">
		<h1>Site Details </h1>
		<form:form action="checkin" method="post">
		<table>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Check In Client"></td>
			</tr>
		</table>
		</form:form>
	</div>

	<div align="center">
		<h1>Site Details </h1>
		<form:form action="requestItems" method="post">
			<table>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="Request Items"></td>
				</tr>
			</table>
		</form:form>
	</div>

</body>
</html>