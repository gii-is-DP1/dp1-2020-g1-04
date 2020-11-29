<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<petclinic:layout pageName="adopcion">

 <sec:authorize access="hasAnyAuthority('duenoadoptivo')">
    <h2>
        <c:if test="${adopcion['new']}">Nueva adopcion</c:if> 
    </h2>
    <form:form modelAttribute="adopcion" class="form-horizontal" id="edit-duenoAdoptivo-form">
    <form:hidden path="aceptada"/>
    <form:hidden path="motivoDecision"/>
    <form:hidden path="fechaDecision"/>
        <div class="form-group has-feedback">
            <petclinic:inputField label="Cuántas personas constituyen su unidad familiar" name="unidadFamiliar"/>
            <petclinic:inputField label="Cuántos son mayores de edad" name="mayoresDeEdad"/>
            <petclinic:inputField label="Motivo de la adopción" name="motivo"/>
            <div class="form-group">
            <label>Tengo permiso de mi comunidad de vecinos</label>
            <form:checkbox path="permisoComunidadVecinos"/>
            </div>
            <div class="form-group">
            <label>Tengo otros animales en casa</label>
            <form:checkbox path="otrosAnimales"/>
            </div>
            <div class="form-group">
            <label>He leído y estoy de acuerdo con los requisitos de adopción</label>
            <form:checkbox path="leidoRequisitos"/>
            </div>
        <div class="form-group">
            <label>Animal</label>
             
            <select name="animal">
        		  <c:forEach var="item" items="${animales}">
           			 <option value="${item.id}">${item.nombre}</option>
         		 </c:forEach>
      		  </select>
      		  </div>
      		  </div>
      		  
      		  <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${adopcion['new']}">
                        <button class="btn btn-default" type="submit">Solicitar Adopción</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Actualizar Adopción</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
    </sec:authorize>

</petclinic:layout>