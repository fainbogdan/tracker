<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../static/layout.jsp" %>
<div class="container">
    <div class="page-header">
        <h1>Emergency <small> Create an Emergency event</small></h1>
    </div>
    <div class="row">
        <div class="col-lg-5 col-md-5 col-sm-5">
        	<div class="panel panel-info">
				  <!-- Default panel contents -->
				  <div class="panel-heading">Events running today</div>
				  <div class="panel-body">
				    <p>These events may interfere with the event you create. Make sure your event doesn't fall into such environment and timings</p>
				  </div>
	              <ul class="list-group today-emergencies">
	              	<c:choose>
	              		<c:when test="${fn:length(emergenciesForToday) >0}">
	              			<c:forEach items="${emergenciesForToday }" var="event">
			                  <li class="list-group-item emergency" event_id='<c:out value="${event.getId() }"></c:out>'>
			                  	<c:choose>
			                  		<c:when test="${event.getEvent_type()=='emergency'}">
										<i class="fa fa-exclamation-triangle"></i>
									</c:when>
									<c:otherwise>
										<i class="fa fa-clock-o"></i>
									</c:otherwise>
			                  	</c:choose>
			                  	<c:out value="${event.getName() }" />
			                  </li>
			              </c:forEach>
	              		</c:when>
	              		<c:otherwise>
	              			<li class="list-group-item">No events for today</li>
	              		</c:otherwise>
	              	</c:choose>
	              </ul> 
            </div>
        </div>
        
        <div class="col-lg-7 col-md-7 col-sm-7">
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
                    <div class="col-md-8 col-md-offset-2">
                        <mvc:button class="btn btn-danger btn-block btn-lg">Start Emergency Event</mvc:button>
                    </div>
                </div>
            </mvc:form>
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
    </div>
  </div>
</div>
<!-- Modal -->

</body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script data-require="bootstrap" data-semver="3.3.2" src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.3/moment.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.7.14/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript">

(function()
{
	 $('#datetimepicker1').datetimepicker();
	 
	 $('li.emergency').on('click',function(event)
	 {
		 var event_id=$(this).attr('event_id');
		 event.preventDefault();
		 $.ajax({
			url:'/tracker/events/'+event_id+'/json' ,
			method:'get',
			contentType:'application/json',
			dataType:'json',
			success:function(data)
			{
				var modal_title='<span>'+data.name+'</span>';
		        $('.modal-title').html(modal_title);
		        var exp_start=data.expected_start;
		        var exp_end=data.expected_end;
		        var act_start=data.actual_start;
		        var act_end=data.actual_end;
		        var modal_body='<table class="table table-bordered">'+
					                '<tr><td><b>Event Name</b></td> <td><a href="/tracker/events/'+data.id+'">'+data.name+'</a></td></tr>'+
					                '<tr><td><b>Event description</b></td> <td>'+data.description+'</td></tr>'+
					                '<tr><td><b> Environment</b></td> <td>'+data.environment+'</td></tr>'+
					                '<tr><td><b> expected start</b></td> <td>'+exp_start.year+'-'+exp_start.monthOfYear+'-'+exp_start.dayOfMonth+' &nbsp;&nbsp;&nbsp;'+exp_start.hourOfDay+':'+exp_start.minuteOfHour+':'+exp_start.secondOfMinute+'</td></tr>';
			    
                if(exp_end!=null)
			    	modal_body+='<tr><td><b> Expected end</b></td> <td>'+exp_end.year+'-'+exp_end.monthOfYear+'-'+exp_end.dayOfMonth+' &nbsp;&nbsp;&nbsp;'+exp_end.hourOfDay+':'+exp_end.minuteOfHour+':'+exp_end.secondOfMinute+'</td></tr>';
				if(act_start!=null)
			    	modal_body+='<tr><td><b> Actual start</b></td> <td>'+act_start.year+'-'+act_start.monthOfYear+'-'+act_start.dayOfMonth+' &nbsp;&nbsp;&nbsp;'+act_start.hourOfDay+':'+act_start.minuteOfHour+':'+act_start.secondOfMinute+'</td></tr>';
			    if(act_end!=null)
			    	modal_body+='<tr><td><b> Actual end</b></td> <td>'+act_end.year+'-'+act_end.monthOfYear+'-'+act_end.dayOfMonth+' &nbsp;&nbsp;&nbsp;'+act_end.hourOfDay+':'+act_end.minuteOfHour+':'+act_end.secondOfMinute+'</td></tr>';
			    modal_body+='</table>';

		        $('.modal-body').html(modal_body);
		        $('#myModal').modal('show')
			}
		 });
	 });
})();
</script>
</html>