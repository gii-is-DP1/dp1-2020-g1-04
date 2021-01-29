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

	<sec:authorize access="hasAnyAuthority('director')">
		<h2>
			Actualizar Estado Adopcion de <c:out value="${adopcion.animal.nombre}"></c:out></h2>
	<a href="/animales/show/<c:out value="${adopcion.animal.id }"></c:out>"><img
		src="<c:out value="${adopcion.animal.foto}"></c:out>" alt="<c:out value="${adopcion.animal.nombre}"></c:out>"
		class="foto"></a>
		
		<table class="table table-striped">
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
			<td><b><a href="/animal/show/<c:out value="${adopcion.animal.id }" />"><c:out value="${adopcion.animal.nombre}" /></a></b></td>
		</tr>

			<tr>
				<th>Dueño Adoptivo</th>
				<td><b><a href="/duenosAdoptivos/<c:out value="${adopcion.dueno.id }" />"><c:out value="${adopcion.dueno.nombre}" /></a></b></td>
			</tr>
		</table>
		
		<form:form modelAttribute="adopcion" class="form-horizontal"
			id="edit-adopcion-form">
			<form:hidden path="mayoresDeEdad"/>
			<form:hidden path="motivo"/>
			<form:hidden path="unidadFamiliar"/>
			<div class="form-group">
					<label class="col-sm-2 control-label">Estado</label>
					<div class="col-sm-10"> <select name="estado" class="form-control">
						<c:forEach var="item"   items="${estados}">
							<option value="${item}"
							<c:if test="${item == adopcion.estado}">
           			 selected
								</c:if>
							><c:out value="${item}"/></option>
						</c:forEach>
					</select>
				</div>
				</div>
			
			<div class="form-group has-feedback">
				<petclinic:inputField
					label="Motivo Decisión"
					name="motivoDecision" />
				</div>
				  <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button class="btn btn-default" type="submit">Actualizar Adopcion</button>
                </div>
            </div>
				
		</form:form>
	</sec:authorize>

</petclinic:layout>