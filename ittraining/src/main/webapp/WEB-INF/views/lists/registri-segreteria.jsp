<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<c:forEach items="${listaDomandeRegistri}" var="lista" varStatus="loop">
		<div class="row card panel" style="padding: 20px;">
	      <div class="row">
	      	<h6 class="teal-text">Domanda presso <c:out value="${domanda.azienda.getNome()}"/></h6>
	      	<div class=" row">
	      		 <p>numero di CFU: <c:out value="${lista.cfu}"/></p>
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
		  </div>
		  
	      <div class="row"></div>
	      <div class="row"></div>
          <div class="row"></div>
	      <div class="row"></div>
	      <div class="row"></div>
     </c:forEach>
</body>
</html>