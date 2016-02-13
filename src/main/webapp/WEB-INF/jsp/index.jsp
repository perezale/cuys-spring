<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<div class="row">
	<!-- LEFT PANE -->
	<div class="col-md-3">
		<div class="page-header">
			<h4>Usuario</h4>
		</div>
		<img class="img-responsive img-thumbnail center-block" width="50%"
			src="http://api.adorable.io/avatars/285/abott@adorable.io.png" />
	</div>
	<!-- MID PANE -->
	<div class="col-md-6">
		<div class="page-header">
			<h4>Últimos mensajes</h4>
		</div>

		<table class="table table-bordered table-hover table-striped">
			<thead>
				<tr>
					<th>Date</th>
					<th>Item</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${items}" var="item">
					<tr>
						<td><c:out value="${item.publishedDate}" /> <br /> <c:out
								value="${item.post.title}" /></td>

						<td><strong> <a
								href="<c:out value="${item.link}" />" target="_blank"> <c:out
										value="${item.title}" />
							</a>
						</strong> <br /> ${item.description}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<!-- RIGHT PANE -->
	<div class="col-md-3">
		<div class="page-header">
			<h4>Enlaces</h4>
		</div>
		<div style="background: #004071; padding: 5px" id="exactas">
			<a target="_blank" href="http://www.exa.unicen.edu.ar/"><img
				class="center-block"
				src="http://www.exa.unicen.edu.ar/sites/all/themes/fcex2014/images/logoUnicen-blanco-h58.png" /></a>
		</div>
	</div>

</div>
