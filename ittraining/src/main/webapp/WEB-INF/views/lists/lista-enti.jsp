<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
		  <ul class="collection teal-text">
		  	<c:forEach items="${listaAziende}" var="current" varStatus="loop">
			    <li class="collection-item avatar">
			      <img src="resources/images/logo.png" alt="" class="circle">
			      <span class="title">Azienda</span>
			      <p>Nome: <c:out value="${current.nome}"/></p>
			      <p>Sede: <c:out value="${current.sede}"/></p>
			      <p>Indirizzo: <c:out value="${current.indirizzo}"/></p>
			      <p>Telefono: <c:out value="${current.telefono}"/></p>
			      <a href="/compila-domanda-form" class="secondary-content"><i class="material-icons left">business_center</i>Compila domanda</a>
			    </li>
			</c:forEach>
		  </ul>
</html>