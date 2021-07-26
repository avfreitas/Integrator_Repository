<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.time.LocalDateTime,java.time.format.DateTimeFormatter"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="br.com.qualitsys.model.Categoria"%>
<%@ page import="br.com.qualitsys.model.Montadora"%>
<%@ page import="br.com.qualitsys.model.ResultJoin"%>
<%@ page import="br.com.qualitsys.model.Aplicacao"%>

 

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
	 
	<div class="jumbotron  jumbotron-fluid">
		<div class="container">
			<h1>Catálogo Eletrônico - Peças - Versão 0.0.1</h1>
			<hr>
			<p>Qualitsys Consultoria de Informática Ltda.</p>
			<p><%out.print(dataHoraExecucao);%></p>
			<hr>
			<p><%out.print("Usuário logado: <b>" + session.getAttribute("usuario") + "</b>");%></p>
			<hr>

			<div class="container p-3 my-3 bg-primary text-white">
				<p><%out.print("<h5>Categoria: <b>" + session.getAttribute("nomeCategoriaEscolhida") + "</h5></b>");%></p>
				<p><%out.print("<h5>Montadora: <b>" + session.getAttribute("nomeMontadoraEscolhida") + "</h5></b>");%></p>
			</div>

			<%
 				ArrayList<ArrayList<Aplicacao>> listagemAplicacoes = (ArrayList<ArrayList<Aplicacao>>) session.getAttribute("listagemAplicacoes");
				Integer qtdeItens = listagemAplicacoes.size();
						
				out.print("<br>" + "********* qtdeItens:  " + qtdeItens);
						
				for (int i=0; i<listagemAplicacoes.size(); i++)  {
					for (int j=0; j<listagemAplicacoes.get(i).size(); j++)  
						out.print("<br>" + "Código do Produto:  "  + listagemAplicacoes.get(i).get(j).getCodproduto());
						
				} 
			%>
			</div>
			</div>
</body>
</html>

