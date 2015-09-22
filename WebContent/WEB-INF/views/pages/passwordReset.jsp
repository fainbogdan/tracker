<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container">
	<div class="page-header">
        <h1 class="text-center">Reset password</h1>
    </div>
    
    <c:if test="${not empty message }">
    	<h4 class="text-center text-danger"> <c:out value="${message }"></c:out> </h4>
    </c:if>
    
    <form role="form" method="post" action="passwordReset">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<input type="hidden" name="token" value='<c:out value="${token }" /> '/>
		<div class="row">
			<div class="form-group col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 col-sm-12 col-xs-12">
				<label>New password</label>
				<input type="password" name="password" class="form-control" placeholder="Enter new password">
			</div>
		</div>
		<div class="row">
			<div class="form-group col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 col-sm-12 col-xs-12">
				<label>Retype new password</label>
				<input type="password" name="repassword" class="form-control" placeholder="Retype new password">
			</div>
		</div>
		<div class="row">
			<div class="form-group col-lg-4 col-lg-offset-4 col-md-4 col-md-offset-4 col-sm-12 col-xs-12">
				<button class="btn btn-primary btn-block" id="accountRecoveryBtn">Reset password</button>
			</div>
		</div>
	</form>
</div>