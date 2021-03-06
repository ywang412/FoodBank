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

            <c:choose>

                <c:when test="${missing}">
                    <tr>
                        <td>Total Male Bunks:</td>
                        <td><input type="text" name="male_bunks" />
                        </td>
                    </tr>
                    <tr>
                        <td>Total Female Bunks:</td>
                        <td><input type="text" name="female_bunks" />
                        </td>
                     </tr>
                    <tr>
                        <td>Total Mixed Bunks:</td>
                        <td><input type="text" name="mixed_bunks" />
                        </td>
                    </tr>
                    <tr>
                        <td>Total Rooms:</td>
                        <td><input type="text" name="total_rooms" />
                        </td>
                    </tr>
                </c:when>
                <c:otherwise>



                    <tr>
                        <td><b>NOT Editable Information:</b></td>


                    </tr>
                    <tr>
                        <td>Total Male Bunks:</td>
                        <td> ${male_bunks}
                        </td>
                    </tr>
                    <tr>
                        <td>Total Female Bunks:</td>
                        <td> ${female_bunks}
                        </td>
                    </tr>
                    <tr>
                        <td>Total Mixed Bunks:</td>
                        <td> ${mixed_bunks}
                        </td>
                    </tr>

                    <tr>
                        <td>Total Rooms:</td>
                        <td> ${total_rooms}
                        </td>
                    </tr>
                </c:otherwise>
            </c:choose>






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
                    <input type="hidden" name="shelterId" value="${shelterId}">
                    <input type="hidden" name="username" value="${username}">
                    <input type="hidden" name="siteId" value="${siteId}">
                    <input type="hidden" name="male_bunks" value="${male_bunks}">
                    <input type="hidden" name="female_bunks" value="${female_bunks}">
                    <input type="hidden" name="mixed_bunks" value="${mixed_bunks}">
                    <input type="hidden" name="total_rooms" value="${total_rooms}">
                </c:otherwise>
            </c:choose>
        </table>




    </form>
</div>
<br>

<div align="center">

    <form method="post" action="/shelterremove">
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
                <input type="hidden" name="shelterId" value="${shelterId}">
                <input type="hidden" name="username" value="${username}">
                <input type="hidden" name="siteId" value="${siteId}">

            </c:otherwise>

        </c:choose>
    </form>
</div>

<br>


<div align="center">
    <form method="get" action="/shelterform">
        <c:choose>
            <c:when test="${disabled}">

            </c:when>
            <c:when test ="${missing}">

            </c:when>
            <c:otherwise>
                <button type="submit" align="center">Return To Shelter</button>
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
