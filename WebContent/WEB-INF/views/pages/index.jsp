<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tracker | Home</title>
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
    <script data-require="jquery" data-semver="2.1.3" src="http://code.jquery.com/jquery-2.1.3.min.js"></script>
    <script data-require="bootstrap" data-semver="3.3.2" src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="public/css/home-styles.css">
</head>


<body id="index-body">
<%@ include file="../static/layout.jsp" %>
<div class="container" id="index-container">
	<div class="col-sm-12" >
		<div id="events-header-box">
				<div><i class="fa fa-rocket fa-5x"></i></div>
				<div>
					<span>Tracker</span>
					Track the progress of everything you do with your system's
				</div>
		</div>
	</div>
	<div class="col-sm-12">
		<div class="col-sm-3">
			<a class="link" href="emergency" >
			<div id="emergency-event-box">
				<div id="emergency-event-box-header">
					<div><i class="fa fa-exclamation-triangle fa-3x"></i></div>
					<span class="event-box-title">Emergency</span>
				</div>
				<ul>
					<li>For creating emergency events</li>
					<li>Events that start immediately</li>
					<li>Accelerated Workflow</li>
					<li>Systems restart or break-fix</li>
				</ul>
				<span class="event-box-footer" id="x">Create Event</span>
			</div>
			</a>
		</div>
		
		<div class="col-sm-3">
			<a href="planned" class="link">
			<div id="planned-event-box">
				<div id="planned-event-box-header">
					<div><i class="fa fa-calendar fa-3x"></i></div>
					<span class="event-box-title">Planned</span>
				</div>
				<ul>
					<li>For creating planned events</li>
					<li>Events done at flexible times</li>
					<li>Planned Workflow</li>
					<li>Planned upgrades & fixe's</li>
				</ul>
				<span class="event-box-footer">Create Event</span>
			</div>
			</a>
		</div>
			
		<div class="col-sm-3">
			<a href="#" class="link">
			<div id="start-event-box">
				<div id="start-event-box-header">
					<div><i class="fa fa-power-off fa-3x"></i></div>
					<span class="event-box-title">Execute</span>
				</div>
				<ul>
					<li>Work on events created</li>
					<li>Track the progress of event</li>
					<li>Edit the pre-written checklist</li>
					<li>Manage the order of workflow</li>
				</ul>
				<span class="event-box-footer">Start Event</span>
			</div>
			</a>
		</div>
		
		<div class="col-sm-3">
			<a href="#" class="link">
			<div id="reporting-event-box">
				<div id="reporting-event-box-header">
					<div><i class="fa fa-file-excel-o fa-3x"></i></div>
					<span class="event-box-title">Reports</span>
				</div>
				<ul>
					<li>Start a planned event</li>
					<li>Track the progress of event</li>
					<li>Edit the pre-written checklist</li>
					<li>Manage the order of workflow</li>
				</ul>
				<span class="event-box-footer">Start Event</span>
			</div>
			</a>
		</div>
	</div>
</div>	
</body>
</html>