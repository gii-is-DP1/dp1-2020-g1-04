<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="animales">
	<h2>Animales</h2>
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
        <c:forEach items="${animales}" var="animal">
            <tr>
                <td>
                 <a href="<c:url value="/animales/show/${animal.id}"/>" class="btn btn-primary btn-block">${animal.nombre }</a>
                </td>
                <td>
                <img src="${animal.foto }" alt="Foto de ${animal.nombre }" class="foto">
                </td>
                 <td>
                  <a href="<c:url value="/centros/show/${animal.centroDeAdopcion.id}"/>" class="btn btn-primary btn-block">Centro ${animal.centroDeAdopcion.id}</a>
                </td>
                 <td>
                    <c:out value="${animal.atencion.dificultad}"/>
                </td>
                <td>
                    <c:out value="${animal.atencion.atencion}"/>
                </td>
                <td>
                    <c:out value="${animal.peligrosidad.licencia}"/>
                </td>
                <td>
                    <c:out value="${animal.requisitos.licencia}"/>
                </td>
                <td>
                    <c:out value="${animal.requisitos.seguro}"/>
                </td>
                             
                <td>
                    <c:out value="${animal.adoptado}"/>
                </td>
                <td>
                    <c:out value="${animal.tamanyo}"/>
                </td>
                <td>
                    <c:out value="${animal.edad}"/>
                </td>
                <td>
                    <c:out value="${animal.sexo}"/>
                </td>
                <td>
                    <c:out value="${animal.categoria.tipo.key}"/>
                </td>
                 <td>
                    <c:out value="${animal.categoria.raza}"/>
                </td>
                
                
               

            </tr>
        </c:forEach>
        </tbody>
    </table>

</petclinic:layout> 