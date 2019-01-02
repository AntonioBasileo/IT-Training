<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
	<head>
		<!-- Favicon -->
		<link rel="shortcut icon" href="resources/images/logo.png" />
		
		<meta charset="ISO-8859-1">
		
		<title>IT Training - Home</title>
		
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
	
		<!-- Sidenav -->
		<jsp:include page="/WEB-INF/views/nav/side-nav.jsp" />
		 
		 <%if(request.getAttribute("utente") == null) { %>
		 
			<!-- Navbar -->
			<jsp:include page="/WEB-INF/views/nav/nav-home.jsp" />
			
		<% } else if(request.getAttribute("utente").getClass().getSimpleName().equals("Studente")) { %>
		
			<!-- Navbar -->
			<jsp:include page="/WEB-INF/views/nav/nav-studente.jsp" />
			
		<% } else if(request.getAttribute("utente").getClass().getSimpleName().equals("ImpiegatoSegreteria")) { %>
		
			<!-- Navbar -->
			<jsp:include page="/WEB-INF/views/nav/nav-segreteria.jsp" />
		
		<% } else if(request.getAttribute("utente").getClass().getSimpleName().equals("TutorAccademico")) {   %>
		
			<!-- Navbar -->
			<jsp:include page="/WEB-INF/views/nav/nav-accademico.jsp" />
		
		<% } else if(request.getAttribute("utente").getClass().getSimpleName().equals("TutorAziendale")) {  %>
		
			<!-- Navbar -->
			<jsp:include page="/WEB-INF/views/nav/nav-aziendale.jsp" />
		
		<% } %>
		
		<!-- Container -->
		<div class = "container">
		
			<div class="row"></div>
			<div class="row"></div>
			
			<!-- Slider -->
			<jsp:include page="/WEB-INF/views/carousel/slider.jsp" />
			
			<div class="row"></div>
			<div class="row"></div>
			
					    
			    <!-- Cards -->
				  <div class="row">
					  <div class="col s12">
					    <div class="card horizontal">
					      <div class="card-image">
					        <img src="resources/images/co-work.svg">
					      </div>
					      <div class="card-stacked">
					        <div class="card-content">
					          <p>IT Training è una piattaforma progettata e sviluppata per gli studenti del Dipartimento 
					          di Informatica dell'Università degli Studi di Salerno. L'idea è nata durante il 
					          corso di Ingegneria del Software nell'anno accademico 2018/2019 e 
					          l'obiettivo è quello di facilitare il più possibile il processo di attivazione di un tirocinio.</p>
					        </div>
					        <div class="card-action">
					          <a href="https://corsi.unisa.it/informatica">Dipartimento di Informatica</a>
					        </div>
					      </div>
					    </div>
					  </div>
				  </div>
				  <div class="row">
				    <div class="col s12 m6">
				      <div class="card blue-grey darken-1">
				        <div class="card-content white-text">
				          <span class="card-title"></span>
				          <p>Scegli tra le aziende convenzionate con l'università quella che più si adatta alle tue esigenze.</p>
				        </div>
				        <div class="card-action">
				        </div>
				      </div>
				    </div>
				    <div class="col s12 m6">
				      <div class="card blue-grey darken-1">
				        <div class="card-content white-text">
				          <span class="card-title"></span>
				          <p>Scegli tra i tutor accademici quello che più si avvicina al percorso che hai progettato.</p>
				        </div>
				        <div class="card-action">
				        </div>
				      </div>
				    </div>
				  </div>
 
		</div>
				  
				  <!-- Footer -->
				  <jsp:include page="/WEB-INF/views/footer/footer.jsp" />
		
	</body>
</html>