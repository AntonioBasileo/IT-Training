<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<body>
	<spring:message var="notifica" code="${testoNotifica}"/> 
	
	<input id="toast" type="hidden" value="${notifica}"/>

		<c:if test="${empty listaDomandeRegistri}">			
			<div class="row">
				<div class="col s12">
						<div class="card horizontal">
							<div class="card-image">
								<img src="../resources/images/choose.svg">
							</div>
								<div class="card-stacked">
								<div class="card-content">
									<h5 class="teal-text">Spiacenti</h5>
									<p>Non è presente ancora alcun registro</p>
								</div>
					        <div class="card-action">
					          <a href="/home">Torna alla home</a>
					        </div>
						</div>
					</div>
				</div>
			</div>
	
	</c:if>


	<c:if test="${not empty listaDomandeRegistri}">
	<c:forEach items="${listaDomandeRegistri}" var="lista" varStatus="loop">
		<div class="row card panel" style="padding: 20px;">
	      <div class="row">
	      	<h6 class="teal-text">Domanda presso <c:out value="${lista.azienda.getNome()}"/></h6>
	      	<div class=" row">
	      		 <p>Numero di CFU: <c:out value="${lista.cfu}"/></p>
	      	</div>
	      	
	      	<div class=" row">
	      		 <p>Data inizio: <c:out value="${lista.inizioTirocinio.getDayOfMonth()}"/>/<c:out value="${lista.inizioTirocinio.getMonthValue()}"/>/<c:out value="${lista.inizioTirocinio.getYear()}"/></p>
	      	</div>
	      	
	      	<div class=" row">
	      		 <p>Data fine: <c:out value="${lista.fineTirocinio.getDayOfMonth()}"/>/<c:out value="${lista.fineTirocinio.getMonthValue()}"/>/<c:out value="${lista.fineTirocinio.getYear()}"/></p>
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
	            <td><c:out value="${registro.data.getDayOfMonth()}"/>/<c:out value="${registro.data.getMonthValue()}"/>/<c:out value="${registro.data.getYear()}"/></td>
	            <td><c:out value="${registro.inizio}"/></td>
	            <td><c:out value="${registro.fine}"/></td>
	            <td><c:out value="${registro.descrizione}"/></td>
	          </tr>
	        </c:forEach>
		</tbody>
      </table>
		  
	      <div class="row"></div>
	      <div class="row"></div>
      
      				
				<c:if test="${lista.cfu == 6}">
					<c:choose>	
				        <c:when test="${lista.getNumeroOre() < 9000}">
				          <div class="row">
							  <p class="teal-text">Non è possibile approvare il registro, lo studente non ha ancora raggiunto il numero di ore previsto</p>
						  </div>
						</c:when>	
						
				        <c:when test="${lista.getNumeroOre() >= 9000}">
				          <c:choose>
				          	<c:when test="${lista.status == 4 }">
						          <div class="row right">
									  <a class="btn waves-effect waves-light" href="/approva-registro?id=${lista.id}"><i class="material-icons right">send</i>Approva</a>
								  </div>
							</c:when>
				          	<c:when test="${lista.status == 5 }">
						          <div class="row">
									  <p class="teal-text">Registro approvato</p>
								  </div>
							</c:when>
				          	<c:when test="${lista.status == 6 }">
						          <div class="row">
									  <p class="teal-text">Registro approvato dal tutor accademico</p>
								  </div>
							</c:when>
				          	<c:when test="${lista.status == 7 }">
						          <div class="row">
									  <p class="teal-text">Registro approvato definitivamente</p>
								  </div>
							</c:when>
						  </c:choose>
						</c:when>
				    </c:choose>
			    </c:if>
			    
			    <c:if test="${domanda.cfu == 12}">
					<c:choose>	
				        <c:when test="${lista.getNumeroOre() < 18000}">
				          <div class="row">
				          	<p class="teal-text">Non è possibile approvare il registro, lo studente non ha ancora raggiunto il numero di ore previsto</p>
						  </div>
						</c:when>	
						
				        <c:when test="${lista.getNumeroOre() >= 18000}">
				          <c:choose>
				          	<c:when test="${lista.status == 4 }">
						          <div class="row right">
									  <a class="btn waves-effect waves-light" href="/approva-registro?id=${lista.id}"><i class="material-icons right">send</i>Approva</a>
								  </div>
							</c:when>
				          	<c:when test="${lista.status == 5 }">
						          <div class="row">
									  <p class="teal-text">Registro approvato</p>
								  </div>
							</c:when>
				          	<c:when test="${lista.status == 6 }">
						          <div class="row">
									  <p class="teal-text">Registro approvato</p>
								  </div>
							</c:when>
				          	<c:when test="${lista.status == 7 }">
						          <div class="row">
									  <p class="teal-text">Registro approvato</p>
								  </div>
							</c:when>
						  </c:choose>
						</c:when>
				    </c:choose>
			    </c:if>
			    
			    <c:if test="${domanda.cfu == 18}">
					<c:choose>	
				        <c:when test="${lista.getNumeroOre() < 27000}">
				          <div class="row">
				          	<p class="teal-text">Non è possibile approvare il registro, lo studente non ha ancora raggiunto il numero di ore previsto</p>
						  </div>
						</c:when>	
						
				        <c:when test="${lista.getNumeroOre() >= 27000}">
				          <c:choose>
				          	<c:when test="${lista.status == 4 }">
						          <div class="row right">
									  <a class="btn waves-effect waves-light" href="/approva-registro?id=${lista.id}"><i class="material-icons right">send</i>Approva</a>
								  </div>
							</c:when>
				          	<c:when test="${lista.status == 5 }">
						          <div class="row">
									  <p class="teal-text">Registro approvato</p>
								  </div>
							</c:when>
				          	<c:when test="${lista.status == 6 }">
						          <div class="row">
									  <p class="teal-text">Registro approvato</p>
								  </div>
							</c:when>
				          	<c:when test="${lista.status == 7 }">
						          <div class="row">
									  <p class="teal-text">Registro approvato</p>
								  </div>
							</c:when>
						  </c:choose>
						</c:when>
				    </c:choose>
			    </c:if>
      
	</div>
		  
	      <div class="row"></div>
	      <div class="row"></div>
          <div class="row"></div>
	      <div class="row"></div>
	      <div class="row"></div>
     </c:forEach>
     </c:if>
</body>
</html>