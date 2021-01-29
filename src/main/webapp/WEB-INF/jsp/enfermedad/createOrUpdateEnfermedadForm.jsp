<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="enfermedad">
<jsp:attribute name="customScript">
        <script>
									$(function() {
										$("#comienzo").datepicker({
											dateFormat : 'dd/mm/yy'
										});
										$("#fin").datepicker({
											dateFormat : 'dd/mm/yy'
										});
									});
								</script>
    </jsp:attribute>
	<jsp:body>

        <h2>
			<c:if test="${enfermedad['new']}">Nueva </c:if>Enfermedad  para <c:out value="${enfermedad.animal.nombre}"/></h2> 
			<a href="/animales/show/${enfermedad.animal.id }"><img src="<c:out value="${enfermedad.animal.foto}"/>" alt="<c:out value="${enfermedad.animal.nombre}"/>" class="foto"></a>
            <form:form modelAttribute="enfermedad"
			class="form-horizontal">
            <div class="form-group has-feedback">
                <petclinic:inputField label="Nombre" name="nombre" />
                 <petclinic:inputField label="Inicio" name="comienzo"/>
             		<p style="color:red"><c:out value="${errorFecha}"/></p>
                 <petclinic:inputField label="Fin" name="fin"/>
                 <petclinic:inputField label="Comentario" name="comentario"/>
              <div class="form-group">
          	<label class="col-sm-2 control-label">Curado</label> 
           <div class="col-sm-10">
            <select name="curado" size="2" class="form-control">
            		 <option value="true" 
           			 <c:if test="${enfermedad.curado}">
           			 selected
           			 </c:if>
           			 >Si</option>
         		  <option value="false" 
           			 <c:if test="${!enfermedad.curado}">
           			 selected
           			 </c:if>
           			 >No</option>
         		
      		  </select>
       </div>
       </div>
       </div>
              
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button class="btn btn-default" type="submit">Guardar Enfermedad</button>
                </div>
            </div>
            
    </form:form>
 
 </jsp:body>
</petclinic:layout>