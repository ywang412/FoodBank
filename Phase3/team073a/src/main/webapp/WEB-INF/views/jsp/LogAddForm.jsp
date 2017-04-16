<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>

<html>

  <head>
    <title>ASACS Add Log</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" type="text/css" media="all" href="../../css/asacs073.css"/>
  </head>

  <body>

	 <div>
    
      <h2>Add Log</h2>

    	<div>
            <label for="service">
              <span>Service:</span>              
            </label>
            ${serviceObj.description} for Client ${client.fullName}
        </div> 

      <form:form action="LogAddSubmit" modelAttribute="logEntry" method="post">
    
        <fieldset>
          
          <legend> Log Info </legend>


          
          <div>
            <label for="logDate">
              <span>Log Date:</span>              
            </label>
            <form:input path="logDate" id="logDate" readonly="readonly" />
          </div>
		  <div>
            <label for="usage">
              <span>Usage:</span>              
            </label>
            <form:input type="text" path="logUsage" id="usage" />
          </div>
		  <div>
            <label for="notes">
              <span>Notes:</span>              
            </label>
            <form:textarea type="text" path="logEntry" id="notes"/>
          </div>
		  
		  <div class="submit">
            <button type="submit" name="save">Save</button>          
          </div>
         </fieldset>
		 
	
	  </form:form>
		 
	</div>
    

  </body>
</html>