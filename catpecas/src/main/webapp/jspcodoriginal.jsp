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
	<!-- ------------- Preparação do Cabeçalho da Página jspcodoriginal.jsp ----------------   -->
	<!-- ----------------------------------------------------------------------------   -->


	<%!@SuppressWarnings("unchecked")%>
	<%

	//* -----------------------------------------------------------------------------
	//* ---------  Bloqueia chamada direta dessa view pelo usuário ------------------
	//* ------------------------------------------------------------------------------
	
	 	if (session.getAttribute("usuario") == null)
	 	getServletContext().getRequestDispatcher("/jsperrologin.jsp").forward(request, response);

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
			<p> <% out.print(dataHoraExecucao); %> </p>
			<hr>
			<p><% out.print("Usuário logado: <b> <font color=\"blue\">" + session.getAttribute("usuario") + "</b>");%></p>
			<hr></div>
	
		<!-- ---------------------------------------------------------------------------------   -->
		<!-- --------- Form para envio do campo de pesquisa searchcodoriginal   --------------   -->
		<!-- --------- Essa view (jspcodoriginal.jsp) chama o servlet: controller07 ----------   -->
		<!-- --------- campo searchcodoriginal enviado pelo request ao controller07 ----------   -->
		<!-- ---------------------------------------------------------------------------------   -->	
	
		<div class="container">
			<p><strong> <font color=blue></font><font color=black>Entre com o código original:</font></strong></p>		
				<nav class="navbar navbar-light bg-light">
  						<div class="container-fluid"> 
  							
    						<form class="d-flex" action="controller07">
       							<input class="form-control me-2" type="search" placeholder="Digite aqui" aria-label="Search" name="searchcodoriginal" id="codoriginal">
      							<button class="btn btn-outline-success" type="submit">Submit</button> </form></div>
 							
 							<!-- --------------------------------------------------------------------------   -->
							<!-- -------- Tratamento de Erro de Código Original ---------------------------   -->
							<!-- -------- Variável de Controle manipulada na Session: msgerro  ------------   -->
							<!-- -------- msgerro = '00007' =>  Código original não encontrado!  ----------   -->
							<!-- --------------------------------------------------------------------------   -->	
 					
    						<% 
								String msgerro = (String) session.getAttribute("msgerro");
								if (msgerro == null) 
									session.setAttribute("msgerro"," ");
								else 
									if (msgerro.equals("00007") ) {
										out.print("<div class='alert alert-warning' id = 'msgerro' role='alert'><b><font color='red'>Itens não encontrados para esse código original!" +"</font></b><br>");
										session.setAttribute("msgerro"," ");
									}
							%>
        						
				</nav></div>
				
		<!-- --------------------------------------------------------------------------   -->
		<!-- --------- Envio de Botões de Retorno e Logout  ---------------------------   -->
		<!-- --------- Se Botão Retorna pressionado => chama a view: jspmain  ---------   -->
		<!-- --------- Se Botão Logout pressionado => chama a view: jspfim  -----------   -->
		<!-- --------------------------------------------------------------------------   -->			
	 	
	 	<br><hr>	
	 	<div class="container"> 
	 			<br>
				<a class="btn btn-primary" href="jspmain.jsp" role="button">Retorna</a>
				<hr> 
				<a class="btn btn-danger" href="jspfim.jsp" role="button">Logout</a></div>	
    </div>
</body>
</html>
