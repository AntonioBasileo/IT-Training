<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
		  <ul class="collection">
		  	<c:forEach items="${listaDomandeAzienda}" var="lista" varStatus="loop">
			    <li class="collection-item avatar">
			      <img src="resources/images/logo.png" alt="" class="circle">
			      <p><span class="teal-text">Azienda ospitante:</span> <c:out value="${lista.azienda.nome}"/></p>
			      <p><span class="teal-text">Data richiesta:</span> <c:out value="${lista.data.getDayOfMonth()}"/>-<c:out value="${lista.data.getMonthValue()}"/>-<c:out value="${lista.data.getYear()}"/></p>
			      <p><span class="teal-text">Data inizio:</span> <c:out value="${lista.inizioTirocinio.getDayOfMonth()}"/>-<c:out value="${lista.inizioTirocinio.getMonthValue()}"/>-<c:out value="${lista.inizioTirocinio.getYear()}"/></p>
			      <p><span class="teal-text">Data fine:</span> <c:out value="${lista.fineTirocinio.getDayOfMonth()}"/>-<c:out value="${lista.fineTirocinio.getMonthValue()}"/>-<c:out value="${lista.fineTirocinio.getYear()}"/></p>
			      <c:choose>
			      
			      
			      	<c:when test="${lista.status == 0 }">
			      		<span class="teal-text">Stato:</span> In attesa di approvazione da parte dell'azienda
					    <div class="row">
					    
					    
					      <!-- Modal Structure -->
						  <div id="modal${lista.id}" class="modal">
						    <div class="modal-content">
						      <h5 class="teal-text">Progetto formativo</h5>
						      <div class="row"></div>
						      <div class="row">
						      	 <form:form modelAttribute="progettoForm" method="POST" action="/inserisci-progetto">
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
						  <div class="row"></div>
						  
						  
					     <a class="waves-effect waves-light btn-small modal-trigger" href="#modal${lista.id}"><i class="material-icons right">check</i>Accetta</a>
					     <a class="red waves-effect waves-light btn-small modal-trigger" href="/rifiuta-domanda?id=${lista.id}"><i class="material-icons right">close</i>Rifiuta</a>
					    </div>
			      	</c:when>
			      	
			      	
			      	<c:when test="${lista.status == 1 }">
			      		<span class="teal-text">Stato:</span> Accettata in attesa che il tutor accademico accetti il progetto formativo
			      		
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
			      		<span class="teal-text">Stato:</span> Rifiutata
			      	</c:when>
			      	
			      	
			      	<c:when test="${lista.status == 3 }">
			      		<span class="teal-text">Stato:</span> Progetto formativo rifiutato
			      	</c:when>
			      	
			      	
			      	<c:when test="${lista.status == 4 }">
			      		<span class="teal-text">Stato:</span> Approvata
			      	</c:when>
			      	
			      	
			      </c:choose>
			      <p class="secondary-content"><i class="material-icons left">business_center</i></p>
			    </li>
			</c:forEach>
		  </ul>
</html>