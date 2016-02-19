<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<div class="row">	
	<!-- MID PANE -->
	<div class="col-md-8">
		<div>		
		  <!-- Nav tabs -->
		  <ul class="nav nav-tabs" role="tablist">
		  	<li role="presentation" class="active">
		  		<a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">Aportes</a>
		  	</li> 		    		    
		  </ul>
		
		  <!-- Tab panes -->
		  <div class="tab-content" style="margin-top: 15px;">
		  	<div role="tabpanel" class="tab-pane active" id="messages">
		  		<c:forEach items="${posts}" var="post">
					<div class="media">	
						<div class="media-left">
						    <a href="#">
						      <img class="media-object img-circle" alt="" width="64px"
						       src="https://pingendo.github.io/pingendo-bootstrap/assets/user_placeholder.png" >
						    </a>						    
						    <p class="text-center"> <c:out value="${post.user.name}" /></p>
					  	</div>
						<div class="media-body">
						    <h4 class="media-heading"><a
									href="<spring:url value="/aportes/${post.id}.html" />" target="_blank"> <c:out value="${post.title}" /></a><small>
								<c:out value="${post.timeAgo}" />
								</small>
							</h4>											  												
							<c:out value="${post.message}" escapeXml="false" />
							<c:if test="${not empty post.message}">
							<br></c:if>
							<c:forEach items="${post.subjects}" var="subject">
									<span class="badge">${subject.title}</span>
							</c:forEach>							
						</div>
					</div>						 
				</c:forEach>
		  	</div>
		     		   
		  </div>
		
		</div>
		 					
	</div>
	<!-- LEFT PANE -->
	<div class="col-md-4">
<!-- 		<div class="page-header"> -->
<!-- 			<h4>Usuario</h4> -->
<!-- 		</div> -->
<!-- 		<img class="img-responsive img-thumbnail center-block" width="50%" -->
<!-- 			src="http://api.adorable.io/avatars/285/abott@adorable.io.png" /> -->
		<div class="page-header">
			<h4>Novedades</h4>
		</div>
		<c:forEach items="${items}" var="item">
			<div class="media">	
				<div class="media-left">
				    <a href="#">
				      <img title="<c:out value="${item.blog.title}" /> " class="media-object" alt="" width="32px"
				       src="/img/facultadexa.png" >
				    </a>				    			    
			  	</div>
				<div class="media-body">
				    <h6 class="media-heading"><a
							href="<c:out value="${item.link}" />" target="_blank"> <c:out
									value="${item.title}" />
						</a><small><c:out value="${item.timeAgo}" /></small></h6>				  					
				</div>
			</div>						 
		</c:forEach>
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

