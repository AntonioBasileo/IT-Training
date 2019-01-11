<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<c:if test="${empty listaDomandeApprovate}">			
			<div class="row">
				<div class="col s12">
						<div class="card horizontal">
							<div class="card-image">
								<img src="../resources/images/choose.svg">
							</div>
								<div class="card-stacked">
								<div class="card-content">
									<h5 class="teal-text">Spiacenti</h5>
									<p>Non è ancora possibile compilare un registro poichè non ci sono ancora domande di tirocinio approvate</p>
								</div>
					        <div class="card-action">
					          <a href="/home">Torna alla home</a>
					        </div>
						</div>
					</div>
				</div>
			</div>
	
	</c:if>

	<c:if test="${not empty listaDomandeApprovate}">
	<c:forEach items="${listaDomandeApprovate}" var="lista" varStatus="loop">
			  <ul class="collection">
			    <li class="collection-item avatar">
			      <i class="material-icons circle">folder</i>
			      <p><span class="teal-text">Azienda ospitante: </span> <c:out value="${lista.azienda.nome}"/></p>
			      <p><span class="teal-text">Data richiesta: </span> <c:out value="${lista.data.getDayOfMonth()}"/>-<c:out value="${lista.data.getMonthValue()}"/>-<c:out value="${lista.data.getYear()}"/></p>
			      <p><span class="teal-text">Data inizio: </span> <c:out value="${lista.inizioTirocinio.getDayOfMonth()}"/>-<c:out value="${lista.inizioTirocinio.getMonthValue()}"/>-<c:out value="${lista.inizioTirocinio.getYear()}"/></p>
			      <p><span class="teal-text">Data fine: </span> <c:out value="${lista.fineTirocinio.getDayOfMonth()}"/>-<c:out value="${lista.fineTirocinio.getMonthValue()}"/>-<c:out value="${lista.fineTirocinio.getYear()}"/></p>
			      <p><span class="teal-text">Studente: </span> <c:out value="${lista.studente.nome}"/> <c:out value="${lista.studente.cognome}"/></p>
			      <p><span class="teal-text">Numero CFU: </span> <c:out value="${lista.cfu }"/></p>
			      <p><span class="teal-text">Numero ore: </span> <c:out value="${lista.oreTotali }"/></p>
			      <a href="/home/registro-form?id=${lista.id}" class="secondary-content"><i class="material-icons left">business_center</i>Compila registro</a>
			    </li>
		  </ul>
	
	</c:forEach>
	</c:if>
</html>