<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>

  <head>
    <title>ASACS Client Search</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" type="text/css" media="all" href="<c:url value="../../resources/asacs073.css" />"/>
  </head>

  <body>

	 <div>
    
      <h2>Search Client</h2>
      
      

      <form:form action="ClientSearchSubmit" method="post" modelAttribute="searchClient">
		
		<fieldset>
          
          <legend> Search client - enter one field  </legend>
			
      		<form:errors class="fieldError" />
			<div>
				<form:label path="searchParms" for="searchParms">
				<span>Search Name</span>              
				</form:label>
				<form:input type="text" path="searchParms" name="searchParms"  />
				<form:errors path="searchParms" class="fieldError" />
			</div>
			<div>
				<form:label path="searchDescription" for="searchDescription">
				<span>Search ID/Description</span>              
				</form:label>
				<form:input type="text" path="searchDescription" name="searchDescription"  />
				<form:errors path="searchDescription" class="fieldError" />
			</div>
		  <!-- type="submit" name="clientAdd" action="goToAdd" -->
			<div class="submit">
				<button type="submit" name="clientSearch" action="clientSearch">Search</button>
				<button type="submit" name="goToAdd" action="goToAdd">Add New Client</button>
								
			</div>
			</fieldset>
		</form:form>
		<!--  
			<div class="submit">			
				<spring:url value="/ClientAddForm" var="clientAddUrl" />
				<button  onclick="location.href='${clientAddUrl}'">Add New Client</button>
			</div>
		-->		
		
	</div>
    
    <div class="asacslist">
    
      <h2>List of Clients</h2>
      
      <table>
        <thead>
          <tr>
            <th>Full Name</th>
            <th>ID/Description</th>
            <th>Phone Number</th>
            <th>Head of Household</th>
            <th>Select</th>
          </tr>
        </thead>
        <tbody>
        	<c:forEach var="cl" items="${searchResults}">
	          <tr>
	            <td>${cl.fullName}</td>
	            <td>${cl.description}</td>
	            <td>${cl.phoneNumber}</td>
	            <td>${cl.hoH}</td>
	            <td>
	            <spring:url value="/ClientViewForm?clientId=${cl.clientId}" var="clientUrl" />
	            <button onclick="location.href='${clientUrl}'">Select</button>
	            
	            </td>            
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