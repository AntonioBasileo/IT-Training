<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	

	  
	<c:choose>
		
		<c:when test="${empty studente.tutor}">
		    <div class="row"></div>
			<div class="row"></div>
		  <ul class="collection">
		  	<c:forEach items="${listaTutor}" var="lista" varStatus="loop">
			    <li class="collection-item avatar">
			      <img src="../resources/images/logo.png" alt="" class="circle">
			      <p><span class="teal-text">Nome:</span> <c:out value="${lista.nome}"/></p>
			      <div class="row"></div>
			      <p><span class="teal-text">Cognome:</span> <c:out value="${lista.cognome}"/></p>
			      <div class="row"></div>
			      <p>Tutor accademico del Dipartimento di Informatica</p>
			      <a href="#modal${lista.username}" class="secondary-content modal-trigger"><i class="material-icons left">supervisor_account</i>Scegli</a>
			      
			      	  <!-- Modal Structure -->
					  <div id="modal${lista.username}" class="modal">
					    <div class="modal-content">
					      <h5 class="teal-text">Scegli tutor</h5>
					      <p>Sei sicuro di voler scegliere 	questo tutor?</p>
					    </div>
					    <div class="modal-footer">
					      <a href="/home/lista-tutor/scegli-tutor?op=${lista.username}" class="waves-effect waves-light btn-small"><i class="material-icons left">check</i>Sì, sono sicuro</a>
					    </div>
					  </div>
			    </li>
			</c:forEach>
		  </ul>
		</c:when>
		
		
		<c:when test="${not empty studente.tutor}">
		    <div class="row"></div>
			<div class="row"></div>
			<div class="row">
				<div class="col s12">
						<div class="card horizontal">
							<div class="card-image">
								<img src="../resources/images/check-tutor.svg">
							</div>
								<div class="card-stacked">
								<div class="card-content">
									<h5 class="teal-text">Avviso</h5>
									<p>Hai scelto il tuo tutor accademico</p>
								</div>
					        <div class="card-action">
					          <a href="/home">Torna alla home</a>
					        </div>
						</div>
					</div>
				</div>
			</div>
		</c:when>
		
		
	</c:choose>
</html>