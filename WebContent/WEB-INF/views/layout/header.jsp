<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" id="logo" href="/tracker"><i class="fa fa-rocket"></i> Tracker</a>
    </div>
    
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav navbar-right">
      	<c:choose>
      		<c:when test="${empty loggeduser }">
		        <li><a href="/tracker/login">Login</a></li>
		        <li><a href="/tracker/register">Register</a></li> 
            </c:when>
            <c:otherwise>
            	
            	<li  class="dropdown" id="notificationItems">
				  <button class="btn btn-link notification-bell">
				    <i class="fa fa-bell fa-lg"></i>
				  </button>
				  <c:if test="${fn:length(eventsToApprove) ne 0}">
				  	<span class="badge badge-notify"> 
				  		<c:out value="${fn:length(eventsToApprove) }" />
				  	</span> 
				  </c:if>
				  <ul class="dropdown-menu">
				  </ul>
            	</li>
            	
            	<li class="dropdown" id="accountItems">
	            	<form action="/tracker/logout" method="post">
	            		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	            		<input type="hidden" name="username" value='<c:out value="${loggeduser.getUsername() }" />'>
					    <button id="accountHolder" class="btn btn-link dropdown-toggle" data-toggle="dropdown"><c:out value="${loggeduser.getLast_name()}" />
					    <i class="fa fa-caret-down fa-lg"></i></button>
					    <ul class="dropdown-menu">
					      	<li class="accountItem">								 
							 	<h4><a><c:out value="${loggeduser.fullname() }" /></a></h4>
							 	<span><c:out value="${loggeduser.getEmail() }" /></span>								
						 	</li>
					      	<li class="divider"></li>
					      	<li><a href="#"><button class="btn-link">Logout</button></a></li>
					    </ul>	
				    </form>				
            	</li>
            	
        	</c:otherwise>
        </c:choose>
      </ul>
    </div>
  </div> 
</nav>

<!-- Modal -->
<div class="modal fade" id="notification-model" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title"></h4>
      </div>
      <div class="modal-body">
      </div>
      <div class="modal-footer">
        <button type="button" id="notification-save" class="btn btn-primary">Confirm</button>
      </div>
    </div>
  </div>
</div>
<!-- Modal -->


<script src="/tracker/public/Js/header.js"></script>

