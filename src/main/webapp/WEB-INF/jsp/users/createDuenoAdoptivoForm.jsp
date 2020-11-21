<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="duenosAdoptivos">
    <h2>
        <c:if test="${duenoAdoptivo['new']}">New </c:if> DuenoAdoptivo
    </h2>
    <form:form modelAttribute="duenoAdoptivo" class="form-horizontal" id="add-duenoAdoptivo-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Nombre" name="nombre"/>
            <petclinic:inputField label="Apellidos" name="apellidos"/>
            <petclinic:inputField label="Dirección" name="direccion"/>
            <petclinic:inputField label="DNI" name="dni"/>
            <petclinic:inputField label="Teléfono" name="telefono"/>
            <petclinic:inputField label="Email" name="email"/>
            <petclinic:inputField label="Username" name="user.username"/>
            <petclinic:inputField label="Password" name="user.password"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${duenoAdoptivo['new']}">
                        <button class="btn btn-default" type="submit">Add DueñoAdoptivo</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Update DueñoAdoptivo</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>
