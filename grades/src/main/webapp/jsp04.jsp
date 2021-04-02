<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page
	import="java.time.LocalDateTime,java.time.format.DateTimeFormatter"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="br.com.qualitsys.model.Grade"%>
<%@ page import="br.com.qualitsys.model.ResultJoin"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Politécnica - Grades</title>
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
	<%! @SuppressWarnings("unchecked") %>
	<%
		if (session.getAttribute("usuario") == null)
			getServletContext().getRequestDispatcher("/jsperrologin.jsp").forward(request, response);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy   HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String dataHoraExecucao = "Execução processada em:   " + dtf.format(now);

	%>
 
	<div class="jumbotron  jumbotron-fluid">
		<div class="container">
		    <h1>USCS - Escola Politécnica - Consulta de Grades</h1>
			<hr>
			<p>Qualitsys Consultoria de Informática Ltda.</p>
			<p><% out.print(dataHoraExecucao); %></p>

			<hr>
			<p><%out.print("Usuário logado: <b>" + session.getAttribute("usuario") + "</b>");%>
			
			<hr>
				    
			<div class="container"> 
				 <form class="form-inline" action="jsp03.jsp">
			     		<button type="submit" class="btn btn-warning">Retorna para outra Grade desse curso </button>
			  	</form>
			</div>
			
			<hr>
			<div class="container"> 
				 <form class="form-inline" action="jspfim.jsp">
			     		<button type="submit" class="btn btn-primary">Logout</button>
			  	</form>
			 </div>
			
			 <hr>
			
			
		 	  <div 
	    	  		class ="container p-3 my-3 bg-primary text-white"> 
	    	  		<p><%out.print("<h5>Curso: <b>" + session.getAttribute("nomeCursoEscolhido") + "</h5></b>");%>
	    	  		<p><%out.print("<h5>Grade: <b>" + session.getAttribute("idGradeEscolhida") + "</h5></b>");%>
	   		  </div>
	   		  
					<p><%out.print("Ano vigente da Grade: <b>" + session.getAttribute("anoGradeEscolhida") + "</b>");%>
					<p><%out.print("Semestre vigente: <b>" + session.getAttribute("semestreGradeEscolhida") + "</b>");%>
					
					<table class="table table-sm">
			
					   <thead>
						    <tr>
						      <th scope="col">Grupo</th>
						      <th scope="col">Código Disciplina</th>
						      <th scope="col">Nome Disciplina</th>
						      <th scope="col">Carga Horária</th>
						    </tr>
						</thead>
			
  						<tbody>
							<%
								ArrayList<ResultJoin> listagemGrade = 
									(ArrayList<ResultJoin>) request.getAttribute("listagemGrade");
								int n = listagemGrade.size();
								for (int i=0 ; i < n; i++) {
									 out.print("<tr>");	
									 out.print("<td><p>" + listagemGrade.get(i).getGrupo() +"</td>");
									 out.print("<td><p>" + listagemGrade.get(i).getIdDisciplina() +"</td>");
									 out.print("<td><p>" + listagemGrade.get(i).getNomeDisciplina() +"</td>");
									 out.print("<td><p>" + listagemGrade.get(i).getCargaHoraria() +"</td>");
									 out.print("</tr>");	
								}
							%>
			  	   		</tbody>
			</table>
		  	
		    <br><hr>
			   
		</div>
	</div>
	 
 </body>
</html>

