<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.time.LocalDateTime,java.time.format.DateTimeFormatter"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="br.com.qualitsys.model.Montadora"%>
<%@ page import="br.com.qualitsys.model.ResultJoinItem"%>
   
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
		    <p><% out.print("Usuário logado: <b> <font color=\"blue\">" + session.getAttribute("usuario") + "</b>");%></p>
			<hr>
			<br>
			<table class="table table-sm">
				<thead>
					<tr>
						<th scope="col">Item</th>
						<th scope="col">Código</th>
						<th scope="col">Descrição</th>
						<th scope="col">Códigos Originais</th>
						</tr>
				</thead>
 				<tbody>
	 			<%
	 				ArrayList<String> listagemMontadoras = new ArrayList<String>();
	 				listagemMontadoras = (ArrayList<String>) session.getAttribute("listagemMontadoras");
	 			
					ArrayList<ResultJoinItem> listagemItem = (ArrayList<ResultJoinItem>) session.getAttribute("listagemItem");
	 				for (int i = 0; i < listagemItem.size(); i++) {
											
							String adrImagem = "imagens/"+  session.getAttribute("codItemEscolhido") + ".JPG";
							
							out.print("<tr>");	
							out.print("<td><p><img src=" + adrImagem + " />" + "</td>");
							
							out.print("<td><p>" +  session.getAttribute("codItemEscolhido") + "  /  ");
							if (listagemItem.get(i).getMercadoparalelo().equals("s"))  
								out.print("   " + session.getAttribute("codItemEscolhido") + "A" + "</td>");
							else 
								out.print("</td>"); 
							
							out.print("<td><p>" + listagemItem.get(i).getDescitem() + "</td>");
							out.print("<td><p>" + listagemItem.get(i).getCodigosoriginais() + "</td>");
							out.print("</tr>");	
					}
				%>
				</tbody>
			</table>
			<%	
				out.print("<p>" + "<strong><font color=\"black\">Montadoras: " + "</font></strong></p>"); 
				for (int i=0; i<listagemMontadoras.size(); i++) {
	  					out.print("<p><font color=\"black\">" + listagemMontadoras.get(i).toString() + "</font></p>"); 
	 			}
			%>
			<hr>
 			<div class="container"> 
				<a class="btn btn-primary" href="jspitem.jsp" role="button">Retorna</a>
				<hr><br>
				<a class="btn btn-danger" href="jspfim.jsp" role="button">Logout</a></div>	
		</div>
	</div>

</body>
</html>






