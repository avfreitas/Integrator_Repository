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
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>

<body>
	<%!@SuppressWarnings("unchecked")%>
	<%
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
			<p>WhatAapp:   11 99766-2438 / 11 93087-5039</p>
			<hr>
			<p><strong>Catálogo Eletrônico de Peças</strong></p>
			<p> <% out.print(dataHoraExecucao); %> </p>
			<hr>  

			<div class="container p-3 my-3 bg-primary text-white">
				<p><%out.print("<h5>Categoria: <b>" + session.getAttribute("nomeCategoriaEscolhida") + "</h5></b>");%></p>
				<p><%out.print("<h5>Montadora: <b>" + session.getAttribute("nomeMontadoraEscolhida") + "</h5></b>");%></p>
			</div>

			<br>
	<!--   	<table class="table table-sm">   -->	
			
			<table class="table table-striped">
				<thead>
					<tr>
						<th scope="col"></th>
						<th scope="col">Código Interno</th>
						<th scope="col">Descrição do Item</th>
						</tr>
				
				</thead>
 
				<tbody>
					<%
						ArrayList<ResultJoin> listagemItens = (ArrayList<ResultJoin>) session.getAttribute("listagemItens");
						int n = listagemItens.size();
					
						for (int i = 0; i < n; i++) {
											
							String mercadoparalelo = listagemItens.get(i).getMercadoparalelo();
							
							out.print("<tr>");	
							out.print("<td><p><img src=" + "imagens/"+ listagemItens.get(i).getCoditem() + ".JPG />" + "</td>");
							
							out.print("<td><p>" + listagemItens.get(i).getCoditem() + "  /  ");
							if (mercadoparalelo.equals("s"))  
								out.print("   " + listagemItens.get(i).getCoditem() + "A" + "</td>");
							else 
								out.print("</td>"); 
						
							out.print("<td><p>" + listagemItens.get(i).getDescitem() + "</td>");
							out.print("</tr>");	
						}
					%>
				</tbody>
			</table>

			<br>
			<hr>

			<div class="container"> 
				<a class="btn btn-primary" href="jspmain.jsp" role="button">Retorna</a>
				<hr><br>
				<a class="btn btn-danger" href="jspfim.jsp" role="button">Logout</a></div>	

		</div>
	</div>

</body>
</html>

