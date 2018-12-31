<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
		  <ul class="collection  teal-text">
		  	<c:forEach items="${listaDomandeStudente}" var="lista" varStatus="loop">
			    <li class="collection-item avatar">
			      <img src="resources/images/logo.png" alt="" class="circle">
			      <p>Azienda ospitante: <c:out value="${lista.azienda.nome}"/></p>
			      <p>Data: <c:out value="${lista.data}"/></p>
			      <p>Data inizio: <c:out value="${lista.inizioTirocinio}"/></p>
			      <p>Data fine: <c:out value="${lista.fineTirocinio}"/></p>
			      <c:choose>
			      	<c:when test="${lista.status == 0 }">
			      		Stato: In attesa di approvazione da parte dell'azienda
			      	</c:when>
			      	<c:when test="${lista.status == 1 }">
			      		Stato: Approvata dall'azienda
			      	</c:when>
			      	<c:when test="${lista.status == 2 }">
			      		Stato: Rifiutata
			      	</c:when>
			      	<c:when test="${lista.status == 3 }">
			      		Stato: In attesa che il tutor accademico approvi il progetto formativo
			      	</c:when>
			      	<c:when test="${lista.status == 4 }">
			      		Stato: Accettata
			      	</c:when>
			      </c:choose>
			      <p class="secondary-content"><i class="material-icons left">school</i></p>
			    </li>
			</c:forEach>
		  </ul>
</html>