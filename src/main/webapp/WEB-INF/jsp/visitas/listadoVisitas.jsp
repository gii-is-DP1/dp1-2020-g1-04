<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="visistas">
    <h2>Mis Visitas</h2>

    <table id="visitasTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">¿Cuándo?</th>
            <th style="width: 150px;">¿Dónde?</th>
            <th style="width: 150px;">Animal</th>
            <th style="width: 120px;">Cuidador</th>
            <th style="width: 120px;">Comentarios</th>
            
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${visitas}" var="visita">
            <tr>
                
                <td>
                	<c:out value="${visita.momento}"/>
                </td>
                <td>
                    <c:out value="${visita.lugar}"/>
                </td>
                 <td>
                       <spring:url value="/animales/show/{animalId}" var="animalUrl">
                        <spring:param name="animalId" value="${visita.animal.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(animalUrl)}"><c:out value="${visita.animal.nombre} "/></a>
                
                    </td>
                    <td>
                    <spring:url value="/cuidadores/show/{cuidadorId}" var="cuidadorUrl">
                        <spring:param name="cuidadorId" value="${visita.cuidador.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(cuidadorUrl)}"><c:out value="${visita.cuidador.nombre} "/></a>
                </td>
                
                 <td>
                     <spring:url value="/comentarios/{visitaId}" var="comentariosUrl">
                        <spring:param name="visitaId" value="${visita.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(comentariosUrl)}"><c:out value="Comentarios"/></a>
                </td>
      

            
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>