<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page
	import="java.time.LocalDateTime,java.time.format.DateTimeFormatter"%>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Politécnica - Alunos Ativos</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"> <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> <script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>

<body>
	<%! @SuppressWarnings("unchecked") %>
	<%
		if(session.getAttribute("usuario") == null)
			getServletContext().getRequestDispatcher("/jsperrologin.jsp").forward(request, response);
	
		if (request.getAttribute("listaNomesAlunosAtivos") == null )
				getServletContext().getRequestDispatcher("/jsperroacesso.jsp").forward(request, response);	
	
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy   HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		String dataHoraExecucao = "Execução processada em:   " + dtf.format(now); 
		
		ArrayList<String> listaNomesAlunosAtivos = (ArrayList<String>)request.getAttribute("listaNomesAlunosAtivos");
		ArrayList<String> listaRAAlunosAtivos = (ArrayList<String>)request.getAttribute("listaRAAlunosAtivos");
		int size = listaNomesAlunosAtivos.size();
		
	%>

	<div class="jumbotron jumbotron-fluid">
		<div class="container">
		
			<h1>USCS - Escola Politécnica - Listagem de Estudantes Ativos</h1>
			<hr>
			<p>Qualitsys Consultoria de Informática Ltda.</p>
			<p><% out.print(dataHoraExecucao);%> </p>
			<hr>
	 		<p> <% out.print("Usuário logado: <b>" + session.getAttribute("usuario") + "</b>"); %>
	 		<hr>
	 		<div class ="container p-3 my-3 bg-primary text-white"> 
	 			<% out.print("<h5>Total de Alunos ativos: <b>" + size + "</h5></b>"); %>
	 		</div>
				
			<table class="table table-sm">
			
					   <thead>
						    <tr>
						      <th scope="col">Nome</th>
						      <th scope="col">Matrícula</th>
						    </tr>
						</thead>
						
  						<tbody>
							<%
								for (int i=0 ; i < size; i++) {
									 out.print("<tr>");	
									 out.print("<td><p>" + listaNomesAlunosAtivos.get(i)+"</td>");
									 out.print("<td><p>" + listaRAAlunosAtivos.get(i)+"</td>");
									 out.print("</tr>");	
								}
							%>
			  	   		</tbody>
			</table>
				  	
			</div>
		</div>
</body>
</html>

