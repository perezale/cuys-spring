<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<div class="page-header">
    <h1>Cátedras</h1>
</div>

<security:authorize access="hasRole('ADMIN')">	
	<!-- Button trigger modal -->
	<button type="button" class="btn btn-primary btn-lg" data-toggle="modal"
		data-target="#myModal">Nueva catedra</button>
	
	<!-- Modal -->
	<form:form commandName="subject" cssClass="form-horizontal blogForm">
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Nueva catedra</h4>
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
							<label for="urlTitle" class="col-sm-2 control-label">Slug:</label>
							<div class="col-sm-10">
								<form:input path="urlTitle" cssClass="form-control" />
								<form:errors path="urlTitle" />
							</div>
						</div>
						<div class="form-group">
							<label for="referenceLink" class="col-sm-2 control-label">Sitio oficial:</label>
							<div class="col-sm-10">
								<form:input path="referenceLink" cssClass="form-control" />
								<form:errors path="referenceLink" />
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
			$(".blogForm").validate({
				
				rules : {
					title : {
						required : true,
						minlength : 1
					},
					urlTitle : {
						required : true,						
						minlength : 1					
					},
					referenceLink : {
						required : false,
						url : true
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
</security:authorize>

<c:forEach items="${subjects}" var="subject">
	<h4><a href="<spring:url value="/catedras/${subject.urlTitle}.html" />">${subject.title}</a></h4>				
</c:forEach>


