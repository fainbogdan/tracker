/**
 * 
 */


function sort()
{
	var order = [], counter = 1;
    $('.sortable .checklistItem').each(function () {
       var data=new Object();
       data["id"]=$(this).attr('id');
       data["item_order"]=counter++;
       data["phase"]=$(this).parents('.sortable').attr('phase');
       order.push(data);
    });
    
    $.ajax({
    	url:'/tracker/checklist/sort',
    	method:'post',
    	contentType:'application/json',
    	dataType:'json',
    	beforeSend: function (xhr) {
    		 xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
    	},
    	data:JSON.stringify(order),
    });
}
	
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
	            sort();
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

	
    $(document).on('click','.fa-pencil',function()          //on click edit checklist item
    {
    	var editingItem=$(this).parents('.checklistItem');                  //get editing items details and place in modal text boxes
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
                        beforeSend: function (xhr) {
                        	 xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
                    	},
                        data:JSON.stringify({name:$('#newName').val(), details:$('#newDetails').val()}),
                        success:function(data)
                        {
                        	$(editingItem).find('.item-name a').html(data.name);
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
    	var deletingItem=$(this).parents('.checklistItem');   
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
                        beforeSend: function (xhr) {
                        	 xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
                    	},
                    }).done(function(data){
                    	$(deletingItem).remove();     //remove item from UI
                        $('#myModal').modal('hide');
                    	
                    }).done(function(){
                    	sort();
                    });
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
        var addToGroup=$(this).parent().prev('.sortable');

        $('#myModal').modal('show')
                .on('click','#save',function()
                {
                    if($.trim($('#newName').val())!="")         //check if modal values are empty
                    {
                    	//send new item to database
                        $.ajax({
                        	method:'post',
                            url:'/tracker/checklist/',
                            contentType:'application/json',
                            dataType:'json',
                            beforeSend: function (xhr) {
                            	 xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
                        	},
                            data:JSON.stringify({event_id:$(addToGroup).attr('event_id'), name:$('#newName').val(), details:$('#newDetails').val(), phase:$(addToGroup).attr('phase')}),
                            success:function(data)
                            {
                            	var x='<div class="checklistItem" id="'+data.id+'" >'+
		    	        				'<div class="row checklistItem-header">'+
		    	        					'<div class="col-md-6 item-name">'+
		    		        					'<a href="/tracker/checklists/'+data.id+'">'+data.name+'</a>'+
		    	        					'</div>'+
		    		        				'<div class="col-md-2 text-right">'+
		    		        					'<i class="fa fa-lg fa-pencil pointer"></i>'+
		    		        				'</div>'+
		    		        				'<div class="col-md-2 text-right">'+
		    		        					'<i class="fa fa-lg fa-trash-o pointer"></i>'+
		    		        				'</div>'+
		    		        				'<div class="col-md-2 text-right">'+
		    			        				'<i class="fa fa-lg fa-ellipsis-h pointer"></i>'+
		    		        				'</div>'+
		    	        				'</div>'+
		    	        				'<div class="row checklistItem-body">'+
		    	        					'<div class="col-sm-12">'+
		    	        						'<h5><strong>Deatils:</strong></h5>'+
		    	        						'<span class="item-details">'+data.details+'</span>'+
		    	        					'</div>'+
		    	        				'</div>'+
		    	        			'</div>';

			                        //add new item to the group from which the add button is clicked
			                        $(addToGroup).append(x);
			                        sort();
			                        $('#myModal').modal('hide');
                            }
                        });
                    }
                    else            //if modal values are empty . display as error
                    {
                        $('#updatedItem').addClass('textbox-error');
                    }
                });
    });

    $(document).on('hidden.bs.modal','#myModal', function (e) {
		$(this).unbind();               // removing modal from DOM to avoid caching.
    });


});