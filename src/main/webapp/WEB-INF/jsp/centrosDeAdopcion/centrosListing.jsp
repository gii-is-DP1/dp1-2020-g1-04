<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="centros">
	<h2>Centros de Adopcion</h2>
	    <table id="centrosTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 20%;">Nombre</th>
            <th style="width: 40%;">Dirección</th>
            <th style="width: 40%;">Cantidad máxima de animales</th>           
            <th style="width: 40%;">Lista de animales</th>
            <th style="width: 40%;">Lista de cuidadores</th>
            

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${centros}" var="centro">
            <tr>
                <td>
                  <c:out value="${centro.nombre}"/>
                </td>
                <td>
                    <c:out value="${centro.direccion}"/>
                </td>
                 <td>
                    <c:out value="${centro.cantidadMax}"/>
				</td>
				
	            <td>
    	            <a href="<spring:url value="/animal/animalList.jsp" htmlEscape="true" />">Lista de Animales</a>
        	    </td>      
        	    	            <td>
    	            <a href="<spring:url value="/cuidadores/findAllByCentro/${centro.id }" htmlEscape="true" />">Lista de Cuidadores</a>
        	    </td>        
        	</tr>
    
            </tr>
        </c:forEach>
        </tbody>
    </table>

</petclinic:layout> 