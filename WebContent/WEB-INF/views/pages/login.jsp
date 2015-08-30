<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container">
	<sf:form role="form" action="login" method="post">
		<c:if test="${param.error != null}">
			<p class="text-danger text-center">Invalid username and password</p>
		</c:if>
		<c:if test="${param.logout != null}">
			<p>You have been logged out</p>
		</c:if>
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 col-sm-12 col-xs-12">
				<div class="form-group">
					<label>Username</label>
					<input type="text" name="username" id="username" class="form-control" placeholder="Enter Username">
				</div>
				<div class="form-group">
					<label>Password</label>
					<input type="password" name="password" id="password" class="form-control" placeholder="Enter Password">
				</div>
				<div class="checkbox">
					<label>
						<input type="checkbox" id="remember_me" name="remember-me"> Remember me
					</label>
				</div>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-4 col-lg-offset-4 col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4 col-xs-8 col-xs-offset-2">
				<button type="submit" class="btn btn-primary btn-block"><i class="fa fa-lock"></i> Sign in to Tracker</button>
			</div>
			<div class="text-center col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<a href="#">Forgot Password?</a>
			</div>
		</div>
	</sf:form>
</div>