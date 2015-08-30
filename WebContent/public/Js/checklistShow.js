(function() {
	
	$('#attachments').on('click',function(event){
		 event.preventDefault();
		 var modal_title='<span><i class="fa fa-cloud-upload"></i> Upload attachments</span>';
	        $('.modal-title').html(modal_title);
	        
	        var modal_body='<div class="row">'+
								'<div class="col-lg-4 col-md-4 col-sm-4 col-xs 12">'+
									'<button class="btn btn-primary btn-block" id="addFilesBtn"><i class="fa fa-plus"></i> Add files</button>'+
								'</div>'+
								'<div class="col-lg-4 col-md-4 col-sm-4 col-xs 12">'+
									'<button class="btn btn-primary btn-block" id="uploadFilesBtn"><i class="fa fa-upload"></i> Upload files</button>'+
								'</div>'+
								'<div class="col-lg-4 col-md-4 col-sm-4 col-xs 12">'+
									'<button class="btn btn-primary btn-block" id="discardFilesBtn"><i class="fa fa-ban"></i> Discard files</button>'+
								'</div>'+
							'</div>';

	        $('.modal-body').html(modal_body);
	        $('#myModal').modal('show');
	});
	
	$(document).on('click','#addFilesBtn',function(){
		$('#attachedFiles').trigger('click');
	});
	
	$(document).on('click','#uploadFilesBtn',function(){
		$('#submitFiles').trigger('click');
	});
	
})();
