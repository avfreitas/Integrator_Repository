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
	<%!@SuppressWarnings("unchecked")%>
	<%
	 
	 //	if (session.getAttribute("usuario") == null)
	 //	getServletContext().getRequestDispatcher("/jsperrologin.jsp").forward(request, response);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy   HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String dataHoraExecucao = "Execução processada em:   " + dtf.format(now);

		String format1 = "<option style=\"background: #5cb85c; color: #fff;\" value = \"";
		String format2 = "\">";
		String format3 = "</option>";
	%>

	<div class="jumbotron  jumbotron-fluid">
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
			<p><% out.print("Usuário logado: <b> <font color=\"blue\">" + session.getAttribute("usuario") + "</b>");%></p>
			<hr></div>
						
		<hr>
		<div class="container">
			<p><strong> <font color=blue></font>Entre com o código do item:</strong></p>		
					<nav class="navbar navbar-light bg-light">
  						<div class="container-fluid">
  							
    						<form class="d-flex" action="controller03">
       							<input class="form-control me-2" type="search" placeholder="Digite aqui o item" aria-label="Search"  name="coditem" id="coditem">
      							<button class="btn btn-outline-success" type="submit">Submit</button>
    						</form></div>
    						
					</nav></div>
	 
		<hr>		
	 	<div class="container"> 
					<a class="btn btn-primary" href="jspmain.jsp" role="button">Retorna</a>
					<hr><br>
					<a class="btn btn-danger" href="jspfim.jsp" role="button">Logout</a></div>	
    </div>
</body>
</html>
