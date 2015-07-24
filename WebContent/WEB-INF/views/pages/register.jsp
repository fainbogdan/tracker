<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tracker | Login</title>
<link rel="icon" type="image/png" href="http://paulferrett.com/fontawesome-favicon/generate.php?icon=rocket">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <!-- Le styles -->
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
    <link data-require="bootstrap-css" data-semver="3.3.1" rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />
    <link data-require="bootstrap@*" data-semver="3.3.2" rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" />
    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
	<link rel="stylesheet" href="public/css/home-styles.css">
</head>
<body>
<%@ include file="../static/layout.jsp" %>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
<div class="container">

	<div class="page-header">
        <h1 class="text-center">New Account <small> Registration</small></h1>
    </div>
    <div class="row col-md-8 col-md-offset-2">
        <mvc:form role="form" class="form-horizontal" modelAttribute="user">
            <div class="form-group">
                <label class="col-md-3">First Name</label>
                <div class="col-md-9">
                    <mvc:input type="text" path="first_name" class="form-control" name="first_name" placeholder="First Name" />
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
                    <input type="email" class="form-control" name="email" placeholder="Email Address">
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3">Phone</label>
                <div class="col-md-9">
                    <input type="text" class="form-control" name="phone" placeholder="Telephone">
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
        </mvc:form>
    </div>
</div>
</body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script data-require="bootstrap" data-semver="3.3.2" src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</html>