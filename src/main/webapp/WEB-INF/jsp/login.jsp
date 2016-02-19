<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp" %>

<style>

.form-signin {
	max-width: 330px;
	padding: 15px;
	margin: 0 auto;
}

.form-signin .form-signin-heading, .form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin .checkbox {
	font-weight: normal;
}

.form-signin .form-control {
	position: relative;
	height: auto;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
	padding: 10px;
	font-size: 16px;
}

.form-signin .form-control:focus {
	z-index: 2;
}

.form-signin input[type="email"] {
	margin-bottom: -1px;
	border-bottom-right-radius: 0;
	border-bottom-left-radius: 0;
}

.form-signin input[type="password"] {
	margin-bottom: 10px;
	border-top-left-radius: 0;
	border-top-right-radius: 0;
}
</style>

<form class="form-signin" action="<spring:url value="/login" />" method="POST">
	<h2 class="form-signin-heading">Ingreso</h2>
	<c:if test="${param.logout != null}">
		<div class="alert alert-success" role="alert"> <strong>Chau!</strong> Gracias por pasar por CUYS. </div>
	</c:if>
	<c:if test="${param.error != null}">
		<div class="alert alert-warning" role="alert"> <strong>ERROR</strong> Credenciales inválidas </div>
	</c:if>
	<label for="inputEmail" class="sr-only">Email address</label> <input
		type="text" id="username" name="username" class="form-control"
		placeholder="Usuario" required autofocus> <label
		for="inputPassword" class="sr-only">Contraseña</label> <input
		type="password" id="inputPassword" name="password" class="form-control"
		placeholder="Password" required>
	<div class="checkbox">
		<label> <input type="checkbox" value="remember-me">
			Remember me
		</label>
	</div>	
	<button class="btn btn-lg btn-primary btn-block" type="submit">Ingresar</button>
</form>