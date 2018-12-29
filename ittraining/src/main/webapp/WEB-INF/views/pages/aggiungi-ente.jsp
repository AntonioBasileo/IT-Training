<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
		<!-- Favicon -->
		<link rel="shortcut icon" href="resources/images/logo.png" />
		
		<meta charset="ISO-8859-1">
		
		<title>IT Training - Convenzione</title>
		
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
	    <script src="resources/js/home.js"></script>
		
	    
	    <!--Import Google Icon Font-->
	    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>
	<!-- Navbar -->
	<nav>
		<div class="nav-wrapper">
			<ul id="nav-mobile" class="right">
				<li><a href="/convenzione"><i class="material-icons left">business_center</i>Aggiungi Convenzione</a></li>
				<li><a href="/lista-domande"><i class="material-icons left">contact_mail</i>Lista Domande</a></li>
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
	    <form:form class="col s12" action="/segreteria/aggiungi-ente" method="POST" modelAttribute="convenzioneForm">
	      <div class="row">
	        <div class="input-field col s6">
	          <i class="material-icons prefix">account_circle</i>
	          <form:input path="nome" id="nome" type="text" class="validate"/>
	          <label for="nome">Nome azienda</label>
	          <form:errors path="nome" cssClass="helper-text red-text chip" />
	        </div>
	        <div class="input-field col s6">
	          <i class="material-icons prefix">home</i>
	          <form:input path="sede" id="sede" type="tel" class="validate"/>
	          <label for="sede">Sede</label>
	          <form:errors path="sede" cssClass="helper-text red-text chip" />
	        </div>
	      </div>
	      <div class="row"></div>
		  <div class="row"></div>
	      <div class="row"></div>
		  <div class="row"></div>
	      <div class="row">
	        <div class="input-field col s6">
	          <i class="material-icons prefix">directions</i>
	          <form:input path="indirizzo" id="indirizzo" type="text" class="validate"/>
	          <label for="indirizzo">Indirizzo</label>
	          <form:errors path="indirizzo" cssClass="helper-text red-text chip" />
	        </div>
	        <div class="input-field col s6">
	          <i class="material-icons prefix">phone</i>
	          <form:input path="telefono" id="telefono" type="tel" class="validate"/>
	          <label for="telefono">Telefono</label>
	          <form:errors path="telefono" cssClass="helper-text red-text chip" />
	        </div>
	      </div>
	      <div class="row"></div>
		  <div class="row"></div>
		  <button class="btn waves-effect waves-light" type="submit" name="action">Aggiungi
			<i class="material-icons right">archive</i>
		  </button>
	    </form:form>
	  </div>
	</div>

</body>
</html>