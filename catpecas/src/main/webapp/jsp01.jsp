<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.time.LocalDateTime,java.time.format.DateTimeFormatter"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="br.com.qualitsys.model.Categoria"%>
<%@ page import="br.com.qualitsys.model.Montadora"%>

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

	<!-- ----------------------------------------------------------------------------   -->
	<!-- ------------- Preparação do Cabeçalho da Página jsp01.jsp ------------------   -->
	<!-- ----------------------------------------------------------------------------   -->

	<%!@SuppressWarnings("unchecked")%>
	<%
	
		//* -----------------------------------------------------------------------------
		//* ---------  Bloqueia chamada direta dessa view pelo usuário ------------------
		//* -----------------------------------------------------------------------------
	
		if (session.getAttribute("usuario") == null)
			getServletContext().getRequestDispatcher("/jsperrologin.jsp").forward(request, response);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy   HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String dataHoraExecucao = "Execução processada em:   " + dtf.format(now);

		//* -------------------------------------------------------------------------------------------------
		//* ------------  Recupera da Session a lista de Categorias criada pelo Controller01 ----------------
		//* -------------------------------------------------------------------------------------------------
		
		ArrayList<Categoria> listaCategorias1 = (ArrayList<Categoria>) session.getAttribute("listaCategorias1");
		int n1 = listaCategorias1.size();

		//* -------------------------------------------------------------------------------------------------
		//* -----------   Recupera da Session a lista de Montadoras criada pelo Controller01 ----------------
		//* -------------------------------------------------------------------------------------------------
		
		ArrayList<Montadora> listaMontadoras1 = (ArrayList<Montadora>) session.getAttribute("listaMontadoras1");
		int n2 = listaMontadoras1.size();

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
			
			<!-- ---------------------------------------------------------------------------------------------   -->
			<!-- --------- Form para envio dos controles para escolha da Categoria e Montadora   -------------   -->
			<!-- ----------Chamada do servlet controler02   --------------------------------------------------   -->
			<!-- ---------------------------------------------------------------------------------------------   -->	
			
			<form class="form-inline" action="controller02" method="post">
				<div class="container">
				<p><% out.print("<p> <font color=\"green\">" + "<b> Selecione a categoria:</b>");%></p>
				<select name="codcategoria1" id="codcategoria" class="selectpicker show-tick" data-style="btn-success">
							<% for (int i = 0; i < n1; i++)
								out.println(format1 + listaCategorias1.get(i).getCodCategoria() + format2 +
								listaCategorias1.get(i).getDescCategoria() + format3); %> </select></div>
				<br><hr>
				
				<div class="container">
				<p><% out.print("<p> <font color=\"green\">" + "<b> Selecione a montadora:</b>");%></p>
				<select name="codmontadora1" id="codmontadora" class="selectpicker show-tick" data-style="btn-success">
							<% for (int i = 0; i < n2; i++)
								out.println(format1 + listaMontadoras1.get(i).getCodMontadora() + format2 +
								listaMontadoras1.get(i).getDescMontadora() + format3); %> </select></div>
				<br><hr>
				
				<!-- --------------------------------------------------------------------------   -->
				<!-- -------- Tratamento de Erro de Recuperação de Item -----------------------   -->
				<!-- -------- Variável de Controle manipulada na Session: msgerro  ------------   -->
				<!-- -------- msgerro = '00005' =>  Itens não encontrados ! -------------------   -->
				<!-- --------------------------------------------------------------------------   -->	
		
				<% 
						String msgerro = (String) session.getAttribute("msgerro");
						if (msgerro == null) 
								session.setAttribute("msgerro"," ");
						else 
						if (msgerro.equals("00005") ) {
							out.print("<div class='alert alert-warning' id = 'msgerro' role='alert'><b><font color='red'>Desculpe!  Itens não encontrados ! " +"</font></b><br>");
							out.print("<font color='black'>Categoria Selecionada => &nbsp </font><b> <font color='black'>" + session.getAttribute("nomeCategoriaEscolhida1") + "</font></b><br>");
							out.print("<font color='black'>Montadora Selecionada => &nbsp </font><b> <font color='black'>" + session.getAttribute("nomeMontadoraEscolhida1") + "</b></div>");
							session.setAttribute("msgerro"," ");
						}
				%>
				
				<div><button name="subcatalogo" type="submit" class="btn btn-success">Clique aqui para exibição do Catálogo</button></div>
				<hr>
				
				
			</form>
		</div>
	
		<hr>
		
		<!-- --------------------------------------------------------------------------   -->
		<!-- --------- Envio de Botões de Retorno e Logout  ---------------------------   -->
		<!-- --------- Se Botão Retorna pressionado => chama a view: jspmain  ---------   -->
		<!-- --------- Se Botão Logout pressionado => chama a view: jspfim  -----------   -->
		<!-- --------------------------------------------------------------------------   -->			
		
		<div class="container"> 
			<a class="btn btn-primary" href="jspmain.jsp" role="button">Retorna</a>
			<hr><br>
			<a class="btn btn-danger" href="jspfim.jsp" role="button">Logout</a></div>	
			 
  </div>
</body>
</html>

