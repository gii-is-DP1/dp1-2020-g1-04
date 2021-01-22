<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="animales">
	<h2>Categoría Canino</h2>
	<table id="animalesTable" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 50%;">Nombre</th>
				<th style="width: 50%;">Foto</th>
				<th style="width: 50%;">Centro</th>
				<th style="width: 50%;">Grado de Dificultad</th>
				<th style="width: 50%;">Grado de Atencion</th>
				<th style="width: 50%;">Licencia Peligrosidad</th>
				<th style="width: 50%;">Licencia Adopcion</th>
				<th style="width: 50%;">Seguro</th>
				<th style="width: 50%;">Adoptado</th>
				<th style="width: 50%;">Tamaño</th>
				<th style="width: 50%;">Edad</th>
				<th style="width: 50%;">Sexo</th>
				<th style="width: 50%;">Categoria</th>
				<th style="width: 50%;">Raza</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${animalesCanino}" var="canino">
				<tr>
					<td><a href="<c:url value="/animales/show/${canino.id}"/>"
						class="btn btn-primary btn-block">${canino.nombre }</a></td>
					<td><img src="${animal.foto }" alt="Foto de ${canino.nombre }"
						class="foto"></td>
					<td><a
						href="<c:url value="/centros/show/${canino.centroDeAdopcion.id}"/>"
						class="btn btn-primary btn-block">Centro
							${canino.centroDeAdopcion.id}</a></td>
					<td><c:out value="${canino.atencion.dificultad}" /></td>
					<td><c:out value="${canino.atencion.atencion}" /></td>
					<td><c:out value="${canino.peligrosidad.licencia}" /></td>
					<td><c:out value="${canino.requisitos.licencia}" /></td>
					<td><c:out value="${canino.requisitos.seguro}" /></td>

					<td><c:out value="${canino.adoptado}" /></td>
					<td><c:out value="${canino.tamanyo}" /></td>
					<td><c:out value="${canino.edad}" /></td>
					<td><c:out value="${canino.sexo}" /></td>
					<td><c:out value="${canino.categoria.tipo}" /></td>
					<td><c:out value="${canino.categoria.raza}" /></td>

				</tr>
			</c:forEach>
		</tbody>
	</table>


<h2>Categoría Felino</h2>
	<table id="animalesTable" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 50%;">Nombre</th>
				<th style="width: 50%;">Foto</th>
				<th style="width: 50%;">Centro</th>
				<th style="width: 50%;">Grado de Dificultad</th>
				<th style="width: 50%;">Grado de Atencion</th>
				<th style="width: 50%;">Licencia Peligrosidad</th>
				<th style="width: 50%;">Licencia Adopcion</th>
				<th style="width: 50%;">Seguro</th>
				<th style="width: 50%;">Adoptado</th>
				<th style="width: 50%;">Tamaño</th>
				<th style="width: 50%;">Edad</th>
				<th style="width: 50%;">Sexo</th>
				<th style="width: 50%;">Categoria</th>
				<th style="width: 50%;">Raza</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${animalesFelino}" var="felino">
				<tr>
					<td><a href="<c:url value="/animales/show/${felino.id}"/>"
						class="btn btn-primary btn-block">${felino.nombre }</a></td>
					<td><img src="${felino.foto }" alt="Foto de ${felino.nombre }"
						class="foto"></td>
					<td><a
						href="<c:url value="/centros/show/${felino.centroDeAdopcion.id}"/>"
						class="btn btn-primary btn-block">Centro
							${felino.centroDeAdopcion.id}</a></td>
					<td><c:out value="${felino.atencion.dificultad}" /></td>
					<td><c:out value="${felino.atencion.atencion}" /></td>
					<td><c:out value="${felino.peligrosidad.licencia}" /></td>
					<td><c:out value="${felino.requisitos.licencia}" /></td>
					<td><c:out value="${felino.requisitos.seguro}" /></td>

					<td><c:out value="${felino.adoptado}" /></td>
					<td><c:out value="${felino.tamanyo}" /></td>
					<td><c:out value="${felino.edad}" /></td>
					<td><c:out value="${felino.sexo}" /></td>
					<td><c:out value="${felino.categoria.tipo}" /></td>
					<td><c:out value="${felino.categoria.raza}" /></td>

				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<h2>Categoría Reptil</h2>
	<table id="animalesTable" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 50%;">Nombre</th>
				<th style="width: 50%;">Foto</th>
				<th style="width: 50%;">Centro</th>
				<th style="width: 50%;">Grado de Dificultad</th>
				<th style="width: 50%;">Grado de Atencion</th>
				<th style="width: 50%;">Licencia Peligrosidad</th>
				<th style="width: 50%;">Licencia Adopcion</th>
				<th style="width: 50%;">Seguro</th>
				<th style="width: 50%;">Adoptado</th>
				<th style="width: 50%;">Tamaño</th>
				<th style="width: 50%;">Edad</th>
				<th style="width: 50%;">Sexo</th>
				<th style="width: 50%;">Categoria</th>
				<th style="width: 50%;">Raza</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${animalesReptil}" var="reptil">
				<tr>
					<td><a href="<c:url value="/animales/show/${reptil.id}"/>"
						class="btn btn-primary btn-block">${reptil.nombre }</a></td>
					<td><img src="${reptil.foto }" alt="Foto de ${reptil.nombre }"
						class="foto"></td>
					<td><a
						href="<c:url value="/centros/show/${reptil.centroDeAdopcion.id}"/>"
						class="btn btn-primary btn-block">Centro
							${reptil.centroDeAdopcion.id}</a></td>
					<td><c:out value="${reptil.atencion.dificultad}" /></td>
					<td><c:out value="${reptil.atencion.atencion}" /></td>
					<td><c:out value="${reptil.peligrosidad.licencia}" /></td>
					<td><c:out value="${reptil.requisitos.licencia}" /></td>
					<td><c:out value="${reptil.requisitos.seguro}" /></td>

					<td><c:out value="${reptil.adoptado}" /></td>
					<td><c:out value="${reptil.tamanyo}" /></td>
					<td><c:out value="${reptil.edad}" /></td>
					<td><c:out value="${reptil.sexo}" /></td>
					<td><c:out value="${reptil.categoria.tipo}" /></td>
					<td><c:out value="${reptil.categoria.raza}" /></td>

				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<h2>Categoría Ave</h2>
	<table id="animalesTable" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 50%;">Nombre</th>
				<th style="width: 50%;">Foto</th>
				<th style="width: 50%;">Centro</th>
				<th style="width: 50%;">Grado de Dificultad</th>
				<th style="width: 50%;">Grado de Atencion</th>
				<th style="width: 50%;">Licencia Peligrosidad</th>
				<th style="width: 50%;">Licencia Adopcion</th>
				<th style="width: 50%;">Seguro</th>
				<th style="width: 50%;">Adoptado</th>
				<th style="width: 50%;">Tamaño</th>
				<th style="width: 50%;">Edad</th>
				<th style="width: 50%;">Sexo</th>
				<th style="width: 50%;">Categoria</th>
				<th style="width: 50%;">Raza</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${animalesAve}" var="ave">
				<tr>
					<td><a href="<c:url value="/animales/show/${ave.id}"/>"
						class="btn btn-primary btn-block">${ave.nombre }</a></td>
					<td><img src="${ave.foto }" alt="Foto de ${ave.nombre }"
						class="foto"></td>
					<td><a
						href="<c:url value="/centros/show/${ave.centroDeAdopcion.id}"/>"
						class="btn btn-primary btn-block">Centro
							${ave.centroDeAdopcion.id}</a></td>
					<td><c:out value="${ave.atencion.dificultad}" /></td>
					<td><c:out value="${ave.atencion.atencion}" /></td>
					<td><c:out value="${ave.peligrosidad.licencia}" /></td>
					<td><c:out value="${ave.requisitos.licencia}" /></td>
					<td><c:out value="${ave.requisitos.seguro}" /></td>

					<td><c:out value="${ave.adoptado}" /></td>
					<td><c:out value="${ave.tamanyo}" /></td>
					<td><c:out value="${ave.edad}" /></td>
					<td><c:out value="${ave.sexo}" /></td>
					<td><c:out value="${ave.categoria.tipo}" /></td>
					<td><c:out value="${ave.categoria.raza}" /></td>

				</tr>
			</c:forEach>
		</tbody>
	</table>

</petclinic:layout>
