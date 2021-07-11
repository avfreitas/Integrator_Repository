<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.time.LocalDateTime,java.time.format.DateTimeFormatter"%>
	
	
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Catálogo Eletrônico</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>

<body>
	<%
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy   HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		String dataHoraExecucao = "Execução processada em:   " + dtf.format(now); 
	%>
	
	<div class="jumbotron jumbotron-fluid">
		<div class="container">
			<h1>Catálogo Eletrônico - Versão 0.0.1</h1>
			<hr>
			<p>Qualitsys Consultoria de Informática Ltda.</p>
			<p> <% out.print(dataHoraExecucao); %> </p>
			<hr>
			
			<div class="alert alert-dark">
    			<strong>Acesso não permitido!  Necessário Login de Usuário!</strong> 
  			</div>
					
		</div>
	</div>
</body>
</html>
