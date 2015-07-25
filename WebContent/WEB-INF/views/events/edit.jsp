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
	        			<div class='checklistItem' id='<c:out value="${checklist.getId() }" />'> 
	        				<div class="row checklistItem-header">
	        					<div class="col-md-6 item-name">
		        					<c:choose>
		        						<c:when test="${empty checklist.getCompleted()}"><c:out value="${checklist.getName() }" /></c:when>
		        						<c:otherwise><strike><c:out value="${checklist.getName() }" /></strike></c:otherwise>
		        					</c:choose>	
		        				</div>
		        				<div class="col-md-2 text-right">
		        					<c:if test="${empty checklist.getCompleted()}">
			        					<i class="fa fa-lg fa-pencil"></i>
			        				</c:if>
		        				</div>
		        				<div class="col-md-2 text-right">
		        					<c:if test="${empty checklist.getCompleted()}">
			        					<i class="fa fa-lg fa-trash-o"></i>
			        				</c:if>
		        				</div>
		        				<div class="col-md-2 text-right">
			        				<i class="fa fa-lg fa-ellipsis-h"></i>
		        				</div>
	        				</div>
	        				
	        				<div class="row checklistItem-body">
	        					<div class="col-sm-12">
	        						<h5><strong>Deatils:</strong></h5>
	        						<span class="item-details"><c:out value="${checklist.getDetails() }" /></span>
	        					</div>
	        				</div>
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
	        			<div class='checklistItem' id='<c:out value="${checklist.getId() }" />'> 
	        				<div class="row checklistItem-header">
	        					<div class="col-md-6 item-name">
		        					<c:choose>
		        						<c:when test="${empty checklist.getCompleted()}"><c:out value="${checklist.getName() }" /></c:when>
		        						<c:otherwise><strike><c:out value="${checklist.getName() }" /></strike></c:otherwise>
		        					</c:choose>	
		        				</div>
		        				<div class="col-md-2 text-right">
		        					<c:if test="${empty checklist.getCompleted()}">
			        					<i class="fa fa-lg fa-pencil"></i>
			        				</c:if>
		        				</div>
		        				<div class="col-md-2 text-right">
		        					<c:if test="${empty checklist.getCompleted()}">
			        					<i class="fa fa-lg fa-trash-o"></i>
			        				</c:if>
		        				</div>
		        				<div class="col-md-2 text-right">
			        				<i class="fa fa-lg fa-ellipsis-h"></i>
		        				</div>
	        				</div>
	        				
	        				<div class="row checklistItem-body">
	        					<div class="col-sm-12">
	        						<h5><strong>Deatils:</strong></h5>
	        						<span class="item-details"><c:out value="${checklist.getDetails() }" /></span>
	        					</div>
	        				</div>
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
	        			<div class='checklistItem' id='<c:out value="${checklist.getId() }" />'> 
	        				<div class="row checklistItem-header">
	        					<div class="col-md-6 item-name">
		        					<c:choose>
		        						<c:when test="${empty checklist.getCompleted()}"><c:out value="${checklist.getName() }" /></c:when>
		        						<c:otherwise><strike><c:out value="${checklist.getName() }" /></strike></c:otherwise>
		        					</c:choose>	
		        				</div>
		        				<div class="col-md-2 text-right">
		        					<c:if test="${empty checklist.getCompleted()}">
			        					<i class="fa fa-lg fa-pencil"></i>
			        				</c:if>
		        				</div>
		        				<div class="col-md-2 text-right">
		        					<c:if test="${empty checklist.getCompleted()}">
			        					<i class="fa fa-lg fa-trash-o"></i>
			        				</c:if>
		        				</div>
		        				<div class="col-md-2 text-right">
			        				<i class="fa fa-lg fa-ellipsis-h"></i>
		        				</div>
	        				</div>
	        				
	        				<div class="row checklistItem-body">
	        					<div class="col-sm-12">
	        						<h5><strong>Deatils:</strong></h5>
	        						<span class="item-details"><c:out value="${checklist.getDetails() }" /></span>
	        					</div>
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
	
	<div class="done-editing text-center">
		<a href='../<c:out value="${event.getId() }" />' class="btn btn-primary">Done Editing Checklist</a>
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
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script data-require="bootstrap" data-semver="3.3.2" src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(function(){

	$('.sortable').sortable({
	    connectWith: ".connectedSortable",
	    cursor: "move",
	    cancel:'.no-sort',
	    start: function(event, ui) {
	    	$('.checklistItem .fa-times').trigger('click');
	    },
	    update: function (event, ui) {
	        if (this === ui.item.parent()[0])
	        {
	            var order = [], counter = 1;
	            var newOrder=new Object();
	            $('.sortable .checklistItem').each(function () {
	               // order.push($(this).attr('id') + "=" + counter++);
	                newOrder[$(this).attr('id')]=counter++;
	            });
	            
	            $.ajax({
	            	url:'/tracker/checklist/sort',
	            	method:'post',
	            	data:JSON.stringify(newOrder),
	            	success: function(data)
	            	{
	            		
	            	}
	            });
	        }
	    }
	}).disableSelection();
	
	$(document).on('click','.fa-ellipsis-h',function()  //on clicking dots icon zoom the div and show other details of item
    {
		$('.checklistItem .fa-times').trigger('click');
		
        $(this).parents('.checklistItem').addClass('zoom no-sort').animate(         //zoom
                {width: window.innerWidth*0.7},
                {
	                complete:function()
	                {
	                	$(this).find('.fa-ellipsis-h').removeClass('fa-ellipsis-h').addClass('fa-times');
	                    $(this).find('.fa-pencil,.fa-trash-o').hide();
	                	$(this).find('.checklistItem-body').show();
	                }
                }
        );
        $(this).parents('.checklistItem').offset({top:'75',left:window.innerWidth*0.15})
    });

    $('.fa-pencil').on('click',function()          //on click edit checklist item
    {
    	 editingItem=$(this).parents('.checklistItem');                  //get editing items details and place in modal text boxes
        //display modal for editing
        var modal_title='<span>Edit Checklist</span>';
        $('.modal-title').html(modal_title);
        
        var modal_body='<form role="form"> ' +
			               '<div class="form-group"> ' +
				               '<label class="control-label">Item:</label> ' +
				               '<input type="text" class="form-control" id="newName" value="'+$(editingItem).find('.item-name').text().trim()+'"> ' +
				            '</div>'+
				            '<div class="form-group"> ' +
				               '<label for="message-text" class="control-label">Details:</label> ' +
				               '<textarea class="form-control" rows="5" id="newDetails">'+$(editingItem).find('.item-details').text().trim()+'</textarea> ' +
			               '</div> ' +
		               '</form> ';

        $('.modal-body').html(modal_body);
        $('.checklistItem .fa-times').trigger('click');        //while editing zoomout other checklist items, to avoid overlapping
        $('#myModal').modal('show')
                .on('click','#save',function()              //on clicking submit to finish editing
                {
                    $.ajax({                //send updated item values to
                        method:'put',
                        url:'/tracker/checklist/'+$(editingItem).attr('id'),
                        contentType:'application/json',
                        dataType:'json',
                        data:JSON.stringify({name:$('#newName').val(), details:$('#newDetails').val()}),
                        success:function(data)
                        {
                        	$(editingItem).find('.item-name').text(data.name);
                        	$(editingItem).find('.item-details').text(data.details);
                        	$('#myModal').modal('hide');
                        }
                    });
                });
    });

    $(document).on('click','.fa-times',function(e)          //zoom out div to hide details
    {
    	$(this).parents('.checklistItem').find('.checklistItem-body').hide();
        $(this).parents('.checklistItem').find('.fa-pencil,.fa-trash-o').show();          //show icons
      	$(this).removeClass('fa-times').addClass('fa-ellipsis-h'). parents('.checklistItem').css({'width':'','height':'','left':'','top':''}).removeClass('zoom no-sort');
    });

    $(document).on('click','.fa-trash-o',function()         //on click delete
    {
    	deletingItem=$(this).parents('.checklistItem');   
    	$('.checklistItem .fa-times').trigger('click');
    	
        //show modal confirmation before delete
        var modal_title='<span>Delete Checklist</span>';
        $('.modal-title').html(modal_title);
        
        var modal_body='Are you sure? Do you want to delete </br><li>' +$(deletingItem).find('.item-name').text().trim()+ '</li>';
        $('.modal-body').html(modal_body);
        $('#myModal').modal('show')
                .on('click', '#save', function (e) {
                    //send deleted data to database
                    $.ajax({
                        url: '/tracker/checklist/'+$(deletingItem).attr('id'),
                        type:'delete',
                        success: function (data)
                        {
                        	$(deletingItem).remove();     //remove item from UI
                           /* $.ajax({                    //send sorted items order to database
                                type:'PUT',
                                url:'/checklists/1',
                                data:$('form').serialize(),
                                success:function(data)
                                {
                                }
                            });*/
                        }
                    });

                    $('#modal').modal('hide');
                });
    });

    $(document).on('click','.newChecklistItem',function()
    {
        //show modal to enter new item details
         var modal_title='<span>Add Checklist</span>';
        $('.modal-title').html(modal_title);
        
        var modal_body='<form role="form"> ' +
			               '<div class="form-group"> ' +
				               '<label class="control-label">Item:</label> ' +
				               '<input type="text" class="form-control" id="newName"> ' +
				            '</div>'+
				            '<div class="form-group"> ' +
				               '<label for="message-text" class="control-label">Details:</label> ' +
				               '<textarea class="form-control" rows="5" id="newDetails"></textarea> ' +
			               '</div> ' +
		               '</form> ';

        $('.modal-body').html(modal_body);
        
        $('.checklistItem .fa-times').trigger('click');        //zoom out other divs to avoid overlapping
        addToGroup=$(this).parents('.col-sm-4');

        $('#myModal').modal('show')
                .on('click','#addChecklistBtn',function()
                {
                    if($.trim($('#updatedItem').val())!="")         //check if modal values are empty
                    {
                        $(addToGroup).nextAll().find('input[name="order[]"]').each(function()
                        {
                            $(this).val(1+parseInt($(this).val()));         //increase order of items next to new item
                        });

                        //create new item
                        var newOrder=($(addToGroup).find('input[name="order[]"]').length) + ($(addToGroup).prevAll().find('input[name="order[]"]').length) +1;
                        var x="<div class='form-group checklistItem'> " +
                                "<input type='hidden' name='checklistId[]'>"+
                                "<input type='hidden' value='"+$('#updatedItem').val()+"' name='item[]' > " +
                                "<input type='hidden' value='"+newOrder+ "' name='order[]'>"+
                                "<input type='hidden' value='" +$(addToGroup).find('.connectedSortable').attr('phase')+ "' name='phase[]'>"+
                                "<input type='hidden' value='" +$('#updatedNote').val()+"' name='note[]'>" +
                                "<div class='col-sm-6'> " +
                                "<div class='item'>"+$('#updatedItem').val()+"</div>"+
                                "<div class='note' style='display:none;'>"+$('#updatedNote').val()+"</div>"+
                                "</div> " +
                                "<div class='col-sm-2 icon-top-padding'> " +
                                "<i class='fa fa-pencil fa-lg pointer'></i>"+
                                "</div> " +
                                "<div class='col-sm-2 .icon-top-padding'>"+
                                "<i class='fa fa-trash-o fa-lg pointer'></i>"+
                                "</div>"+
                                "<div class='col-sm-2 .icon-top-padding'>"+
                                "<i class='fa fa-ellipsis-h fa-lg pointer'></i>"+
                                "</div>"+
                                "</div>";

                        //add new item to the group from which the add button is clicked
                        var ele=$(x).appendTo($(addToGroup).find('.connectedSortable'));
                        $('#modal').modal('hide');

                        //send new item to database
                        $.ajax({
                            type:'post',
                            url:'/checklists',
                            data:{event_id:"{{$event->id}}",item:ele.find('input[name="item[]"]').val(),order:ele.find('input[name="order[]"]').val(),phase:ele.find('input[name="phase[]"]').val(),note:ele.find('input[name="note[]"]').val()},
                            success:function(data)
                            {
                                $(ele).find('input[name="checklistId[]"]').val(data);
                            }
                        });

                        $('#modal').modal('hide');
                    }
                    else            //if modal values are empty . display as error
                    {
                        $('#updatedItem').addClass('textbox-error');
                    }
                });
    });

    /* $(document).on('hidden.bs.modal','#modal', function () {
        $(this).remove();           //remove modal from DOM on hidden
    })*/

});
</script>
</html>