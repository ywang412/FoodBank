<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Phil
  Date: 4/13/2017
  Time: 10:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Soup Kitchen Information</title>


    <style>
        .error {
            color: #ff0000;
        }

        .errorblock {
            color: #000;
            background-color: #ffEEEE;
            border: 3px solid #ff0000;
            padding: 8px;
            margin: 16px;
        }
    </style>

</head>
<body>

<div align="center">
    <h1>Edit Soup Kitchen Information</h1>
    <form action="/soupkitchenedit" method="post">
        <table>
            <tr>
                <td>Editing Site:</td>
                <td>${shortName}</td>
            </tr>

            <tr>
                <td>Description:</td>
                <td>  ${descriptionString} </td>

                <td>Change Description To:</td>
                <td><input type="text" name="descriptionString" />
                </td>


            </tr>
            <tr>
                <td>Conditions For Use:</td>
                <td>  ${conditionsForUse}  </td>

                <td>Change Conditions For Use To:</td>
                <td><input type="text" name="conditionsForUse" />
                </td>


            </tr>

            <tr>
                <td>Hours Of Operation:</td>
                <td>   ${hours}  </td>
                <td>Change Hours of Operation To:</td>
                <td><input type="text" name="hours" />
                </td>


            </tr>

            <tr>
                <td>Available Seats:</td>
                <td>   ${available_seats}  </td>
                <td>Change Available Seats To:</td>
                <td><input type="number" name="available_seats" />
                </td>


            </tr>
            <tr>
                <td>Total Seats:</td>
                <td>   ${total_seats}  </td>
                <td>Change Available Seats To:</td>
                <td><input type="number" name="seats_limit" />
                </td>


            </tr>

        </table>
        <br>
        <br>
        <table>
            <c:choose>
                <c:when test="${disabled}">
                    <td colspan="2" align="center"><input type="submit" name = "submit" value="Submit"  disabled="disabled"> </td>
                </c:when>
                <c:otherwise>
                    <td colspan="2" align="center"><input type="submit" name = "submit" value="Submit"  > </td>
                    <input type="hidden" name="soupKitchenId" value="${soupKitchenId}">
                    <input type="hidden" name="username" value="${username}">
                    <input type="hidden" name="siteId" value="${siteId}">
                </c:otherwise>
            </c:choose>
        </table>

    </form>
</div>
<br>
<br>
<div align="center">
    <form method="get" action="/soupkitchenform">
        <button type="submit" align="center">Return</button>
        <input type="hidden" name="username" value="${username}">
        <input type="hidden" name="siteId" value="${siteId}">
    </form>
</div>

</body>
</html>
