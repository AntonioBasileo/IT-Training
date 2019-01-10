<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>

  <div class="row">
    <form:form class="col s12" action="/compila-domanda" method="POST" modelAttribute="domandaForm"> 
    	<!-- Data inizio -->    
      	<div class="row date-input">
          <div class="col s12">
          </div>
          <p class="teal-text">Data inizio:</p>
          <div class="input-field col s4">
            <i class="material-icons prefix">event_note</i>
            <form:input path="giornoInizio" id="giornoInizio" /> 
            <label for="giornoInizio">Giorno</label> 
          </div>
          <div class="input-field col s5">
            <form:select path="meseInizio" id="meseInizio">
              <form:option value="1">
                Gennaio
              </form:option>
              <form:option value="2">
              	Febbraio
              </form:option>
              <form:option value="3">
                Marzo
              </form:option>
              <form:option value="4">
                Aprile
              </form:option>
              <form:option value="5">
                Maggio
              </form:option>
              <form:option value="6">
                Giugno
              </form:option>
              <form:option value="7">
                Luglio
              </form:option>
              <form:option value="8">
                Agosto
              </form:option>
              <form:option value="9">
                Settembre
              </form:option>
              <form:option value="10">
                Ottobre
              </form:option>
              <form:option value="11">
                Novembre
              </form:option>
              <form:option value="12">
                Dicembre
              </form:option>
            </form:select>
          </div>
          <div class="input-field col s3">
            <form:input
                     path="annoInizio"   
                        id="annoInizio" /> 
                        <label for="annoInizio">Anno</label>
          </div>
          
          <div class="input-field col s12">
            <form:errors path="giornoInizio" cssClass="helper-text red-text chip" />
          </div>
        </div>
        
         
    	<!-- Data fine -->    
      	<div class="row date-input">
          <div class="col s12">
          </div>
          <p class="teal-text">Data fine:</p>
          <div class="input-field col s4">
            <i class="material-icons prefix">event_note</i>
            <form:input path="giornoFine" id="giornoFine" />
            <label for="giornoFine">Giorno</label>  
          </div>
          <div class="input-field col s5">
            <form:select path="meseFine" id="meseFine">
              <form:option value="1">
                Gennaio
              </form:option>
              <form:option value="2">
              	Febbraio
              </form:option>
              <form:option value="3">
                Marzo
              </form:option>
              <form:option value="4">
                Aprile
              </form:option>
              <form:option value="5">
                Maggio
              </form:option>
              <form:option value="6">
                Giugno
              </form:option>
              <form:option value="7">
                Luglio
              </form:option>
              <form:option value="8">
                Agosto
              </form:option>
              <form:option value="9">
                Settembre
              </form:option>
              <form:option value="10">
                Ottobre
              </form:option>
              <form:option value="11">
                Novembre
              </form:option>
              <form:option value="12">
                Dicembre
              </form:option>
            </form:select>
          </div>
          <div class="input-field col s3">
            <form:input
                        path="annoFine"
                        id="annoFine" /> 
                        <label for="annoFine">Anno</label>
          </div>
          
          <div class="input-field col s12">
            <form:errors path="giornoFine" cssClass="helper-text red-text chip" />
          </div>
        </div>  
        
        <div class="row">
          <div class="input-field col s12">
            <i class="material-icons prefix">business_center</i>
            <form:input path="nomeAzienda" id="azienda" value="${nomeAzienda}"/>
            <label for="nomeAzienda">Nome azienda</label>
          </div>
         </div>  
        
        <div class="row">
          <div class="input-field col s12">
            <form:errors path="nomeAzienda" cssClass="helper-text red-text chip" />
          </div>
         </div>
        
        <div class="row">
        	<p class="teal-text">CFU tirocinio:</p>
        	<div class="input-fiel col s6">
        		<form:select path="cfu" id="cfu"  selected="selected">
        			<form:option value="6">
        				6
        			</form:option>
        			<form:option value="12">
        				12
        			</form:option>
        			<form:option value="18">
        				18
        			</form:option>
        		</form:select>
        	</div>
        		
        	<div class="input-field col s12">
            	<form:errors path="cfu" cssClass="helper-text red-text chip" />
         	 </div>
        </div>
        
        <div class="row right">
        	<button class="btn waves-effect waves-light" type="submit" name="action_studente">Invia
				 <i class="material-icons right">send</i>
			</button>
		</div>
    </form:form>
  </div>
</html>