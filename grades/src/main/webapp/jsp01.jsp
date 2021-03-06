<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.time.LocalDateTime,java.time.format.DateTimeFormatter"%>
	
	
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Politécnica - Grades</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>

<body>
	<%
		if(session.getAttribute("usuario") == null)
			getServletContext().getRequestDispatcher("/jsperrologin.jsp").forward(request, response);
	
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy   HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		String dataHoraExecucao = "Execução processada em:   " + dtf.format(now); 
	%>
	
	<div class="jumbotron jumbotron-fluid">
		<div class="container">
			<h1>USCS - Escola Politécnica - Consulta de Grades</h1>
			<hr>
			<p>Qualitsys Consultoria de Informática Ltda.</p>
			<p> <% out.print(dataHoraExecucao); %> </p>
		
			<hr>
			<p> <% out.print("Usuário logado: <b>" + session.getAttribute("usuario") + "</b>"); %>
			<hr>
			<form class="form-inline" action="controller01" method = "post">
  				<label for="codaluno" class="mr-sm-2">Código do Aluno:</label>
  				<input type="text" class="form-control mb-2 mr-sm-2" placeholder="Digite aqui" name = "codaluno" id="codaluno">
  				<button type="submit" class="btn btn-primary mb-2">Submit com Código do Aluno</button>
			</form>
			<hr>
			
			<form class="form-inline" action="controller02" method = "post">
  				<label for="cpfaluno" class="mr-sm-2">CPF  do  Aluno :</label>
  				<input type="text" class="form-control mb-2 mr-sm-2" placeholder="Digite aqui" name = "cpfaluno" id="codaluno">
  				<button type="submit" class="btn btn-primary mb-2">Submit com CPF do Aluno</button>
			</form>
			<hr>
			
			<form class="form-inline" action="jspfim.jsp">
  				<button type="submit" class="btn btn-primary mb-2">Logout</button>
			</form>
		 
		</div>
	 </div>
	</body>
</html>

