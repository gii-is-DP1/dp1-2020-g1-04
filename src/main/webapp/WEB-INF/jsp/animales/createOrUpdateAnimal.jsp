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

<petclinic:layout pageName="animales">

	<sec:authorize access="hasAnyAuthority('director')">
		<h2>
			<c:if test="${animal['new']}">Nuevo </c:if>
			animal
		</h2>

		<form:form modelAttribute="animal" class="form-horizontal"
			id="edit-duenoAdoptivo-form">
			<div class="form-group has-feedback">
				<petclinic:inputField label="Nombre" name="nombre" />
				<petclinic:inputField label="Imagen" name="foto"></petclinic:inputField>
				<petclinic:inputField label="Licencia" name="peligrosidad.licencia" />
				<petclinic:inputField label="Grado de peligrosidad"
					name="peligrosidad.grado" />
				<petclinic:inputField label="Requiere licencia"
					name="requisitos.licenciarequerida" />
				<petclinic:inputField label="Requiere seguro"
					name="requisitos.seguro" />
				<petclinic:inputField label="Grado de Atencion"
					name="atencion.atencion" />
				<petclinic:inputField label="Grado de dificultad"
					name="atencion.dificultad" />
				<petclinic:inputField label="Fecha de nacimiento"
					name="fechaNacimiento" />
				<petclinic:inputField label="Chip" name="chip" />
				<petclinic:inputField label="Nº Registro" name="numeroRegistro" />
				<petclinic:inputField label="Adoptado" name="adoptado" />
				<petclinic:inputField label="Tamaño" name="tamanyo" />
				<!--  >  <petclinic:inputField label="Edad" name="edad"/><-->
				<petclinic:inputField label="Sexo" name="sexo" />
				<petclinic:inputField label="Primera incorporacion"
					name="fechaPrimeraIncorporacion" />
				<petclinic:inputField label="Ultima incorporacion"
					name="fechaUltimaIncorporacion" />

				<div class="form-group">
					<label class="col-sm-2 control-label">Cuidador</label>
					<div class="col-sm-10">
						<select name="cuidador" class="form-control">
							<c:forEach var="item" items="${cuidadores}">
								<option value="${item.id}"
									<c:if test="${item.id == animal.cuidador.id}">
           			 selected
           			 </c:if>>${item.nombre}</option>
							</c:forEach>
						</select>
					</div>
				</div>


				<div class="form-group">
					<label class="col-sm-2 control-label">Centro De Adopcion</label>
					<div class="col-sm-10">
						<select name="centroDeAdopcion" class="form-control">
							<c:forEach var="item" items="${centros}">
								<option value="${item.id}"
									<c:if test="${item.id == animal.centroDeAdopcion.id}">
           			 selected
								</c:if>>${item.nombre}</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">Tipo</label>
					<div class="col-sm-10"> <select name="categoria.tipo" class="form-control">
						<c:forEach var="item" items="${tipos}">
							<option value="${item}"
							<c:if test="${item.key == animal.categoria.tipo.key}">
           			 selected
								</c:if>
							>${item.key}</option>
						</c:forEach>
					</select>
				</div>
				</div>
				
				<petclinic:inputField label="Raza" name="categoria.raza" />

			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<c:choose>
						<c:when test="${animal['new']}">
							<button class="btn btn-default" type="submit">Añadir
								Animal</button>
						</c:when>
						<c:otherwise>
							<button class="btn btn-default" type="submit">Actualizar
								animal</button>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</form:form>
	</sec:authorize>

</petclinic:layout>