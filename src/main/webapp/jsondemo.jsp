<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Jquery Json Demo</title>
    <<link href="bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
	<script src="jquery/1.11.3/jquery-1.11.3.min.js"></script>
	<script src="bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
    <p><label>演示</label></p>
    <button id="but1" type="button" class="btn">Json解析</button>
    <div id="div1">

    </div>
</body>
<script language="JavaScript">
	
	$("#but1").click(function() {
		console.log("hello world!");
		$.get("http://localhost:8080/3hfund/exp/wf", function(data) {
			$.each(data, function(index) {
	            alert(data[index].displayName);
	        });
		})
	})

</script>

</html>