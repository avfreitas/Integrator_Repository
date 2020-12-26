<%@page contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>

<html>
 <head>
    <meta charset="utf-8">
    <title> Alunos </title>
    <link rel="stylesheet" href="main.css" type="text/css" />
 </head>
<body   bgcolor="#7799FF">
	
	<%= request.getAttribute("dataHoraExecucao") %>
		
	<br>
	<br>
	<hr>	
	<h1>Código do Estudante entrado no sistema: </h1>
	
	<h2> <%= request.getAttribute("codaluno") %> </h2>
	<hr>
</body>
</html>

