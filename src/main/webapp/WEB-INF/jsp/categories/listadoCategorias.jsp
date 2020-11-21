<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="category">
    <h2>Categorias</h2>

    <table id="categoryTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Tipo</th>
            <th style="width: 150px;">Raza</th>
            
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${category}" var="cat">
            <tr>
                <td>
                	<c:out value="${cat.tipo}"/>
                </td>
                <td>
                    <c:out value="${cat.raza}"/>
                </td>    
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>