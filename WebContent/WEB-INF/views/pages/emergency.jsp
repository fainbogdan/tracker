<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tracker | Emergency</title>
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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.7.14/css/bootstrap-datetimepicker.min.css">
	<link rel="stylesheet" href="public/css/home-styles.css">
</head>

<body>
<%@ include file="../static/layout.jsp" %>
<div class="container">
    <div class="page-header">
        <h1>Emergency <small> Create an Emergency event</small></h1>
    </div>
    <div class="row">
        <div class="col-sm-6">
            <mvc:form role="form" modelAttribute="event" method="post">
            	<mvc:input type="hidden" path="event_type" name="event_type" value="emergency" />
                <div class="form-group">
                    <mvc:label path="expected_start">When it is happening?</mvc:label>
                    <div class='input-group date' id='datetimepicker1'>
                        <mvc:input path="expected_start" type='text' class="form-control" placeholder="Start Date time of event"/>
                        <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </span>
                    </div>
                </div>
                <div class="form-group">
                    <mvc:label path="name">What is happening?</mvc:label>
                    <mvc:input path="name" type="text" class="form-control" placeholder="Title for this event" />
                </div>
                <div class="form-group">
                    <mvc:label path="environment">Environment?</mvc:label>
                    <mvc:input path="environment" type="text" class="form-control" placeholder="Possible effected area" />
                </div>
                <div class="form-group">
                    <mvc:label path="description">Description?</mvc:label>
                    <mvc:textarea path="description" rows="3" class="form-control" placeholder="Additional related information"></mvc:textarea>
                </div>
                <div class="form-group">
                    <div class="col-sm-8 col-sm-offset-2">
                        <mvc:button class="btn btn-danger btn-block btn-lg">Start Emergency Event</mvc:button>
                    </div>
                </div>
            </mvc:form>
        </div>
        <div class="col-sm-1"></div>
        <div class="col-sm-5">
            <!--calendar goes here -->
        </div>
    </div>
</div>

</body>
<script data-require="jquery" data-semver="2.1.3" src="http://code.jquery.com/jquery-2.1.3.min.js"></script>
<script data-require="bootstrap" data-semver="3.3.2" src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.3/moment.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.7.14/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript">
		$(document).ready(function()
	    {
	        $('#datetimepicker1').datetimepicker();
	    });
</script>
</html>