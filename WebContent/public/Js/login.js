/**
 * 
 */

(function() {
	
	$('#loginBtn').on('click',function(){
		$.ajax({
			url:'',
			method:'GET',
			contentType:'application/json',
			datatype:'json',
			beforeSend: function (xhr) {
	    		 xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
	    	},
			data:json.stringify({'username':$('#username').val() }),
			success:function(data){
				console.log(data);
			}
		});
	});
	
})();