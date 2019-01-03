<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
		<div class="row card panel">
	      <table>
	      <div class="row">
	      	<h6 class="teal-text">Domanda presso <c:out value="${lista.azienda.getNome()}"/></h6>
	      	<div class=" row">
	      		 <p>numero di CFU: <c:out value="${lista.cfu}"/></p>
	      	</div>
	      	<p></p>
	      </div>
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
		    <form class="col s12">
		      <div class="row">
		        <div class="input-field col s3">
		          <i class="material-icons prefix">event_note</i>
		          <input id="icon_prefix" type="text" class="validate" value="${lista.inizioTirocinio.getDayOfMonth()}" disabled>
		          <label for="icon_prefix">Data inizio</label>
		        </div>
		        <div class="input-field col s3">
		          <input id="icon_telephone" type="tel" class="validate" value="${lista.inizioTirocinio.getMonthValue()}" disabled>
		        </div>
		        <div class="input-field col s3">
		          <input id="icon_telephone" type="tel" class="validate" value="${lista.inizioTirocinio.getYear()}" disabled>
		        </div>
		      </div>
		      <div class="row">
		        <div class="input-field col s3">
		          <i class="material-icons prefix">event_note</i>
		          <input id="icon_prefix" type="text" class="validate" value="${lista.fineTirocinio.getDayOfMonth()}" disabled>
		          <label for="icon_prefix">Data fine</label>
		        </div>
		        <div class="input-field col s3">
		          <input id="icon_telephone" type="tel" class="validate" value="${lista.fineTirocinio.getMonthValue()}" disabled>
		        </div>
		        <div class="input-field col s3">
		          <input id="icon_telephone" type="tel" class="validate" value="${lista.fineTirocinio.getYear()}" disabled>
		        </div>
		      </div>
		    </form>
		  </div>
		  </div>
	      <div class="row"></div>
	      <div class="row"></div>
   </c:forEach>
      
</html>