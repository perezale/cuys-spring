<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<div class="page-header">
    <h1>Nuevo aporte</h1>
</div>

<div class="panel panel-default">
	<div class="panel-body">
		<form class="form-horizontal">
			<div class="form-group well">
				<input id="message" class="form-control" placeholder="Titulo"></input>
			</div>
			<div class="form-group panel-body">											
				<textarea id="message" class="form-control" placeholder="Nuevo mensaje"></textarea>					
			</div>
			<div class="form-group panel-body">						
				<select id="select-subject" class="combobox input-large form-control">
					<option value="" selected disabled>Cátedra</option>
					<c:forEach items="${subjects}" var="subject">
						<option value="${subject.urlTitle}">${subject.title}</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group panel-body">

				<button type="button" class="btn btn-default ">
					<span class="glyphicon glyphicon-paperclip" aria-hidden="true"></span>
				</button>
				<button type="submit" class="btn btn-primary">Publicar</button>
			</div>
		</form>
	</div>
</div>


