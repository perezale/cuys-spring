<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="row">
	<!-- LEFT PANE -->
	<div class="col-md-3">
<!-- 		<div class="page-header"> -->
<!-- 			<h4>Usuario</h4> -->
<!-- 		</div> -->
<!-- 		<img class="img-responsive img-thumbnail center-block" width="50%" -->
<!-- 			src="http://api.adorable.io/avatars/285/abott@adorable.io.png" /> -->
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
		<div>		
		  <!-- Nav tabs -->
		  <ul class="nav nav-tabs" role="tablist">
		  	<li role="presentation" class="active">
		  		<a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">Messages</a>
		  	</li>
		    <li role="presentation">
		    	<a href="#home" aria-controls="home" role="tab" data-toggle="tab">RSS</a>
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
									href="<spring:url value="/aportes/${post.id}.html" />" target="_blank"> <c:out value="${post.title}" />
								</a><small>
								<c:forEach items="${post.subjects}" var="subject">
									<span class="badge">${subject.title}</span>
								</c:forEach>
								</small>
							</h4>				  						
							<fmt:formatDate value="${post.publishedDate}" pattern="dd/MM/yyyy" var="newdatevar" />		
						    <c:out value="${newdatevar}" /><br>							
							<c:out value="${post.message}" escapeXml="false" /><br>
							
						</div>
					</div>						 
				</c:forEach>
		  	</div>
		    <div role="tabpanel" class="tab-pane" id="home">
		    	<c:forEach items="${items}" var="item">
					<div class="media">	
						<div class="media-left">
						    <a href="#">
						      <img class="media-object img-circle" alt="" width="64px"
						       src="https://s3.amazonaws.com/uifaces/faces/twitter/lady_katherine/128.jpg" >
						    </a>
						    <strong><c:out value="${item.blog.title}" /></strong> <br/>				    
					  	</div>
						<div class="media-body">
						    <h4 class="media-heading"><a
									href="<c:out value="${item.link}" />" target="_blank"> <c:out
											value="${item.title}" />
								</a></h4>				  
							<c:out value="${item.publishedDate}" /><br>																				
							<c:out value="${fn:substring(item.description, 0, 400)}" />
						</div>
					</div>						 
				</c:forEach>
		    </div>		    		    
		  </div>
		
		</div>
		 					
	</div>
	
</div>
