<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.time.LocalDateTime,java.time.format.DateTimeFormatter"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="br.com.qualitsys.model.Categoria"%>
<%@ page import="br.com.qualitsys.model.Montadora"%>
<%@ page import="br.com.qualitsys.model.ResultJoin"%>

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
	<!-- ------------- Preparação do Cabeçalho da Página jsp02.jsp ------------------   -->
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
			<p><% out.print("Usuário logado: <b> <font color='blue'>" + session.getAttribute("usuario") + "</font></b>");%></p>
			<hr>
				
			<!-- ------------------------------------------------------------------------------------------   -->
			<!-- --------- Recupera da Session a listagem de itens a serem exibidas ao usuário ------------   -->
			<!-- ------------------------------------------------------------------------------------------   -->
			
			<% ArrayList<ResultJoin> listagemItens1 = (ArrayList<ResultJoin>) session.getAttribute("listagemItens1"); %>
		
			<!-- -----------------------------------------------------------------------------   -->
			<!-- --------- Recupera nome da Categoria Escolhida pelo usuário    --------------   -->
			<!-- -----------------------------------------------------------------------------   -->
			
			<p><%out.print("<font color='black'>Categoria: </font><font color='green'><b>" + session.getAttribute("nomeCategoriaEscolhida1") + "</b></font>");%></p>
			
			<!-- -----------------------------------------------------------------------------   -->
			<!-- --------- Recupera nome da Montadora Escolhida pelo usuário    --------------   -->
			<!-- -----------------------------------------------------------------------------   -->
			
			<p><%out.print("<font color='black'>Montadora: </font><font color='green'><b>" + session.getAttribute("nomeMontadoraEscolhida1") + "</b></font>");%></p>
			<br>
	
			<!-- -----------------------------------------------------------------------------   -->
			<!-- --------- Tabela para exibição dos itens selecionados pelo usuário     ------   -->
			<!-- -----------------------------------------------------------------------------   -->
		
			<table class="table table-striped">
				<thead>
					<tr>
						<th scope="col">Item</th>
						<th scope="col">Código</th>
						<th scope="col">Descrição</th>
					</tr>
				
				</thead>
 
				<tbody>
					<%
	
						int n = listagemItens1.size();
					
						for (int i = 0; i < n; i++) {
											
							String mercadoparalelo = listagemItens1.get(i).getMercadoparalelo();
							
							out.print("<tr>");	
							out.print("<td><p><img src=" + "imagens/"+ listagemItens1.get(i).getCoditem() + ".JPG />" + "</td>");
							
							out.print("<td><p>" + listagemItens1.get(i).getCoditem());
							if (mercadoparalelo.equals("s"))  
								out.print(" / " + listagemItens1.get(i).getCoditem() + "A" + "</td>");
							else 
								out.print("</td>"); 
						
							out.print("<td><p>" + listagemItens1.get(i).getDescitem() + "</td>");
							out.print("</tr>");	
						}
					%>
				</tbody>
			</table>

			<br>
			<hr>
			
			<!-- --------------------------------------------------------------------------   -->
			<!-- --------- Envio de Botões de Retorno e Logout  ---------------------------   -->
			<!-- --------- Se Botão Retorna pressionado => chama a view: jsp01    ---------   -->
			<!-- --------- Se Botão Logout pressionado => chama a view: jspfim  -----------   -->
			<!-- --------------------------------------------------------------------------   -->			
	
			<div class="container"> 
				<a class="btn btn-primary" href="jsp01.jsp" role="button">Retorna</a>
				<hr><br>
				<a class="btn btn-danger" href="jspfim.jsp" role="button">Logout</a></div>	
		</div>
	</div>
</body>
</html>

