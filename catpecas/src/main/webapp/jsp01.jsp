<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page
	import="java.time.LocalDateTime,java.time.format.DateTimeFormatter"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="br.com.qualitsys.model.Categoria"%>
<%@ page import="br.com.qualitsys.model.Montadora"%>



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

		//Recupera da Session a lista de Categorias criada pelo Controller01
		ArrayList<Categoria> listaCategorias = (ArrayList<Categoria>) session.getAttribute("listaCategorias");
		int n1 = listaCategorias.size();

		//Recupera da Session a lista de Categorias criada pelo Controller01
		ArrayList<Montadora> listaMontadoras = (ArrayList<Montadora>) session.getAttribute("listaMontadoras");
		int n2 = listaMontadoras.size();

		String format1 = "<option style=\"background: #5cb85c; color: #fff;\" value = \"";
		String format2 = "\">";
		String format3 = "</option>";
	%>

	<div class="jumbotron  jumbotron-fluid">
		<div class="container">
			<h1>Catálogo Eletrônico - Peças - Versão 0.0.1</h1>
			<hr>
			<p>Qualitsys Consultoria de Informática Ltda.</p>
			<p><% out.print(dataHoraExecucao); %></p>
			<hr>
			<p><% out.print("Usuário logado: <b> <font color=\"blue\">" + session.getAttribute("usuario") + "</b>");%></p>
			<hr>
			
			<form class="form-inline" action="controller02" method="post">
				<div class="container">
				<p><% out.print("<p> <font color=\"green\">" + "<b> Selecione a categoria:</b>");%></p>
				<select name="codcategoria" id="codcategoria" class="selectpicker show-tick" data-style="btn-success">
							<% for (int i = 0; i < n1; i++)
								out.println(format1 + listaCategorias.get(i).getCodCategoria() + format2 +
								listaCategorias.get(i).getDescCategoria() + format3); %> </select></div>
				<br><br><hr>
				
				<div class="container">
				<p><% out.print("<p> <font color=\"green\">" + "<b> Selecione a montadora:</b>");%></p>
				<select name="codmontadora" id="codmontadora" class="selectpicker show-tick" data-style="btn-success">
							<% for (int i = 0; i < n2; i++)
								out.println(format1 + listaMontadoras.get(i).getCodMontadora() + format2 +
								listaMontadoras.get(i).getDescMontadora() + format3); %> </select></div>
								
				<br><br><hr>
				
				<div class="container">	
				<button name="subcatalogo" type="submit" class="btn btn-success">Clique aqui para exibição do Catálogo</button></div></form>
		
		</div>
	</div>
</body>
</html>

