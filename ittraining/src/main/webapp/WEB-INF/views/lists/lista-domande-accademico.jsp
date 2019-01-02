<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
		  <ul class="collection">
		  	<c:forEach items="${listaDomandeAccademico}" var="lista" varStatus="loop">
			    <li class="collection-item avatar">
			      <img src="resources/images/logo.png" alt="" class="circle">
			      <p><span class="teal-text">Azienda ospitante: </span> <c:out value="${lista.azienda.nome}"/></p>
			      <p><span class="teal-text">Data richiesta: </span> <c:out value="${lista.data.getDayOfMonth()}"/>-<c:out value="${lista.data.getMonthValue()}"/>-<c:out value="${lista.data.getYear()}"/></p>
			      <p><span class="teal-text">Data inizio: </span> <c:out value="${lista.inizioTirocinio.getDayOfMonth()}"/>-<c:out value="${lista.inizioTirocinio.getMonthValue()}"/>-<c:out value="${lista.inizioTirocinio.getYear()}"/></p>
			      <p><span class="teal-text">Data fine: </span> <c:out value="${lista.fineTirocinio.getDayOfMonth()}"/>-<c:out value="${lista.fineTirocinio.getMonthValue()}"/>-<c:out value="${lista.fineTirocinio.getYear()}"/></p>
			      <p><span class="teal-text">Studente: </span> <c:out value="${lista.studente.nome}"/> <c:out value="${lista.studente.cognome}"/></p>
			      <c:choose>
			      
			      
			      	<c:when test="${lista.status == 0 }">
			      		<div class="row"></div>
			      		<p class="yellow lighten-1 waves-effect waves-light btn-small"><i class="material-icons right">data_usage</i>In attesa dell'azienda</p>
			      	</c:when>
			      	<c:when test="${lista.status == 1 }">
			      		<div class="row"></div>
			      		<p class="amber waves-effect waves-light btn-small"><i class="material-icons right">check</i>Accettata dall'azienda</p>
			      		
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
						      <a href="/approva-progetto?id=${lista.id}" class="waves-effect waves-light btn-small">Approva</a>
						      <a href="/rifiuta-progetto?id=${lista.id}" class="red waves-effect waves-light btn-small">Rifiuta</a>
						    </div>
						  </div>
			      	</c:when>
			      	
			      	
			      	<c:when test="${lista.status == 2 }">
			      		<div class="row"></div> 
			      		<p class="red waves-effect waves-light btn-small"><i class="material-icons right">close</i>Rifiutata dall'azienda</p>
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
			      		<p class="green accent-3 waves-effect waves-light btn-small"><i class="material-icons right">check</i>Approvata</p>
			      		
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
			      
			      
			      <p class="secondary-content"><i class="material-icons left">school</i></p>
			    </li>
			</c:forEach>
		  </ul>
</html>