<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>

  <head>
    <title>ASACS View Client</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" type="text/css" media="all" href="<c:url value="../../resources/asacs073.css" />"/>
  </head>

  <body>

	 <div>
    
      <h2>View Client</h2>
      
      	<c:if test="${not empty msg}">			
			<strong>${msg}</strong>		   
		</c:if>

    	<div>
            <label for="service">
              <span>Service:</span>              
            </label>
            ${serviceObj.description}
        </div> 

      <form:form action="ClientViewSubmit" modelAttribute="client" method="post">
    
        <fieldset>
          
          <legend> Client Info </legend>


          
          <div>
            <label for="fullname">
              <span>Full Name:</span>              
            </label>
            <form:input path="fullName" id="fullName" readonly="readonly" />
          </div>
		  <div>
            <label for="iddesc">
              <span>ID/Description:</span>              
            </label>
            <form:input type="text" path="description" id="description" readonly="readonly"/>
          </div>
          <div>
            <label for="headOfHousehold">Head of Household:</label>
            <form:checkbox path="headOfHousehold" id="headOfHousehold" name="headOfHousehold" checkbox="${client.headOfHousehold}" readonly="readonly"/>
          </div>
		  <div>
            <label for="phoneNumber">
              <span>Phone Number:</span>              
            </label>
            <form:input type="text" path="phoneNumber" id="phoneNumber" readonly="readonly" />
          </div>
		  
		  <div class="submit">
            <button type="submit" name="addLog" action="addLog">Add Log</button>
            <button type="submit" name="editClient" action="editClient">Edit Client</button>
            <button type="submit" name="viewClientWaitlist" action="viewClientWaitlist">View Client Waitlist</button>     
          </div>
         </fieldset>
		 
	
	  </form:form>
		 
	</div>

     <br>
     <div align="center">
       <form method="post" action="SiteInfo">
         <button type="submit" align="center">Return To Site</button>
       </form>
     </div>
  </body>
</html>