<%@page import="org.joda.time.Period"%>
<%@page import="org.joda.time.Duration"%>
<%@page import="org.joda.time.LocalDateTime"%>
<%@page import="org.joda.time.DateTime"%>
<%@page import="java.util.Date"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	<div class="container">
		<input type="hidden" name="event_id" value='<c:out value="${event.getId() }"></c:out>'>
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
				<span>
					<message>You are watching this event</message> 
					<c:choose>
						<c:when test="${amIWatching }">
							<i class="fa fa-eye fa-lg watcher-icon pointer" data-status="watching"></i>
						</c:when>
						<c:otherwise>
							<i class="fa fa-eye-slash fa-lg watcher-icon pointer" data-status="nwatching"></i>
						</c:otherwise>
					</c:choose>
				</span>
				<hr/>
				<div class="row">
					<div class="col-md-6">
						<dl>
							<dt><i class="fa fa-cubes"></i>  Environment</dt> <dd><c:out value="${event.getEnvironment() }" /></dd>
							<dt><i class="fa fa-user fa-fw"></i> Created By</dt> <dd><c:out value="${event.getCreator().fullname() }"></c:out> </dd>
							<dt><i class="fa fa-calendar fa-fw"></i> Estimated Start</dt> <dd><c:out value="${event.getExpected_start().toString('MM-dd-yyyy HH:mm:ss') }" /></dd>
							<dt><i class="fa fa-user fa-fw"></i> Estimated End</dt> <dd><c:out value="${event.getExpected_end().toString('MM-dd-yyyy HH:mm:ss') }" /></dd>
							<dt><i class="fa fa-clock-o fa-fw"></i> Estimated Duartion</dt> 
							<dd>
								<%
									try{
										LocalDateTime exp_start=new LocalDateTime(pageContext.getAttribute("expected_start"));
										LocalDateTime exp_end=new LocalDateTime(pageContext.getAttribute("expected_end"));
										Period exp_period=new Period(exp_start,exp_end);
										out.print(exp_period.toStandardMinutes().getMinutes()/24/60 + " days " + exp_period.toStandardMinutes().getMinutes()/60%24 + " hours " + exp_period.toStandardMinutes().getMinutes()%60+" minutes");
									}catch(Exception e){
										out.print("Long event");
									}
									
								%>
							 </dd>
						</dl>
					</div>
					<div class="col-md-6">
						<dl>
							<dt><i class="fa fa-user fa-fw"></i> Started By</dt> <dd><c:out value="${event.getExecuter().fullname() }" >N/A</c:out></dd>
							<dt><i class="fa fa-calendar fa-fw"></i> Actual Start</dt> <dd><c:out value="${event.getActual_start().toString('MM-dd-yyyy HH:mm:ss') }" >N/A</c:out></dd>
							<dt><i class="fa fa-calendar fa-fw"></i> Actual End</dt> <dd><c:out value="${event.getActual_end().toString('MM-dd-yyyy HH:mm:ss') }" >N/A</c:out></dd>
							<dt><i class="fa fa-clock-o fa-fw"></i> Actual Duration</dt> 
							<c:set var="actual_start" value="${event.getActual_start() }"></c:set>
							<c:set var="actual_end" value="${event.getActual_end() }"></c:set>
							<dd>
								<%
									try{
										LocalDateTime act_start=new LocalDateTime(pageContext.getAttribute("actual_start"));
										LocalDateTime act_end=new LocalDateTime(pageContext.getAttribute("actual_end"));
										Period act_period=new Period(act_start,act_end);
										out.print(act_period.toStandardMinutes().getMinutes()/24/60 + " days " + act_period.toStandardMinutes().getMinutes()/60%24 + " hours " + act_period.toStandardMinutes().getMinutes()%60+" minutes");
									}catch(Exception e){
										out.print("Long event");
									}
								%>
							 </dd>
						</dl>
					</div>
				</div>
				<hr/>
				<div>
					<dl>
						<dt><i class="fa fa-info-circle"></i> Description</dt>
						<dd><c:out value="${event.getDescription() }" /></dd>
					</dl>
				</div>
			</div>
			
			<div class="col-md-6">
				<h2>Run Plan
				<a href='<c:out value="${event.getId() }" />/edit'><span class="pull-right"><i class="fa fa-pencil-square-o edit-checklist-icon"></i></span></a>
				</h2>
				<div id="pre-event">
					<h3>Pre-Event Checklist</h3>
					<ul class="list-group">
						<c:forEach items="${event.sortedChecklist() }" var="checklist">
							<c:if test="${checklist.getPhase()=='setup' }">
								<li class="list-group-item" checklist-id='<c:out value="${checklist.getId() }" />'>
									<c:choose>
										<c:when test="${checklist.getCompleted() eq 'Y' }">
											<i class='fa fa-check-circle fa-2x fa-fw checklist-icon pointer checklist-<c:out  value="${checklist.getId() }"></c:out> '></i>
										</c:when>
										<c:when test="${checklist.getCompleted() eq 'N' }">
											<i class='fa fa-times-circle fa-2x fa-fw checklist-icon pointer checklist-<c:out  value="${checklist.getId() }"></c:out> '></i>
										</c:when>
										<c:otherwise>
											<i class='fa fa-circle-o fa-2x fa-fw checklist-icon pointer checklist-<c:out  value="${checklist.getId() }"></c:out> '></i>
										</c:otherwise>
									</c:choose>
									<a href='/tracker/checklists/<c:out value="${checklist.getId() }" />'><c:out value="${checklist.getName() }" /></a>
								</li>
							</c:if>
						</c:forEach>
					</ul>
				</div>
				
				<div id="start-event text-center">
					<c:choose>
						<c:when test="${empty event.getActual_start() }">
							<button class="btn btn-success btn-lg btn-block" id="start_event_btn" data-loading-text="Starting event..." event_id='<c:out value="${event.getId()}"></c:out>' >Start Event</button>
						</c:when>
						<c:otherwise>
							<h3 class="text-success">Event started at <c:out value="${event.getActual_start().toString('MM-dd-yyyy HH:mm:ss') }" /></h3>
						</c:otherwise>
					</c:choose>
				</div>
				
				<div id="during-event">
					<h3>During-Event Checklist</h3>
					<ul class="list-group">
						<c:forEach items="${event.sortedChecklist() }" var="checklist">
							<c:if test="${checklist.getPhase()=='execute' }">
								<li class="list-group-item" checklist-id='<c:out value="${checklist.getId() }" />'>
									<c:choose>
										<c:when test="${checklist.getCompleted() eq 'Y' }">
											<i class='fa fa-check-circle fa-2x fa-fw checklist-icon pointer checklist-<c:out  value="${checklist.getId() }"></c:out> '></i>
										</c:when>
										<c:when test="${checklist.getCompleted() eq 'N' }">
											<i class='fa fa-times-circle fa-2x fa-fw checklist-icon pointer checklist-<c:out  value="${checklist.getId() }"></c:out> '></i>
										</c:when>
										<c:otherwise>
											<i class='fa fa-circle-o fa-2x fa-fw checklist-icon pointer checklist-<c:out  value="${checklist.getId() }"></c:out> '></i>
										</c:otherwise>
									</c:choose>
									<a href='/tracker/checklists/<c:out value="${checklist.getId() }" />'><c:out value="${checklist.getName() }" /></a>
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
							<h3 class="text-danger text-center">Event ended at <c:out value="${event.getActual_end().toString('MM-dd-yyyy HH:mm:ss') }" /></h3>
						</c:otherwise>
					</c:choose>
				</div>
				
				<div id="post-event">
					<h3>Post-Event Checklist</h3>
					<ul class="list-group">
						<c:forEach items="${event.sortedChecklist() }" var="checklist">
							<c:if test="${checklist.getPhase()=='teardown' }">
								<li class="list-group-item" checklist-id='<c:out value="${checklist.getId() }" />'>
									<c:choose>
										<c:when test="${checklist.getCompleted() eq 'Y' }">
											<i class='fa fa-check-circle fa-2x fa-fw checklist-icon pointer checklist-<c:out  value="${checklist.getId() }"></c:out> '></i>
										</c:when>
										<c:when test="${checklist.getCompleted() eq 'N' }">
											<i class='fa fa-times-circle fa-2x fa-fw checklist-icon pointer checklist-<c:out  value="${checklist.getId() }"></c:out> '></i>
										</c:when>
										<c:otherwise>
											<i class='fa fa-circle-o fa-2x fa-fw checklist-icon pointer checklist-<c:out  value="${checklist.getId() }"></c:out> '></i>
										</c:otherwise>
									</c:choose>
									<a href='/tracker/checklists/<c:out value="${checklist.getId() }" />'><c:out value="${checklist.getName() }" /></a>
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
<script src="/tracker/public/Js/eventShow.js"></script>