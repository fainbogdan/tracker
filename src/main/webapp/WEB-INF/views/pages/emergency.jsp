<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="container">
    <div class="page-header">
        <h1>Emergency <small> Create an Emergency event</small></h1>
    </div>
    <div class="row">
        <div class="col-lg-6 col-md-6 col-sm-6">
            <sf:form role="form" modelAttribute="event" method="post">
            	<sf:input type="hidden" path="event_type" name="event_type" value="emergency" />
                <div class="form-group">
                    <sf:label path="expected_start">When it is happening?</sf:label>
                    <div class='input-group date' id='datetimepicker1'>
                        <sf:input path="expected_start" type='text' class="form-control" placeholder="Start Date time of event"/>
                        <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </span>
                    </div>
                     <sf:errors path="expected_start" class="text-danger" />
                </div>
                <div class="form-group">
                    <sf:label path="name">What is happening?</sf:label>
                    <sf:input path="name" type="text" class="form-control" placeholder="Title for this event" 
                    		title="What?" 
                    		data-content="Describe the general event. What is happening? An engine restart? A power outage?" 	
                    		data-toggle="popover" 
                            data-trigger="focus" />
                    <sf:errors path="name" class="text-danger" />
                </div>
                <div class="form-group">
                    <sf:label path="environment">Environment?</sf:label>
                    <sf:input path="environment" type="text" class="form-control" placeholder="Possible effected area" 
                    		title="Where?" 
                    		data-content="Describe what server may be affected or what locations might be impacted" 	
                    		data-toggle="popover" 
                            data-trigger="focus" />
                     <sf:errors path="environment" class="text-danger" />
                </div>
                <div class="form-group">
                    <sf:label path="description">Description?</sf:label>
                    <sf:textarea path="description" rows="3" class="form-control" placeholder="Additional related information"
                    		title="Something more?" 
                    		data-content="Description of actions need to be taken. Affecting patient care? System health in trouble? Etc..." 	
                    		data-toggle="popover" 
                            data-trigger="focus" ></sf:textarea>
                     <sf:errors path="description" class="text-danger" />
                </div>
                <div class="form-group">
                    <div class="col-md-8 col-md-offset-2">
                        <sf:button class="btn btn-danger btn-block btn-lg">Start Emergency Event</sf:button>
                    </div>
                </div>
            </sf:form>
        </div>
        <div class="col-lg-6 col-md-6 col-sm-6">
        	<div class="panel panel-default">
				  <!-- Default panel contents -->
				  <div class="panel-heading"><i class="fa fa-calendar"></i>  Events running today</div>
				  <div class="panel-body">
				    <p>These events may interfere with the event you create. Make sure your event doesn't conflict such environment and timings</p>
				  </div>
	              <ul class="list-group today-emergencies">
	              	<c:choose>
	              		<c:when test="${fn:length(eventsForToday) >0}">
	              			<c:forEach items="${eventsForToday }" var="event">
			                  <li class="list-group-item event pointer" data-event_id='<c:out value="${event.getId() }"></c:out>'>
			                  	<c:choose>
			                  		<c:when test="${event.getEvent_type()=='emergency'}">
										<i class="fa fa-exclamation-triangle emergency-type"></i>
									</c:when>
									<c:otherwise>
										<i class="fa fa-clock-o planned-type"></i>
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

<script src="/tracker/public/Js/emergency.js"></script>
