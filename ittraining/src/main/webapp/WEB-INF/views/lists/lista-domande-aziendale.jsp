<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
	<c:if test="${empty listaDomandeAzienda}">
	
	
			<div class="row">
				<div class="col s12">
						<div class="card horizontal">
							<div class="card-image">
								<img src="resources/images/choose.svg">
							</div>
								<div class="card-stacked">
								<div class="card-content">
									<h5 class="teal-text">Spiacenti</h5>
									<p>Non è presente ancora alcuna domanda</p>
								</div>
					        <div class="card-action">
					          <a href="/home">Torna alla home</a>
					        </div>
						</div>
					</div>
				</div>
			</div>
	
	</c:if>

	<spring:message var="notifica" code="${testoNotifica}"/> 
	
	<input id="toast" type="hidden" value="${notifica}"/>
		  <ul class="collection">
		  	<c:forEach items="${listaDomandeAzienda}" var="lista" varStatus="loop">
			    <li class="collection-item avatar">
			      <i class="material-icons circle">folder</i>
			      <p><span class="teal-text">Azienda ospitante: </span> <c:out value="${lista.azienda.nome}"/></p>
			      <p><span class="teal-text">Data richiesta: </span> <c:out value="${lista.data.getDayOfMonth()}"/>-<c:out value="${lista.data.getMonthValue()}"/>-<c:out value="${lista.data.getYear()}"/></p>
			      <p><span class="teal-text">Data inizio: </span> <c:out value="${lista.inizioTirocinio.getDayOfMonth()}"/>-<c:out value="${lista.inizioTirocinio.getMonthValue()}"/>-<c:out value="${lista.inizioTirocinio.getYear()}"/></p>
			      <p><span class="teal-text">Data fine: </span> <c:out value="${lista.fineTirocinio.getDayOfMonth()}"/>-<c:out value="${lista.fineTirocinio.getMonthValue()}"/>-<c:out value="${lista.fineTirocinio.getYear()}"/></p>
			      <p><span class="teal-text">Studente: </span> <c:out value="${lista.studente.nome}"/> <c:out value="${lista.studente.cognome}"/></p>
			      <p><span class="teal-text">Numero CFU: </span> <c:out value="${lista.cfu }"/></p>
			      <p><span class="teal-text">Numero ore: </span> <c:out value="${lista.oreTotali }"/></p>
			      <c:choose>
			      
			      
			      	<c:when test="${lista.status == 0 }">
			      		<div class="row"></div>
			      		<p class="yellow lighten-1 waves-effect waves-light btn-small"><i class="material-icons right">data_usage</i>In attesa</p>
					    <div class="row">
					    
					    
					      <!-- Modal Structure -->
						  <div id="modalAccetta${lista.id}" class="modal">
						    <div class="modal-content">
						      <h5 class="teal-text">Progetto formativo</h5>
						      <div class="row"></div>
						      <div class="row">
						      	 <form:form modelAttribute="progettoFormAccetta" method="POST" action="/inserisci-progetto">
							      	<div class="input-field col s12">
							          <form:textarea path="descrizione" id="textarea1" class="materialize-textarea"></form:textarea>
							          <label for="textarea1">Inserisci il progetto formativo</label>
							        </div>
							        <div class="row">
							        	<div class="col s5">
									        <form:input path="idDomanda" type="hidden" name="idDomanda" value="${lista.id}"/>
								        </div>
							        </div>
							          <button class="btn-small waves-effect waves-light" type="submit" name="action">Invia
    										<i class="material-icons right">send</i>
  									  </button>
							      </form:form>
						      </div>
						    </div>
						    <div class="modal-footer">
						      <a class="modal-close waves-effect waves-green btn-flat">Indietro</a>
						    </div>
						  </div>
					    
					    
					      <!-- Modal Structure -->
						  <div id="modalRifiuta${lista.id}" class="modal">
						    <div class="modal-content">
						      <h5 class="teal-text">Rifiuta domanda</h5>
						      <div class="row"><p>Sei sicuro di voler rifiutare la domanda?</p></div>
						      <div class="row">
						      	 <form:form modelAttribute="progettoFormRifiuta" method="POST" action="/rifiuta-domanda">
							          <form:input path="descrizione" id="textarea1" type="hidden" value=""/>
									  <form:input path="idDomanda" type="hidden" name="idDomanda" value="${lista.id}"/>
							          <button class="red btn-small waves-effect waves-light" type="submit" name="action">Rifiuta
    										<i class="material-icons right">send</i>
  									  </button>
							      </form:form>
						      </div>
						    </div>
						    <div class="modal-footer">
						      <a class="modal-close waves-effect waves-green btn-flat">Indietro</a>
						    </div>
						  </div>
						  <div class="row"></div>
						  
						  
					     <a class="waves-effect waves-light btn-small modal-trigger" href="#modalAccetta${lista.id}"><i class="material-icons right">check</i>Accetta</a>
					     <a class="red waves-effect waves-light btn-small modal-trigger" href="#modalRifiuta${lista.id}">
					     <i class="material-icons right">close</i>Rifiuta</a>
					    </div>
			      	</c:when>
			      	
			      	
			      	<c:when test="${lista.status == 1 }">
			      		<div class="row"></div>
			      		<p class="amber waves-effect waves-light btn-small"><i class="material-icons right">check</i>Accettata</p>
			      		
			      		<div class="row"></div>
			      		
			      		  <div class="row">
				      		  <!-- Modal Trigger -->
							  <a class="waves-effect waves-light btn-small modal-trigger" href="#modal${lista.id}">Visualizza progetto Formativo</a>
						  </div>
						
						  <!-- Modal Structure -->
						  <div id="modal${lista.id}" class="modal">
						    <div class="modal-content">
						      <h5 class="teal-text">Progetto formativo</h5>
						      <p><c:out value="${lista.progettoFormativo.getDescrizione()}"/></p>
						    </div>
						    <div class="modal-footer">
						      <a href="#!" class="modal-close waves-effect waves-green btn-flat">Ok</a>
						    </div>
						  </div>
			      		
			      	</c:when>
			      	
			      	
			      	<c:when test="${lista.status == 2 }">
			      		<div class="row"></div>
			      		<p class="red waves-effect waves-light btn-small"><i class="material-icons right">close</i>Rifiutata</p>
			      	</c:when>
			      	
			      	
			      	<c:when test="${lista.status == 3 }">
			      		<div class="row"></div>
			      		<p class="red waves-effect waves-light btn-small"><i class="material-icons right">close</i>Progetto formativo rifiutato</p>
			      		
			      		<div class="row"></div>
			      		
			      		  <div class="row">
				      		  <!-- Modal Trigger -->
							  <a class="waves-effect waves-light btn-small modal-trigger" href="#modal${lista.id}">Visualizza progetto Formativo</a>
						  </div>
						
						  <!-- Modal Structure -->
						  <div id="modal${lista.id}" class="modal">
						    <div class="modal-content">
						      <h5 class="teal-text">Progetto formativo</h5>
						      <p><c:out value="${lista.progettoFormativo.getDescrizione()}"/></p>
						    </div>
						    <div class="modal-footer">
						      <a href="#!" class="modal-close waves-effect waves-green btn-flat">Ok</a>
						    </div>
						  </div>
			      	</c:when>
			      	
			      	
			      	<c:when test="${lista.status == 4 }">
			      		<div class="row"></div>
			      		<p class="green accent-3 waves-effect waves-light btn-small"><i class="material-icons right">done_all</i>Approvata dal tutor accademico</p>
			      		
			      		<div class="row"></div>
			      		
			      		  <div class="row">
				      		  <!-- Modal Trigger -->
							  <a class="waves-effect waves-light btn-small modal-trigger" href="#modal${lista.id}">Visualizza progetto Formativo</a>
						  </div>
						
						  <!-- Modal Structure -->
						  <div id="modal${lista.id}" class="modal">
						    <div class="modal-content">
						      <h5 class="teal-text">Progetto formativo</h5>
						      <p><c:out value="${lista.progettoFormativo.getDescrizione()}"/></p>
						    </div>
						    <div class="modal-footer">
						      <a href="#!" class="modal-close waves-effect waves-green btn-flat">Ok</a>
						    </div>
						  </div>
			      	</c:when>
			      	
			      	
			      	<c:when test="${lista.status == 5 }">
			      		<div class="row"></div>
			      		<p class="green accent-3 waves-effect waves-light btn-small"><i class="material-icons right">done_all</i>Registro approvato dal </p>
			      		
			      		<div class="row"></div>
			      		
			      		  <div class="row">
				      		  <!-- Modal Trigger -->
							  <a class="waves-effect waves-light btn-small modal-trigger" href="#modal${lista.id}">Registro approvato dal tutor aziendale</a>
						  </div>
						
						  <!-- Modal Structure -->
						  <div id="modal${lista.id}" class="modal">
						    <div class="modal-content">
						      <h5 class="teal-text">Progetto formativo</h5>
						      <p><c:out value="${lista.progettoFormativo.getDescrizione()}"/></p>
						    </div>
						    <div class="modal-footer">
						      <a href="#!" class="modal-close waves-effect waves-green btn-flat">Ok</a>
						    </div>
						  </div>
			      	</c:when>
			      	
			      	
			      	<c:when test="${lista.status == 6 }">
			      		<div class="row"></div>
			      		<p class="green accent-3 waves-effect waves-light btn-small"><i class="material-icons right">done_all</i>Registro approvato dal tutor accademico</p>
			      		
			      		<div class="row"></div>
			      		
			      		  <div class="row">
				      		  <!-- Modal Trigger -->
							  <a class="waves-effect waves-light btn-small modal-trigger" href="#modal${lista.id}">Visualizza progetto Formativo</a>
						  </div>
						
						  <!-- Modal Structure -->
						  <div id="modal${lista.id}" class="modal">
						    <div class="modal-content">
						      <h5 class="teal-text">Progetto formativo</h5>
						      <p><c:out value="${lista.progettoFormativo.getDescrizione()}"/></p>
						    </div>
						    <div class="modal-footer">
						      <a href="#!" class="modal-close waves-effect waves-green btn-flat">Ok</a>
						    </div>
						  </div>
			      	</c:when>
			      	
			      	
			      	<c:when test="${lista.status == 7 }">
			      		<div class="row"></div>
			      		<p class="green accent-3 waves-effect waves-light btn-small"><i class="material-icons right">done_all</i>Terminato con successo</p>
			      		
			      		<div class="row"></div>
			      		
			      		  <div class="row">
				      		  <!-- Modal Trigger -->
							  <a class="waves-effect waves-light btn-small modal-trigger" href="#modal${lista.id}">Visualizza progetto Formativo</a>
						  </div>
						
						  <!-- Modal Structure -->
						  <div id="modal${lista.id}" class="modal">
						    <div class="modal-content">
						      <h5 class="teal-text">Progetto formativo</h5>
						      <p><c:out value="${lista.progettoFormativo.getDescrizione()}"/></p>
						    </div>
						    <div class="modal-footer">
						      <a href="#!" class="modal-close waves-effect waves-green btn-flat">Ok</a>
						    </div>
						  </div>
			      	</c:when>
			      	
			      	
			      </c:choose>
			      <p class="secondary-content"><i class="material-icons left">business_center</i></p>
			    </li>
			</c:forEach>
		  </ul>
</html>