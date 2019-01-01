<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
		  <ul class="collection">
		  	<c:forEach items="${listaAziende}" var="lista" varStatus="loop">
			    <li class="collection-item avatar">
			      <img src="resources/images/logo.png" alt="" class="circle">
			      <p><span class="teal-text">Nome:</span> <c:out value="${lista.nome}"/></p>
			      <p><span class="teal-text">Sede:</span> <c:out value="${lista.sede}"/></p>
			      <p><span class="teal-text">Indirizzo:</span> <c:out value="${lista.indirizzo}"/></p>
			      <p><span class="teal-text">Telefono:</span> <c:out value="${lista.telefono}"/></p>
			      <a href="/compila-domanda-form?azienda=${current.nome}" class="secondary-content"><i class="material-icons left">business_center</i>Compila domanda</a>
			    </li>
			</c:forEach>
		  </ul>
</html>