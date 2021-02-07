<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>


<petclinic:layout pageName="adopciones">
	<h2>Adopciones Solicitadas</h2>
	<table id="adopcionTable" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 20%">Detalles</th>
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
			<c:forEach items="${solicitadas}" var="solicitada">
				<tr>
					<td><a
						href="<spring:url value="/adopcion/show/${solicitada.id }" htmlEscape="true" />">Detalles</a>
					</td>
					<td><c:out value="${solicitada.unidadFamiliar}" /></td>
					<td><c:out value="${solicitada.mayoresDeEdad}" /></td>
					<td><c:out value="${solicitada.leidoRequisitos}" /></td>
					<td><c:out value="${solicitada.permisoComunidadVecinos}" /></td>
					<td><c:out value="${solicitada.otrosAnimales}" /></td>
					<td><c:out value="${solicitada.motivo}" /></td>
					<td><c:out value="${solicitada.estado}" /></td>
					<td><c:out value="${solicitada.motivoDecision}" /></td>
					<td><c:out value="${solicitada.fechaDecision}" /></td>
					<td><a
						href="<spring:url value="/cuidadores/show/${solicitada.animal.cuidador.id }" htmlEscape="true" />">Cuidador</a>
					</td>
					<td><a
						href="<spring:url value="/animales/show/${solicitada.animal.id}" htmlEscape="true" />">Animal</a>
					</td>

				</tr>
			</c:forEach>
		</tbody>
	</table>
	<h2>Adopciones Aceptadas</h2>
	<table id="adopcionTable" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 20%">Detalles</th>
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
			<c:forEach items="${aceptadas}" var="aceptada">
				<tr>
					<td><a
						href="<spring:url value="/adopcion/show/${aceptada.id }" htmlEscape="true" />">Detalles</a>
					</td>
					<td><c:out value="${aceptada.unidadFamiliar}" /></td>
					<td><c:out value="${aceptada.mayoresDeEdad}" /></td>
					<td><c:out value="${aceptada.leidoRequisitos}" /></td>
					<td><c:out value="${aceptada.permisoComunidadVecinos}" /></td>
					<td><c:out value="${aceptada.otrosAnimales}" /></td>
					<td><c:out value="${aceptada.motivo}" /></td>
					<td><c:out value="${aceptada.estado}" /></td>
					<td><c:out value="${aceptada.motivoDecision}" /></td>
					<td><c:out value="${aceptada.fechaDecision}" /></td>
					<td><a
						href="<spring:url value="/cuidadores/show/${aceptada.animal.cuidador.id }" htmlEscape="true" />">Cuidador</a>
					</td>
					<td><a
						href="<spring:url value="/animales/show/${aceptada.animal.id}" htmlEscape="true" />">Animal</a>
					</td>

				</tr>
			</c:forEach>
		</tbody>
	</table>
	<h2>Adopciones Denegadas</h2>
	<table id="adopcionTable" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 20%">Detalles</th>
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
			<c:forEach items="${denegadas}" var="denegada">
				<tr>
					<td><a
						href="<spring:url value="/adopcion/show/${denegada.id }" htmlEscape="true" />">Detalles</a>
					</td>
					<td><c:out value="${denegada.unidadFamiliar}" /></td>
					<td><c:out value="${denegada.mayoresDeEdad}" /></td>
					<td><c:out value="${denegada.leidoRequisitos}" /></td>
					<td><c:out value="${denegada.permisoComunidadVecinos}" /></td>
					<td><c:out value="${denegada.otrosAnimales}" /></td>
					<td><c:out value="${denegada.motivo}" /></td>
					<td><c:out value="${denegada.estado}" /></td>
					<td><c:out value="${denegada.motivoDecision}" /></td>
					<td><c:out value="${denegada.fechaDecision}" /></td>
					<td><a
						href="<spring:url value="/cuidadores/show/${denegada.animal.cuidador.id }" htmlEscape="true" />">Cuidador</a>
					</td>
					<td><a
						href="<spring:url value="/animales/show/${denegada.animal.id}" htmlEscape="true" />">Animal</a>
					</td>

				</tr>
			</c:forEach>
		</tbody>
	</table>

</petclinic:layout>
