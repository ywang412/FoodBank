<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <form:form action="EditSoupKitchen" method="post">
        <table>
            <tr>
                <td>Editing Site:</td>
                <td>${shortName}</td>
            </tr>

            <tr>
                <td>Description:</td>
                <td>  ${descriptionString} </td>

                <td>Change Description To:</td>
                <td>
                </td>


            </tr>
            <tr>
                <td>Conditions For Use:</td>
                <td>  ${conditionsForUse}  </td>

                <td>Change Conditions For Use To:</td>
                <td>
                </td>


            </tr>

            <tr>
                <td>Hours Of Operation:</td>
                <td>   ${hours}  </td>
                <td>Change Hours of Operation To:</td>
                <td>
                </td>


            </tr>

            <tr>
                <td>Available Seats:</td>
                <td>   ${available_seats}  </td>
                <td>Change Available Seats To:</td>
                <td>
                </td>


            </tr>

            <tr>

                <c:choose>
                    <c:when test="${disabled}">
                        <td colspan="2" align="center"><input type="submit" name = "submit" value="Submit"  disabled="disabled"> </td>
                    </c:when>
                    <c:otherwise>
                        <td colspan="2" align="center"><input type="submit" name = "submit" value="Submit"  > </td>
                    </c:otherwise>
                </c:choose>

            </tr>
        </table>
    </form:form>
</div>


</body>
</html>
