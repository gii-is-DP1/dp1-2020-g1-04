 <%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="enfermedad">
 
 
 
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
	</petclinic:layout>