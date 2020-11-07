<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="duenosAdoptivos">

    <h2>Información Dueño Adoptivo</h2>


    <table class="table table-striped">
        <tr>
            <th>Nombre Completo</th>
            <td><b><c:out value="${duenoAdoptivo.nombre} ${duenoAdoptivo.apellidos}"/></b></td>
        </tr>
        <tr>
            <th>Dirección</th>
            <td><c:out value="${duenoAdoptivo.direccion}"/></td>
        </tr>
        <tr>
            <th>DNI</th>
            <td><c:out value="${duenoAdoptivo.dni}"/></td>
        </tr>
        <tr>
            <th>Teléfono</th>
            <td><c:out value="${duenoAdoptivo.telefono}"/></td>
        </tr>
        <tr>
            <th>Email</th>
            <td><c:out value="${duenoAdoptivo.email}"/></td>
        </tr>
    </table>
    <c:if test="${principal.username == duenoAdoptivo.user.username}">

    <spring:url value="{duenoAdoptivoId}/edit" var="editUrl">
        <spring:param name="duenoAdoptivoId" value="${duenoAdoptivo.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Edit Owner</a>
	</c:if>
    

    <br/>
    <br/>
    <br/>

</petclinic:layout>

