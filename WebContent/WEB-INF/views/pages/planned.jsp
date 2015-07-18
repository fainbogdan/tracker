<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tracker | Planned</title>
<link rel="icon" type="image/png" href="http://paulferrett.com/fontawesome-favicon/generate.php?icon=rocket">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
        <h1>Planned <small> Create an Planned event</small></h1>
    </div>
    <div class="row">
        <div class="col-md-6">
            <mvc:form role="form" method="post" commandName="event">
        		<mvc:input type="hidden" path="event_type" name="event_type" value="planned" />
                <div class="form-group">
                    <label>Start time?</label>
                    <div class='input-group date' id='datetimepicker1'>
                        <mvc:input type='text' path="expected_start" class="form-control" placeholder="Start Date time of event"/>
                        <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </span>
                    </div>
                    <mvc:errors path="expected_start" class="error"></mvc:errors>
                </div>
                <div class="form-group">
                    <label>End time?</label>
                    <div class='input-group date' id='datetimepicker2'>
                        <mvc:input type='text' path="expected_end" class="form-control" placeholder="End Date time"/>
                        <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </span>
                    </div>
                    <mvc:errors path="expected_end" class="error"></mvc:errors>
                </div>
                <div class="form-group">
                    <label>What is happening?</label>
                    <mvc:input type="text" path="name" class="form-control" placeholder="Title for this event"></mvc:input>
                    <mvc:errors path="name" class="error"></mvc:errors>
                </div>
                <div class="form-group">
                    <label>Environment?</label>
                    <mvc:input type="text" path="environment" class="form-control" placeholder="Possible effected area"></mvc:input>
                    <mvc:errors path="environment" class="error"></mvc:errors>
                </div>
                <div class="form-group">
                    <label>Description?</label>
                    <mvc:textarea rows="3" path="description" class="form-control" placeholder="Additional related information"></mvc:textarea>
                    <mvc:errors path="description" class="error"></mvc:errors>
                </div>
                <div class="form-group">
                    <div class="col-md-8 col-md-offset-2">
                        <button class="btn btn-primary btn-block btn-lg">Create Planned Event</button>
                    </div>
                </div>
            </mvc:form>
        </div>
        <div class="col-md-1"></div>
        <div class="col-md-5">
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
	        $('#datetimepicker2').datetimepicker();
	        $("#datetimepicker1").on("dp.change", function (e) {
	            $('#datetimepicker2').data("DateTimePicker").minDate(e.date);
	        });
	        $("#datetimepicker2").on("dp.change", function (e) {
	            $('#datetimepicker1').data("DateTimePicker").maxDate(e.date);
	        });
	    });
		
		
</script>
</html>