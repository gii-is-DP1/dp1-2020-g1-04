<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<petclinic:layout pageName="Eventos">
   
    <h2>Eventos</h2>

    <table id="visitasTable" class="table table-striped">
        <thead>
       <tr>
            <th style="width: 150px;">Título</th>
            <th style="width: 150px;">¿Cuándo?</th>
            <sec:authorize access="hasAnyAuthority('director')">
             <th style="width: 150px;">Cuidadores Asignados</th>
             <th style="width: 150px;">Dueños Adoptivos inscritos</th>          
        	</sec:authorize>
        	
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${eventos}" var="evento">
            <tr>
                
               <td>
                     
              
                    <a href="/eventos/show/${evento.id}"><c:out value="${evento.titulo} "/></a>
                
                    </td>
                <td>
                    <c:out value="${evento.fecha}"/>
                </td>
                 <sec:authorize access="hasAnyAuthority('director')">
                <td>
                    <c:out value="${evento.cuidadores.size()}"/>
                </td>
                <td>
                    <c:out value="${evento.duenos.size()}"/>
                </td>
                </sec:authorize>
                
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>