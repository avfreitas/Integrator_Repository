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

	<%!@SuppressWarnings("unchecked")%>
	<%
		if (session.getAttribute("usuario") == null)
			getServletContext().getRequestDispatcher("/jsperrologin.jsp").forward(request, response);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy   HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String dataHoraExecucao = "Execução processada em:   " + dtf.format(now);
	%>
	
	<!--   -->
	<% out.print("<jsp:include page=" + "controller03" + " flush =" + "true "  +  "/> "); 	%>
	<!--   -->
 
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
			<hr>

			 
			<p><%out.print("<font color=\"black\">" +   
					"Categoria:  <strong> <font color=\"green\">" + session.getAttribute("nomeCategoriaEscolhida") + "</strong>");%></p>
			<p><%out.print("<font color=\"black\">" +   
					"Montadora: <strong> <font color=\"green\">" + session.getAttribute("nomeMontadoraEscolhida") + "</strong>");%></p>
			 

			<br>
			<table class="table table-sm">
				<thead>
					<tr>
						<th scope="col"></th>
						<th scope="col">Código Interno</th>
						<th scope="col">Códigos Originais</th>
						<th scope="col">Descrição do Item</th>
						</tr>
				</thead>
 				<tbody>
	 			<%
							
					ArrayList<ResultJoin> listagemItens = (ArrayList<ResultJoin>) session.getAttribute("listagemItens");
	 				for (int i = 0; i < listagemItens.size(); i++) {
											
							String mercadoparalelo = listagemItens.get(i).getMercadoparalelo();
							String adrImagem = "imagens/"+ listagemItens.get(i).getCoditem() + ".JPG";
							
							out.print("<tr>");	
							out.print("<td><p><img src=" + adrImagem + " />" + "</td>");
							
							out.print("<td><p>" + listagemItens.get(i).getCoditem() + "  /  ");
							if (mercadoparalelo.equals("s"))  
								out.print("   " + listagemItens.get(i).getCoditem() + "A" + "</td>");
							else 
								out.print("</td>"); 
							
							out.print("<td><p>" + listagemItens.get(i).getCodigosoriginais() + "</td>");
							out.print("<td><p>" + listagemItens.get(i).getDescitem() + "</td>");
							out.print("</tr>");	
					}
				%>
				</tbody>
			</table>

			<br>
			<hr>

			<div class="container">
				<form class="form-inline" action="jsp01.jsp">
					<button type="submit" class="btn btn-warning">Retorna</button>
				</form>
			</div>

			<hr>
			<div class="container">
				<form class="form-inline" action="jspfim.jsp">
					<button type="submit" class="btn btn-danger">Logout</button>
				</form>
			</div>

		</div>
	</div>

</body>
</html>

