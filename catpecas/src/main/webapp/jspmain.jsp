<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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

	<!-- ----------------------------------------------------------------------------   -->
	<!-- ------------- Preparação do Cabeçalho da Página jspmain.jsp ----------------   -->
	<!-- ----------------------------------------------------------------------------   -->

	<%!@SuppressWarnings("unchecked")%>
	<%
	 
		//* -----------------------------------------------------------------------------
		//* ---------  Bloqueia chamada direta dessa view pelo usuário ------------------
		//* ------------------------------------------------------------------------------
		
	 	if (session.getAttribute("usuario") == null)
	 		getServletContext().getRequestDispatcher("/jsperrologin.jsp").forward(request, response);
	

		session.setAttribute("msgerro"," ");
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
			<p>WhatsApp:   11 99766-2438 / 11 93087-5039</p>
			<hr>
			<p><strong>Catálogo Eletrônico de Peças</strong></p>
			<p><% out.print(dataHoraExecucao); %></p>
			<hr>
			<p><% out.print("Usuário logado: <b> <font color=\"blue\">" + session.getAttribute("usuario") + "</font></b>");%></p>
			<hr></div>
			
	
		<!-- --------------------------------------------------------------------------   -->
		<!-- --------- div para botões de chamadas de funcionalidades do sistema ------   -->
		<!-- --------- Funcionalidades: consulta pelo item, consulta pela categoria ---   -->
		<!-- ---------                  consulta pela montadora -----------------------   -->
		<!-- ---------                  e consulta pelo código original    ------------   -->
		<!-- --------------------------------------------------------------------------   -->	
		<!-- ------ Chamadas das views:  jspitem  -------------------------------------   -->
		<!-- ------ Chamadas dos controllers:  controller01 e controller04 ------------   -->
		<!-- --------------------------------------------------------------------------   -->
	
				
		<div class="container"> 
				<p><% out.print("<b><font color='black'>Escolha a consulta desejada: </font></b>");%></p>
				<a class="btn btn-primary" href="jspitem.jsp" role="button">Item interno</a>
				<a class="btn btn-success" href="controller01" role="button">Categoria/Montadora</a> 	
				<a class="btn btn-primary" href="controller04" role="button">Montadora</a> 
				<a class="btn btn-success" href="jspcodoriginal.jsp" role="button">Código Original</a></div>
				<br>	
		<hr>
		
		
		
		<!-- --------------------------------------------------------------------------   -->
		<!-- --------- Form para botão de Logout  -------------------------------------   -->
		<!-- --------- Se Botão Logout pressionado => chama a view: jspfim ------------   -->
		<!-- --------------------------------------------------------------------------   -->	
				
		<div class="container">
					<form class="form-inline" action="jspfim.jsp">
						<button type="submit" class="btn btn-danger">Logout</button></form></div>	
						
							
    </div>
</body>
</html>
