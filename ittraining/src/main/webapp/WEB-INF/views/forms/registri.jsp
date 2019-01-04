<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
	<c:if test="${empty listaDomandeApprovate}">
	
	
			<div class="row">
				<div class="col s12">
						<div class="card horizontal">
							<div class="card-image">
								<img src="resources/images/choose.svg">
							</div>
								<div class="card-stacked">
								<div class="card-content">
									<h5 class="teal-text">Spiacenti</h5>
									<p>Non è presente ancora alcun registro da compilare</p>
								</div>
					        <div class="card-action">
					          <a href="/home">Torna alla home</a>
					        </div>
						</div>
					</div>
				</div>
			</div>
	
	</c:if>

	<c:forEach items="${listaDomandeApprovate}" var="lista" varStatus="loop">
		<div class="row card panel" style="padding: 20px;">
	      <div class="row">
	      	<h6 class="teal-text">Domanda presso <c:out value="${lista.azienda.getNome()}"/></h6>
	      	<div class=" row">
	      		 <p>numero di CFU: <c:out value="${lista.cfu}"/></p>
	      	</div>
	    </div>
	      
	      <table>
        <thead>
          <tr>
              <th>Data</th>
              <th>Ora inizio</th>
              <th>Ora fine</th>
              <th>Descrizione</th>
          </tr>
        </thead>
		
		
        <tbody>
        	<c:forEach items="${lista.getRegistri()}" var="registro" varStatus="loop">
	          <tr>
	            <td><c:out value="${registro.data}"/></td>
	            <td><c:out value="${registro.inizio}"/></td>
	            <td><c:out value="${registro.fine}"/></td>
	            <td><c:out value="${registro.descrizione}"/></td>
	          </tr>
	        </c:forEach>
		</tbody>
      </table>
	      <div class="row"></div>
	      <div class="row"></div>
          <div class="row">
		    <form:form class="col s12" id="registroForm" action="/compila-registro" method="POST" modelAttribute="registroForm">
				      <div class="row">
				        <div class="input-field col s3">
				          <i class="material-icons prefix">event_note</i>
				          <form:input path="giornoInizio" id="giornoInizio" type="text" class="validate" value="${lista.inizioTirocinio.getDayOfMonth()}" disabled="true"/>
				          <label for="giornoInizio">Data inizio</label>
				        </div>
				        <div class="input-field col s3">
				          <form:input path="meseInizio" id="meseInizio" type="tel" class="validate" value="${lista.inizioTirocinio.getMonthValue()}" disabled="true"/>
				        </div>
				        <div class="input-field col s3">
				          <form:input path="annoInizio" id="annoInizio" type="tel" class="validate" value="${lista.inizioTirocinio.getYear()}" disabled="true"/>
				        </div>
				      </div>
				      <div class="row">
				        <div class="input-field col s3">
				          <i class="material-icons prefix">event_note</i>
				          <form:input path="giornoFine" id="giornoFine" type="text" class="validate" value="${lista.fineTirocinio.getDayOfMonth()}" disabled="true"/>
				          <label for="giornoFine">Data fine</label>
				        </div>
				        <div class="input-field col s3">
				          <form:input path="meseFine" id="meseFine" type="tel" class="validate" value="${lista.fineTirocinio.getMonthValue()}" disabled="true"/>
				        </div>
				        <div class="input-field col s3">
				          <form:input path="annoFine" id="annoFine" type="tel" class="validate" value="${lista.fineTirocinio.getYear()}" disabled="true"/>
				        </div>
				      </div>		
				      
				      	      
		        <div class="row date-input">
		          <div class="col s12">
		          </div>
		          <div class="input-field col s4">
		            <i class="material-icons prefix">event_note</i>
		            <form:input path="giorno" id="giorno"  type="text" class="validate"/>
		              <label for="giorno">Giorno</label>  
		          </div>
		          <div class="input-field col s5">
		            <form:select path="mese" class="icons" id="mese">
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
		            <form:input path="anno"
		                        id="anno"  type="text" class="validate"/>
		                        <label for="anno">Anno</label>  
		          </div>
		          
		          <div class="input-field col s6">
		            <form:errors path="anno" cssClass="helper-text red-text chip" />
		          </div>
		        </div>
		        
		        <jsp:include page="/WEB-INF/views/forms/ora-inizio-registro.jsp" />
		        <jsp:include page="/WEB-INF/views/forms/ora-fine-registro.jsp" />
		       
		        
		        <div class="row">
		        	<p class="teal-text">Numero ore:</p>
		        
		        	<div class="input-field col s6">
		        	
		        		<form:select path="numero_ore" class="icons" id="numero_ore">
		        		
		        			<form:option value="1">1</form:option>
		        			<form:option value="2">2</form:option>
		        			<form:option value="3">3</form:option>
		        			<form:option value="4">4</form:option>
		        			<form:option value="5">5</form:option>
		        			<form:option value="6">6</form:option>
		        			<form:option value="7">7</form:option>
		        			<form:option value="8">8</form:option>
		        		
		        		</form:select>
		        	
		        	</div>
		        
		        </div>
		        
		          <div class="row right">
					  <button class="btn waves-effect waves-light" type="submit" name="registro_submit">Compila
					    <i class="material-icons right">send</i>
					  </button>
				  </div>
		    </form:form>
		  </div>
		  </div>
	      <div class="row"></div>
	      <div class="row"></div>
   </c:forEach>
      
</html>