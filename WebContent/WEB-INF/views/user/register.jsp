<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="container">

	<div class="page-header">
        <h1 class="text-center">New Account <small> Registration</small></h1>
    </div>
    <div class="row col-md-8 col-md-offset-2">
        <sf:form role="form" class="form-horizontal" modelAttribute="user">
            <div class="form-group">
                <label class="col-md-3">First Name</label>
                <div class="col-md-9">
                    <sf:input type="text" path="first_name" class="form-control" name="first_name" placeholder="First Name" />
                    <sf:errors path="first_name" class="text-danger"></sf:errors>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3">Last Name</label>
                <div class="col-md-9">
                    <sf:input type="text"  path="last_name" class="form-control" name="last_name" placeholder="Last Name" />
                	<sf:errors path="last_name" class="text-danger"></sf:errors>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3">Username</label>
                <div class="col-md-9">
                    <sf:input type="text"  path="username" class="form-control" name="username" placeholder="Desired Username" />
                	<sf:errors path="username" class="text-danger"></sf:errors>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3">Password</label>
                <div class="col-md-9">
                    <sf:input type="password"  path="password" class="form-control" name="password" placeholder="Password" />
                    <sf:errors path="password" class="text-danger"></sf:errors>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3">Confirm Password</label>
                <div class="col-md-9">
                    <sf:input type="password" class="form-control" path="repassword" name="repassword" placeholder="Confirm Password" />
                    <sf:errors class="text-danger"></sf:errors>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3">Email</label>
                <div class="col-md-9">
                    <sf:input type="email" path="email" class="form-control" name="email" placeholder="Company email address" />
                    <sf:errors path="email" class="text-danger"></sf:errors>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3">Phone</label>
                <div class="col-md-9">
                    <sf:input type="text" path="phone" class="form-control" name="phone" placeholder="Telephone" />
                    <sf:errors path="phone" class="text-danger"></sf:errors>
                </div>
            </div>
            <div class="form-group" style="margin-top: 2em;">
                <div class="col-xs-8 col-xs-offset-2">
                    <button class="btn btn-primary btn-lg btn-block">Create Account</button>
                </div>
            </div>
        </sf:form>
    </div>
</div>
