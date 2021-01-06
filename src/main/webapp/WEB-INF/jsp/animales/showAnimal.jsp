<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<petclinic:layout pageName="animales">



    <h2>${animal.nombre} al detalle</h2>
	<a href="/animales/show/${animal.id }"><img src="${animal.foto}" alt="${animal.nombre}" class="foto"></a>
	
    <table class="table table-striped">
        <tr>
            <th>Nombre</th>
            <td><b><c:out value="${animal.nombre}"/></b></td>
        </tr>
        
 
            	<tr>
            		<th>Necesita Licencia</th>
            		<td><b><c:choose>
   					 <c:when test="${animal.peligrosidad.licencia}">
      					 <c:out value="Si"/> 
    				</c:when>    
    				<c:otherwise>
     			<c:out value="No"/> 
   				 </c:otherwise>
				</c:choose></b></td>
           		 </tr>
             	<tr>
            		<th>Grado De Peligrosidad</th>
            		<td><b><c:out value="${animal.peligrosidad.grado}"/></b></td>
          		</tr>
        
        <tr>
       <th>Requiere Licencia</th>
            		<td><b>
            		<c:choose>
   					 <c:when test="${animal.requisitos.licencia}">
      					 <c:out value="Si"/> 
    				</c:when>    
    				<c:otherwise>
     			<c:out value="No"/> 
   				 </c:otherwise>
				</c:choose>
				</b>
        </tr>
        <tr>
       <th>Requiere Seguro</th>
            		<td><b>            		
            		<c:choose>
   					 <c:when test="${animal.requisitos.seguro}">
      					 <c:out value="Si"/> 
    				</c:when>    
    				<c:otherwise>
     			<c:out value="No"/> 
   				 </c:otherwise>
				</c:choose>
            		</b></td>
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
            		<td><b>
            		
            		<c:choose>
   					 <c:when test="${animal.adoptado}">
      					 <c:out value="Si"/> 
    				</c:when>    
    				<c:otherwise>
     			<c:out value="No"/> 
   				 </c:otherwise>
				</c:choose>
            		</b></td>
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
        
        <tr>
              
      </table>
      
 <sec:authorize access="hasAnyAuthority('duenoadoptivo')">
 <c:if test="${!animal.adoptado}">
  <spring:url value="/adopcion/new/{animalId}" var="editUrl">
        <spring:param name="animalId" value="${animal.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Solicitar su adopción</a>
 </c:if>
 </sec:authorize>
	
	<sec:authorize access="hasAnyAuthority('director')">
    <spring:url value="/animales/edit/{animalId}" var="editUrl">
        <spring:param name="animalId" value="${animal.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Editar Animal</a>
	</sec:authorize>
	
	<sec:authorize access="hasAnyAuthority('cuidador')">
	  <tr>
            <th><h2>Enfermedades</h2></th>
            <th>
           <table id="enfermedadesTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Nombre</th>
            <th style="width: 150px;">Inicio</th>
             <th style="width: 150px;">Curado</th>       
             <th style="width: 150px;">Detalles</th>                 
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${animal.enfermedades}" var="enfermedad">
            <tr>
               <td>
                	<c:out value="${enfermedad.nombre}"/>
                </td>
                <td>
                	<c:out value="${enfermedad.comienzo}"/>
                </td>
                 <td>
                	<c:if test="${enfermedad.curado}">
                	<c:out value="Si"/>
                	</c:if>
                	<c:if test="${!enfermedad.curado}">
                	<c:out value="No"/>
                	</c:if>
                </td>
                <td>
                <a href="/enfermedad/show/${animal.id }">Ver</a>
                </td>
                               
            </tr>
        </c:forEach>
        </tbody>
    </table>
	 
	 <spring:url value="/enfermedad/nuevo/{animalId}" var="editUrl">
        <spring:param name="animalId" value="${animal.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Añadir Enfermedad</a>
	</th>
	</tr>
	</sec:authorize>
	
    <br/>
    <br/>
    <br/>

</petclinic:layout>