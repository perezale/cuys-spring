<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<!-- Button trigger modal -->
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal"
	data-target="#myModal">Nueva entrada</button>

<!-- Modal -->
<form:form commandName="post" cssClass="form-horizontal postForm">
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Nueva entrada</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="title" class="col-sm-2 control-label">Titulo:</label>
						<div class="col-sm-10">
							<form:input path="title" cssClass="form-control" />
							<form:errors path="title" />
						</div>
					</div>
					<div class="form-group">
						<label for="url" class="col-sm-2 control-label">URL:</label>
						<div class="col-sm-10">
							<form:input path="url" cssClass="form-control" />
							<form:errors path="url" />
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
					<input type="submit" class="btn btn-primary" value="Publicar" />
				</div>
			</div>
		</div>
	</div>
</form:form>
<script type="text/javascript">
	$(document).ready(function(){
		$('.triggerRemove').click(function(e){
			e.preventDefault();
			$("#modalRemove .removeBtn").attr("href", $(this).attr("href"));
			$("#modalRemove").modal();
		});
		$(".postForm").validate({
			
			rules : {
				title : {
					required : true,
					minlength : 1
				},
				url : {
					required : true,
					url: true,
					minlength : 1					
				}
			},
			highlight : function(element){
				$(element).closest(".form-group").removeClass("has-success").addClass("has-error");	
			},
			unhighlight : function(element){
				$(element).closest(".form-group").removeClass("has-error").addClass("has-success");	
			}			
		});
	});
</script>


<c:forEach items="${user.posts}" var="post">
	<h1>
		<c:out value="${post.title}" />
	</h1>

	<a href="<spring:url value="/post/remove/${post.id}.html"/>"
		class="btn btn-danger triggerRemove">Eliminar</a>

	<p>
		<c:out value="${post.url}" />
	</p>

	<table class="table table-bordered table-hover table-striped">
		<thead>
			<tr>
				<th>Date</th>
				<th>Item</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${post.items}" var="item">
				<tr>
					<td><c:out value="${item.publishedDate}" /></td>
					<td>
						<strong>
						<a href="<c:out value="${item.link}" />" target="_blank">
							<c:out value="${item.title}" />
						</a>
						</strong>						
						<br />
						${item.description}
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</c:forEach>

<!-- Modal -->
<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Eliminar mensaje</h4>
			</div>
			<div class="modal-body">¿Seguro que desea eliminar el mensaje?
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
				<a href="" class="btn btn-danger removeBtn">Eliminar</a>
			</div>
		</div>
	</div>
</div>