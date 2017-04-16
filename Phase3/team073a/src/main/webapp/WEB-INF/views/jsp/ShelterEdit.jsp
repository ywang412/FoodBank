<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Phil
  Date: 4/13/2017
  Time: 11:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Shelter Information</title>

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
    <h1>Edit Shelter Information</h1>
    <form action="/shelteredit" method="post">
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
                    <input type="hidden" name="shelterId" value="${shelterId}">
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
    <form method="get" action="/shelterform">
        <button type="submit" align="center">Return</button>
        <input type="hidden" name="username" value="${username}">
        <input type="hidden" name="siteId" value="${siteId}">
    </form>
</div>


</body>
</html>