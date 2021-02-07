<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="adopciones">
	<h2>Adopciones</h2>
	    <table id="adopcionTable" class="table table-striped">
        <thead>
        <tr>
        <th style="width: 20%;">Detalles</th>
            <th style="width: 20%;">Unidad Familiar</th>
            <th style="width: 40%;">Mayores Edad</th>
            <th style="width: 40%;">Requisitos Leídos</th>           
            <th style="width: 40%;">Permiso Comunidad</th>
            <th style="width: 40%;">¿Otros animales?</th>
            <th style="width: 40%;">Motivo adopción</th>
            <th style="width: 40%;">Estado</th> 
            <th style="width: 40%;">Motivo de la decisión</th>
            <th style="width: 40%;">Fecha de la decisión</th>
            <th style="width: 40%;">Cuidador</th>
            <th style="width: 40%;">Animal</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${adopciones}" var="adopcion">
            <tr>
            <td>
            	<a href="/adopcion/show/<c:out value="${adopcion.id}"/>">Ver</a>
            </td>
                <td>
                  <c:out value="${adopcion.unidadFamiliar}"/>
                </td>
                <td>
                    <c:out value="${adopcion.mayoresDeEdad}"/>
                </td>
                 <td>
                    <c:out value="${adopcion.leidoRequisitos}"/>
				</td>
				<td>
                    <c:out value="${adopcion.permisoComunidadVecinos}"/>
				</td>
				<td>
                    <c:out value="${adopcion.otrosAnimales}"/>
				</td>
				<td>
                    <c:out value="${adopcion.motivo}"/>
				</td>
				<td>
                    <c:out value="${adopcion.estado}"/>
				</td>
				<td>
                    <c:out value="${adopcion.motivoDecision}"/>
				</td>
				 <td>
                    <c:out value="${adopcion.fechaDecision}"/>
				</td>
				<td>
					 <a href="<spring:url value="/cuidadores/show/${adopcion.animal.cuidador.id }" htmlEscape="true" />">Cuidador</a>
				</td>
				<td>
					 <a href="<spring:url value="/animales/show/${adopcion.animal.id}" htmlEscape="true" />">Animal</a>
				</td>
				
            </tr>
        </c:forEach>
        </tbody>
    </table>

</petclinic:layout> 