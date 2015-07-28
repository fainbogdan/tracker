<%@page import="org.joda.time.LocalDateTime"%>
<%@page import="org.joda.time.DateTime"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tracker | Event</title>
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
	<link rel="stylesheet" href="/tracker/public/css/home-styles.css">
</head>

<body>
<%@ include file="../static/layout.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<div class="container">
		<div class="row">
			<div class="col-md-6">
				
				<span class="fa-stack fa-3x pull-left event-cal-type">
					<i class="fa fa-calendar-o fa-stack-2x"></i>
					<c:if test="${event.getEvent_type()=='emergency'}">
						<i class="fa fa-exclamation-triangle fa-stack-1x"></i>
					</c:if>
					<c:set var="expected_start" value="${event.getExpected_start() }"></c:set>
					<strong class="fa-stack-1x event-date">
					<% 
						LocalDateTime date=new LocalDateTime(pageContext.getAttribute("expected_start"));
						out.print(date.getDayOfMonth());
					%>
					</strong>
				</span>
				<h1><c:out value="${event.getName() }"></c:out></h1>
				<span>You are Watching this event <i class="fa fa-eye fa-lg watcher-icon"></i></span>
				<hr/>
				<div class="row">
					<div class="col-md-6">
						<dl>
							<dt><i class="fa fa-cubes"></i>  Environment</dt> <dd><c:out value="${event.getEnvironment() }" /></dd>
							<dt><i class="fa fa-user fa-fw"></i> Created By</dt> <dd></dd>
							<dt><i class="fa fa-calendar fa-fw"></i> Estimated Start</dt> <dd><c:out value="${event.getExpected_start() }" /></dd>
							<dt><i class="fa fa-user fa-fw"></i> Estimated End</dt> <dd><c:out value="${event.getExpected_end() }" /></dd>
							<dt><i class="fa fa-clock-o fa-fw"></i> Estimated Duartion</dt> <dd></dd>
						</dl>
					</div>
					<div class="col-md-6">
						<dl>
							<dt><i class="fa fa-user fa-fw"></i> Started By</dt> <dd></dd>
							<dt><i class="fa fa-calendar fa-fw"></i> Actual Start</dt> <dd><c:out value="${event.getActual_start() }" /></dd>
							<dt><i class="fa fa-calendar fa-fw"></i> Actual End</dt> <dd><c:out value="${event.getActual_end() }" /></dd>
							<dt><i class="fa fa-clock-o fa-fw"></i> Actual Duration</dt><dd></dd>
						</dl>
					</div>
				</div>
				<hr/>
				<span>
					<dl>
						<dt><i class="fa fa-info-circle"></i> Description</dt>
						<dd><c:out value="${event.getDescription() }" /></dd>
					</dl>
				</span>
			</div>
			
			<div class="col-md-6">
				<h2>Run Plan
				<a href='<c:out value="${event.getId() }" />/edit'><span class="pull-right"><i class="fa fa-pencil-square-o edit-checklist-icon"></i></span></a>
				</h2>
				<div id="pre-event">
					<h3>Pre-Event Checklist</h3>
					<ul class="list-group">
						<c:forEach items="${event.getChecklist() }" var="checklist">
							<c:if test="${checklist.getPhase()=='setup' }">
								<li class="list-group-item" checklist-id='<c:out value="${checklist.getId() }" />'>
									<c:choose>
										<c:when test="${checklist.getCompleted() eq 'Y' }">
											<i class="fa fa-check-circle fa-2x fa-fw checklist-icon"></i>
										</c:when>
										<c:when test="${checklist.getCompleted() eq 'N' }">
											<i class="fa fa-times-circle fa-2x fa-fw checklist-icon"></i>
										</c:when>
										<c:otherwise>
											<i class="fa fa-circle-o fa-2x fa-fw checklist-icon"></i>
										</c:otherwise>
									</c:choose>
									<c:out value="${checklist.getName() }" />
								</li>
							</c:if>
						</c:forEach>
					</ul>
				</div>
				
				<div id="start-event">
					<button class="btn btn-success btn-lg btn-block">Start Event</button>
				</div>
				
				<div id="during-event">
					<h3>During-Event Checklist</h3>
					<ul class="list-group">
						<c:forEach items="${event.getChecklist() }" var="checklist">
							<c:if test="${checklist.getPhase()=='execute' }">
								<li class="list-group-item" checklist-id='<c:out value="${checklist.getId() }" />'>
									<c:choose>
										<c:when test="${checklist.getCompleted() eq 'Y' }">
											<i class="fa fa-check-circle fa-2x fa-fw checklist-icon"></i>
										</c:when>
										<c:when test="${checklist.getCompleted() eq 'N' }">
											<i class="fa fa-times-circle fa-2x fa-fw checklist-icon"></i>
										</c:when>
										<c:otherwise>
											<i class="fa fa-circle-o fa-2x fa-fw checklist-icon"></i>
										</c:otherwise>
									</c:choose>
									<c:out value="${checklist.getName() }" />
								</li>
							</c:if>
						</c:forEach>
					</ul>
				</div>
				
				<div id="end-event">
					<button class="btn btn-danger btn-lg btn-block">Start Event</button>
				</div>
				
				<div id="post-event">
					<h3>Post-Event Checklist</h3>
					<ul class="list-group">
						<c:forEach items="${event.getChecklist() }" var="checklist">
							<c:if test="${checklist.getPhase()=='teardown' }">
								<li class="list-group-item" checklist-id='<c:out value="${checklist.getId() }" />'>
									<c:choose>
										<c:when test="${checklist.getCompleted() eq 'Y' }">
											<i class="fa fa-check-circle fa-2x fa-fw checklist-icon"></i>
										</c:when>
										<c:when test="${checklist.getCompleted() eq 'N' }">
											<i class="fa fa-times-circle fa-2x fa-fw checklist-icon"></i>
										</c:when>
										<c:otherwise>
											<i class="fa fa-circle-o fa-2x fa-fw checklist-icon"></i>
										</c:otherwise>
									</c:choose>
									<c:out value="${checklist.getName() }" />
								</li>
							</c:if>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script data-require="bootstrap" data-semver="3.3.2" src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(function()
{
	$('.checklist-icon').click(function()
	{
		var updatedIcon=$(this);
		var newState;
		if($(updatedIcon).hasClass('fa-check-circle'))
			newState='N';
		else
			newState='Y';
		
		$(this).addClass('fa-spinner fa-spin');
		$.ajax({
			url:'/tracker/checklistState/'+$(this).parents('li').attr('checklist-id'),
			method:'put',
			contentType:'application/json',
			datatype:'json',
			data:JSON.stringify({"id": $(this).parents('li').attr('checklist-id'), "completed":newState}),
			success:function(data)
			{
				if(data.message=="success")
				{
					var updatedChecklist=data.checklist;
					if(updatedChecklist.completed=="Y")
						$(updatedIcon).removeClass('fa-circle-o fa-times-circle fa-spinner fa-spin').addClass('fa-check-circle');
					else
						$(updatedIcon).removeClass('fa-check-circle fa-spinner fa-spin').addClass('fa-times-circle');
				}
				else
				{
					var alert='<div class="alert alert-danger alert-error"> ' +
				                    '<a href="#" class="close" data-dismiss="alert">&times;</a> ' +
				                    '<strong>Error!</strong> Cannot start event unless pre event items are completed ' +
			                    '</div>';
		            $('body').prepend(alert);
		            setTimeout(function()
		            {
		                $('.alert').remove();
		            },5000);
				}
			}
		});
		
		
	    /*var icon=$(this);
	    var phase;
	    var startDate;
	    var endDate;
	
	    /*checking if event is started before entering inro execute phase. check if event is ended or not before ending into post events */
	   /* $.getJSON('/checklists/'+$(icon).attr('checklist-id'), {
	        type: 'json'
	    }, 
	    function(data, status, jqxhr) 
	    {
	        phase=data.phase;
	
	        var event_id ="";
	                $.getJSON('/events/'+event_id, {
	                    type: 'json'
	                }, 
	                function(data, status, jqxhr) 
	                {
	                    startDate=data.actual_start_ago;
	                    endDate=data.actual_end_ago;
	
	                    if(phase=="execute")
	                    {
	                        if(startDate!='')
	                            changeIconState(icon);            //changing state of execute phase items if event started
	                        else
	                        {
	                            //displaying alert on error
	                            var alert='<div id="alert" class="bs-example" > ' +
	                                    '<div class="alert alert-danger alert-error"> ' +
	                                    '<a href="#" class="close" data-dismiss="alert">&times;</a> ' +
	                                    '<strong>Error!</strong> Cannot do this action without Completing setUp and start Event. ' +
	                                    '</div> ' +
	                                    '</div>';
	                            $('body').prepend(alert);
	                            setTimeout(function()
	                            {
	                                $('#alert').remove();
	                            },5000);
	                            return false;
	                        }
	                    }
	
	                    if(phase=="teardown")
	                    {
	                        if(endDate!='')     //if event has end date
	                            changeIconState(icon);   //changing state of post event items if event ended
	                        else
	                        {               //if event not ended. show error
	                            var alert='<div id="alert" class="bs-example" > ' +
	                                    '<div class="alert alert-danger alert-error"> ' +
	                                    '<a href="#" class="close" data-dismiss="alert">&times;</a> ' +
	                                    '<strong>Error!</strong> Post-Event items can be done only after completing previous items and end event. ' +
	                                    '</div> ' +
	                                    '</div>';
	                            $('body').prepend(alert);
	                            setTimeout(function()
	                            {
	                                $('#alert').remove();
	                            },5000);
	                            return false;
	                        }
	                    }
	                    else
	                        changeIconState(icon);      //changing state of pre event items.
	                });
	    });*/
	
	});
});
	
</script>
</html>