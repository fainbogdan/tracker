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
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3">Last Name</label>
                <div class="col-md-9">
                    <input type="text"  path="last_name" class="form-control" name="last_name" placeholder="Last Name">
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3">Username</label>
                <div class="col-md-9">
                    <input type="text"  path="username" class="form-control" name="username" placeholder="Desired Username">
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3">Password</label>
                <div class="col-md-9">
                    <input type="password"  path="password" class="form-control" name="password" placeholder="Password">
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3">Confirm Password</label>
                <div class="col-md-9">
                    <input type="password" class="form-control" name="repassword" placeholder="Confirm Password">
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3">Email</label>
                <div class="col-md-9">
                    <input type="email" path="email" class="form-control" name="email" placeholder="Email Address">
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3">Phone</label>
                <div class="col-md-9">
                    <input type="text" path="phone" class="form-control" name="phone" placeholder="Telephone">
                </div>
            </div>
            <div class="form-group" style="margin-top: 2em;">
                <div class="col-md-4 col-md-offset-1">
                    <button class="btn btn-primary btn-lg btn-block">Create Account</button>
                </div>
                <div class="col-md-4 col-md-offset-1">
                    <button type="reset" class="btn btn-default btn-lg btn-block" style="background: #E6E6E6;">Reset</button>
                </div>
            </div>
        </sf:form>
    </div>
</div>
