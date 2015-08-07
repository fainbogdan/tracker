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
	<link rel="stylesheet" href="public/css/home-styles.css">
</head>


<body id="index-body">
<%@ include file="../static/layout.jsp" %>
<div class="container" id="index-container">
	
	<div class="row" id="events-header-box">
		<div class="col-lg-1 col-md-1 col-sm-1 col-xs-2"><i class="fa fa-rocket fa-4x"></i></div>
		<div class="col-lg-11 col-md-11 col-sm-11 col-xs-10">
			<h1>Tracker</h1>
			<span>Track the progress of everything you do with your system's</span>
		</div>
	</div>
	
	<div class="row">
	
		<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
			<a class="link" href="emergency" >
			<div class="event-box">
				<div class="event-box-header text-center">
					<div><i class="fa fa-exclamation-triangle fa-3x"></i></div>
					<span class="event-box-title">Emergency</span>
				</div>
				<div class="event-box-body text-left text-muted">
					Use this to create an emergency event with accelerated workflow. These events start immediately as they are created. 
					Users can dynamic checklist items as required. Ex: Systems restart, break-fix.
				</div>
				<div class="event-box-footer text-center">
					<span>Start Event</span>
				</div>
			</div>
			</a>
		</div>
		
		<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
			<a href="planned" class="link">
			<div class="event-box">
				<div class="event-box-header text-center">
					<div><i class="fa fa-calendar fa-3x"></i></div>
					<span class="event-box-title">Planned</span>
				</div>
				<div class="event-box-body text-left text-muted">
					Use this to create a planned event. Thing are pre-planned and listed here. Users can dynamically add new
					Checklist items as required. Manage the order of workflow. Ex: Planned installation's & upgrades.
				</div>
				<div class="event-box-footer text-center">
					<span>Start Event</span>
				</div>
			</div>
			</a>
		</div>
			
		<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
			<a href="events" class="link">
			<div class="event-box">
				<div class="event-box-header text-center">
					<div><i class="fa fa-power-off fa-3x"></i></div>
					<span class="event-box-title">Execute</span>
				</div>
				<div class="event-box-body text-left text-muted">
					Use this to start an event that is already created. You can also search for old events. Edit or add pre-written checklist items.
					Change the order of items. Watch or unwatch in events that are created by other users.
				</div>
				<div class="event-box-footer text-center">
					<span>Start Event</span>
				</div>
			</div>
			</a>
		</div>
		
		<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
			<a href="#" class="link">
			<div class="event-box">
				<div class="event-box-header text-center">
					<div><i class="fa fa-file-excel-o fa-3x"></i></div>
					<span class="event-box-title">Reports</span>
				</div>
				<div class="event-box-body text-left text-muted">
					Use this option to create weekly, monthly or custom reports about events in the system. Users can search for events, 
					applying different filters on search results. You can download reports in excel format.
				</div>
				<div class="event-box-footer text-center">
					<span>Start Event</span>
				</div>
			</div>
			</a>
		</div>
		
	</div>
	
</div>	
</body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script data-require="bootstrap" data-semver="3.3.2" src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</html>