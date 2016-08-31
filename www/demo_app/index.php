<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Ruhrpott Workshop - App</title>

		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="/burp-103/demo_app/css/bootstrap.min.css">
		<!-- Bootstrap Theme -->
		<link rel="stylesheet" href="/burp-103/demo_app/css/bootstrap-theme.min.css">
		<!-- Custome CSS -->
		<link rel="stylesheet" href="/burp-103/demo_app/css/index.css">

		<!-- jQuery -->
		<script src="/burp-103/demo_app/js/jquery.min.js"></script>
		<!-- Bootstrap JavaScript -->
		<script src="/burp-103/demo_app/js/bootstrap.min.js"></script>
		<script type="text/javascript">
			function execute_payload_php(){
				window.location.assign("/burp-103/demo_app/?payload="+$('#payload_php').val());
			};
		</script>
	</head>
	<body>
		<div class="jumbotron vertical-center">
			
			<div class="container">
				<!--PHP reflection-->
				<div class="row">
					<h1>DEMO APP</h1>
					 <div class="input-group">
				      <input type="text" class="form-control" placeholder="Payload here!" autofocus id="payload_php">
				      <span class="input-group-btn">
				        <button class="btn btn-danger" type="button" aria-label="Execute" onclick="execute_payload_php()">
				        	<span class="glyphicon glyphicon-console" aria-hidden="true"></span>
				        </button>
				      </span>
					</div>
				</div>
				<!-- The payload will be reflected in here.-->
				<div id="execute_here">
					<?php
						if (isset($_GET["payload"])) {
							if(base64_decode($_GET["payload"],true)){
								echo "<!--DECODE-->\n<h3>Output:</h3>\n<p class='payload'>" . base64_decode($_GET["payload"]) . "</p>\n";
							} else {
								echo "<!--ENCODE-->\n<h3>Output:</h3>\n<p class='payload'>" . base64_encode($_GET["payload"]) . "</p>\n";
							}
						} else {
							echo "<!--No output-->\n";
						}
					?>
				</div>
			</div>
		</div>
			<div class="media">
				<a class="pull-right" href="/burp-103/slides/#/1">
					<h1><span class="glyphicon glyphicon-arrow-right" aria-hidden="true"></span></h1>
				</a>
			</div>
	</body>
</html>