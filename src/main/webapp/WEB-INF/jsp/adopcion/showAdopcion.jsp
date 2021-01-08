<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="adopcion">

	<h2>Solicitud de Adopcion de ${adopcion.animal.nombre} al detalle</h2>
	<a href="/animales/show/${adopcion.animal.id }"><img
		src="${adopcion.animal.foto}" alt="${adopcion.animal.nombre}"
		class="foto"></a>

	<table class="table table-striped">
		<tr>
			<th>Estado</th>
			<td><b><c:out value="${adopcion.estado}" /></b></td>
		</tr>
		<tr>
			<th>Motivo decision</th>
			<td><b><c:out value="${adopcion.motivoDecision}" /></b></td>
		</tr>

		<tr>
			<th>Fecha decision</th>
			<td><b><c:out value="${adopcion.fechaDecision}" /></b></td>
		</tr>

		<tr>
			<th>Unidad Familiar</th>
			<td><b><c:out value="${adopcion.unidadFamiliar}" /></b></td>
		</tr>
		<tr>
			<th>Mayores de Edad</th>
			<td><b><c:out value="${adopcion.mayoresDeEdad}" /></b></td>
		</tr>
		<tr>
			<th>Permiso Comunidad</th>
			<td><b><c:out value="${adopcion.permisoComunidadVecinos}" /></b></td>
		</tr>
		<tr>
			<th>¿Tiene en casa otros animales?</th>
			<td><b><c:out value="${adopcion.otrosAnimales}" /></b></td>
		</tr>
		<tr>
			<th>¿Por qué quiere adoptar?</th>
			<td><b><c:out value="${adopcion.motivo}" /></b></td>
		</tr>
		<tr>
			<th>Animal</th>
			<td><b><a href="/animal/show/${adopcion.animal.id }">${adopcion.animal.nombre}</a></b></td>
		</tr>

		<sec:authorize access="hasAuthority('director')">
			<tr>
				<th>Dueño Adoptivo</th>
				<td><b><a href="/duenosAdoptivos/${adopcion.dueno.id }">${adopcion.dueno.nombre}</a></b></td>
			</tr>



		</sec:authorize>

	</table>
	<sec:authorize access="hasAuthority('director')">
		<c:if test="${adopcion.estado eq 'PENDIENTE'}">
			<spring:url value="/adopcion/actualizarEstado/{adopcionId}" var="editUrl">
				<spring:param name="adopcionId" value="${adopcion.id}" />
			</spring:url>
			<a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Actualizar
				Adopcion</a>
		</c:if>
		</sec:authorize>
</petclinic:layout>