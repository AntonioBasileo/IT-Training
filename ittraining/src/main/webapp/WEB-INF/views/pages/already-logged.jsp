<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<head>
		<!-- Favicon -->
		<link rel="shortcut icon" href="resources/images/logo.png" />
		
		<meta charset="ISO-8859-1">
		
		<title>Loggato</title>
		
	    <!--Import Google Icon Font-->
	    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
      
		<!-- Compiled and minified CSS -->
	    <link rel="stylesheet" href="resources/css/materialize.min.css">
	    <link rel="stylesheet" href="resources/css/materialize.css">
	    
	    <!--Import Google Icon Font-->
	    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	</head>
</head>
<body>
	<div class="row"></div>
	<div class="row"></div>
	<div class="row"></div>
	<div class="row"></div>

	<div class="container">
		<div class="row">
			<div class="col s12">
					<div class="card horizontal">
						<div class="card-image">
							<img src="resources/images/logged.svg">
						</div>
							<div class="card-stacked">
							<div class="card-content">
								<p>Sei già loggato come <c:out value="${nomeUtente}"/>. Vuoi uscire oppure tornare alla home?</p>
							</div>
						<div class="card-action">
							<a href="/home">Home</a>
							<a href="/logout">Logout</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>