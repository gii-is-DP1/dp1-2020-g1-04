<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>


<petclinic:layout pageName="Visita">
	<h2>La Visita del ${visita.momento} al detalle</h2>



	<table class="table table-striped">
		<tr>
			<th>¿Donde?</th>
			<td><b><c:out value="${visita.lugar}" /></b></td>
		</tr>
		<tr>
			<th>¿Cuándo?</th>
			<td><b><c:out value="${visita.momento}" /></b></td>
		</tr>
		<tr>
			<th>Animal</th>
			<td><b><c:out value="${visita.animal.nombre}" /></b> <b> <img
					src="${visita.animal.foto }" alt="Foto de ${visita.animal.nombre }"
					class="foto"></b></td>
		</tr>
		<tr>
			<sec:authorize access="hasAnyAuthority('duenoadoptivo')">
				<th>Cuidador</th>
				<td><b><spring:url value="/cuidadores/show/{cuidadorId}"
							var="cuidadorUrl">
							<spring:param name="cuidadorId" value="${visita.cuidador.id}" />
						</spring:url> <a href="${fn:escapeXml(cuidadorUrl)}"><c:out
								value="${visita.cuidador.nombre} " /></a></b></td>
			</sec:authorize>

			<sec:authorize access="hasAnyAuthority('cuidador')">
				<th>Dueño Adoptivo</th>
				<td><b> <spring:url value="/duenoAdoptivo/show/{duenoId}"
							var="duenoUrl">
							<spring:param name="duenoId" value="${visita.dueno.id}" />
						</spring:url> <a href="${fn:escapeXml(duenoUrl)}"><c:out
								value="${visita.dueno.nombre} " /></a></b></td>

			</sec:authorize>
		</tr>

	</table>
<h2>Comentarios</h2>

	<table id="comentariosTable" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 150px;">Autor</th>
				<th style="width: 150px;">¿Cuándo?</th>
				<th style="width: 150px;">Comentario</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${comentarios}" var="comentario">
				<tr>
					<td>
					<c:out value="${comentario.dueno.nombre}"></c:out>
					<c:out value="${comentario.cuidador.nombre}"></c:out>
					<c:out value="${comentario.director.nombre}"></c:out>
					<td><c:out value="${comentario.momento}" /></td>
					<td><c:out value="${comentario.comentario}" /></td>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>


</petclinic:layout>