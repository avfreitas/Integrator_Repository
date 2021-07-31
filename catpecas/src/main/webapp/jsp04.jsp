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

		//* ----------------------------------------------------------------------------------------------
		//* -----------   Recupera da Session a lista de Categorias criada pelo Controller04 -------------
		//*-----------------------------------------------------------------------------------------------
		ArrayList<Montadora> listaMontadoras = (ArrayList<Montadora>) session.getAttribute("listaMontadoras");
		int n = listaMontadoras.size();

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
			<!-- --------- Form para envio dos controles para escolha da Montadora   -------------------------   -->
			<!-- ----------Chamada do servlet controler05   --------------------------------------------------   -->
			<!-- ---------------------------------------------------------------------------------------------   -->	
		
			<form class="form-inline" action="controller05" method="post">
						
				<div class="container">
				<p><% out.print("<p> <font color=\"green\">" + "<b> Selecione a montadora:</b>");%></p>
				<select name="codmontadora" id="codmontadora" class="selectpicker show-tick" data-style="btn-success">
							<% for (int i = 0; i < n; i++)
								out.println(format1 + listaMontadoras.get(i).getCodMontadora() + format2 +
								listaMontadoras.get(i).getDescMontadora() + format3); %> </select></div>
								
				<hr>
				
				<div class="form-group">
    					<label for="formGroupExampleInput"><font color='black'><b>Buscar => </b></font>&nbsp; </label>
    					<input type="text" class="form-control" id="formGroupExampleInput" name=searchitem placeholder="Digite o texto aqui">
  				</div>
  				<br> 
  				<% 
					String msgerro = (String) session.getAttribute("msgerro");
					if (msgerro == null)  
						session.setAttribute("msgerro"," ");
					else if (msgerro.equals("00003") ) 
						out.print("<div class='alert alert-warning' id = 'msgerro' role='alert'><b><font color='red'>Desculpe!  Itens não encontrados para essa pesquisa!" +"</font></b><br></div>");
				%>
  				
  				<hr>
				<button name="subcatalogo" type="submit" class="btn btn-success">Clique aqui para exibição do Catálogo</button></form>
				<hr>
				
				</div>
				
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

