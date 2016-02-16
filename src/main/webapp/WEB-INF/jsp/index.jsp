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
		<div class="page-header">
			<h4>Enlaces</h4>
		</div>
		<div style="background: #004071; padding: 5px" id="exactas">
			<a target="_blank" href="http://www.exa.unicen.edu.ar/"><img
				class="center-block"
				src="http://www.exa.unicen.edu.ar/sites/all/themes/fcex2014/images/logoUnicen-blanco-h58.png" /></a>
		</div>
	</div>
	<!-- MID PANE -->
	<div class="col-md-9">
		<div class="page-header">
			<h4>Últimos mensajes</h4>
		</div>			 		
		<c:forEach items="${items}" var="item">
			<div class="media">	
				<div class="media-left">
				    <a href="#">
				      <img class="media-object" src="https://s3.amazonaws.com/uifaces/faces/twitter/lady_katherine/128.jpg" alt="">
				    </a>
				    <strong><c:out value="${item.blog.title}" /></strong> <br/>				    
			  	</div>
				<div class="media-body">
				    <h4 class="media-heading"><a
							href="<c:out value="${item.link}" />" target="_blank"> <c:out
									value="${item.title}" />
						</a></h4>				  
					<c:out value="${item.publishedDate}" /><br>
					<c:out value="${item.description}" escapeXml="false" />		 
				</div>
			</div>						 
		</c:forEach>
			
	</div>
	
</div>
