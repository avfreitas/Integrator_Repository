<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.time.LocalDateTime,java.time.format.DateTimeFormatter"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Alpha Distribuidora de Peças Ltda.</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

<%
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");  
	LocalDateTime now = LocalDateTime.now();  
	String dataHoraExecucao = "Execução processada em: " + dtf.format(now); 
		
%>
	
	<div class="jumbotron jumbotron-fluid">
		<div class="container">
			<h2><strong>Alpha Distribuidora de Auto Peças Ltda.</strong></h2>
			<hr>
			<p>Rua João Gottfritz Filho, 75 - Jardim Regis - São Paulo - SP</p>
			<p>Fones:   11 5662-8252  / 11 3459-7128</p>
			<p>WhatAapp:   11 99766-2438 / 11 93087-5039</p>
			<hr>
			<p><strong>Catálogo Eletrônico de Peças</strong></p>
			<p> <% out.print(dataHoraExecucao); %> </p>
			<hr>
		
			<form class="form-inline" action="LoginServlet"
				method="post">
				<div class="form-group mr-2">
					<label class="sr-only" for="inputEmail">Email</label> 
					<input type="email" class="form-control" name=usuario id="inputEmail"
						placeholder="Email" required>
				</div>
				<div class="form-group mr-2">
					<label class="sr-only" for="inputPassword">Password</label> 
					<input type="password" class="form-control" name=password id="inputPassword"
						placeholder="Password" required>
				</div>
				<button type="submit" class="btn btn-primary">Login</button>
			</form>
			
			<% 
				String msgerro = (String) session.getAttribute("msgerro");
				if (msgerro == null)  
					session.setAttribute("msgerro"," ");
				else if (msgerro.equals("00001") ) 
						out.print("<div class='alert alert-warning' id = 'msgerro' role='alert'> Desculpe!  Usuário ou Senha inválida!</div>");
			%>
		
		</div>
	</div>	
</body>
</html>
