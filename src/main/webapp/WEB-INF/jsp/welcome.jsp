<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<petclinic:layout pageName="home">
    <h2><fmt:message key="welcome"/></h2>
    <div class="row">
    <h2>Project <c:out value="${title}"/></h2>
    <p><h2>Group <c:out value="${group}"/></h2></p>
    <p><ul>
    <c:forEach items="<c:out value="${persons}"/>" var="persons">
    	<li><c:out value="${persons.nombre } ${persons.apellidos }"/></li>
    </c:forEach></ul></p>
    <div class="row">
        <div class="col-md-12">
            <spring:url value="/resources/images/logoPNG_3.png" htmlEscape="true" var="petsImage"/>
            <img class="img-responsive" src="${petsImage}"/>
        </div>
    </div>
</petclinic:layout>
