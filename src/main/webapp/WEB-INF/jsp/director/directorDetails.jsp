<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<petclinic:layout pageName="director">



    <h2>Detalles Director</h2>


    <table class="table table-striped">
        <tr>
            <th>Nombre Completo</th>
            <td><b><c:out value="${director.nombre} ${director.apellidos}"/></b></td>
        </tr>
        <tr>
            <th>DNI</th>
            <td><c:out value="${director.dni}"/></td>
        </tr>
        <tr>
            <th>Teléfono</th>
            <td><c:out value="${director.telefono}"/></td>
        </tr>
        <tr>
            <th>Email</th>
            <td><c:out value="${director.email}"/></td>
        </tr>
        
    </table>
    
	<sec:authorize access="hasAnyAuthority('director')">
    <spring:url value="{directorId}/directorEdit" var="editUrl">
        <spring:param name="directorId" value="${director.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Editar Director</a>
	</sec:authorize>

	<c:if test="${pageContext.request.userPrincipal.name == director.user.username}">

    <spring:url value="{directorId}/listCentros" var="listCentros">
        <spring:param name="directorId" value="${director.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(listUrl)}" class="btn btn-default">Lista Centros Adopción</a>
	</c:if>

    <br/>
    <br/>
    <br/>

</petclinic:layout>