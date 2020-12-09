<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<petclinic:layout pageName="eventos">


 
       <jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#fecha").datepicker({dateFormat: 'dd/mm/yy'});
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <h2><c:if test="${evento['new']}">Nuevo </c:if>Evento</h2>
            <form:form modelAttribute="evento" class="form-horizontal">
            <div class="form-group has-feedback">
                <petclinic:inputField label="Titulo" name="titulo"/>
                <petclinic:inputField label="Dirección" name="direccion"/>
                <petclinic:inputField label="Date" name="fecha"/>
                <petclinic:inputField label="Aforo Máximo" name="aforo" type="number" min="1" />
              	<petclinic:inputField label="Descripción" name="descripcion"/>
              	 <div class="form-group">
          		 <label>Cuidadores</label>
             
           		 <select name="cuidador" multiple>
        		  <c:forEach var="item" items="${cuidadores}">
           			 <option value="${item.id}">${item.nombre}</option>
         		 </c:forEach>
      		  </select>
      		  </div>
      		  
      	</div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="hidden" name="petId" value="${visit.pet.id}"/>
                    <button class="btn btn-default" type="submit">Add Visit</button>
                </div>
            </div>
            
    </form:form>
 
 </jsp:body>
 </petclinic:layout>