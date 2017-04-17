<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%--
  Created by IntelliJ IDEA.
  User: Phil
  Date: 4/13/2017
  Time: 10:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ASACS Soup Kitchen Information</title>
</head>
<body>
<div align="center">
    <h1>ASACS Soup Kitchen Information</h1>

    <c:choose>
        <c:when test="${disabled}">
            <br>
            <b style="color:red;">  NO SOUP KITCHEN AT THIS SITE! </b>
        </c:when>
    </c:choose>

    <form:form action="soupkitchenedit" method="get" >
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
            <tr>
                <td>Available Seats:</td>
                <td>   ${available_seats}  </td>
            </tr>
            <tr>
                <td>Total Seats:</td>
                <td>   ${seats_limit}  </td>
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
                            <input type="hidden" name="soup_kitchen_id" value="${soup_kitchen_id}">

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
        <c:when test="${not_available}">
            <b align="center">No Seats Available!</b>

        </c:when>
        <c:otherwise>

            <form method="post" action="soupkitchenform" align="center">
                <button type="submit" align="center">Check In Client</button>
                <input type="hidden" name="username" value="${username}">
                <input type="hidden" name="siteId" value="${siteId}">
                <input type="hidden" name="soup_kitchen_id" value="${soup_kitchen_id}">
            </form>
        </c:otherwise>
    </c:choose>

    <br>

    <c:choose>
        <c:when test="${disabled}">
            <b align="center">Not Applicable</b>
        </c:when>
        <c:when test="${not_release}">
            <b align="center">All Seats Unoccupied.</b>

        </c:when>

        <c:otherwise>

            <form method="post" action="soupkitchenrelease" align="center">
                <button type="submit" align="center">Release Seat</button>
                <input type="hidden" name="username" value="${username}">
                <input type="hidden" name="siteId" value="${siteId}">
                <input type="hidden" name="soup_kitchen_id" value="${soup_kitchen_id}">
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

            <form method="post" action="AddRequest">
                <button type="submit" align="center">Request Items</button>
                <input type="hidden" name="username" value="${username}">
                <input type="hidden" name="siteId" value="${siteId}">
                <input type="hidden" name="soup_kitchen_id" value="${soup_kitchen_id}">

            </form>
        </c:otherwise>
    </c:choose>

</div>
<br>
<br>
<div align="center">
    <form method="post" action="SiteInfo">
        <button type="submit" align="center">Return To Site</button>
    </form>
</div>

</body>
</html>
