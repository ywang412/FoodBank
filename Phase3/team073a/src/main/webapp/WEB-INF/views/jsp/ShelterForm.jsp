<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Phil
  Date: 4/13/2017
  Time: 11:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ASACS Shelter Information</title>
</head>
<body>
<div align="center">
    <h1>Shelter</h1>
    <form:form action="shelteredit" method="get" >
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
                <td>Unoccupied Bunks:  Total  :  Male / Female / Mixed</td>
            </tr>
            <tr>
                <td>                  ${unoccupied_bunks} :  ${unoccupied_bunks_male} / ${unoccupied_bunks_female}  / ${unoccupied_bunks_mixed}</td>
            </tr>
            <tr>
                <td>Occupied Bunks:   Total  :  Male / Female / Mixed         </td>
            </tr>
            <tr>
                <td>                ${occupied_bunks} : ${occupied_bunks_male} / ${occupied_bunks_female}  / ${occupied_bunks_mixed} </td>
            </tr>
            <tr>
                <td>Unoccupied Rooms:</td>
                <td>   ${unoccupied_rooms}  </td>
            </tr>
            <tr>
                <td>Occupied Rooms:</td>
                <td>   ${occupied_rooms}  </td>
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
                            <input type="hidden" name="shelter_id" value="${shelterId}">

                        </td>
                    </c:otherwise>
                </c:choose>

            </tr>
        </table>
    </form:form>
</div>

<div align="center">
    <h3>Check In Client Into Bunk</h3>

    <c:choose>
        <c:when test="${disabled}">
            <b align="center">Not Applicable</b>
        </c:when>
        <c:otherwise>
            <table>
                <tr>

                    <td>
                        <c:choose>
                            <c:when test="${not_available_bunk_male}">
                                <b align="center">All Male Bunks Occupied.</b>
                            </c:when>
                            <c:otherwise>

                                <form method="post" action="/shelterformbunk" align="center">
                                    <button type="submit" align="center">Check Into Male Bunk</button>
                                    <input type="hidden" name="username" value="${username}">
                                    <input type="hidden" name="siteId" value="${siteId}">
                                    <input type="hidden" name="shelter_id" value="${shelterId}">
                                    <input type="hidden" name="bunk_type" value="1">

                                </form>
                            </c:otherwise>
                        </c:choose>
                    </td>

                    <td>
                        <c:choose>
                            <c:when test="${not_available_bunk_female}">
                                <b align="center">All Female Bunks Occupied.</b>
                            </c:when>
                            <c:otherwise>

                                <form method="post" action="/shelterformbunk" align="center">
                                    <button type="submit" align="center">Check Into Female Bunk</button>
                                    <input type="hidden" name="username" value="${username}">
                                    <input type="hidden" name="siteId" value="${siteId}">
                                    <input type="hidden" name="shelter_id" value="${shelterId}">
                                    <input type="hidden" name="bunk_type" value="2">
                                </form>
                            </c:otherwise>
                        </c:choose>
                    </td>


                    <td>
                        <c:choose>
                            <c:when test="${not_available_bunk_mixed}">
                                <b align="center">All Mixed Bunks Occupied.</b>
                            </c:when>
                            <c:otherwise>

                                <form method="post" action="/shelterformbunk" align="center">
                                    <button type="submit" align="center">Check Into Mixed Bunk</button>
                                    <input type="hidden" name="username" value="${username}">
                                    <input type="hidden" name="siteId" value="${siteId}">
                                    <input type="hidden" name="shelter_id" value="${shelterId}">
                                    <input type="hidden" name="bunk_type" value="3">
                                </form>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>

            </table>

        </c:otherwise>
    </c:choose>
</div>

<div align="center">
    <br>
    <h3>Release Client From Bunk</h3>
    <c:choose>
        <c:when test="${disabled}">
            <b align="center">Not Applicable</b>
        </c:when>
        <c:when test="${not_release_bunk}">
            <b align="center">All Bunks Unoccupied.</b>

        </c:when>

        <c:otherwise>

            <table>
                <tr>
                    <td>
                        <c:choose>
                            <c:when test="${not_release_bunk_male}">
                                <b align="center">All Male Bunks Unoccupied.</b>

                            </c:when>
                            <c:otherwise>
                                <form method="post" action="/shelterbunkrelease" align="center">
                                    <button type="submit" align="center">Release Male Bunk</button>
                                    <input type="hidden" name="username" value="${username}">
                                    <input type="hidden" name="siteId" value="${siteId}">
                                    <input type="hidden" name="shelter_id" value="${shelterId}">
                                    <input type="hidden" name="bunk_type" value="1">
                                </form>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${not_release_bunk_female}">
                                <b align="center">All Female Bunks Unoccupied.</b>

                            </c:when>
                            <c:otherwise>
                                <form method="post" action="/shelterbunkrelease" align="center">
                                    <button type="submit" align="center">Release Female Bunk</button>
                                    <input type="hidden" name="username" value="${username}">
                                    <input type="hidden" name="siteId" value="${siteId}">
                                    <input type="hidden" name="shelter_id" value="${shelterId}">
                                    <input type="hidden" name="bunk_type" value="2">
                                </form>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>

                        <c:choose>
                            <c:when test="${not_release_bunk_mixed}">
                                <b align="center">All Mixed Bunks Unoccupied.</b>

                            </c:when>
                            <c:otherwise>
                                <form method="post" action="/shelterbunkrelease" align="center">
                                    <button type="submit" align="center">Release Mixed Bunk</button>
                                    <input type="hidden" name="username" value="${username}">
                                    <input type="hidden" name="siteId" value="${siteId}">
                                    <input type="hidden" name="shelter_id" value="${shelterId}">
                                    <input type="hidden" name="bunk_type" value="3">
                                </form>
                            </c:otherwise>
                        </c:choose>
                    </td>

                </tr>

            </table>

        </c:otherwise>
    </c:choose>

</div>

<div align="center">
    <h3>Check In Client Into Room</h3>



    <table>
        <tr>
            <td>
                <c:choose>
                    <c:when test="${disabled}">
                        <b align="center">Not Applicable</b>
                    </c:when>

                    <c:when test="${not_available_room}">
                        <b align="center">All Rooms Occupied.</b>

                        <form method="post" action="/shelterWaitlistRoom">
                            <button type="submit" align="center">Get Client On Waitlist</button>
                            <input type="hidden" name="username" value="${username}">
                            <input type="hidden" name="siteId" value="${siteId}">
                            <input type="hidden" name="shelter_id" value="${shelterId}">

                        </form>

                    </c:when>

                    <c:otherwise>

                        <form method="post" action="/shelterclaimroom" align="center">
                            <button type="submit" align="center">Check Into Room</button>
                            <input type="hidden" name="username" value="${username}">
                            <input type="hidden" name="siteId" value="${siteId}">
                            <input type="hidden" name="shelter_id" value="${shelterId}">

                        </form>
                    </c:otherwise>
                </c:choose>
            </td>

            <td>
                <c:choose>
                    <c:when test="${disabled}">
                        <b align="center">Not Applicable</b>
                    </c:when>
                    <c:when test="${not_release_room}">
                        <b align="center">All Rooms Unoccupied.</b>

                    </c:when>

                    <c:otherwise>

                        <form method="post" action="/shelterreleaseRoom" align="center">
                            <button type="submit" align="center">Release Room</button>
                            <input type="hidden" name="username" value="${username}">
                            <input type="hidden" name="siteId" value="${siteId}">
                            <input type="hidden" name="shelter_id" value="${shelterId}">
                        </form>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>

    </table>
</div>

<br>
<br>
<div align="center">
    <form method="post" action="/Waitlist">
        <button type="submit" align="center">View Shelter Waitlist</button>
    </form>
</div>

<br>
<br>
<div align="center">
    <form method="post" action="/SiteInfo">
        <button type="submit" align="center">Return To Site</button>
    </form>
</div>
</body>
</html>
