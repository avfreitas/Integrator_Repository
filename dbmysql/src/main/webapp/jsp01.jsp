<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.time.LocalDateTime,java.time.format.DateTimeFormatter"%>
	
	
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Politécnica - Ocorrências</title>
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
			<h1>USCS - Escola Politécnica - Sistema de Ocorrências</h1>
			<hr>
			<p>Qualitsys Consultoria de Informática Ltda.</p>
			<p> <% out.print(dataHoraExecucao); %> </p>
			<hr>
		</div>
		
		<div class="container">
		
			<div class="p-3 mb-2 bg-success text-white"> 
				<h6>Código do Aluno:  <b>${aluno.RA}</b></h6>
				<h6>Nome: <b>${aluno.nome}</b></h6>
			</div>
			
			<div class="p-3 mb-2 bg-secondary text-white"> 
				<p>Dígito: ${aluno.digito}</p>
				<p>CPF: ${aluno.cpf}</p>
				<p>Turma: ${aluno.turma}</p>
				<p>Grupo: ${aluno.grupo}</p>
				<p>Curso: ${aluno.curso}</p>
				<p>Emailpessoal: ${aluno.emailpessoal}</p>
				<p>Emailgoogle: ${aluno.emailgoogle}</p>
				<p>Fone1: ${aluno.fone1}</p>
				<p>Fone2: ${aluno.fone2}</p>
				<p>Semestre: ${aluno.semestre}</p>
			</div>
				
		</div>
	</div>
	
	</body>
</html>

