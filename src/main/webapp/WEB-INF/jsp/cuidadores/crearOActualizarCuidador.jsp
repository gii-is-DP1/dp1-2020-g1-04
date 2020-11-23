<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="cuidadores">
    <h2>
        <c:if test="${cuidador['new']}">New </c:if> cuidador
    </h2>
   <form:form modelAttribute="cuidador" class="form-horizontal" id="add-cuidador-form">
        <div class="form-group has-feedback">
             <petclinic:inputField label="Nombre" name="nombre"/>
            <petclinic:inputField label="Apellidos" name="apellidos"/>
            <petclinic:inputField label="DNI" name="dni"/>
            <petclinic:inputField label="Teléfono" name="telefono"/>
            <petclinic:inputField label="Email" name="email"/>
            <petclinic:inputField label="Username" name="user.username"/>
            <petclinic:inputField label="Password" name="user.password"/>
            <label>Centro De Adopción</label>
            <select name="centroDeAdopcion">
        		  <c:forEach var="item" items="${centros}">
           			 <option value="${item.id}">${item.nombre}</option>
         		 </c:forEach>
      		  </select>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${cuidador['new']}">
                        <button class="btn btn-default" type="submit">Añadir Cuidador</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Actualizar Cuidador</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>

</petclinic:layout>
