<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
		<meta charset="ISO-8859-1">
		<!-- Favicon -->
		<link rel="shortcut icon" href="resources/images/logo.png" />
		
		<meta charset="ISO-8859-1">
		
		<title>Aggiungi ente</title>
		
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
		
	    
	    <!--Import Google Icon Font-->
	    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>
	<!-- Navbar -->
	<nav>
		<div class="nav-wrapper">
			<ul id="nav-mobile" class="right">
				<li><a href="/home"><i class="material-icons left">business_center</i>Aggiungi Convenzione</a></li>
				<li><a href="/home"><i class="material-icons left">contact_mail</i>Lista Domande</a></li>
				<li><a href="/home"><i class="material-icons left">home</i>IT Training</a></li>
			</ul>
		</div>
	</nav>
	<!-- Container -->
	<div class="container">
	<div class="row"></div>
	<div class="row"></div>
	<div class="row"></div>
	<div class="row"></div>
	  <div class="row">
	    <form class="col s12">
	      <div class="row">
	        <div class="input-field col s6">
	          <i class="material-icons prefix">account_circle</i>
	          <input id="nome" type="text" class="validate">
	          <label for="nome">Nome azienda</label>
	        </div>
	        <div class="input-field col s6">
	          <i class="material-icons prefix">home</i>
	          <input id="sede" type="tel" class="validate">
	          <label for="sede">Sede</label>
	        </div>
	      </div>
	      <div class="row"></div>
		  <div class="row"></div>
	      <div class="row"></div>
		  <div class="row"></div>
	      <div class="row">
	        <div class="input-field col s6">
	          <i class="material-icons prefix">directions</i>
	          <input id="indirizzo" type="text" class="validate">
	          <label for="indirizzo">Indirizzo</label>
	        </div>
	        <div class="input-field col s6">
	          <i class="material-icons prefix">phone</i>
	          <input id="telefono" type="tel" class="validate">
	          <label for="telefono">Telefono</label>
	        </div>
	      </div>
	      <div class="row"></div>
		  <div class="row"></div>
		  <button class="btn waves-effect waves-light" type="submit" name="action">Aggiungi
			<i class="material-icons right">archive</i>
		  </button>
	    </form>
	  </div>
	</div>

</body>
</html>