<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="container">
    <div class="page-header">
        <h1>Planned <small> Create an Planned event</small></h1>
    </div>
    <div class="row">
        <div class="col-lg-6 col-md-6 col-sm-6">
            <sf:form role="form" method="post" commandName="event">
        		<sf:input type="hidden" path="event_type" name="event_type" value="planned" />
                <div class="form-group">
                    <label>Start time?</label>
                    <div class='input-group date' id='datetimepicker1'>
                        <sf:input type='text' path="expected_start" class="form-control" placeholder="Start Date time of event"/>
                        <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </span>
                    </div>
                    <sf:errors path="expected_start" class="text-danger"></sf:errors>
                </div>
                <div class="form-group">
                    <label>End time?</label>
                    <div class='input-group date' id='datetimepicker2'>
                        <sf:input type='text' path="expected_end" class="form-control" placeholder="End Date time"/>
                        <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </span>
                    </div>
                    <sf:errors path="expected_end" class="text-danger"></sf:errors>
                </div>
                <div class="form-group">
                    <label>What is happening?</label>
                    <sf:input type="text" path="name" class="form-control" placeholder="Title for this event" 
                    		title="What?" 
                    		data-content="Describe the general event. What is happening? An engine restart? A power outage?" 	
                    		data-toggle="popover" 
                            data-trigger="focus" />
                    <sf:errors path="name" class="text-danger"></sf:errors>
                </div>
                <div class="form-group">
                    <label>Environment?</label>
                    <sf:input type="text" path="environment" class="form-control" placeholder="Possible effected area"
                    		title="Where?" 
                    		data-content="Describe what server may be affected or what locations might be impacted" 	
                    		data-toggle="popover" 
                            data-trigger="focus" />
                    <sf:errors path="environment" class="text-danger"></sf:errors>
                </div>
                <div class="form-group">
                    <label>Description?</label>
                    <sf:textarea rows="6" path="description" class="form-control" placeholder="Additional related information" 
                    		title="Something more?" 
                    		data-content="Description of actions need to be taken. Affecting patient care? System health in trouble? Etc..." 	
                    		data-toggle="popover" 
                            data-trigger="focus" ></sf:textarea>
                    <sf:errors path="description" class="text-danger"></sf:errors>
                </div>
                <div class="form-group">
                    <div class="col-md-8 col-md-offset-2">
                        <button class="btn btn-primary btn-block btn-lg">Create Planned Event</button>
                    </div>
                </div>
            </sf:form>
        </div>
        <div class="col-lg-6 col-md-6 col-sm-6">
        	<div class="panel panel-default">
				  <!-- Default panel contents -->
				  <div class="panel-heading"><i class="fa fa-calendar"></i>  Events in the system</div>
				  <div class="panel-body">
				    <p>These events may interfere with the event you create. Make sure your event doesn't conflict with such environment and timings</p>
				  	<!--calendar goes here -->
            		<div id="events-calendar"></div>
				  </div>
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

<script src="/tracker/public/Js/planned.js"></script>