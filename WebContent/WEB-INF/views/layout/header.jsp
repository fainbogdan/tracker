<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            	<li>
            		<a href="#"><c:out value="${loggeduser.fullname() }"></c:out> </a>
            	</li>
		        <li>
		        	<form action="/tracker/logout" method="post">
		        		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		        		<button class="btn-link logout" style="padding-top:12px;ext-decoration: none;">Logout</button>
		        	</form>
		        </li>
        	</c:otherwise>
        </c:choose>
      </ul>
    </div>
  </div> 
</nav>
