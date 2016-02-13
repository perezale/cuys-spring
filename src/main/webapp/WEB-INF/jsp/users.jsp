<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp" %>

<div class="page-header">
    <h1>Usuarios</h1>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		$('.triggerRemove').click(function(e){
			e.preventDefault();
			$("#modalRemove .removeBtn").attr("href", $(this).attr("href"));
			$("#modalRemove").modal();
		});
	});
</script>

<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>Usuarios</th>
			<th>Operaciones</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${users}" var="user">
			<tr>
				<td>
				<a href="<spring:url value="/users/${user.id}.html" />" >
				<c:out value="${user.name}" />
				</a>
				</td>
				<td>
				<a href="<spring:url value="/users/remove/${user.id}.html" />" class="btn btn-danger triggerRemove" >
					Eliminar
				</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>


<!-- Modal -->
<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Eliminar usuario</h4>
      </div>
      <div class="modal-body">
        ¿Seguro que desea eliminar el usuario?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
        <a href="" class="btn btn-danger removeBtn">Eliminar</a>        
      </div>
    </div>
  </div>
</div>