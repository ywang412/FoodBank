<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Phil
  Date: 4/15/2017
  Time: 7:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ASACS Food Bank Information</title>

    <style>
        tbody div{
            overflow:scroll;
            height:100px;
        }
    </style>
</head>
<body>
<div align="center">
    <h1>ASACS Food Bank Information</h1>

    <c:choose>
        <c:when test="${disabled}">
            <br>
            <b style="color:red;">  NO FOOD BANK AT THIS SITE! </b>
        </c:when>
    </c:choose>

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

        <tr align="center">
            <td>Food Bank Items List:</td>

            <td colspan="2">

                <form method="get" action="ItemSearch">
                    <button type="submit" >Item Search</button>
                    <input type="hidden" name="username" value="${username}">
                    <input type="hidden" name="siteId" value="${siteId}">
                    <input type="hidden" name="food_bank_id" value="${foodBankId}">
                </form>

            </td>
            <td colspan="3">

                <form method="get" action="AddItem">
                    <button type="submit" >Add New Item</button>
                    <input type="hidden" name="username" value="${username}">
                    <input type="hidden" name="siteId" value="${siteId}">
                    <input type="hidden" name="food_bank_id" value="${foodBankId}">
                </form>

            </td>
            <td colspan="3">

                <form method="get" action="ViewRequests">
                    <button type="submit" >View Food Bank Requests</button>
                    <input type="hidden" name="username" value="${username}">
                    <input type="hidden" name="siteId" value="${siteId}">
                    <input type="hidden" name="food_bank_id" value="${foodBankId}">
                </form>

            </td>
        </tr>
    </table>

</div>

<br>
<div align="center">
    <h3>Current Food Bank Items</h3>

    <table border="1">
        <thead>
            <tr>
                <th >Name</th>
                <th >Units</th>
                <th >Storage Type</th>
                <th >Item Type</th>
                <th  >Food Catagory</th>
                <th  >Supply Catagory</th>
                <th >Exp. Date</th>
            </tr>
        </thead>

        <tbody>


                    <div class="scrollit">

                            <c:forEach items="${lists}" var="item">
                                <tr>
                                    <td>${item.itemName}</td>
                                    <td>${item.numberOfUnits}</td>
                                    <td>${item.storageType}</td>
                                    <td>${item.itemType}</td>
                                    <td>${item.foodCategory}</td>
                                    <td>${item.supplyCategory}</td>
                                    <td>${item.expirationDate}</td>
                                </tr>
                            </c:forEach>

                    </div>


        </tbody>
    </table>


</div>
<br>
<div align="center">
    <form method="post" action="SiteInfo">
        <button type="submit" align="center">Return To Site</button>
    </form>
</div>
</body>
</html>
