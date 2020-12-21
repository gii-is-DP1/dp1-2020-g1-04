<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<petclinic:layout pageName="eventos">

<c:out value="${confirmacion}"/>

<c:if test = "${evento.cuidadores.size()>0 || role.equals('[director]')}">
    <h2>${evento.titulo} al detalle</h2>


    <table class="table table-striped">
        <tr>
            <th>Titulo</th>
            <td><b><c:out value="${evento.titulo}"/></b></td>
        </tr>
         <tr>
            <th>¿Cuándo?</th>
            <td><b><c:out value="${evento.fecha}"/></b></td>
        </tr>
         <tr>
            <th>Aforo</th>
            <td><b><c:out value="${evento.aforo}"/></b></td>
        </tr>
        <tr>
            <th>Plazas Disponibles</th>
            <td><b><c:out value="${plazasDisponibles}"/></b></td>
        </tr>
         <tr>
            <th>Descripción</th>
            <td><b><c:out value="${evento.fecha}"/></b></td>
        </tr>
        <sec:authorize access="hasAnyAuthority('director')">
         
         <tr>
            <th>Cuidadores Asignados</th>
            <th>
           <table id="cuidadoresTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Nombre</th>
            <th style="width: 150px;">Apellidos</th>
            <th style="width: 150px;">Destituir</th>
                        
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${evento.cuidadores}" var="asociado">
            <tr>
               <td>
                	<c:out value="${asociado.nombre}"/>
                </td>
                <td>
                	<c:out value="${asociado.apellidos}"/>
                </td>
                <td>
                	<a href="/eventos/${evento.id}/quitarCuidador/${asociado.id}"><c:out value="quitar "/></a>
                </td>
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
           </th>
           
        </tr>
                
                
                
            <tr>
            <th>DueñosAdoptivos Inscritos</th>
            <th>
           <table id="cuidadoresTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Nombre</th>
            <th style="width: 150px;">Apellidos</th>
                                    
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${evento.duenos}" var="duenos">
            <tr>
               <td>
                	<c:out value="${duenos.nombre}"/>
                </td>
                <td>
                	<c:out value="${duenos.apellidos}"/>
                </td>
                               
            </tr>
        </c:forEach>
        </tbody>
    </table>
           </th>
           
        </tr>     
           </sec:authorize>
     
        
      </table>
      <sec:authorize access="hasAnyAuthority('director')">
  <h2>Asociar Cuidadores</h2>
 <table id="cuidadoresTable" class="table table-striped">
        <thead>
        <tr>
         <th style="width: 150px;">Nombre</th>
            <th style="width: 150px;">Apellidos</th>
            <th style="width: 150px;">Destituir</th>
                        
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${cuidadores}" var="cuidador">
            <tr>
               <td>
                	<c:out value="${cuidador.nombre}"/>
                </td>
                <td>
                	<c:out value="${cuidador.apellidos}"/>
                </td>
                <td>
                	<a href="/eventos/${evento.id}/añadirCuidador/${cuidador.id}"><c:out value="añadir "/></a>
                </td>
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
             </sec:authorize>
             
             <sec:authorize access="hasAnyAuthority('duenoadoptivo')"> 
				<td>
				<c:if test = "${inscrito>0}">
				
					<a href="/eventos/${evento.id}/borrarse" class="btn btn-default" onclick="return confirm('¿Seguro que deseas borrarte del Evento?');"><c:out value="borrarse "/></a>
				</c:if>
				<c:if test="${inscrito<=0 && evento.aforo>evento.duenos.size()}">
				
					<a href="/eventos/${evento.id}/inscribirse"  class="btn btn-default" onclick="return confirm('¿Seguro que deseas inscribirte al Evento?');"><c:out value="inscribirse "/></a>
				</c:if>
				</td>
            	</sec:authorize>
             </c:if>
</petclinic:layout>