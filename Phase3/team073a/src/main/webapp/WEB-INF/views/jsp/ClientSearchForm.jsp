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
    <link rel="stylesheet" type="text/css" media="all" href="../../css/asacs073.css"/>
  </head>

  <body>

	 <div>
    
      <h2>Search Client</h2>

      <form action="ClientSearchSubmit" method="post">
			
      
			<div>
				<label for="searchClient">
				<span>Search Client</span>              
				</label>
				<input type="text" name="searchClient" />
			</div>
		  <!-- type="submit" name="clientAdd" action="goToAdd" -->
			<div>
				<button type="submit" name="clientSearch" action="clientSearch">Search</button>
			</div>
		</form>
				<spring:url value="/ClientAddForm" var="clientAddUrl" />
				<button  onclick="location.href='${clientAddUrl}'">Add New Client</button>
		
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



  </body>
</html>