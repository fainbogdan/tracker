<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container">
	<div class="page-header">
        <h1 class="text-center">Account recovery</h1>
    </div>
    
    <%
		if(session.getAttribute("send_activation_link") !=null){
			String error=(String) session.getAttribute("send_activation_link");
			String content="<h4 class='text-danger text-center'>"+error+"</h4>";
			session.removeAttribute("send_activation_link");
			out.print(content);
		}
	%>
    
    <c:if test="${not empty message }">
    	<h4 class="text-center text-danger"> <c:out value="${message }"></c:out> </h4>
    </c:if>
    
	<form role="form" method="post" action="accountRecovery">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<div class="row">
			<div class="form-group col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 col-sm-12 col-xs-12">
				<label>Email</label>
				<input type="text" name="email" class="form-control" placeholder="Registered Email">
			</div>
		</div>
		<div class="row">
			<div class="form-group col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 col-sm-12 col-xs-12">
				<label>Request</label>
				<label class="radio-inline"><input type="radio" name="recover" value="activation">Activation link</label>
				<label class="radio-inline"><input type="radio" name="recover" value="password">Reset password link</label>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-lg-4 col-lg-offset-4 col-md-4 col-md-offset-4 col-sm-12 col-xs-12">
				<button class="btn btn-primary btn-block" id="accountRecoveryBtn">Submit request</button>
			</div>
		</div>
	</form>
</div>
