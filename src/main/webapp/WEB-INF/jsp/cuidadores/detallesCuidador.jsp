<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<petclinic:layout pageName="cuidadores">



    <h2>Detalles Cuidador</h2>


    <table class="table table-striped">
        <tr>
            <th>Nombre Completo</th>
            <td><b><c:out value="${cuidador.nombre} ${cuidador.apellidos}"/></b></td>
        </tr>
        <sec:authorize access="hasAnyAuthority('director')">
        <tr>
            <th>DNI</th>
            <td><c:out value="${cuidador.dni}"/></td>
        </tr>
        <tr>
            <th>Teléfono</th>
            <td><c:out value="${cuidador.telefono}"/></td>
        </tr>
        </sec:authorize>
        <tr>
            <th>Email</th>
            <td><c:out value="${cuidador.email}"/></td>
        </tr>
        
         <tr>
            <th>Centro De Adopcion</th>
            <td><c:out value="${cuidador.centroDeAdopcion.nombre}"/></td>
        </tr>
        
        
    </table>
    
	<sec:authorize access="hasAnyAuthority('director')">
    <spring:url value="{cuidadorId}/directorEdit" var="editUrl">
        <spring:param name="cuidadorId" value="${cuidador.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Editar Cuidador </a>
	
	</sec:authorize>

	<c:if test="${pageContext.request.userPrincipal.name == cuidador.user.username}">

    <spring:url value="{cuidadorId}/edit" var="editUrl">
        <spring:param name="cuidadorId" value="${cuidador.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Editar Cuidador</a>
	</c:if>

    <br/>
    <br/>
    <br/>

</petclinic:layout>

