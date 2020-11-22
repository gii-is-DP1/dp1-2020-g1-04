<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="animales">

    <c:if test="${pageContext.request.userPrincipal.name == director.user.username}">
    <h2>
        <c:if test="${animal['new']}">Nuevo </c:if> animal
    </h2>

   <form:form modelAttribute="animal" class="form-horizontal" id="edit-duenoAdoptivo-form">
        <div class="form-group has-feedback">
             <petclinic:inputField label="Nombre" name="nombre"/>
            <petclinic:inputField label="Licencia" name="peligrosidad.licencia"/>
            <petclinic:inputField label="Grado de peligrosidad" name="peligrosidad.grado"/>
            <petclinic:inputField label="Requiere licencia" name="requisitos.licencia"/>
            <petclinic:inputField label="Requiere seguro" name="requisitos.seguro"/>
            <petclinic:inputField label="Grado de Atencion" name="atencion.atencion"/>
            <petclinic:inputField label="Grado de dificultad" name="atencion.dificultad"/>
            <petclinic:inputField label="Fecha de nacimiento" name="fechaNacimiento"/>
            <petclinic:inputField label="Chip" name="chip"/>
            <petclinic:inputField label="Nº Registro" name="numeroRegistro"/>
            <petclinic:inputField label="Adoptado" name="adoptado"/>
            <petclinic:inputField label="Tamaño" name="tamanyo"/>
            <petclinic:inputField label="Edad" name="edad"/>
            <petclinic:inputField label="Sexo" name="sexo"/>
            <petclinic:inputField label="Primera incorporacion" name="fechaPrimeraIncorporacion"/>
            <petclinic:inputField label="Ultima incorporacion" name="fechaUltimaIncorporacion"/>
            <petclinic:inputField label="Cuidador" name="cuidador.nombre"/>
            <petclinic:inputField label="Tipo" name="categoria.tipo"/>
            <petclinic:inputField label="Raza" name="categoria.raza"/>
            
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${animal['new']}">
                        <button class="btn btn-default" type="submit">Añadir Animal</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Actualizar animal</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
    </c:if>
     <c:if test="${pageContext.request.userPrincipal.name != director.user.username}">
     <c:out value = "No tienes acceso a estos datos"/><br>
      <a href="/" class="btn btn-default">Volver a Inicio</a>
    </c:if>
</petclinic:layout>