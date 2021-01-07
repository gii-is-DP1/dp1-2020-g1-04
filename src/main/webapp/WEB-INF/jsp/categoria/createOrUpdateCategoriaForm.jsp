<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<petclinic:layout pageName="categoria">

<h2><c:if test="${categoria['new']}">Nuevo </c:if>Categoria</h2>
            <form:form modelAttribute="categoria" class="form-horizontal">
            <div class="form-group has-feedback">
                    <div class="form-group">
          	<label class="col-sm-2 control-label">Tipo</label> 
           <div class="col-sm-10">
            <select name="tipo" size="${categoria.tipo.size() }" class="form-control">
            		  <c:forEach var="item" items="${categoria.tipo}">
           			 <option value="${item.value}" 
           			 <c:if test="${item.value == item.value}">
           			 selected
           			 </c:if>
           			 >${item.key}</option>
         		  </c:forEach>
         		
      		  </select>
       </div>
       </div>
       </div>
                <petclinic:inputField label="Raza" name="raza"/>
               	</div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button class="btn btn-default" type="submit">Continuar</button>
                </div>
            </div>
            
    </form:form>

</petclinic>