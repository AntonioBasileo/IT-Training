<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
		<!-- Favicon -->
		<link rel="shortcut icon" href="resources/images/logo.png" />
		
		<meta charset="ISO-8859-1">
		
		<title>IT Training - Registrazione segreteria</title>
		
	    <!--Import Google Icon Font-->
	    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
      
		<!-- Compiled and minified CSS -->
	    <link rel="stylesheet" href="resources/css/materialize.min.css">
	    <link rel="stylesheet" href="resources/css/materialize.css">
	
	    <!-- Compiled and minified JavaScript -->
	    <script src="resources/js/jquery.min.js" type="text/javascript"></script>
	    <script src="resources/js/materialize.min.js" type="text/javascript"></script>
	    <script src="resources/js/initialize.js"></script>
		
	    
	    <!--Import Google Icon Font-->
	    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>
	<jsp:include page="/WEB-INF/views/nav/nav-studente.jsp" />

	<div class="container">
			<div class="row"></div>
			<div class="row"></div>
			<div class="row"></div>
			<div class="row"></div>
			
			<c:if test="${not empty listaDomandeAccettate}">
				<ul class="collapsible">
				  <c:forEach items="${listaDomandeAccettate}" var="lista" varStatus="loop">
				  	  <li>  
					      <div class="collapsible-header">
						      <i class="material-icons">business_center</i>
						      <p>Azienda ospitante: <c:out value="${lista.azienda.nome}"/></p>
						      <span class="badge white-text waves-effect waves-light btn-small">Compila registro</span>
					      </div>
					    <div class="collapsible-body">
					    	<p class="teal-text">Giorni di tirocinio:</p>
					    	<div class=row>
					    		<div class="row">
					    			<div class="col s3">
					    				<p>Giorno</p>
					    				
					    			</div>
					    			<div class="col s3">
					    				<p>Giorno</p>
					    				
					    			</div>
					    			<div class="col s3">
					    				<p>Giorno</p>
					    				
					    			</div>
					    		</div>
					    	</div>
					    </div>
					  </li>
				  </c:forEach>
				</ul>
			</c:if>
	</div>	
</body>
</html>