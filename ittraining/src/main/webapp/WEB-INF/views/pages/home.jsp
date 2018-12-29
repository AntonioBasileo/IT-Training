<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
		  <ul id="slide-out" class="sidenav">
		    <li><div class="user-view">
		      <div class="background">
		      </div>
		      <a><img class="circle" src="resources/images/logo.png"></a>
		    </div></li>
		    <li><a href="/registrazione-studente-form"><i class="material-icons">school</i>Studente</a></li>
		    <li><a href="/registrazione-segreteria-form"><i class="material-icons">account_balance</i>Segreteria</a></li>
		    <li><a href="/registrazione-accademico-form"><i class="material-icons">supervisor_account</i>Tutor accademico</a></li>
		    <li><a href="/registrazione-aziendale-form"><i class="material-icons">business_center</i>Tutor aziendale</a></li>
		  </ul>
		  
		  
		<!-- Navbar -->
		  <nav>
		    <div class="nav-wrapper">
		      <ul id="nav-mobile" class="right">
		        <li><a data-target="slide-out" class="sidenav-trigger show-on-medium-and-up"><i class="material-icons left">add</i>Registrati</a></li>
		        <li><a href="/login-form"><i class="material-icons left">account_circle</i>Login</a></li>
		        <li><a href="/home"><i class="material-icons left">home</i>IT Training</a></li>
		      </ul>
		    </div>
		  </nav>
		
		<!-- Container -->
		<div class = "container">
		
			<h5>Benvenuto <c:out value="${nomeUtente}"/></h5>
			<div class="row">
				
			</div>
			
			<!-- Slider -->
			  <div class="slider z-depth-2">
			    <ul class="slides">
			      <li>
			        <img src="resources/images/slider1.png"> <!-- random image -->
			        <div class="caption right-align">
			          <h4>Iscriviti alla piattaforma</h4>
			          <h5 class="light grey-text text-lighten-3">Il tuo tirocinio a portata di click</h5>
			        </div>
			      </li>
			      <li>
			        <img src="resources/images/slider2.jpg"> <!-- random image -->
			        <div class="caption right-align">
			          <h4>Scegli il tuo progetto</h4>
			          <h5 class="light grey-text text-lighten-3">Il tuo tirocinio a portata di click</h5>
			        </div>
			      </li>
			      <li>
			        <img src="resources/images/slider3.jpg"> <!-- random image -->
			        <div class="caption right-align">
			          <h4>Acquisisci esperienza</h4>
			          <h5 class="light grey-text text-lighten-3">Il tuo tirocinio a portata di click</h5>
			        </div>
			      </li>
			      <li>
			        <img src="resources/images/slider4.jpg"> <!-- random image -->
			        <div class="caption center-align">
			          <h4>Plasma il tuo percorso!</h4>
			          <h5 class="light grey-text text-lighten-3">Il tuo tirocinio a portata di click</h5>
			        </div>
			      </li>
			    </ul>
			    </div>
			    
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
			        <footer class="page-footer">
			          <div class="container">
			            <div class="row">
			              <div class="col l6 s12">
			              </div>
			              <div class="col l4 offset-l2 s12">
			                <h5 class="white-text">Contatti sviluppatori</h5>
			                <ul>
			                  <li>antonio.basileo92@gmail.com</li>
			                  <li>alessiadagosto@hotmail.it</li>
			                  <li>lauraoliva@gmail.com</li>
			                  <li>romualdomanzo@gmail.com</li>
			                </ul>
			              </div>
			            </div>
			          </div>
			          <div class="footer-copyright">
			            <div class="container">
			            © 2019 Copyright Antonio Basileo, Alessia D'Agosto, Laura Oliva, Romualdo Manzo
			            </div>
			          </div>
			        </footer>
		
	</body>
</html>