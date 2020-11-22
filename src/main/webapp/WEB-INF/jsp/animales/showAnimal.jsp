<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="animales">

	    <h2>Información del Animal</h2>


    <table class="table table-striped">
        <tr>
            <th>Nombre</th>
            <td><c:out value="${animal.nombre}"/></td>
        </tr>
        <tr>
            <th>Peligrosidad</th>
            <td><c:out value="${animal.peligrosidad}"/></td>
        </tr>
        <tr>
            <th>Atención</th>
            <td><c:out value="${animal.atencion}"/></td>
        </tr>
        <tr>
            <th>Requisitos de Adopcion</th>
            <td><c:out value="${animal.requisitos}"/></td>
        </tr>
        <tr>
            <th>Fecha de Nacimiento</th>
            <td><c:out value="${animal.fechaNacimiento}"/></td>
        </tr>
        <tr>
            <th>Chip</th>
            <td><c:out value="${animal.chip}"/></td>
        </tr>
        <tr>
            <th>Nº de Registro</th>
            <td><c:out value="${animal.chip}"/></td>
        </tr>
        <tr>
            <th>¿Está adoptado?</th>
            <td><c:out value="${animal.adoptado}"/></td>
        </tr>
        <tr>
            <th>Tamaño</th>
            <td><c:out value="${animal.tamanyo}"/></td>
        </tr>
        <tr>
            <th>Edad</th>
            <td><c:out value="${animal.edad}"/></td>
        </tr>
        <tr>
            <th>Sexo</th>
            <td><c:out value="${animal.sexo}"/></td>
        </tr>
        <tr>
            <th>Fecha 1ª incorporación</th>
            <td><c:out value="${animal.fechaPrimeraIncorporacion}"/></td>
        </tr>
         <tr>
            <th>Fecha última incorporación</th>
            <td><c:out value="${animal.fechaUltimaIncorporacion}"/></td>
        </tr>
          <tr>
            <th>Cuidador asociado</th>
            <td><c:out value="${animal.cuidador}"/></td>
        </tr>
          <tr>
            <th>Categoría</th>
            <td><c:out value="${animal.categoria}"/></td>
        </tr>
      </table>
      
	
	
    <br/>
    <br/>
    <br/>

</petclinic:layout>
	
      
      