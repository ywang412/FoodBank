<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>

<html>

  <head>
    <title>ASACS Edit Client</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" type="text/css" media="all" href="../../css/asacs073.css"/>
  </head>

  <body>

	 <div>
    
      <h2>Edit Client</h2>

    	<div>
            <label for="service">
              <span>Service:</span>              
            </label>
            <input type="text" id="serviceName" readonly>${serviceName} </input>
        </div> 

      <form:form action="ClientEditSubmit" modelAttribute="client" method="post">
    
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