<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="duenosAdoptivos">
    <h2>Dueños Adoptivos</h2>

    <table id="duenosAdoptivosTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Nombre</th>
            <th style="width: 150px;">Apellidos</th>
            <th style="width: 200px;">Direccion</th>
            <th style="width: 150px;">Dni</th>
            <th style="width: 120px;">Telefono</th>
            <th style="width: 120px;">Email</th>
            
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${selections}" var="duenoAdoptivo">
            <tr>
                <td>
                    <spring:url value="/duenosAdoptivos/show/{duenoAdoptivoId}" var="duenoAdoptivoUrl">
                        <spring:param name="duenoAdoptivoId" value="${duenoAdoptivo.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(duenoAdoptivoUrl)}"><c:out value="${duenoAdoptivo.nombre} "/></a>
                </td>
                <td>
                	<c:out value="${duenoAdoptivo.apellidos}"/>
                </td>
                <td>
                    <c:out value="${duenoAdoptivo.direccion}"/>
                </td>
                <td>
                    <c:out value="${duenoAdoptivo.dni}"/>
                </td>
                <td>
                    <c:out value="${duenoAdoptivo.telefono}"/>
                </td>
                 <td>
                    <c:out value="${duenoAdoptivo.email}"/>
                </td>
      
<!--
                <td> 
                    <c:out value="${owner.user.username}"/> 
                </td>
                <td> 
                   <c:out value="${owner.user.password}"/> 
                </td> 
-->
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
