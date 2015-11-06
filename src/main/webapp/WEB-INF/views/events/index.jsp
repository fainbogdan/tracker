<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.9/css/dataTables.bootstrap.min.css"/>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="container">
	<div class="page-header">
	  <h1 style="font-size: 36px;">Events <small> <c:out value="${fromDate.toString('MM-dd-yyyy') }" /> - <c:out value="${toDate.toString('MM-dd-yyyy') }" /> </small></h1>
	</div>
	
	<div class="row text-center">
		<div class="col-sm-4">
		  <ul class="pager">
		    <li><a href='?page=<c:out value="${page-1 }" />'><span aria-hidden="true">&larr;</span> Previous week</a></li>
		  </ul>
		</div>
		<div class="col-sm-4">
		    <button tabindex="0" class="btn btn-primary" role="button" data-toggle="popover" data-trigger="click" data-placement="bottom" data-container="body" data-html="true" id="PopS"
		    data-content='
		    <div id="popover-content">
		    <form role="form" method="get">
		        <div class="form-group">
		            <label>Events from?</label>
		            <div class="input-group date" id="datetimepicker1">
		                <input type="text" name="expected_start" class="form-control" placeholder="Start Date time of event" />
		                <span class="input-group-addon">
		                    <span class="glyphicon glyphicon-calendar"></span>
		                </span>
		            </div>
		        </div>
		        <div class="form-group">
		            <label>Events to?</label>
		            <div class="input-group date" id="datetimepicker2">
		                <input type="text" name="expected_end" class="form-control" placeholder="End Date time" />
		                <span class="input-group-addon">
		                    <span class="glyphicon glyphicon-calendar"></span>
		                </span>
		            </div>
		        </div>
		        <div class="form-group">
		            <button class="btn btn-primary btn-block">Search between dates</button>
		        </div>
		    </form>
		    </div>'> <i class="fa fa-calendar"></i> - <i class="fa fa-calendar"></i> </button>
		</div>
		<div class="col-sm-4">
		  <ul class="pager">
		    <li><a href='?page=<c:out value="${page+1 }" />'>Next week <span aria-hidden="true">&rarr;</span></a></li>
		  </ul>
		</div>
	</div>
	
	
	
	
	<table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>Name</th>
                <th>Environment</th>
                <th>Created by</th>
                <th>Expected start</th>
                <th>Expected end</th>
            </tr>
        </thead>
 
        <tbody>
            <c:forEach items="${events }" var="event">
            	<tr>
	                <td><a href='/tracker/events/<c:out value="${event.getId() }" ></c:out> '> <c:out value="${event.getName()}" /> </a></td>
	                <td><c:out value="${event.getEnvironment() }"  default="N/A" /></td>
	                <td><c:out value="${event.getCreator().fullname() }"  default="N/A" /></td>
	                <td><c:out value="${event.getExpected_start().toString('MM-dd-yyyy HH:mm') }" default="N/A" /></td>
	                <td><c:out value="${event.getExpected_end().toString('MM-dd-yyyy HH:mm') }" default="N/A" /></td>
	            </tr>
            </c:forEach>
         </tbody>
    </table>
	
	
	
</div>
 
<script type="text/javascript" src="https://cdn.datatables.net/1.10.9/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.9/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/responsive/1.0.7/js/dataTables.responsive.min.js"></script>
<script src="/tracker/public/Js/eventsIndex.js"></script>