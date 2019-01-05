<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

	<spring:message var="notifica" code="${testoNotifica}"/> 
	
	<input id="toast" type="hidden" value="${notifica}"/>
	
	<div class="row"></div>
	<div class="row"></div>
	<div class="row"></div>
	<div class="row"></div>
	  <div class="row">
	    <form:form class="col s12" action="/aggiungi-ente" method="POST" modelAttribute="convenzioneForm">
	      <div class="row">
	        <div class="input-field col s6">
	          <i class="material-icons prefix">account_circle</i>
	          <form:input path="nome" id="nome" type="text" class="validate"/>
	          <label for="nome">Nome azienda</label>
	          <form:errors path="nome" cssClass="helper-text red-text chip" />
	        </div>
	        <div class="input-field col s6">
	          <i class="material-icons prefix">mail</i>
	          <form:input path="email" id="email" type="text" class="validate"/>
	          <label for="email">Email aziendale</label>
	          <form:errors path="email" cssClass="helper-text red-text chip" />
	       </div>
	      </div>
	      <div class="row"></div>
		  <div class="row"></div>
	      <div class="row"></div>
		  <div class="row"></div>
	      <div class="row">
	        <div class="input-field col s6">
	          <i class="material-icons prefix">directions</i>
	          <form:input path="indirizzo" id="indirizzo" type="text" class="validate"/>
	          <label for="indirizzo">Indirizzo</label>
	          <form:errors path="indirizzo" cssClass="helper-text red-text chip" />
	        </div>
	        <div class="input-field col s6">
	          <i class="material-icons prefix">phone</i>
	          <form:input path="telefono" id="telefono" type="tel" class="validate"/>
	          <label for="telefono">Telefono</label>
	          <form:errors path="telefono" cssClass="helper-text red-text chip" />
	        </div>
	      </div>
	      <div class="row"></div>
		  <div class="row"></div>
	      <div class="row"></div>
		  <div class="row"></div>
	      <div class="row">
	        <div class="input-field col s6">
	          <i class="material-icons prefix">home</i>
	          <form:input path="sede" id="sede" type="tel" class="validate"/>
	          <label for="sede">Sede</label>
	          <form:errors path="sede" cssClass="helper-text red-text chip" />
	        </div>
	      </div>
	      <div class="row"></div>
		  <div class="row right">
			  <button class="btn waves-effect waves-light" type="submit" name="action">Aggiungi
				<i class="material-icons right">archive</i>
			  </button>
		  </div>
	    </form:form>
	  </div>

</html>