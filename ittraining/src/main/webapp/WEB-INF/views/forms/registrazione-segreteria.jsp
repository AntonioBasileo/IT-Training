<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
		
			  <!-- Registrazione Segreteria -->
			  <div class="row">
			    <form:form cssClass="col s12" action ="/registrazione/registrazione-segreteria-form/richiesta-registrazione-segreteria" method = "POST" modelAttribute = "registrazioneSegreteria">
			      <div class="row">
			        <div class="input-field col s6">
			          <i class="material-icons prefix">child_care</i>
			          <form:input path="nome" id="first_name_segreteria" type="text" cssClass="validate"/>
			          <label for="first_name_segreteria">Nome</label>
			          <form:errors path="nome" cssClass="helper-text red-text chip" />
			        </div>
			        
			        <div class="input-field col s6">
			          <form:input path="cognome" id="last_name_segreteria" type="text" cssClass="validate"/>
			          <label for="last_name_segreteria">Cognome</label>
			          <form:errors path="cognome" cssClass="helper-text red-text chip" />
			        </div>
			      </div>
			      
				  <div class="row">
					  <div class="input-field col s6">
					  	<i class="material-icons prefix">wc</i>
					    <form:select path="sesso" id="sesso_segreteria">
					      <option value="M">M</option>
					      <option value="F">F</option>
					    </form:select>
					    <form:errors path="sesso" cssClass="helper-text red-text chip" />
					  </div>
				  </div>
				  
			      
        <div class="row date-input">
          <div class="col s12">
          </div>
          <div class="input-field col s4">
            <i class="material-icons prefix">cake</i>
            <form:input
              path="giornoNascita" id="iscrizione-giornoDiNascita-segreteria" />
              <label for="iscrizione-giornoDiNascita-segreteria">Giorno</label>  
          </div>
          <div class="input-field col s5">
            <form:select class="icons" path="meseNascita" id="iscrizione-meseDiNascita-segreteria">
              <form:option value="1" data-icon="resources/images/calendar.svg">
                Gennaio
              </form:option>
              <form:option value="2" data-icon="resources/images/calendar.svg">
              	Febbraio
              </form:option>
              <form:option value="3" data-icon="resources/images/calendar.svg">
                Marzo
              </form:option>
              <form:option value="4" data-icon="resources/images/calendar.svg">
                Aprile
              </form:option>
              <form:option value="5" data-icon="resources/images/calendar.svg">
                Maggio
              </form:option>
              <form:option value="6" data-icon="resources/images/calendar.svg">
                Giugno
              </form:option>
              <form:option value="7" data-icon="resources/images/calendar.svg">
                Luglio
              </form:option>
              <form:option value="8" data-icon="resources/images/calendar.svg">
                Agosto
              </form:option>
              <form:option value="9" data-icon="resources/images/calendar.svg">
                Settembre
              </form:option>
              <form:option value="10" data-icon="resources/images/calendar.svg">
               Ottobre
              </form:option>
              <form:option value="11" data-icon="resources/images/calendar.svg">
               Novembre
              </form:option>
              <form:option value="12" data-icon="resources/images/calendar.svg">
               Dicembre
              </form:option>
            </form:select>
          </div>
          <div class="input-field col s3">
            <form:input
                        path="annoNascita"
                        id="iscrizione-annoDiNascita-segreteria" />
                        <label for="iscrizione-annoDiNascita-segreteria">Anno</label>  
          </div>
          
          <div class="input-field col s6">
            <form:errors path="giornoNascita" cssClass="helper-text red-text chip" />
          </div>
        </div>  
        		<div class="row">
				     <div class="input-field col s12">
				       <i class="material-icons prefix">phone</i>
				       <form:input path="telefono" id="telefono-segreteria" type="text" cssClass="validate"/>
				       <label for="telefono-segreteria">Telefono</label>
				       <form:errors path="telefono" cssClass="helper-text red-text chip" />
				     </div>
				  </div>
        		
				  <div class="row">
				     <div class="input-field col s12">
				       <i class="material-icons prefix">account_circle</i>
				       <form:input path="username" id="username-segreteria" type="text" cssClass="validate"/>
				       <label for="username-segreteria">Username</label>
				       <form:errors path="username" cssClass="helper-text red-text chip" />
				     </div>
				  </div>
				  
			      <div class="row">
			        <div class="input-field col s12">
			          <i class="material-icons prefix">mail</i>
			          <form:input path="email" id="email_segreteria" type="email" cssClass="validate"/>
			          <label for="email_segreteria">Email</label>
			          <form:errors path="email" cssClass="helper-text red-text chip" />
			        </div>
			      </div>
			      
			      <div class="row">
			        <div class="input-field col s12">
			          <i class="material-icons prefix">lock</i>
			          <form:input path="password" id="password_segreteria" type="password" cssClass="validate"/>
			          <label for="password_segreteria">Password</label>
			        </div>
			      </div>
			      
			      <div class="row">
			        <div class="input-field col s12">
			          <i class="material-icons prefix">lock_outline</i>
			          <form:input path="confermaPassword" id="confirm_password_segreteria" type="password" cssClass="validate"/>
			          <label for="confirm_password_segreteria">Conferma password</label>
			          <form:errors path="password" cssClass="helper-text red-text chip" />
			        </div>
			      </div>
			      
			      <div class="row right">
					  <button class="btn waves-effect waves-light" type="submit" name="action_segreteria">Registrati
					    <i class="material-icons right">send</i>
					  </button>
				  </div>
			    </form:form>
		  	</div>
</html>