<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>

  <head>
    <title>ASACS Edit Item</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" type="text/css" media="all" href="../../css/asacs073.css"/>





  </head>

  <body>

	 <div>
    
      <h2>Edit Item</h2>


      <form:form action="ItemEditSubmit" modelAttribute="item" method="post">
    
        <fieldset>
          
          <legend> Item Data </legend>


          
          <div>
            <label for="fullname">
              <span>Item Name:</span>              
            </label>
            <form:input path="itemName" id="itemName" required="required"/>
          </div>
          <div>
            <label for="numberOfUnits">
              <span>Number of units</span>              
            </label>
            <form:input path="numberOfUnits" id="numberOfUnits" required="required"/>
          </div>
          <div>
            <label for="expirationDate">
              <span>Days to expiration:</span>              
            </label>
            <form:input path="expirationDate" id="expirationDate" required="required"/>
          </div>
          <div>
            <label for="storageType">
              <span>Storage Type:</span>              
            </label>


            <div class="dropdown">

              <form:select  path="storageType" id="storageType"  required="required">

                <c:forEach items="${item_storage_type_lists}" var="item_storage_type_enum">

                  <form:option  value="${item_storage_type_enum.storage_type}">${item_storage_type_enum.storage_type_name}</form:option>

                </c:forEach>

              </form:select>
            </div>


          </div>
          <div>
            <label for="itemType">
              <span>Item Type:</span>              
            </label>



            <div class="dropdown">

              <form:select  path="itemType" id="itemType" required="required">

                <c:forEach items="${item_type_lists}" var="item_type_enum">

                  <form:option  value="${item_type_enum.item_type}">${item_type_enum.item_type_name}</form:option>

                </c:forEach>

              </form:select>
            </div>



          </div>
          <div>
            <label for="foodCategory">
              <span>Food Category:</span>              
            </label>


            <div class="dropdown">

              <form:select path="foodCategory" id="foodCategory" required="required">

                <c:forEach items="${lists}" var="food_item_category_enum">

                  <form:option  value="${food_item_category_enum.food_category}">${food_item_category_enum.food_category_name}</form:option>

                </c:forEach>

              </form:select>
            </div>


          </div>



          <div>
            <label for="supplyCategory">
              <span>Supply Category:</span>              
            </label>


            <div class="dropdown">

              <form:select path="supplyCategory" id="supplyCategory" required="required">

                <c:forEach items="${item_supply_categories}" var="item_supply_category_enum">

                    <form:option  value="${item_supply_category_enum.supply_category}">${item_supply_category_enum.supply_category_name}</form:option>

               </c:forEach>

              </form:select>
            </div>

          </div>
          <div>
            <label for="foodBank">
              <span>Food Bank:</span>              
            </label>
            <form:input path="foodBank" id="foodBank" required="required"/>
          </div>
          <div>
		  
	  <div class="submit">
            <button type="submit" name="save">Save</button>    
          </div>
         </fieldset>
		 
	
	  </form:form>
		 
	</div>




  </body>
</html>
