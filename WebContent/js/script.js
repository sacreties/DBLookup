$(function(){ 							   

	// radiys box
	$('.sb_menu li a, .ex_menu li a').css({"border-radius": "15px", "-moz-border-radius":"15px", "-webkit-border-radius":"15px"});
	$('.mainbar .spec a').css({"border-radius": "10px", "-moz-border-radius":"10px", "-webkit-border-radius":"10px"});
	$('.pagenavi a, .pagenavi .current').css({"border-radius": "5px", "-moz-border-radius":"5px", "-webkit-border-radius":"5px"});
	
});	

$(document).ready(function(){
	/*$("#add").click(function(){
		alert("qwerty");
		var add1 = "+"<input  type="text" name="pan">"+";
		$("#appnd").append(add1);
		
	});*/
/*	 mrchntId = $("#merchantId").val();
	panId = $("#pan").val();
	checked = $("#status").is("checked");*/
	
	
	/*selectall function*/
	$('#generate_table').on('change','#selectall',function() {

				$('#generate_table th').find(':checkbox');
				if ($(this).is(':checked')) {
					$('#generate_table tr').find(':checkbox').prop('checked', true);
				} else {
					$('#generate_table tr').find(':checkbox').prop('checked', false);
				}
			});
	
	
	
	
	$("#button").on("click", function(){
		
		  var mid  = $("#merchantId").val();
          var fid = $("#filterId").val();
          var fname = $("#filterName").val();
          var enable = $("#enabled").is(":checked");
          var pan = $("#pan").val();
          var bool = $("#status").is(":checked");

          jdata = {
                          "merchantId": mid,
                          "filterId" : fid,
                          "filterName": fname,
                          "enabled" : enable,
                          "pan" : pan,
                          "status" : bool
          };

          objdata = JSON.stringify(jdata);
          console.log(objdata)
		
		
		/*Write inside sucess ajax call*/
		var checking;
		var stat = $("#status").is(":checked");
		if(stat){
			checking = "allowed";
		}else{
			checking = "blocked";
		}
		
		var appnd = "<tbody><tr style = text-align:center><td ><input class="+'chbox'+" type = "+' checkbox'+"\></td><td>"+$("#merchantId").val()+"</td><td>"+$("#pan").val()+"</td><td class="+'check'+">"+checking+"</td></tr>,</tbody>";
		$("#generate_table").append(appnd);
		
		
		/*Example Ajax Call send or recieve data from Backend*/
	/*	var url = "../WebToolGS/rest/report/get";
		$
				.ajax(
						{
							type : "GET",
							dataType : "json",
							contentType : "application/json",
							url : url,
							crossDomain : true,
							statusCode : {
								200 : function(data) {
									console.log(data);
									var html = "";
									var res = '{"response":'+ data+ '}';
									// var
									// jString =
									// JSON.stringify(res);
									var jobj = JSON.parse(res);

									for ( var i = 0; i < jobj.response.length; i++) {
										iFrameurl = jobj.response[i].url;
										alert(iFrameurl);
										html += "<li class= 'link' style='margin-left:10%; text-decoration: underline; color:blue; cursor:pointer; margin-bottom: 5%;'>"+jobj.response[i].testcaseName+"#"+ jobj.response[i].buildId
												+ "</li>";
									}
									console.log(html);
									$("#left_sidebar ul").html(html);
								}
							}
						})
				.error(
						function(xhr, errorText) {
							alert(errorText);

						});*/
		
	});
	
	
	$("#del_button").on("click", function(){
		
		selectedRows = {};
		console.log($(this).index());
		$(".chbox:checked").each(function() {
			console.log($(this).index());
		});
		/*
			
			
			 
			
			console.log($(this).index());
			var chkVal = $(this).val();
			selectedRows[chkVal] = $(this).parent().parent().index();*/
		
	});
	
	$("#block_button").on("click", function(){
	
		
		/*Write inside sucess ajax call*/
		$('.chbox:checked').each(function(){
			var row = $(this).parent().parent();
			var check_text = row.find('td.check');
			if (check_text.text() == "blocked"){
				check_text.text("allowed");
			}
				else{
					check_text.text("blocked");
				}		
		});
		
		
		
		/*Example Ajax Call send or recieve data from Backend*/
		/*	var url = "../WebToolGS/rest/report/get";
			$
					.ajax(
							{
								type : "GET",
								dataType : "json",
								contentType : "application/json",
								url : url,
								crossDomain : true,
								statusCode : {
									200 : function(data) {
										console.log(data);
										var html = "";
										var res = '{"response":'+ data+ '}';
										// var
										// jString =
										// JSON.stringify(res);
										var jobj = JSON.parse(res);

										for ( var i = 0; i < jobj.response.length; i++) {
											iFrameurl = jobj.response[i].url;
											alert(iFrameurl);
											html += "<li class= 'link' style='margin-left:10%; text-decoration: underline; color:blue; cursor:pointer; margin-bottom: 5%;'>"+jobj.response[i].testcaseName+"#"+ jobj.response[i].buildId
													+ "</li>";
										}
										console.log(html);
										$("#left_sidebar ul").html(html);
									}
								}
							})
					.error(
							function(xhr, errorText) {
								alert(errorText);

							});*/
		
	});
	
	
	$("#del_button").on("click", function(){
	
		
		/*Write inside sucess ajax call*/
		$('.chbox:checked').each(function(){
			//var row = $(this).parent().parent();
			$(this).closest("tr").remove();
			
		});
		
		/*write ajax call here*/
	});
	
	$("#edit_button").on("click", function(){
		alert("qwerty");
		if($("#selectall:checked")){
			alert("User cannot edit mutiple rows");
		}
	});
	
	
});