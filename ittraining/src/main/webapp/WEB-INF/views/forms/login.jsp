<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
				<!-- Login Form -->
			    <div class="collapsible-header">
			      <i class="material-icons">account_circle</i>
			      Login
			    </div>
			    <div class="collapsible-body">
			
				  <div class="row">
				    <form class="col s12">
		  				<div class ="container center">
		  					<img class="hide-on-med-and-down" style="width: 200px; height: 200px" src="resources/images/logo.png">
				      		<div class="row">
				      
				        		<div class="input-field col s12">
				        			<i class="material-icons prefix">account_circle</i>
				          			<input id="username-login" type="text" class="validate">
				          			<label for="username-login">Username</label>
				       			 </div>
				      		</div>
				      	<div class="row">
				        	<div class="input-field col s12">
				        		<i class="material-icons prefix">lock</i>
				          		<input id="password-login" type="password" class="validate">
				          		<label for="password-login">Password</label>
				        	</div>
				      	</div>
				      	
					  	<button class="btn waves-effect waves-light" type="submit" name="action">Login
					    	<i class="material-icons right">send</i>
					  	</button>
				  		</div>
				     </form>
				   </div>
			    
			    </div>
</html>