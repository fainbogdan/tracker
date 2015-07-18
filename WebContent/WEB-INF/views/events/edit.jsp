<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.7.14/css/bootstrap-datetimepicker.min.css">
	<link rel="stylesheet" href="/tracker/public/css/home-styles.css">
</head>
<body>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../static/layout.jsp" %>
<div class="container">
	<div class="row">
	    <div class="col-md-4">
	        <h3>Setup phase</h3>
	        <div id="sortable1" class="sortable connectedSortable">
	        	<c:forEach items="${event.getChecklist() }" var="checklist">
	        		<c:if test="${checklist.getPhase()=='setup' }">
	        			<div class='row checklistItem' id='<c:out value="${checklist.getId() }" />'> 
	        				<div class="col-md-8"><c:out value="${checklist.getName() }" /></div>
	        				<c:if test="${empty checklist.getCompleted()}">
	        					<div class="col-md-1"><i class="fa fa-lg fa-pencil"></i></div>
	        					<div class="col-md-1"><i class="fa fa-lg fa-trash-o"></i></div>
	        				</c:if>
	        				<div class="col-md-1"><i class="fa fa-lg fa-ellipsis-h"></i></div>
	        			</div>
	        		</c:if>
	        	</c:forEach>
	        </div>
	        <div class="addChecklistDiv">
	            <input type="button" class="btn btn-info btn-xs newChecklistItem" value="Add Checklist Item">
	        </div>
	    </div>
	    <div class="col-md-4">
	        <h3>execute phase</h3>
	        <div id="sortable2" class="sortable connectedSortable">
	            <c:forEach items="${event.getChecklist() }" var="checklist">
	        		<c:if test="${checklist.getPhase()=='execute' }">
	        			<div class='row checklistItem' id='<c:out value="${checklist.getId() }" />'> 
	        				<div class="col-md-8"><c:out value="${checklist.getName() }" /></div>
	        				<c:if test="${empty checklist.getCompleted()}">
	        					<div class="col-md-1"><i class="fa fa-lg fa-pencil"></i></div>
	        					<div class="col-md-1"><i class="fa fa-lg fa-trash-o"></i></div>
	        				</c:if>
	        				<div class="col-md-1"><i class="fa fa-lg fa-ellipsis-h"></i></div>
	        			</div>
	        		</c:if>
	        	</c:forEach>
	        </div>
	        <div class="addChecklistDiv">
	            <input type="button" class="btn btn-info btn-xs newChecklistItem" value="Add Checklist Item">
	        </div>
	    </div>
	    <div class="col-md-4">
	        <h3>teardown phase</h3>
	        <div id="sortable3" class="sortable connectedSortable">
	           <c:forEach items="${event.getChecklist() }" var="checklist">
	        		<c:if test="${checklist.getPhase()=='teardown' }">
	        			<div class='row checklistItem' id='<c:out value="${checklist.getId() }" />'> 
	        				<div class="col-md-6">
	        					<c:choose>
	        						<c:when test="${empty checklist.getCompleted()}"><c:out value="${checklist.getName() }" /></c:when>
	        						<c:otherwise><strike><c:out value="${checklist.getName() }" /></strike></c:otherwise>
	        					</c:choose>	
	        				</div>
	        				<div class="col-md-2">
	        					<c:if test="${empty checklist.getCompleted()}">
		        					<i class="fa fa-lg fa-pencil"></i>
		        				</c:if>
	        				</div>
	        				<div class="col-md-2">
	        					<c:if test="${empty checklist.getCompleted()}">
		        					<i class="fa fa-lg fa-trash-o"></i>
		        				</c:if>
	        				</div>
	        				<div class="col-md-2">
		        				<i class="fa fa-lg fa-ellipsis-h"></i>
	        				</div>
	        			</div>
	        		</c:if>
	        	</c:forEach>
	        </div>
	        <div class="addChecklistDiv">
	            <input type="button" class="btn btn-info btn-xs newChecklistItem" value="Add Checklist Item">
	        </div>
	    </div>
	</div>
</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script type="text/javascript">
$(function(){

	$('.sortable').sortable({
	    connectWith: ".connectedSortable",
	    cursor: "move",
	    update: function (event, ui) {
	        if (this === ui.item.parent()[0])
	        {
	            var order = [],
	            counter = 1;
	            $('.sortable div').each(function () {
	                order.push($(this).attr('id') + "=" + counter++);
	            });
	           // alert(order.join('&'));
	            console.log(order.join('&'));
	        }
	    }
	}).disableSelection();

});
</script>
</html>