/**
 * 
 */

(function()
{
	$(document).on('click','.checklist-icon',function()
	{
		var updatedIcon=$(this);
		var goToState;
		if($(updatedIcon).hasClass('fa-check-circle'))
			goToState='N';
		else
			goToState='Y';
		
		//if checking an item as done
		if(goToState=='N')
		{
			var modal_title='Why you want to skip this item?';
			$('#myModal .modal-title').html(modal_title);
			var modal_body='<form> ' +
					            '<div class="form-group"> ' +
						            '<label for="message-text" class="control-label">Enter your note here</label> ' +
						            '<textarea rows="5" class="form-control" id="skipNote"></textarea> ' +
					            '</div> ' +
				            '</form> ';
			$('#myModal .modal-body').html(modal_body);
			$('#myModal').modal('show').on('click','#save',function()
			{
				//if user entered skip note
				if($('#skipNote').val().trim().length)
				{
					$('#myModal').modal('hide');
					$(updatedIcon).addClass('fa-spinner fa-spin');
					$.ajax(
					{
						url:'/tracker/checklistState/'+$(updatedIcon).parents('li').attr('data-checklist-id'),
						method:'put',
						contentType:'application/json',
						datatype:'json',
						 beforeSend: function (xhr) {
	                    	    xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
	                    },
						error: function (xhr, ajaxOptions, thrownError) {
							if(xhr.status==403){	//if unauthorized, remove spinner and show alert
								$(updatedIcon).removeClass('fa-spinner fa-spin');
								systemFailureAlert("Sorry! Unauthorized to work on this. Contact Manager ");
			    	        }
			    	    },
						data:JSON.stringify({id:$(updatedIcon).parents('li').attr('data-checklist-id'),"completed":goToState, skipped_note:$('#skipNote').val().trim()}),
						success:function(data)
						{		//if success. change item state and show alert
							var checklist=data.checklist;
							changeChecklistState(checklist.id, checklist.completed);
							systemSuccessAlert("All changes made to this event committed successfully");
						}
					});
				}
				else{
					//if user did not enter skip note
					$('#skipNote').parents('.form-group').addClass('has-error');
				}
			});
		}
		else	//if unchecking an item
		{
			$(updatedIcon).addClass('fa-spinner fa-spin');
			$.ajax(
			{
				url:'/tracker/checklistState/'+$(updatedIcon).parents('li').attr('data-checklist-id'),
				method:'put',
				contentType:'application/json',
				datatype:'json',
				beforeSend: function (xhr) {
					 xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
             	},
       	      	error: function (xhr, ajaxOptions, thrownError) {
					if(xhr.status==403){	//if unauthorized, remove spinner and show alert
						$(updatedIcon).removeClass('fa-spinner fa-spin');
						systemFailureAlert("Sorry! Unauthorized to work on this. Contact Manager ");
	    	        }
	    	    },
				data:JSON.stringify({id:$(updatedIcon).parents('li').attr('data-checklist-id'),"completed":goToState}),
				success:function(data)
				{
					var checklist=data.checklist;
					if(data.message=="success")
					{
						changeChecklistState(checklist.id, checklist.completed);
						systemSuccessAlert("All changes made to this event committed successfully");
					}
					else{
						$(updatedIcon).removeClass('fa-spinner fa-spin');
						systemFailureAlert(data.message);
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
			url:'/tracker/events/'+$(start_btn).attr('data-event_id')+'/start',
			method:'put',
			contentType:'application/json',
			dataType:'json',
			beforeSend: function (xhr) {
				 xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
         	},
			data:JSON.stringify({id:$(start_btn).attr('data-event_id')}),
			error: function (xhr, ajaxOptions, thrownError) {
				if(xhr.status==403){	//if unauthorized, remove spinner and show alert
					$(start_btn).button('reset');
					systemFailureAlert("Sorry! Unauthorized to work on this. Contact Manager ");
    	        }
    	    },
			success:function(data)
			{
				if(data.message=="success")
					$(start_btn).parent().html('<h3 class="text-success text-center">Event started at '+data.event["actual_start"]+'</h3>');
				else
				{
					$(start_btn).button('reset');
					systemFailureAlert(data.message);
				}
			}
		});
	});

	$('#end_event_btn').click(function()
    {
		var end_btn = $(this).button('loading');
		$.ajax(
		{
			url:'/tracker/events/'+$(end_btn).attr('data-event_id')+'/end',
			method:'put',
			contentType:'application/json',
			dataType:'json',
			beforeSend: function (xhr) {
				 xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
         	},
         	error: function (xhr, ajaxOptions, thrownError) {
					if(xhr.status==403){	//if unauthorized, remove spinner and show alert
						$(end_btn).button('reset');
						systemFailureAlert("Sorry! Unauthorized to work on this. Contact Manager ");
	    	        }
    	    },
			data:JSON.stringify({id:$(end_btn).attr('data-event_id')}),
			success:function(data)
			{
				var end=data.event['actual_end'];
				if(data.message=="success")
					$(end_btn).parent().html('<h3 class="text-danger text-center">Event ended at '+data.event['actual_end']+'</h3>');
				else
				{
					$(end_btn).button('reset');
					systemFailureAlert(data.message);
				}
			}
		});
	});
	
	
	function systemSuccessAlert(message){
    	var alert='<div class="alert alert-success" role="alert"> ' +
				        '<a href="#" class="close" data-dismiss="alert">&times;</a> ' +
				        '<strong>'+message+'</strong>'+
				    '</div>';
		$('body').prepend(alert);
		alertTimeOut();
	}
	
	function systemFailureAlert(message){
    	var alert='<div class="alert alert-danger alert-error" role="alert"> ' +
				        '<a href="#" class="close" data-dismiss="alert">&times;</a> ' +
				        '<strong>'+message+'</strong>'+
				    '</div>';
		$('body').prepend(alert);
		alertTimeOut();
	}
	
	function alertTimeOut() {
		setTimeout(function(){
			$('.alert').fadeOut("slow",function(){
				$(this).remove();
			});
		},5000);
	}
	
	
	function changeChecklistState(id,state) {
		var icon = $('.checklist-'+id);

		icon.removeClass('fa-spinner fa-spin fa-check-circle fa-circle-o fa-times-circle text-success text-danger');

		if (state == 'Y') {
			icon.addClass('fa-check-circle text-success');
		} else if (state == 'N') {
			icon.addClass('fa-times-circle text-danger');
		} else {
			icon.addClass('fa-circle-o');
		}
	}
	
	setInterval(function() {
		$.ajax({
			url:'/tracker/events/'+$('input[name="event_id"]').val()+'/json',
			method:'get',
			dataType:'json',
			beforeSend: function (xhr) {
				 xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
         	},
			success:function(event)
			{
				console.log(event.actual_start);
				if(event.actual_start!=null)
					$('#start-event').html('<h3 class="text-success text-center">Event started at '+event.actual_start+'</h3>');
				if(event.actual_end!=null)
					$('#end-event').html('<h3 class="text-danger text-center">Event ended at '+event.actual_end+'</h3>');
					
				var checklists=event.checklist;
				$(checklists).each(function(index,checklist) {
					changeChecklistState(checklist.id, checklist.completed);
				});
			}
		});
		
	}, 5000);
	
	$(document).on('hidden.bs.modal','#myModal', function (e) {
		$(this).unbind();               // unbing clicks from closed modals
    });
	
	
	$(document).on('click','.watcher-icon',function(){
		var icon=$(this);
		var url="";
		if( $(this).attr('data-status')=="nwatching" )
			url="/addWatcher";
		else
			url="/removeWatcher";
		
		$.ajax({
			url:'/tracker/events/'+$('input[name="event_id"]').val()+url,
			method:'post',
			dataType:'json',
			beforeSend: function (xhr) {
				 xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
         	},
			success:function(status)
			{
				if(status==true){
					$(icon).prev('message').text("You are watching this event");
					$(icon).removeClass('fa-eye fa-eye-slash').addClass('fa-eye');
					$(icon).attr('data-status','watching');
				}
				else if(status==false){
					$(icon).prev('message').text("You are not watching this event");
					$(icon).removeClass('fa-eye fa-eye-slash').addClass('fa-eye-slash');
					$(icon).attr('data-status','nwatching');
				}
			}
		});
	});
	

})();
	
