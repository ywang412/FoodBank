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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

    <title>SiteInfo</title>




</head>
<body>
<div align="center">
    <h1>ASACS Site Information </h1>
    <br>
    <form:form action="login" method="post">
        <table>
            <tr>
                <td><b>Site Name:</b></td>
                <td>${siteInfo.shortName}</td>
            </tr>
            <tr>
                <td><b>Street Adress:</b></td>
                <td>${siteInfo.streetAddress}</td>
            </tr>
            <tr>
                <td><b>Contact Number:</b></td>
                <td>${siteInfo.contactNumber}</td>
            </tr>

        </table>

        <br>
        <br>
        <table border="1">
            <thead>
                <tr>
                    <th>Service Type</th>
                    <th>Description</th>
                    <th>Hours</th>
                    <th>Conditions</th>
                    <th>Add or Remove Service<th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Food Bank</td>
                    <td>${foodBank.descriptionString}</td>
                    <td> N/A </td>
                    <td> N/A </td>
                    <td>
                        <a href="<c:url value='/foodbankedit?username=${user.userName}&siteId=${siteInfo.siteId}&food_bank_id=${foodBank.foodBankId}' />" >Add/Remove</a>
                    </td>
                </tr>
                <tr>
                    <td>Food Pantry</td>
                    <td>${foodPantry.descriptionString}</td>
                    <td>${foodPantry.hours}</td>
                    <td>${foodPantry.conditionsForUse}</td>
                    <td> <a href="<c:url value='/foodpantryedit?username=${user.userName}&siteId=${siteInfo.siteId}&food_pantry_id=${foodPantry.foodPantryId}' />" >Add/Remove</a></td>
                </tr>
                <tr>
                    <td>Soup Kitchen</td>
                    <td>${soupKitchen.descriptionString}</td>
                    <td>${soupKitchen.hours}</td>
                    <td>${soupKitchen.conditionsForUse}</td>
                    <td> <a href="<c:url value='/soupkitchenedit?username=${user.userName}&siteId=${siteInfo.siteId}&soup_kitchen_id=${soupKitchen.soupKitchenId}' />" >Add/Remove</a></td>
                </tr>
                <tr>
                    <td>Shelter</td>
                    <td>${shelter.descriptionString}</td>
                    <td>${shelter.hours}</td>
                    <td>${shelter.conditionsForUse}</td>
                    <td> <a href="<c:url value='/shelteredit?username=${user.userName}&siteId=${siteInfo.siteId}&shelter_id=${shelter.shelterId}' />" >Add/Remove</a></td>
                </tr>


            </tbody>

        </table>
    </form:form>

    <br>
    <br>
    <div align="center">
        <table class="fixed">
            <col width="200px" />
            <col width="200px" />
            <col width="200px" />
            <col width="200px" />
            <tr>
                <td>

                    <a href="<c:url value='/foodbankform?username=${user.userName}&siteId=${siteInfo.siteId}' />" >Go to Food Bank</a>

                </td>

                <td>

                    <a href="<c:url value='/foodpantryform?username=${user.userName}&siteId=${siteInfo.siteId}' />" >Go to Food Pantry</a>

                </td>
                <td>

                    <a href="<c:url value='/soupkitchenform?username=${user.userName}&siteId=${siteInfo.siteId}' />" >Go to Soup Kitchen</a>

                </td>
                <td>

                    <a href="<c:url value='/shelterform?username=${user.userName}&siteId=${siteInfo.siteId}' />" >Go to Shelter</a>

                </td>
            </tr>
        </table>
    </div>

</div>

    <br>
    <br>
    <div align="center">
        <table>
            <tr>
                <td >
                    <form method="post" action="/login">
                        <button type="submit" align="center">Return to User Dashboard</button>
                    </form>
                </td>

            </tr>

            <tr>
                <form method="post" action="/invalidate">
                    <td colspan="2" align="center"><input type="submit" value="Logout"></td>

                </form>
            </tr>

        </table>

    </div>




</body>
</html>