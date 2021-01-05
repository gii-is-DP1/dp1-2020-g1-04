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

<petclinic:layout pageName="eventos">



	<jsp:attribute name="customScript">
        <script>
									$(function() {
										$("#fecha").datepicker({
											dateFormat : 'dd/mm/yy'
										});
									});
								</script>
    </jsp:attribute>
	<jsp:body>
        <h2>
			<c:if test="${comentario['new']}">Nuevo </c:if>Comentario</h2>
            <form:form modelAttribute="comentario"
			class="form-horizontal">
            <div class="form-group has-feedback">
                <petclinic:inputField label="Comentario" name="comentario" />
                
              	</div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button class="btn btn-default" type="submit">Enviar Comentario</button>
                </div>
            </div>
            
    </form:form>
 
 </jsp:body>
</petclinic:layout>