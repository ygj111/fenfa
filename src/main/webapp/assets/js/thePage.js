$(function(){
//	Li加圆圈
	$(".sidebar .level2 li a").prepend("<i class='fa fa-circle-o'></i>  ");
	
	$(".sidebar").metisMenu({
//		toggle:false
	});
	//sidebar
	$(".sidebar li a").click(function(){
		$(".sidebar li a").removeClass("active");
		$(this).addClass("active");
	});
//便签	
	$("#todolist .icon-group a").click(function(){
		if($(this).hasClass("fa-square-o")){
			$(this).removeClass("fa-square-o").addClass("fa-check-square-o").css({"text-decoration":"line-through","color":"grey"});
		}else{
			$(this).removeClass("fa-check-square-o").addClass("fa-square-o").css({"text-decoration":"none","color":"darkslategrey"});
		}
	});
//scroll
	$(window).scroll(function() {		
		if($(window).scrollTop() >= 120){
			$('#scrollpoint').removeClass("hidden");
//			$('#scrollpoint').fadeIn(200);   //渐显
		}else{    
			$('#scrollpoint').addClass("hidden");
//			$('#scrollpoint').fadeOut(200);  //渐出  
		} 
	});
	$(".goup").click(function(){
		$("html,body").animate({scrollTop:"0px"},500);
	});
	
});

function biu(){
	$(".sidebar").toggle(400);
	$("#showspace").css("margin-left","0px");
}


	//进入echarts页面	
function echartsPage(){
	$("#showspace").html();
	$("#showspace").load("pages/echarts.html");
}

	//进入btable页面	
function btable(){
	$("#showspace").html();
	$("#showspace").load("pages/btable.html");
}
	
	//进入dtable页面	
function dtable(){
	$("#showspace").html();
	$("#showspace").load("pages/datatables.html");
}

	//进入form页面	
function form(){
	$("#showspace").html();
	$("#showspace").load("pages/form.html");
}
	//进入form-upload页面	
function upload(){
	$("#showspace").html();
	$("#showspace").load("pages/form-upload.html");
}
	//进入font-awsome-icon页面	
function icon(){
	$("#showspace").html();
	$("#showspace").load("pages/ui-elements/font-awsome-icon.html");
}
	//进入panels and wells页面	
function paw(){
	$("#showspace").html();
	$("#showspace").load("pages/ui-elements/panels-wells.html");
}
	//进入splitter-trees页面	
function spl(){
	$("#showspace").html();
	$("#showspace").load("pages/ui-elements/splitter-trees.html");
}
	//进入buttons页面	
function button(){
	$("#showspace").html();
	$("#showspace").load("pages/ui-elements/buttons.html");
}
	//进入carousel页面	
function carousel(){
	$("#showspace").html();
	$("#showspace").load("pages/ui-elements/carousel.html");
}

	//进入elements页面	
function blank(){
	$("#showspace").html();
	$("#showspace").load("pages/ui-elements/blank.html");
}


