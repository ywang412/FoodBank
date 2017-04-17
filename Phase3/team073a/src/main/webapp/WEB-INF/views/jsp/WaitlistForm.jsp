<%--
  Created by IntelliJ IDEA.
  User: swengineer
  Date: 4/14/17
  Time: 9:16 PM
  To change this template use File | Settings | File Templates.
--%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

    <title>RoomWaitList</title>
</head>
<body>

<div align="center">
    <h1>ASACS Room Wait List </h1>

    <h2>Shelter Name is: ${shelter_ds}</h2>

    <table border="1">
        <tr>
            <td>Position</td>
            <td>Client Name</td>
            <td>client Id</td>
            <td>HeadOfHousehold</td>
            <td>Delete</td>
            <td>Move Up</td>
            <td>Move Down</td>
        </tr>
        <c:forEach items="${allWaitlist}" var="waitlist">
            <tr>
                <td>${waitlist.position}</td>
                <td>${waitlist.fullName}</td>
                <td>${waitlist.clientId}</td>
                <td>${waitlist.headOfHousehold}</td>


                    <form method="post" action="shelterwaitlistremoveclient">
                         <td  align="center">
                             <input type="submit" value="Del">
                             <input type="hidden" name="position" value="${waitlist.position}">
                         </td>
                     </form>



                    <form method="post" action="shelterwaitlistmoveup">
                        <td  align="center">
                            <input type="submit" value="+">
                            <input type="hidden" name="position" value="${waitlist.position}">
                        </td>
                     </form>


                    <form method="post" action="shelterwaitlistmovedown">
                      <td  align="center">
                          <input type="submit" value="-">
                          <input type="hidden" name="position" value="${waitlist.position}">
                      </td>
                     </form>

            </tr>
        </c:forEach>




    </table>

    <br>
    <br>
    <td>

        <a href="<c:url value='/shelterform?username=${userName}&siteId=${siteId}' />" >Return to Shelter</a>

    </td>


    <br>
    <tr>
        <form method="post" action="/invalidate">
            <td colspan="2" align="center"><input type="submit" value="Logout"></td>

        </form>
    </tr>


</div>
</body>
</html>