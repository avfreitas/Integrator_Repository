<%@page contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>

<html>
 <head>
    <meta charset="utf-8">
    <title> Login </title>
    <link rel="stylesheet" href="main.css" type="text/css" />
 </head>
<body   bgcolor="#7799FF">
	
	<%= request.getAttribute("dataHoraExecucao") %>
	<br>
	<%= request.getAttribute("msg2") %> 
	<hr>	
	<h2> <%= request.getAttribute("msg1") %> </h2>
	<hr>	
</body>
</html>

