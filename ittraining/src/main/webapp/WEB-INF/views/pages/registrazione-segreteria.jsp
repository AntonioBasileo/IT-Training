<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	<!-- Navbar -->
	<nav>
		<div class="nav-wrapper">
			<ul id="nav-mobile" class="right">
				<li><a href="/home"><i class="material-icons left">home</i>IT Training</a></li>
			</ul>
		</div>
	</nav>
		  
	<div class="container">
	    <div class="row"></div>
		<div class="row"></div>
		<div class="row">
			<h3 class="teal-text">Registrazione - Segreteria</h3>
		</div>
	    <div class="row"></div>
		<div class="row"></div>
		
		<jsp:include page="/WEB-INF/views/forms/registrazione-segreteria.jsp" />
	</div>
</body>
</html>