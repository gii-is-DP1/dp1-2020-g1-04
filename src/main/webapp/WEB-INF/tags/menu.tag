<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!--  >%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%-->
<%@ attribute name="name" required="true" rtexprvalue="true"
	description="Name of the active menu: home, owners, vets or error"%>

<nav class="navbar navbar-default" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand"
				href="<spring:url value="/" htmlEscape="true" />"><span></span></a>
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#main-navbar">
				<span class="sr-only"><os-p>Toggle navigation</os-p></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>
		<div class="navbar-collapse collapse" id="main-navbar">
			<ul class="nav navbar-nav">

				<petclinic:menuItem active="${name eq 'home'}" url="/"
					title="home page">
					<span class="glyphicon glyphicon-home" aria-hidden="true"></span>
					<span>Home</span>
				</petclinic:menuItem>
								
			
				
				<petclinic:menuItem active="${name eq 'animales'}" url="/animales"
					title="animales">
					<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
					<span>Animales</span>
				</petclinic:menuItem>
				<sec:authorize access="isAuthenticated()">
				<petclinic:menuItem active="${name eq 'eventos'}" url="/eventos"
					title="eventos">
					<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
					<span>Eventos Disponibles</span>
				</petclinic:menuItem>
				</sec:authorize>
<!--  >				<petclinic:menuItem active="${name eq 'error'}" url="/oups"
					title="trigger a RuntimeException to see how it is handled">
					<span class="glyphicon glyphicon-warning-sign" aria-hidden="true"></span>
					<span>Error</span>
				</petclinic:menuItem>
%-->
			</ul>
			
			
			<ul class="nav navbar-nav navbar-right">
				<sec:authorize access="!isAuthenticated()">
					<li><a href="<c:url value="/login" />">Login</a></li>
					<li><a href="<c:url value="/duenoAdoptivo/new" />">Register</a></li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <span class="glyphicon glyphicon-user"></span>
							<strong><sec:authentication property="name" /></strong> <span
							class="glyphicon glyphicon-chevron-down"></span>
					</a>
						<ul class="dropdown-menu">
							<li>
								<div class="navbar-login">
									<div class="row">
										<div class="col-lg-4">
											<p class="text-center">
												<span class="glyphicon glyphicon-user icon-size"></span>
											</p>
										</div>
										<div class="col-lg-8">
											<p class="text-left">
												<strong><sec:authentication property="name" /></strong>
											</p>
											<p class="text-left">
												<a href="<c:url value="/logout" />"
													class="btn btn-primary btn-block btn-sm">Logout</a>
											</p>
										</div>
									</div>
								</div>
							</li>
							
							
							<li class="divider"></li>
							
                            <li> 
								<div class="navbar-login navbar-login-session">
									<div class="row">
										<div class="col-lg-12">
											<p>
											<sec:authentication var="principal" property="principal" />
											
												<!--  <a href="#" class="btn btn-danger btn-block">Change
													Password</a>-->	
												<sec:authorize access="hasAnyAuthority('director')">
												
												<a href="<c:url value="/cuidador/nuevo"/>" class="btn btn-primary btn-block">A�adir Cuidador</a>
												<a href="<c:url value="/centros"/>" class="btn btn-primary btn-block">Listado Centros</a>
												<a href="<c:url value="/adopcion"/>" class="btn btn-primary btn-block">Listado Adopciones</a>
												<a href="<c:url value="/cuidadores"/>" class="btn btn-primary btn-block">Listado de Cuidadores</a>
												<a href="<c:url value="/eventos/nuevo"/>" class="btn btn-primary btn-block">Nuevo Evento</a>
												<a href="<c:url value="/eventos/director/misEventos"/>" class="btn btn-primary btn-block">Mis Eventos</a>
												<a href="<c:url value="/categoria/nuevo"/>" class="btn btn-primary btn-block">A�adir Animal</a>
												<a href="<c:url value="/animales/todos"/>" class="btn btn-primary btn-block">Todos los animales</a>
													<a href="<c:url value="/adopcion/pendientes"/>" class="btn btn-primary btn-block">Solicitud de Adopciones</a>
													<a href="<c:url value="/duenosAdoptivos"/>" class="btn btn-primary btn-block">Listado Due�oAdoptivos</a>
													
													</sec:authorize>	
													
													<sec:authorize access="hasAnyAuthority('duenoadoptivo')">
													<a href="<c:url value="/duenosAdoptivos/show"/>" class="btn btn-primary btn-block">Mi perfil</a>
													<a href="<c:url value="/adopcion/misSolicitudesDeAdopcion"/>" class="btn btn-primary btn-block">Mis Adopciones</a>
													<a href="<c:url value="/visitas/misVisitas"/>" class="btn btn-primary btn-block">Mis Visitas</a>
													<a href="<c:url value="/eventos/misEventos"/>" class="btn btn-primary btn-block">Mis Eventos</a>
														
														</sec:authorize>
														<sec:authorize access="hasAnyAuthority('cuidador')">
													<a href="<c:url value="/cuidadores/show"/>" class="btn btn-primary btn-block">Mi perfil</a>
													<a href="<c:url value="/animales/listaAsignados"/>" class="btn btn-primary btn-block">Animales Asignados</a>
													<a href="<c:url value="/visitas/cuidador/misVisitas"/>" class="btn btn-primary btn-block">Mis Visitas</a>
													<a href="<c:url value="/eventos/cuidador/misEventos"/>" class="btn btn-primary btn-block">Mis Eventos</a>
													<a href="<c:url value="/enfermedad/cuidador"/>" class="btn btn-primary btn-block">Enfermedades de mis animales</a>
														</sec:authorize>
														
											</p>
										</div>
									</div>
								</div>
							</li>
							
							

						</ul></li>
				</sec:authorize>
			</ul>
		</div>



	</div>
</nav>

