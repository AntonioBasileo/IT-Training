<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
		<!-- Favicon -->
		<link rel="shortcut icon" href="resources/images/logo.png" />
		
		<meta charset="ISO-8859-1">
		
		<title>IT Training - Login</title>
		
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
	    <script src="resources/js/initialize.js" type="text/javascript"></script>
	    <script src="resources/js/toast.js" type="text/javascript"></script>
		
	    
	    <!--Import Google Icon Font-->
	    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>

<body>
		<!-- Sidenav -->
		<jsp:include page="/WEB-INF/views/nav/side-nav.jsp" />
		
		<jsp:include page="/WEB-INF/views/nav/nav-home.jsp" />

	<div class="container">
	    <div class="row"></div>
		<div class="row"></div>
		<div class="row"></div>
	    <div class="row"></div>
		<div class="row"></div>
		
		<jsp:include page="/WEB-INF/views/forms/login.jsp" />
	</div>
		
</body>
</html>