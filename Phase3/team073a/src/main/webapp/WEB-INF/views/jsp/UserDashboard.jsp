<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>

    <title>ASACS User Dashboard</title>
</head>
<body>
<div align="center">
    <h1>ASACS User Dashboard </h1>


        <table>
            <tr>
                <td>Username:</td>
                <td>${user.userName}</td>
            </tr>
            <tr>
                <td>Full Name:</td>
                <td>${user.fullName}</td>
            </tr>
            <tr>
                <td>Email:</td>
                <td>${user.userEmail}</td>
            </tr>

            <tr>
                <th>SiteID</th>
                <th>SiteName</th>
                <th>FoodBank</th>
                <th>/FoodPantry</th>
                <th>/SoupKitchen</th>
                <th>/Shelter</th>
            </tr>


            <tr>

                <form:form action="SiteInfo" method="post">
                <td colspan="1" align="left"><input type="submit" value=${siteInfo.siteId}></td>
                </form:form>
                <th>${siteInfo.fullName}</th>
                <th>${message2}</th>
                <th>${message3}</th>
                <th>${message4}</th>
                <th>${message5}</th>
            </tr>


            <tr>

            </tr>

            <tr>
                <form method="post" action="/invalidate">
                    <td colspan="2" align="center"><input type="submit" value="Logout"></td>

                </form>
            </tr>

        </table>
    <form:form action="requestList" method="Get">
      <input type="submit" value="View Requests from user"><input type="hidden" name="username" value="${user.userName}">
    </form:form>
    <form:form action="ItemList" method="Get">
      <input type="submit" value="View/Request Items"><input type="hidden" name="username" value="${user.userName}">
    </form:form>
</div>
</body>
</html>
