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

<petclinic:layout pageName="enfermedad">

	<h2>La Enfermedad "${enfermedad.nombre}" de
		${enfermedad.animal.nombre} al detalle</h2>



	<table class="table table-striped">
		<tr>
			<th>Nombre</th>
			<td><b><c:out value="${enfermedad.nombre}" /></b></td>
		</tr>
		<tr>
			<th>Inicio</th>
			<td><b> <petclinic:localDate date="${enfermedad.comienzo}"
						pattern="dd/MM/yyyy" />
			</b></td>
		</tr>
		<tr>
			<th>Fin</th>
			<td><b> <petclinic:localDate date="${enfermedad.fin}"
						pattern="dd/MM/yyyy" />
			</b></td>
		</tr>
		<tr>
			<th>Curado</th>
			<td><b><c:if test="${enfermedad.curado}">
						<c:out value="Si" />
					</c:if> <c:if test="${!enfermedad.curado}">
						<c:out value="No" />
					</c:if></b></td>
		</tr>
		<tr>

			<th>Comentario</th>
			<td><b> <c:out value="${enfermedad.comentario }" /></b></td>


		</tr>

	</table>

	
	<a href="/animales/show/${enfermedad.animal.id }"><c:out value="Volver a"></c:out><img
		alt="${enfermedad.animal.nombre }" src="${enfermedad.animal.foto }"
		class="foto"> </a>






</petclinic:layout>