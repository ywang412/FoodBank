<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Phil
  Date: 4/17/2017
  Time: 12:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Item Search</title>
</head>
<body>
<h1>Item Search</h1>

<div>


    <form:form action="ItemSearchSubmit" method="post" modelAttribute="searchItem">

        <fieldset>

            <legend> Search item - enter one field  </legend>

            <form:errors class="fieldError" />
            <div>
                <form:label path="searchParms" for="searchParms">
                    <span>Search Name</span>
                </form:label>
                <form:input type="text" path="searchParms" name="searchParms"  />
                <form:errors path="searchParms" class="fieldError" />
            </div>
            <div>
                <form:label path="searchItemType" for="searchItemType">
                    <span>Search By Item Type  {1=none}</span>
                </form:label>
                <form:input type="text" path="searchItemType" name="searchItemType"  />
                <form:errors path="searchItemType" class="fieldError" />
            </div>
            <div>
                <form:label path="searchStorageType" for="searchStorageType">
                    <span>Search By Storage Type {2 = refrigerated, 3 = drygoods, 4=frozen}</span>
                </form:label>
                <form:input type="text" path="searchStorageType" name="searchStorageType"  />
                <form:errors path="searchStorageType" class="fieldError" />
            </div>
            <div>
                <form:label path="searchFoodCategory" for="searchFoodCategory">
                    <span>Search By Food Category  {2 = vegetables, 3 = beans, 4=nuts, 5=grains, 6=meats, 7=seafood,8=diary,9=condiments, 10=drinks}</span>
                </form:label>
                <form:input type="text" path="searchFoodCategory" name="searchFoodCategory"  />
                <form:errors path="searchFoodCategory" class="fieldError" />
            </div>

            <div>
                <form:label path="searchSupplyCategory" for="searchSupplyCategory">
                    <span>Search By Supply Category {2 = personal hygene, 3 = clothing, 4=shelter, 5=other}</span>
                </form:label>
                <form:input type="text" path="searchSupplyCategory" name="searchSupplyCategory"  />
                <form:errors path="searchSupplyCategory" class="fieldError" />
            </div>

            <div>
                <form:label path="searchExpired" for="searchExpired">
                    <span>Search By Expiration Date</span>
                </form:label>
                <form:input type="text" path="searchExpired" name="searchExpired"  />
                <form:errors path="searchExpired" class="fieldError" />
            </div>



            <!-- type="submit" name="clientAdd" action="goToAdd" -->
            <div class="submit">
                <button type="submit" name="itemSearch" action="itemSearch">Search</button>
                <button type="submit" name="goToAddItem" action="goToAddItem">Add New Item</button>

            </div>
        </fieldset>
    </form:form>
    <!--
			<div class="submit">
				<spring:url value="/AddItem" var="ItemAddUrl" />
				<button  onclick="location.href='${itemAddUrl}'">Add New Client</button>
			</div>
		-->

</div>


<div class="asacslist">

    <h2>List of Items</h2>

    <table>
        <thead>
        <tr>
            <th>Name</th>
            <th>Units</th>
            <th>Storage Type</th>
            <th>Item Type</th>
            <th>Food Category</th>
            <th>Supply Category</th>
            <th>Exp. Date</th>
            <th>Location ID</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${searchResults}">
            <tr>
                <td>${item.itemName}</td>
                <td>${item.numberOfUnits}</td>
                <td>${item.storageType}</td>
                <td>${item.itemType}</td>
                <td>${item.foodCategory}</td>
                <td>${item.supplyCategory}</td>
                <td>${item.expirationDate}</td>
                <td>${item.foodBank}</td>
            </tr>
        </c:forEach>
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
