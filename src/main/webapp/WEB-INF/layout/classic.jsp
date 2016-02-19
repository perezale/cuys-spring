<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras"
	prefix="tilesx"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<link rel="stylesheet"
	href="https://bootswatch.com/paper/bootstrap.min.css">
<style>
	body {
	  padding-top: 70px;
	  padding-bottom: 30px;
	}
	
	.theme-dropdown .dropdown-menu {
	  position: static;
	  display: block;
	  margin-bottom: 20px;
	}
	
	.theme-showcase > p > .btn {
	  margin: 5px 0;
	}
	
	.theme-showcase .navbar .container {
	  width: auto;
	}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script type="text/javascript"
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/jquery.validate.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/localization/messages_es_AR.js">
	</script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<title><tiles:getAsString name="title" /></title>
</head>
<body>

	<tilesx:useAttribute name="current" />
	<!-- Static navbar -->
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="<spring:url value="/" />"> 
				 <img height="30" style="margin-top: -4px" src="/img/logo_cuys.png"/>						
				</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li class="${current == 'index' ? 'active' : ''}"><a
						href="<spring:url value="/" />">Inicio</a></li>
					<li class="${current == 'subjects' ? 'active' : ''}"><a
						href="<spring:url value="/catedras.html" />">Cátedras</a></li>
					<security:authorize access="hasRole('ADMIN')">
						<li class="${current == 'users' ? 'active' : ''}"><a
							href="<spring:url value="/users.html" />">Usuarios</a></li>
					</security:authorize>
					<li class="${current == 'register' ? 'active' : ''}"><a
						href="<spring:url value="/register.html" />">Registro</a></li>
					<security:authorize access="! isAuthenticated()">
						<li class="${current == 'login' ? 'active' : ''}"><a
							href="<spring:url value="/login.html" />">Login</a></li>
					</security:authorize>
					<security:authorize access="isAuthenticated()">
						<li class="${current == 'account' ? 'active' : ''}"><a
							href="<spring:url value="/account.html" />">Mi Cuenta</a></li>
						<li><a href="<spring:url value="/logout" />">Logout</a></li>
					</security:authorize>
				</ul>

			</div>
			<!--/.nav-collapse -->
		</div>
		<!--/.container-fluid -->
	</nav>
	
	<div class="container">
		<tiles:insertAttribute name="body" />

		<br> <br>
		<center>
			<tiles:insertAttribute name="footer" />
		</center>
	</div>
	
</body>
</html>
