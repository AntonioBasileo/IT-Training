<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html>

	<spring:message var="notifica" code="${testoNotifica}"/> 
	
	<input id="toast" type="hidden" value="${notifica}"/>
				<!-- Login Form -->
			
				  <div class="row">
				    <form:form class="col s12" method="POST" modelAttribute="loginForm" action="/login">
		  				<div class ="container center">
		  					<img class="hide-on-med-and-down" style="width: 200px; height: 200px" src="resources/images/logo.png">
				      		<div class="row">
				      
				        		<div class="input-field col s12">
				        			<i class="material-icons prefix">account_circle</i>
				          			<form:input path="username" id="username-login" type="text" class="validate"/>
				          			<label for="username-login">Username</label>
				       			 </div>
				      		</div>
				      	<div class="row">
				        	<div class="input-field col s12">
				        		<i class="material-icons prefix">lock</i>
				          		<form:input path="password" id="password-login" type="password" class="validate"/>
				          		<label for="password-login">Password</label>
				          		<form:errors path="password" cssClass="helper-text red-text chip" />
				        	</div>
				      	</div>
				      	
					  	<button class="btn waves-effect waves-light" type="submit" name="action">Login
					    	<i class="material-icons right">send</i>
					  	</button>
				  		</div>
				     </form:form>
				   </div>
</html>