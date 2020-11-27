<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<petclinic:layout pageName="animales">



    <h2>Detalles Animal</h2>


    <table class="table table-striped">
        <tr>
            <th>Nombre</th>
            <td><b><c:out value="${animal.nombre}"/></b></td>
        </tr>
        
 
            	<tr>
            		<th>Licencia</th>
            		<td><b><c:out value="${animal.peligrosidad.licencia}"/></b></td>
           		 </tr>
             	<tr>
            		<th>Grado De Peligrosidad</th>
            		<td><b><c:out value="${animal.peligrosidad.grado}"/></b></td>
          		</tr>
        
        <tr>
       <th>Requiere Licencia</th>
            		<td><b><c:out value="${animal.requisitos.licencia}"/></b></td>
        </tr>
        <tr>
       <th>Requiere Seguro</th>
            		<td><b><c:out value="${animal.requisitos.seguro}"/></b></td>
        </tr>
        
         <tr>
       <th>Grado de Atencion</th>
            		<td><b><c:out value="${animal.atencion.atencion}"/></b></td>
        </tr>
         <tr>
       <th>Grado de Dificultad</th>
            		<td><b><c:out value="${animal.atencion.dificultad}"/></b></td>
        </tr>
        
         <tr>
       <th>Fecha nacimiento</th>
            		<td><b><c:out value="${animal.fechaNacimiento}"/></b></td>
        </tr>
        
        <tr>
       <th>Chip</th>
            		<td><b><c:out value="${animal.chip}"/></b></td>
        </tr>
        
        <tr>
       <th>Numero Registro</th>
            		<td><b><c:out value="${animal.numeroRegistro}"/></b></td>
        </tr>
        
        <tr>
       <th>Adoptado</th>
            		<td><b><c:out value="${animal.adoptado}"/></b></td>
        </tr>
        
         <tr>
       <th>Tamaño</th>
            		<td><b><c:out value="${animal.tamanyo}"/></b></td>
        </tr>
        
         <tr>
       <th>Edad</th>
            		<td><b><c:out value="${animal.edad}"/></b></td>
        </tr>
        
        <tr>
       <th>Sexo</th>
            		<td><b><c:out value="${animal.sexo}"/></b></td>
        </tr>
        
        
         <tr>
       <th>Primera Incorporacion</th>
            		<td><b><c:out value="${animal.fechaPrimeraIncorporacion}"/></b></td>
        </tr>
         <tr>
       <th>Ultima Incorporacion</th>
            		<td><b><c:out value="${animal.fechaUltimaIncorporacion}"/></b></td>
        </tr>
        
            <tr>
       <th>Cuidador</th>
            		<td><b><c:out value="${animal.cuidador.nombre}"/></b></td>
        </tr>
        
           <tr>
       <th>Tipo</th>
            		<td><b><c:out value="${animal.categoria.tipo}"/></b></td>
        </tr>
         <tr>
       <th>Raza</th>
            		<td><b><c:out value="${animal.categoria.raza}"/></b></td>
        </tr>
        
      </table>
      
 
	<sec:authorize access="hasAnyAuthority('director')">
    <spring:url value="{animalId}/edit" var="editUrl">
        <spring:param name="animalId" value="${animal.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Editar Animal</a>
	</sec:authorize>
	
	
    <br/>
    <br/>
    <br/>

</petclinic:layout>