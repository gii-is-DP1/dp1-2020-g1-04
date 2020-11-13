<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="cuidadores">
    <h2>Cuidadoress</h2>

    <table id="cuidadoresTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Nombre</th>
            <th style="width: 150px;">Apellidos</th>
            <th style="width: 150px;">Dni</th>
            <th style="width: 120px;">Telefono</th>
            <th style="width: 120px;">Email</th>
            
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${selections}" var="cuidador">
            <tr>
                <td>
                    <spring:url value="/cuidadores/{cuidadorId}" var="cuidadorUrl">
                        <spring:param name="cuidadorId" value="${cuidador.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(cuidadorUrl)}"><c:out value="${cuidador.nombre} "/></a>
                </td>
                <td>
                	<c:out value="${cuidador.apellidos}"/>
                </td>
                <td>
                    <c:out value="${cuidador.dni}"/>
                </td>
                <td>
                    <c:out value="${cuidador.telefono}"/>
                </td>
                 <td>
                    <c:out value="${cuidador.email}"/>
                </td>
      
<!--
                <td> 
                    <c:out value="$'quitaresto'{cuidador.centroAdoptivo.nombre}"/> 
                </td>
               
-->
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>