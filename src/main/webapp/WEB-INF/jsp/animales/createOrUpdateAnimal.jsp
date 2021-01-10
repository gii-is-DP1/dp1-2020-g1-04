<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<petclinic:layout pageName="animales">
	<jsp:attribute name="customScript">
        <script>
									$(function() {
										$("#fechaNacimiento").datepicker({
											dateFormat : 'dd/mm/yy'
										});
										$("#fechaPrimeraIncorporacion")
												.datepicker({
													dateFormat : 'dd/mm/yy'
												});
										$("#fechaUltimaIncorporacion")
												.datepicker({
													dateFormat : 'dd/mm/yy'
												});
									});
								</script>
    </jsp:attribute>
	<jsp:body>

	<sec:authorize access="hasAnyAuthority('director')">
		<h2>
			<c:if test="${animal['new']}">Nuevo </c:if>
			animal
		</h2>
		
		<form:form modelAttribute="animal" class="form-horizontal"
				id="edit-duenoAdoptivo-form">
			
			  <div class="form-group">
       	<form:hidden path="numeroRegistro"/>	
       	<form:hidden path="adoptado"/>
        <label class="col-sm-2 control-label">Tipo</label>

        <div class="col-sm-10 col-sm-offset-2">
           	<c:out value="${animal.categoria.tipo}"></c:out>
			</div>
		</div>
				  <div class="form-group">
        <label class="col-sm-2 control-label">Raza</label>

        <div class="col-sm-10 col-sm-offset-2">
           	<c:out value="${animal.categoria.raza}"></c:out>
			</div>
		</div>
			
				<petclinic:inputField label="Nombre" name="nombre"/>
				<petclinic:inputField label="Imagen" name="foto"></petclinic:inputField>
								
				 <div class="form-group">
          	<label class="col-sm-2 control-label">Licencia</label> 
           <div class="col-sm-10">
            <select name="peligrosidad.licencia" class="form-control">
            		 <option value="true"
								<c:if test="${animal.peligrosidad.licencia}">
           			 selected
           			 </c:if>>Si</option>
         		  <option value="false"
								<c:if test="${!animal.peligrosidad.licencia}">
           			 selected
           			 </c:if>>No</option>
         		
      		  </select>
       </div>
       </div>
      
			<div class="form-group">
          	<label class="col-sm-2 control-label">Grado de peligrosidad</label> 
           <div class="col-sm-10">
            <select name="peligrosidad.grado" class="form-control">
						<c:forEach var="item" items="${auxiliar}">
           			 <option value="${item}"
									<c:if test="${item eq animal.peligrosidad.grado}">
           			 selected
           			 </c:if>>${item}</option>
         		  </c:forEach>
         		
      		  </select>
						  
       </div>
       </div>
					
									
				 <div class="form-group">
          	<label class="col-sm-2 control-label">Requiere licencia</label> 
           <div class="col-sm-10">
            <select name="requisitos.licenciarequerida"
							class="form-control">
            		 <option value="true"
								<c:if test="${requisitos.licenciarequerida}">
           			 selected
           			 </c:if>>Si</option>
         		  <option value="false"
								<c:if test="${!requisitos.licenciarequerida}">
           			 selected
           			 </c:if>>No</option>
         		
      		  </select>
       </div>
       </div>

				<div class="form-group">
          	<label class="col-sm-2 control-label">Requiere Seguro</label> 
           <div class="col-sm-10">
            <select name="requisitos.seguro" class="form-control">
            		 <option value="true"
								<c:if test="${requisitos.seguro}">
           			 selected
           			 </c:if>>Si</option>
         		  <option value="false"
								<c:if test="${!requisitos.seguro}">
           			 selected
           			 </c:if>>No</option>
         		
      		  </select>
       </div>
       </div>
				
			<div class="form-group">
          	<label class="col-sm-2 control-label">Grado de Atencion</label> 
           <div class="col-sm-10">
            <select name="atencion.atencion" class="form-control">
						<c:forEach var="item" items="${auxiliar}">
           			 <option value="${item}"
									<c:if test="${item eq animal.atencion.atencion}">
           			 selected
           			 </c:if>>${item}</option>
         		  </c:forEach>
         		
      		  </select>
						  
       </div>
       </div>
						
						<div class="form-group">
          	<label class="col-sm-2 control-label">Grado de dificultad</label> 
           <div class="col-sm-10">
            <select name="atencion.dificultad" class="form-control">
						<c:forEach var="item" items="${auxiliar}">
           			 <option value="${item}"
									<c:if test="${item eq animal.atencion.dificultad}">
           			 selected
           			 </c:if>>${item}</option>
         		  </c:forEach>
         		
      		  </select>
						  
       </div>
       </div>
				<petclinic:inputField label="Fecha de nacimiento"
					name="fechaNacimiento" />
				<petclinic:inputField label="Chip" name="chip" />
				<!-- <petclinic:inputField label="Nº Registro" name="numeroRegistro" /> 
					<div class="form-group">
          	<label class="col-sm-2 control-label">Adoptado</label> 
           <div class="col-sm-10">
            <select name="adoptado" class="form-control">
            		 <option value="true"
								<c:if test="${animal.adoptado}">
           			 selected
           			 </c:if>>Si</option>
         		  <option value="false"
								<c:if test="${!animal.adoptado}">
           			 selected
           			 </c:if>>No</option>
         		
      		  </select>
       </div>
       </div>
			-->
				<div class="form-group">
          	<label class="col-sm-2 control-label">Tamaño</label> 
           <div class="col-sm-10">
            <select name="tamanyo" class="form-control">
            		 <option value="Diminuto"
								<c:if test="${fn:contains(animal.tamanyo, 'Diminuto')}">
           			 selected
           			 </c:if>>Diminuto</option>
            		 <option value="Pequeño"
								<c:if test="${fn:contains(animal.tamanyo, 'Pequeño')}">
           			 selected
           			 </c:if>>Pequeño</option>
         		  <option value="Mediano"
								<c:if test="${fn:contains(animal.tamanyo, 'Mediano')}">
           			 selected
           			 </c:if>>Mediano</option>
         		 <option value="Grande"
								<c:if test="${fn:contains(animal.tamanyo, 'Grande')}">
           			 selected
           			 </c:if>>Grande</option>
         		
         		 <option value="SuperGrande"
								<c:if test="${fn:contains(animal.tamanyo, 'SuperGrande')}">
           			 selected
           			 </c:if>>SuperGrande</option>
      		  </select>
       </div>
       </div>
					<div class="form-group">
          	<label class="col-sm-2 control-label">Sexo</label> 
           <div class="col-sm-10">
            <select name="sexo" class="form-control">
            		 <option value="Macho"
								<c:if test="${fn:contains(animal.sexo, 'Macho')}">
           			 selected
           			 </c:if>>Macho</option>
         		  <option value="Hembra"
								<c:if test="${fn:contains(animal.sexo, 'Hembra')}">
           			 selected
           			 </c:if>>Hembra</option>
         		
      		  </select>
       </div>
       </div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">Cuidador</label>
					<div class="col-sm-10">
						<select name="cuidador" class="form-control">
							<c:forEach var="item" items="${cuidadores}">
								<option value="${item.id}"
									<c:if test="${item.id == animal.cuidador.id}">
           			 selected
           			 </c:if>>${item.nombre}</option>
							</c:forEach>
						</select>
					</div>
				</div>


				<div class="form-group">
					<label class="col-sm-2 control-label">Centro De Adopcion</label>
					<div class="col-sm-10">
						<select name="centroDeAdopcion" class="form-control">
							<c:forEach var="item" items="${centros}">
								<option value="${item.id}"
									<c:if test="${item.id == animal.centroDeAdopcion.id}">
           			 selected
								</c:if>>${item.nombre}</option>
							</c:forEach>
						</select>
					</div>
				</div>
	

	
	  <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button class="btn btn-default" type="submit">Guardar Animal</button>
                </div>
            </div>
	
			
			</form:form>
		</sec:authorize>
</jsp:body>
</petclinic:layout>