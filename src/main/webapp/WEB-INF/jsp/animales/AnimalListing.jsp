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
              <th style="width: 50%;">Grado de Dificultad</th>
            <th style="width: 50%;">Grado de Atencion</th>
            <th style="width: 50%;">Grado de peligrosidad</th> 
            <th style="width: 50%;">Licencia Peligrosidad</th>
            <th style="width: 50%;">Licencia Adopcion</th>          
            <th style="width: 50%;">Seguro</th>
            <th style="width: 50%;">Fecha de Nacimiento</th>
            <th style="width: 50%;">Chip</th>
            <th style="width: 50%;">Numero de registro</th>
            <th style="width: 50%;">Adoptado</th>
            <th style="width: 50%;">Tamaño</th>
            <th style="width: 50%;">Edad</th>
            <th style="width: 50%;">Sexo</th>
            <th style="width: 50%;">Primera Incorporacion</th>
            <th style="width:50%;">Ultima incorporacion</th>
			<th style="width: 50%;">Cuidador</th>
			<th style="width: 50%;">Categoria</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${animales}" var="animal">
            <tr>
                <td>
                  <c:out value="${animal.nombre}"/>
                </td>
                 <td>
                    <c:out value="${animal.atencion.dificultad}"/>
                </td>
                <td>
                    <c:out value="${animal.atencion.atencion}"/>
                </td>
                     <td>
                    <c:out value="${animal.peligrosidad.grado}"/>
                </td>
                  <td>
                    <c:out value="${animal.peligrosidad.licenciaPeligrosidad}"/>
                </td>
                <td>
                    <c:out value="${animal.requisitos.licenciaAdopcion}"/>
                </td>
                <td>
                    <c:out value="${animal.requisitos.seguro}"/>
                </td>
              
                <td>
                    <c:out value="${animal.fechaNacimiento}"/>
                </td>
                <td>
                    <c:out value="${animal.chip}"/>
                </td>
                <td>
                    <c:out value="${animal.numeroRegistro}"/>
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
                    <c:out value="${animal.fechaPrimeraIncorporacion}"/>
                </td>
                 <td>
                    <c:out value="${animal.fechaUltimaIncorporacion}"/>
                </td>
                 <td>
                    <c:out value="${animal.cuidador.user.username}"/>
                </td>
                 <td>
                    <c:out value="${animal.categoria.id}"/>
                </td>
                
                
               

            </tr>
        </c:forEach>
        </tbody>
    </table>

</petclinic:layout> 