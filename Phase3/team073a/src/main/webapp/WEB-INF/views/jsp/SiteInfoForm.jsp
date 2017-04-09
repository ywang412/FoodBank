<%--
  Created by IntelliJ IDEA.
  User: swengineer
  Date: 4/6/17
  Time: 5:21 PM
  To change this template use File | Settings | File Templates.
--%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>

    <title>SiteInfo</title>
</head>
<body>
<div align="center">
    <h1>ASACS Site Information </h1>
    <form:form action="saveSite" method="post">
        <table>
            <tr>
                <td>Site Name:</td>
                <td>${siteInfo.shortName}</td>
            </tr>
            <tr>
                <td>Street Adress:</td>
                <td>${siteInfo.streetAddress}</td>
            </tr>
            <tr>
                <td>Contact Number:</td>
                <td>${siteInfo.contactNumber}</td>
            </tr>

            <tr>
                <th>ID</th>
                <th>Description</th>
                <th>Hours</th>
                <th>Conditions</th>
            </tr>


            <tr>
                <th>${foodBank.foodBankId}</th>
                <th>${foodBank.descriptionString}</th>
            </tr>
            <tr>
                <th>${foodPantry.foodPantryId}</th>
                <th>${foodPantry.descriptionString}</th>
                <th>${foodPantry.hours}</th>
                <th>${foodPantry.conditionsForUse}</th>
            </tr>
            <tr>
                <th>${soupKitchen.soupKitchenId}</th>
                <th>${soupKitchen.descriptionString}</th>
                <th>${soupKitchen.hours}</th>
                <th>${soupKitchen.conditionsForUse}</th>
            </tr>
            <tr>
                <th>${shelter.shelterId}</th>
                <th>${shelter.descriptionString}</th>
                <th>${shelter.hours}</th>
                <th>${shelter.conditionsForUse}</th>

            </tr>





            <tr>
                <td colspan="2" align="center"><input type="submit" value="Return to User Dashboard"></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Logout"></td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>