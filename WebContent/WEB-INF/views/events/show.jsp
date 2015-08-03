<%@page import="org.joda.time.Period"%>
<%@page import="org.joda.time.Duration"%>
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
					<c:set var="expected_end" value="${event.getExpected_end() }"></c:set>
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
							<dt><i class="fa fa-calendar fa-fw"></i> Estimated Start</dt> <dd><c:out value="${event.getExpected_start().toString('yyyy-MM-dd HH:mm:ss') }" /></dd>
							<dt><i class="fa fa-user fa-fw"></i> Estimated End</dt> <dd><c:out value="${event.getExpected_end().toString('yyyy-MM-dd HH:mm:ss') }" /></dd>
							<dt><i class="fa fa-clock-o fa-fw"></i> Estimated Duartion</dt> 
							<dd>
								<%
									LocalDateTime exp_start=new LocalDateTime(pageContext.getAttribute("expected_start"));
									LocalDateTime exp_end=new LocalDateTime(pageContext.getAttribute("expected_end"));
									Period exp_period=new Period(exp_start,exp_end);
									out.print(exp_period.toStandardMinutes().getMinutes());
								%>
							 minutes </dd>
						</dl>
					</div>
					<div class="col-md-6">
						<dl>
							<dt><i class="fa fa-user fa-fw"></i> Started By</dt> <dd></dd>
							<dt><i class="fa fa-calendar fa-fw"></i> Actual Start</dt> <dd><c:out value="${event.getActual_start().toString('yyyy-MM-dd HH:mm:ss') }" /></dd>
							<dt><i class="fa fa-calendar fa-fw"></i> Actual End</dt> <dd><c:out value="${event.getActual_end().toString('yyyy-MM-dd HH:mm:ss') }" /></dd>
							<dt><i class="fa fa-clock-o fa-fw"></i> Actual Duration</dt> 
							<c:set var="actual_start" value="${event.getActual_start() }"></c:set>
							<c:set var="actual_end" value="${event.getActual_end() }"></c:set>
							<dd>
								<%
									LocalDateTime act_start=new LocalDateTime(pageContext.getAttribute("actual_start"));
									LocalDateTime act_end=new LocalDateTime(pageContext.getAttribute("actual_end"));
									Period act_period=new Period(act_start,act_end);
									out.print(act_period.toStandardMinutes().getMinutes());
								%>
							 minutes </dd>
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
						<c:forEach items="${event.getSortedChecklist() }" var="checklist">
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
					<c:choose>
						<c:when test="${empty event.getActual_start() }">
							<button class="btn btn-success btn-lg btn-block" id="start_event_btn" data-loading-text="Starting event..." event_id='<c:out value="${event.getId()}"></c:out>' >Start Event</button>
						</c:when>
						<c:otherwise>
							<h3 class="text-success text-center">Event started at <c:out value="${event.getActual_start().toString('yyyy-MM-dd HH:mm:ss') }" /></h3>
						</c:otherwise>
					</c:choose>
				</div>
				
				<div id="during-event">
					<h3>During-Event Checklist</h3>
					<ul class="list-group">
						<c:forEach items="${event.getSortedChecklist() }" var="checklist">
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
					<c:choose>
						<c:when test="${empty event.getActual_end() }">
							<button class="btn btn-danger btn-lg btn-block" id="end_event_btn" data-loading-text="Ending event..." event_id='<c:out value="${event.getId()}"></c:out>' >End Event</button>
						</c:when>
						<c:otherwise>
							<h3 class="text-danger text-center">Event ended at <c:out value="${event.getActual_end().toString('yyyy-MM-dd HH:mm:ss') }" /></h3>
						</c:otherwise>
					</c:choose>
				</div>
				
				<div id="post-event">
					<h3>Post-Event Checklist</h3>
					<ul class="list-group">
						<c:forEach items="${event.getSortedChecklist() }" var="checklist">
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
	
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel"></h4>
      </div>
      <div class="modal-body">
      </div>
      <div class="modal-footer">
        <button type="button" id="save" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>
<!-- Modal -->
</body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script data-require="bootstrap" data-semver="3.3.2" src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(function()
{
	$(document).on('click','.checklist-icon',function()
	{
		var updatedIcon=$(this);
		console.log(updatedIcon);
		var goToState;
		if($(updatedIcon).hasClass('fa-check-circle'))
			goToState='N';
		else
			goToState='Y';
		
		if(goToState=='N')
		{
			var modal_title='Why you want to skip this item?';
			$('.modal-title').html(modal_title);
			var modal_body='<form> ' +
					            '<div class="form-group"> ' +
						            '<label for="message-text" class="control-label">Enter your note here</label> ' +
						            '<textarea rows="5" class="form-control" id="skipNote"></textarea> ' +
					            '</div> ' +
				            '</form> ';
			$('.modal-body').html(modal_body);
			$('#myModal').modal('show').on('click','#save',function()
			{
				if($('#skipNote').val().trim().length)
				{
					$('#myModal').modal('hide');
					$(updatedIcon).addClass('fa-spinner fa-spin');
					$.ajax(
					{
						url:'/tracker/checklistState/'+$(updatedIcon).parents('li').attr('checklist-id'),
						method:'put',
						contentType:'application/json',
						datatype:'json',
						data:JSON.stringify({"completed":goToState, skipped_note:$('#skipNote').val().trim()}),
						success:function(data)
						{
							$(updatedIcon).removeClass('fa-spinner fa-spin');
							var updatedChecklist=data.checklist;
							if(updatedChecklist.completed=="Y")
								$(updatedIcon).removeClass('fa-circle-o fa-times-circle').addClass('fa-check-circle');
							else
								$(updatedIcon).removeClass('fa-check-circle').addClass('fa-times-circle');
						}
					});
				}
				else
					$('#skipNote').parents('.form-group').addClass('has-error');
			});
		}
		else
		{
			$(updatedIcon).addClass('fa-spinner fa-spin');
			$.ajax(
			{
				url:'/tracker/checklistState/'+$(updatedIcon).parents('li').attr('checklist-id'),
				method:'put',
				contentType:'application/json',
				datatype:'json',
				data:JSON.stringify({"completed":goToState}),
				success:function(data)
				{
					$(updatedIcon).removeClass('fa-spinner fa-spin');
					if(data.message=="success")
					{
						var updatedChecklist=data.checklist;
						if(updatedChecklist.completed=="Y")
							$(updatedIcon).removeClass('fa-circle-o fa-times-circle').addClass('fa-check-circle');
						else
							$(updatedIcon).removeClass('fa-check-circle').addClass('fa-times-circle');
					}
					else
					{
						var alert='<div class="alert alert-danger alert-error" role="alert"> ' +
					                    '<a href="#" class="close" data-dismiss="alert">&times;</a> ' +
					                    '<strong>Error!</strong> '+data.message +
				                    '</div>';
			            $('body').prepend(alert);
			            setTimeout(function(){
			                $('.alert').fadeOut("slow",function(){
	                			$(this).remove();
	                		});
			            },5000);
					}
				}
			});
		}
	});
	
	$('#start_event_btn').click(function() 
	{
		var start_btn = $(this).button('loading');
		$.ajax(
		{
			url:'/tracker/events/'+$(start_btn).attr('event_id')+'/start',
			method:'put',
			contentType:'application/json',
			dataType:'json',
			data:JSON.stringify({}),
			success:function(data)
			{
				var start=data.event['actual_start'];
				if(data.message=="success")
					$(start_btn).parent().html('<h3 class="text-success text-center">Event started at '+start.year+'-'+start.monthOfYear+'-'+start.dayOfMonth+'   '+start.hourOfDay+':'+start.minuteOfHour+':'+start.secondOfMinute+'</h3>');
				else
				{
					$(start_btn).button('reset');
					var alert='<div class="alert alert-danger alert-error" role="alert"> ' +
				                    ' <a href="#" class="close" data-dismiss="alert"> &times;</a> ' +
				                    '<strong>Error!</strong> '+data.message  +
				                '</div>';
				    $('body').prepend(alert);
				    setTimeout(function(){
				        $('.alert').fadeOut("slow",function(){
							$(this).remove();
						});
				    },5000);
				}
			}
		});
        
		
		/*if($('#pre-event .checklist-icon').hasClass('fa-circle-o'))     //check if items before start are completed or not. if not show error
        {
            //alert for showing error
            var alert='<div id="alert" class="bs-example" > ' +
                    '<div class="alert alert-danger alert-error"> ' +
                    '<a href="#" class="close" data-dismiss="alert">&times;</a> ' +
                    '<strong>Error!</strong> Cannot start event unless pre event items are completed ' +
                    '</div> ' +
                    '</div>';
            $('body').prepend(alert);
            setTimeout(function()
            {
                $('#alert').remove();
            },5000);
        }
        else            //if completed send start time to database
        {
            $(this).hide();
            var event_id = "";
                    $.post( '/events/'+event_id , {
                        _method: 'PUT',
                        id: event_id,
                        actual_start: moment().format('YYYY-MM-DD HH:mm:ss'),
                        executed_by:"{{Confide::user()->id}}"
                    } , function(data,status,jqxhr){
                        $('#event_start_display').fadeIn();
                        $('#event_started_ago').html(data.actual_start_ago);

                    }, 'json');
        }*/
	});

	$('#end_event_btn').click(function()
    {
		var end_btn = $(this).button('loading');
		$.ajax(
		{
			url:'/tracker/events/'+$(end_btn).attr('event_id')+'/end',
			method:'put',
			contentType:'application/json',
			dataType:'json',
			data:JSON.stringify({}),
			success:function(data)
			{
				var end=data.event['actual_end'];
				if(data.message=="success")
					$(end_btn).parent().html('<h3 class="text-danger text-center">Event ended at '+end.year+'-'+end.monthOfYear+'-'+end.dayOfMonth+'   '+end.hourOfDay+':'+end.minuteOfHour+':'+end.secondOfMinute+'</h3>');
				else
				{
					$(end_btn).button('reset');
					var alert='<div class="alert alert-danger alert-error" role="alert"> ' +
				                    ' <a href="#" class="close" data-dismiss="alert"> &times;</a> ' +
				                    '<strong>Error!</strong> '+data.message  +
				                '</div>';
				    $('body').prepend(alert);
				    setTimeout(function(){
				        $('.alert').fadeOut("slow",function(){
							$(this).remove();
						});
				    },5000);
				}
			}
		});
        
		
		
		
        /*var startdate;
        var event_id = "";
                $.getJSON('/events/'+event_id, {        //get start time for an event
                    type: 'json'
                }, function(data, status, jqxhr) {
                    startdate=data.actual_start_ago;

                    if(startdate=='')       //show alert if event is not yet started
                    {
                        var alert='<div id="alert" class="bs-example" > ' +
                                '<div class="alert alert-danger alert-error"> ' +
                                '<a href="#" class="close" data-dismiss="alert">&times;</a> ' +
                                '<strong>Error!</strong> Complete above Checklist items and start event ' +
                                '</div> ' +
                                '</div>';
                        $('body').prepend(alert);
                        setTimeout(function()
                        {
                            $('#alert').remove();
                        },5000);
                        return;
                    }

                    else    //if event is started
                    {
                        if($('#pre-event .checklist-icon').add($('#during-event .checklist-icon')).hasClass('fa-circle-o') || $('#pre-event .checklist-icon').add($('#during-event .checklist-icon')).hasClass('text-danger'))   //check if there are any completed or skipped items in the event
                        {
                            //show model to enter reason for ending the event
                            var x='<div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true"> ' +
                                    '<div class="modal-dialog"> ' +
                                    '<div class="modal-content"> ' +
                                    '<div class="modal-header"> ' +
                                    '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> ' +
                                    '<h4 class="modal-title" id="exampleModalLabel">some of above items not completed/skipped. Reason for ending:</h4> ' +
                                    '</div> ' +
                                    '<div class="modal-body"> ' +
                                    '<form> ' +
                                    '<div class="form-group"> ' +
                                    '<label for="message-text" class="control-label">Message:</label> ' +
                                    '<textarea class="form-control" id="message-text"></textarea> ' +
                                    '</div> ' +
                                    '</form> ' +
                                    '</div> ' +
                                    '<div class="modal-footer"> ' +
                                    '<button type="button" class="btn btn-default" data-dismiss="modal" id="cancelnote">Cancel</button> ' +
                                    '<button type="button" class="btn btn-primary submitNote" id="submitEventNote">Submit</button> ' +
                                    '</div> ' +
                                    '</div> ' +
                                    '</div> ' +
                                    '</div>';

                            $('body').append(x);

                            $('#modal').modal('show')
                                    .on('click','#submitEventNote',function()    //event on clicking submit button of modal
                                    {
                                        if($.trim($('#modal textarea').val())!="")  //check if text field is modal is empty
                                        {
                                            var event_id = "";
                                                    $.post( '/events/'+event_id , {     //send skipped item note to database
                                                        _method: 'PUT',
                                                        id: event_id,
                                                        actual_end: moment().format('YYYY-MM-DD HH:mm:ss'),
                                                        execution_note:$('#modal textarea').val()
                                                    } , function(data,status,jqxhr){
                                                        $('#event_end_display').fadeIn();
                                                        $('#event_ended_ago').html(data.actual_end_ago);
                                                        $('#modal').modal('hide');
                                                        location.reload();
                                                    }, 'json');
                                        }
                                        else
                                        {
                                            $('#modal textarea').addClass('textbox-error');     //check if text field is modal is empty. if empty display error
                                        }

                                    });
                        }
                        else
                        {
                            $(this).hide();
                            var event_id = ";"
                                    $.post( '/events/'+event_id , {
                                        _method: 'PUT',
                                        id: event_id,
                                        actual_end: moment().format('YYYY-MM-DD HH:mm:ss')
                                    } , function(data,status,jqxhr){
                                        $('#event_end_display').fadeIn();
                                        $('#event_ended_ago').html(data.actual_end_ago);
                                    }, 'json');
                        }
                    }
                });*/

	});
	
	$(document).on('hidden.bs.modal','#myModal', function (e) {
		$(this).unbind();               // removing modal from DOM to avoid caching.
    })

});
	
</script>
</html>