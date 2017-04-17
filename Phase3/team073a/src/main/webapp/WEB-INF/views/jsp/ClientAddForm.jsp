<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>

  <head>
    <title>ASACS Add Client</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" type="text/css" media="all" href="<c:url value="/resources/asacs073.css" />"/>
  </head>

  <body>

	 <div>
    
      <h2>Add Client</h2>

    	<div>
            <label for="service">
              <span>Service:</span>              
            </label>
            ${serviceObj.description}
        </div> 

      <form:form action="ClientAddSubmit" modelAttribute="client" method="post">
    
        <fieldset>
          
          <legend> Client Info </legend>


          
          <div>
            <label for="fullname">
              <span>Full Name:</span>              
            </label>
            <form:input path="fullName" id="fullName" required="required"/>
          </div>
		  <div>
            <label for="iddesc">
              <span>ID/Description:</span>              
            </label>
            <form:input type="text" path="description" id="description" required="required"/>
          </div>
          <div>
            <label>Head of Household:</label>
            <form:checkbox path="headOfHousehold" id="headOfHousehold"/>
          </div>
		  <div>
            <label for="phoneNumber">
              <span>Phone Number:</span>              
            </label>
            <form:input type="text" path="phoneNumber" id="phoneNumber"/>
          </div>
		  
		  <div class="submit">
            <button type="submit" name="save">Save</button>
          </div>
         </fieldset>
		 
	
	  </form:form>
		 
	</div>
    

  </body>
</html>