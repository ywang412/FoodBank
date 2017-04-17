<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Phil
  Date: 4/15/2017
  Time: 11:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Food Bank Information</title>


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
    <h1>Add / Edit Food Bank Information</h1>
    <br>
    <form action="foodbankedit"   method="post">

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

        </table>

        <br>
        <br>
        <table>
            <c:choose>
                <c:when test="${disabled}">
                    <td colspan="2" align="center"><input type="submit" name = "submit" value="Submit"  disabled="disabled"> </td>
                </c:when>
                <c:when test="${missing}">
                    <td colspan="2" align="center"><input type="submit" name = "add" value="Add"  > </td>
                    <input type="hidden" name="username" value="${username}">
                    <input type="hidden" name="siteId" value="${siteId}">
                </c:when>
                <c:otherwise>
                    <td colspan="2" align="center"><input type="submit" name = "submit" value="Submit"  > </td>
                    <input type="hidden" name="foodBankId" value="${foodBankId}">
                    <input type="hidden" name="username" value="${username}">
                    <input type="hidden" name="siteId" value="${siteId}">
                </c:otherwise>
            </c:choose>
        </table>
    </form>
</div>

<br>

<div align="center">

    <form method="post" action="foodbankremove">
    <c:choose>


        <c:when test="${disabled}">

        </c:when>
        <c:when test ="${missing}">

        </c:when>
        <c:when test="${lastone}">

            <button type="submit" align="center" disabled="disabled">Remove</button>
            <b> Cannot Remove - Last Service At Site!</b>
        </c:when>
        <c:otherwise>

                <button type="submit" align="center">Remove</button>
                <input type="hidden" name="foodBankId" value="${foodBankId}">
                <input type="hidden" name="username" value="${username}">
                <input type="hidden" name="siteId" value="${siteId}">

        </c:otherwise>

    </c:choose>
    </form>
</div>

<br>


<div align="center">
    <form method="get" action="foodbankform">
    <c:choose>
        <c:when test="${disabled}">

        </c:when>
        <c:when test ="${missing}">

        </c:when>
        <c:otherwise>
                    <button type="submit" align="center">Return To Food Bank</button>
                    <input type="hidden" name="username" value="${username}">
                    <input type="hidden" name="siteId" value="${siteId}">
        </c:otherwise>
    </c:choose>
    </form>
</div>

<br>
<div align="center">
    <form method="post" action="SiteInfo">
        <button type="submit" align="center">Return To Site</button>
    </form>
</div>
</body>
</html>
