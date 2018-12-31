<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
		  <ul class="collection teal-text">
		  	<c:forEach items="${listaDomandeStudente}" var="current" varStatus="loop">
			    <li class="collection-item avatar">
			      <img src="resources/images/logo.png" alt="" class="circle">
			      <span class="title">Domanda</span>
			      <p>Azienda ospitante: <c:out value="${current.azienda.nome}"/></p>
			      <p>Data: <c:out value="${current.data}"/></p>
			      <p>Data inizio: <c:out value="${current.inizioTirocinio}"/></p>
			      <p>Data fine: <c:out value="${current.fineTirocinio}"/></p>
			      <c:choose>
			      	<c:when test="${current.status == 0 }">
			      		Stato: In attesa di approvazione da parte dell'azienda
			      	</c:when>
			      	<c:when test="${current.status == 1 }">
			      		Stato: Approvata
			      	</c:when>
			      	<c:when test="${current.status == 2 }">
			      		Stato: Rifiutata
			      	</c:when>
			      </c:choose>
			      <p class="secondary-content"><i class="material-icons left">school</i></p>
			    </li>
			</c:forEach>
		  </ul>
</html>