<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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
	    <script src="resources/js/home.js"></script>
		
	    
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
		
		<!-- Container -->
		<div class = "container">
		<!-- Badges -->
			<!-- Login -->
			<ul class="collapsible">
			  <li>
			    <div class="collapsible-header">
			      <i class="material-icons">account_circle</i>
			      Login
			    </div>
			    <div class="collapsible-body">
			
				  <div class="row">
				    <form class="col s12">
		  				<div class ="container center">
		  					<img class="hide-on-med-and-down" style="width: 200px; height: 200px" src="resources/images/logo.png">
				      		<div class="row">
				      
				        		<div class="input-field col s12">
				        			<i class="material-icons prefix">account_circle</i>
				          			<input id="username-login" type="text" class="validate">
				          			<label for="username-login">Username</label>
				       			 </div>
				      		</div>
				      	<div class="row">
				        	<div class="input-field col s12">
				        		<i class="material-icons prefix">lock</i>
				          		<input id="password-login" type="password" class="validate">
				          		<label for="password-login">Password</label>
				        	</div>
				      	</div>
			      
						  <div class="row">
							  <div class="input-field col s6">
							    <select id="ruolo">
							      <option value="studente">Studente</option>
							      <option value="segreteria">Segreteria</option>
							      <option value="tutor_accademico">Tutor accademico</option>
							      <option value="tutor_aziendale">Tutor aziendale</option>
							    </select>
							  </div>
						  </div>
					  	<button class="btn waves-effect waves-light" type="submit" name="action">Login
					    	<i class="material-icons right">send</i>
					  	</button>
				  		</div>
				     </form>
				   </div>
			    
			    </div>
			  </li>
			  <li>
			  
			  
			  
			  
			  <!-- Registrazione Studente -->
			    <div class="collapsible-header">
			      <i class="material-icons">school</i>
			      Registrati come studente
			      </div>
			    <div class="collapsible-body">
			  <div class="row">
			    <form:form cssClass="col s12" action ="/richiesta-registrazione-studente" method = "POST" modelAttribute = "registrazioneStudente">
			      <div class="row">
			        <div class="input-field col s6">
			          <i class="material-icons prefix"></i>
			          <form:input path="nome" id="first_name_studente" type="text" cssClass="validate"/>
			          <label for="first_name_studente">Nome</label>
			        </div>
			        
			        <div class="input-field col s6">
			          <i class="material-icons prefix"></i>
			          <form:input path="cognome" id="last_name_studente" type="text" cssClass="validate"/>
			          <label for="last_name_studente">Cognome</label>
			        </div>
			      </div>
			      
				  <div class="row">
					  <div class="input-field col s6">
					  	<i class="material-icons prefix">wc</i>
					    <form:select path="sesso" id="sesso_studente">
					      <option value="M">M</option>
					      <option value="F">F</option>
					    </form:select>
					  </div>
				  </div>
				  
			      
        <div class="row date-input">
          <div class="col s12">
          </div>
          <div class="input-field col s4">
            <i class="material-icons prefix">cake</i>
            <form:input
              path="giornoNascita" id="iscrizione-giornoDiNascita" />  
          </div>
          <div class="input-field col s5">
            <form:select path="meseNascita" id="iscrizione-meseDiNascita">
              <form:option value="" disabled="true" selected="selected">
              </form:option>
              <form:option value="1">
                Gennaio
              </form:option>
              <form:option value="2">
              	Febbraio
              </form:option>
              <form:option value="3">
                Marzo
              </form:option>
              <form:option value="4">
                Aprile
              </form:option>
              <form:option value="5">
                Maggio
              </form:option>
              <form:option value="6">
                Giugno
              </form:option>
              <form:option value="7">
                Luglio
              </form:option>
              <form:option value="8">
                Agosto
              </form:option>
              <form:option value="9">
                Settembre
              </form:option>
              <form:option value="10">
               Ottobre
              </form:option>
              <form:option value="11">
               Novembre
              </form:option>
              <form:option value="12">
               Dicembre
              </form:option>
            </form:select>
          </div>
          <div class="input-field col s3">
            <form:input
                        path="annoNascita"
                        id="iscrizione-annoDiNascita" /> 
          </div>
        </div>
			      
			      <div class="row">
			        <div class="input-field col s6">
			          <i class="material-icons prefix">keyboard</i>
			          <form:input path="matricola" id="matricola" type="text" cssClass="validate"/>
			          <label for="matricola">Matricola</label>
			        </div>
			      </div>
			      
				  <div class="row">
				     <div class="input-field col s6">
				       <i class="material-icons prefix">account_circle</i>
				       <form:input path="username" id="username-studente" type="text" cssClass="validate"/>
				       <label for="username-studente">Username</label>
				     </div>
				  </div>
				  
			      <div class="row">
			        <div class="input-field col s6">
			          <i class="material-icons prefix">mail</i>
			          <form:input path="email" id="email_studente" type="email" cssClass="validate"/>
			          <label for="email_studente">Email</label>
			        </div>
			      </div>
			      
			      <div class="row">
			        <div class="input-field col s6">
			          <i class="material-icons prefix">lock</i>
			          <form:input path="password" id="password_studente" type="password" cssClass="validate"/>
			          <label for="password_studente">Password</label>
			        </div>
			      </div>
			      
			      <div class="row">
			        <div class="input-field col s6">
			          <i class="material-icons prefix">lock_outline</i>
			          <form:input path="confermaPassword" id="confirm_password_studente" type="password" cssClass="validate"/>
			          <label for="confirm_password_studente">Conferma password</label>
			        </div>
			      </div>
			      
				  <button class="btn waves-effect waves-light" type="submit" name="action_studente">Registrati
				    <i class="material-icons right">send</i>
				  </button>
			    </form:form>
		  	</div>
			    
		  </div>
			  </li>
			  <li>
			  
			  
			  
			  
			  
			  
			  <!-- Registrazione Segreteria -->
			    <div class="collapsible-header">
			      <i class="material-icons">account_balance</i>
			      Registrati come segreteria
			      </div>
			    <div class="collapsible-body">
			  <div class="row">
			    <form:form cssClass="col s12" action ="/richiesta-registrazione-segreteria" method = "POST" modelAttribute = "registrazioneSegreteria">
			      <div class="row">
			        <div class="input-field col s6">
			          <i class="material-icons prefix"></i>
			          <form:input path="nome" id="first_name_segreteria" type="text" cssClass="validate"/>
			          <label for="first_name_segreteria">Nome</label>
			        </div>
			        
			        <div class="input-field col s6">
			          <i class="material-icons prefix"></i>
			          <form:input path="cognome" id="last_name_segreteria" type="text" cssClass="validate"/>
			          <label for="last_name_segreteria">Cognome</label>
			        </div>
			      </div>
			      
				  <div class="row">
					  <div class="input-field col s6">
					  	<i class="material-icons prefix">wc</i>
					    <form:select path="sesso" id="sesso_segreteria">
					      <option value="M">M</option>
					      <option value="F">F</option>
					    </form:select>
					  </div>
				  </div>
				  
			      
        <div class="row date-input">
          <div class="col s12">
          </div>
          <div class="input-field col s4">
            <i class="material-icons prefix">cake</i>
            <form:input
              path="giornoNascita" id="iscrizione-giornoDiNascita-segreteria" />  
          </div>
          <div class="input-field col s5">
            <form:select path="meseNascita" id="iscrizione-meseDiNascita-segreteria">
              <form:option value="" disabled="true" selected="selected">
              </form:option>
              <form:option value="1">
                Gennaio
              </form:option>
              <form:option value="2">
              	Febbraio
              </form:option>
              <form:option value="3">
                Marzo
              </form:option>
              <form:option value="4">
                Aprile
              </form:option>
              <form:option value="5">
                Maggio
              </form:option>
              <form:option value="6">
                Giugno
              </form:option>
              <form:option value="7">
                Luglio
              </form:option>
              <form:option value="8">
                Agosto
              </form:option>
              <form:option value="9">
                Settembre
              </form:option>
              <form:option value="10">
               Ottobre
              </form:option>
              <form:option value="11">
               Novembre
              </form:option>
              <form:option value="12">
               Dicembre
              </form:option>
            </form:select>
          </div>
          <div class="input-field col s3">
            <form:input
                        path="annoNascita"
                        id="iscrizione-annoDiNascita-segreteria" /> 
          </div>
        </div>  
				  <div class="row">
				     <div class="input-field col s6">
				       <i class="material-icons prefix">account_circle</i>
				       <form:input path="username" id="username-segreteria" type="text" cssClass="validate"/>
				       <label for="username-segreteria">Username</label>
				     </div>
				  </div>
				  
			      <div class="row">
			        <div class="input-field col s6">
			          <i class="material-icons prefix">mail</i>
			          <form:input path="email" id="email_segreteria" type="email" cssClass="validate"/>
			          <label for="email_segreteria">Email</label>
			        </div>
			      </div>
			      
			      <div class="row">
			        <div class="input-field col s6">
			          <i class="material-icons prefix">lock</i>
			          <form:input path="password" id="password_segreteria" type="password" cssClass="validate"/>
			          <label for="password_segreteria">Password</label>
			        </div>
			      </div>
			      
			      <div class="row">
			        <div class="input-field col s6">
			          <i class="material-icons prefix">lock_outline</i>
			          <form:input path="confermaPassword" id="confirm_password_studente" type="password" cssClass="validate"/>
			          <label for="confirm_password_segreteria">Conferma password</label>
			        </div>
			      </div>
			      
				  <button class="btn waves-effect waves-light" type="submit" name="action_segreteria">Registrati
				    <i class="material-icons right">send</i>
				  </button>
			    </form:form>
		  	</div>
			    
		  </div>
			  </li>
			  <li>
			  
			  
			  
			  
			  
			  
			  <!-- Registrazione Tutor Accademico -->
			    <div class="collapsible-header">
			      <i class="material-icons">supervisor_account</i>
			      Registrati come tutor accademico
			      </div>
			    <div class="collapsible-body">
			  <div class="row">
			    <form:form cssClass="col s12" action ="/richiesta-registrazione-accademico" method = "POST" modelAttribute = "registrazioneAccademico">
			      <div class="row">
			        <div class="input-field col s6">
			          <i class="material-icons prefix"></i>
			          <form:input path="nome" id="first_name_accademico" type="text" cssClass="validate"/>
			          <label for="first_name_accademico">Nome</label>
			        </div>
			        
			        <div class="input-field col s6">
			          <i class="material-icons prefix"></i>
			          <form:input path="cognome" id="last_name_accademico" type="text" cssClass="validate"/>
			          <label for="last_name_accademico">Cognome</label>
			        </div>
			      </div>
			      
				  <div class="row">
					  <div class="input-field col s6">
					  	<i class="material-icons prefix">wc</i>
					    <form:select path="sesso" id="sesso_accademico">
					      <option value="M">M</option>
					      <option value="F">F</option>
					    </form:select>
					  </div>
				  </div>
				  
			      
        <div class="row date-input">
          <div class="col s12">
          </div>
          <div class="input-field col s4">
            <i class="material-icons prefix">cake</i>
            <form:input
              path="giornoNascita" id="iscrizione-giornoDiNascita-accademico" />  
          </div>
          <div class="input-field col s5">
            <form:select path="meseNascita" id="iscrizione-meseDiNascita-accademico">
              <form:option value="" disabled="true" selected="selected">
              </form:option>
              <form:option value="1">
                Gennaio
              </form:option>
              <form:option value="2">
              	Febbraio
              </form:option>
              <form:option value="3">
                Marzo
              </form:option>
              <form:option value="4">
                Aprile
              </form:option>
              <form:option value="5">
                Maggio
              </form:option>
              <form:option value="6">
                Giugno
              </form:option>
              <form:option value="7">
                Luglio
              </form:option>
              <form:option value="8">
                Agosto
              </form:option>
              <form:option value="9">
                Settembre
              </form:option>
              <form:option value="10">
               Ottobre
              </form:option>
              <form:option value="11">
               Novembre
              </form:option>
              <form:option value="12">
               Dicembre
              </form:option>
            </form:select>
          </div>
          <div class="input-field col s3">
            <form:input
                        path="annoNascita"
                        id="iscrizione-annoDiNascita-accademico" /> 
          </div>
        </div>
			      
				  <div class="row">
				     <div class="input-field col s6">
				       <i class="material-icons prefix">account_circle</i>
				       <form:input path="username" id="username-accademico" type="text" cssClass="validate"/>
				       <label for="username-accademico">Username</label>
				     </div>
				  </div>
				  
			      <div class="row">
			        <div class="input-field col s6">
			          <i class="material-icons prefix">mail</i>
			          <form:input path="email" id="email_accademico" type="email" cssClass="validate"/>
			          <label for="email_accademico">Email</label>
			        </div>
			      </div>
			      
			      <div class="row">
			        <div class="input-field col s6">
			          <i class="material-icons prefix">lock</i>
			          <form:input path="password" id="password_accademico" type="password" cssClass="validate"/>
			          <label for="password_accademico">Password</label>
			        </div>
			      </div>
			      
			      <div class="row">
			        <div class="input-field col s6">
			          <i class="material-icons prefix">lock_outline</i>
			          <form:input path="confermaPassword" id="confirm_password_accademico" type="password" cssClass="validate"/>
			          <label for="confirm_password_studente">Conferma password</label>
			        </div>
			      </div>
			      
				  <button class="btn waves-effect waves-light" type="submit" name="action_studente">Registrati
				    <i class="material-icons right">send</i>
				  </button>
			    </form:form>
		  	</div>
			    
		  </div>
			  </li>
			  <li>
			  
			  
			  
			  
			  
			  
			  <!-- Registrazione Tutor Aziendale -->
			    <div class="collapsible-header">
			      <i class="material-icons">business_center</i>
			      Registrati come tutor aziendale
			      </div>
			    <div class="collapsible-body">
			  <div class="row">
			    <form:form cssClass="col s12" action ="/richiesta-registrazione-aziendale" method = "POST" modelAttribute = "registrazioneAziendale">
			      <div class="row">
			        <div class="input-field col s6">
			          <i class="material-icons prefix"></i>
			          <form:input path="nome" id="first_name_aziendale" type="text" cssClass="validate"/>
			          <label for="first_name_aziendale">Nome</label>
			        </div>
			        
			        <div class="input-field col s6">
			          <i class="material-icons prefix"></i>
			          <form:input path="cognome" id="last_name_aziendale" type="text" cssClass="validate"/>
			          <label for="last_name_aziendale">Cognome</label>
			        </div>
			      </div>
			      
				  <div class="row">
					  <div class="input-field col s6">
					  	<i class="material-icons prefix">wc</i>
					    <form:select path="sesso" id="sesso_aziendale">
					      <option value="M">M</option>
					      <option value="F">F</option>
					    </form:select>
					  </div>
				  </div>
				  
			      
        <div class="row date-input">
          <div class="col s12">
          </div>
          <div class="input-field col s4">
            <i class="material-icons prefix">cake</i>
            <form:input
              path="giornoNascita" id="iscrizione-giornoDiNascita-aziendale" />  
          </div>
          <div class="input-field col s5">
            <form:select path="meseNascita" id="iscrizione-meseDiNascita-aziendale">
              <form:option value="" disabled="true" selected="selected">
              </form:option>
              <form:option value="1">
                Gennaio
              </form:option>
              <form:option value="2">
              	Febbraio
              </form:option>
              <form:option value="3">
                Marzo
              </form:option>
              <form:option value="4">
                Aprile
              </form:option>
              <form:option value="5">
                Maggio
              </form:option>
              <form:option value="6">
                Giugno
              </form:option>
              <form:option value="7">
                Luglio
              </form:option>
              <form:option value="8">
                Agosto
              </form:option>
              <form:option value="9">
                Settembre
              </form:option>
              <form:option value="10">
               Ottobre
              </form:option>
              <form:option value="11">
               Novembre
              </form:option>
              <form:option value="12">
               Dicembre
              </form:option>
            </form:select>
          </div>
          <div class="input-field col s3">
            <form:input
                        path="annoNascita"
                        id="iscrizione-annoDiNascita-aziendale" /> 
          </div>
        </div>
			      
				  <div class="row">
				     <div class="input-field col s6">
				       <i class="material-icons prefix">account_circle</i>
				       <form:input path="username" id="username-aziendale" type="text" cssClass="validate"/>
				       <label for="username-aziendale">Username</label>
				     </div>
				  </div>
				  
			      <div class="row">
			        <div class="input-field col s6">
			          <i class="material-icons prefix">mail</i>
			          <form:input path="email" id="email_aziendale" type="email" cssClass="validate"/>
			          <label for="email_aziendale">Email</label>
			        </div>
			      </div>
			      
			      <div class="row">
			        <div class="input-field col s6">
			          <i class="material-icons prefix">lock</i>
			          <form:input path="password" id="password_aziendale" type="password" cssClass="validate"/>
			          <label for="password_aziendale">Password</label>
			        </div>
			      </div>
			      
			      <div class="row">
			        <div class="input-field col s6">
			          <i class="material-icons prefix">lock_outline</i>
			          <form:input path="confermaPassword" id="confirm_password_aziendale" type="password" cssClass="validate"/>
			          <label for="confirm_password_aziendale">Conferma password</label>
			        </div>
			      </div>
			      
				  <button class="btn waves-effect waves-light" type="submit" name="action_aziendale">Registrati
				    <i class="material-icons right">send</i>
				  </button>
			    </form:form>
		  	</div>
			    
		  </div>
			  </li>
			</ul>
			
			
			
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