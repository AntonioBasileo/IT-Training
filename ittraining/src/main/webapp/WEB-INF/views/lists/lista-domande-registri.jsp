<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

	<c:forEach items="${listaDomandeApprovate}" var="lista" varStatus="loop">
			  <ul class="collection">
			    <li class="collection-item avatar">
			      <img src="resources/images/logo.png" alt="" class="circle">
			      <p><span class="teal-text">Azienda ospitante: </span> <c:out value="${lista.azienda.nome}"/></p>
			      <p><span class="teal-text">Data richiesta: </span> <c:out value="${lista.data.getDayOfMonth()}"/>-<c:out value="${lista.data.getMonthValue()}"/>-<c:out value="${lista.data.getYear()}"/></p>
			      <p><span class="teal-text">Data inizio: </span> <c:out value="${lista.inizioTirocinio.getDayOfMonth()}"/>-<c:out value="${lista.inizioTirocinio.getMonthValue()}"/>-<c:out value="${lista.inizioTirocinio.getYear()}"/></p>
			      <p><span class="teal-text">Data fine: </span> <c:out value="${lista.fineTirocinio.getDayOfMonth()}"/>-<c:out value="${lista.fineTirocinio.getMonthValue()}"/>-<c:out value="${lista.fineTirocinio.getYear()}"/></p>
			      <p><span class="teal-text">Studente: </span> <c:out value="${lista.studente.nome}"/> <c:out value="${lista.studente.cognome}"/></p>
			      <p><span class="teal-text">Numero CFU: </span> <c:out value="${lista.cfu }"/></p>
			      <p><span class="teal-text">Numero ore: </span> <c:out value="${lista.oreTotali }"/></p>
			      <a href="/registro-form?id=${lista.id}" class="secondary-content"><i class="material-icons left">business_center</i>Compila registro</a>
			    </li>
		  </ul>
	
	</c:forEach>
</html>