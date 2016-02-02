<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<h1><c:out value="${user.name}" /></h1>

<script type="text/javascript">
	$(document).ready(function(){
		$('.triggerRemove').click(function(e){
			e.preventDefault();
			$("#modalRemove .removeBtn").attr("href", $(this).attr("href"));
			$("#modalRemove").modal();
		});
	});
</script>


<c:forEach items="${user.posts}" var="post">
	<h1><c:out value="${post.title}" /></h1>
	
	<a href="<spring:url value="/post/remove/${post.id}.html"/>"  class="btn btn-danger triggerRemove" >Eliminar</a>
	
	<p><c:out value="${post.url}" /></p>

	<table class="table table-bordered table-hover table-striped">
		<thead>
			<tr>
				<th>Title</th>
				<th>Link</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${post.items}" var="item">
				<tr>
					<td><c:out value="${item.title}" /></td>
					<td><c:out value="${item.link}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</c:forEach>

<!-- Modal -->
<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Eliminar mensaje</h4>
      </div>
      <div class="modal-body">
        ¿Seguro que desea eliminar el mensaje?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
        <a href="" class="btn btn-danger removeBtn">Eliminar</a>        
      </div>
    </div>
  </div>
</div>