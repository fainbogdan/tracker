/**
 * 
 */

(function()
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
						 beforeSend: function (xhr) {
	                    	    xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
	                    },
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
				beforeSend: function (xhr) {
					 xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
             	},
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
			beforeSend: function (xhr) {
				 xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
         	},
			data:JSON.stringify({}),
			success:function(data)
			{
				if(data.message=="success")
					$(start_btn).parent().html('<h3 class="text-success text-center">Event started at '+data.event["actual_start"]+'</h3>');
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
			beforeSend: function (xhr) {
				 xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
         	},
			data:JSON.stringify({}),
			success:function(data)
			{
				var end=data.event['actual_end'];
				if(data.message=="success")
					$(end_btn).parent().html('<h3 class="text-danger text-center">Event ended at '+data.event['actual_end']+'</h3>');
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
	});
	
	/*$('.checklist-details').on('click',function()
	{
		 var checklist_id=$(this).attr('event_id');
		 $.ajax({
			url:'/tracker/events/'+event_id+'/json' ,
			method:'get',
			contentType:'application/json',
			dataType:'json',
			success:function(data)
			{
				var modal_title='<span>Checklist details</span>';
		        $('.modal-title').html(modal_title);
		        var modal_body='<table class="table table-bordered">'+
					                '<tr><td><b>Event Name</b></td> <td><a href="/tracker/events/'+data.id+'">'+data.name+'</a></td></tr>'+
					                '<tr><td><b>Event description</b></td> <td>'+data.description+'</td></tr>'+
					                '<tr><td><b> Event type</b></td> <td>'+data.event_type+'</td></tr>'+
					                '<tr><td><b> Environment</b></td> <td>'+data.environment+'</td></tr>'+
					                '<tr><td><b> expected start</b></td> <td>'+data.expected_start+'</td></tr>';
			    
               if(data.expected_end!=null)
			    	modal_body+='<tr><td><b> Expected end</b></td> <td>'+data.expected_end+'</td></tr>';
				if(data.actual_start!=null)
			    	modal_body+='<tr><td><b> Actual start</b></td> <td>'+data.actual_start+'</td></tr>';
			    if(data.actual_end!=null)
			    	modal_body+='<tr><td><b> Actual end</b></td> <td>'+data.actual_end+'</td></tr>';
			    modal_body+='</table>';

		        $('.modal-body').html(modal_body);
		        $('#myModal').modal('show')
			}
		 });
		
        $('.modal-title').html(modal_title);
        
        var modal_body='Are you sure? Do you want to delete </br><li>' +$(deletingItem).find('.item-name').text().trim()+ '</li>';
        $('.modal-body').html(modal_body);
        $('#myModal').modal('show')
	});*/
	
	$(document).on('hidden.bs.modal','#myModal', function (e) {
		$(this).unbind();               // unbing clicks from closed modals
    });

})();
	
