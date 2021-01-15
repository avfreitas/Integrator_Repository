<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page
	import="java.time.LocalDateTime,java.time.format.DateTimeFormatter"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="br.com.qualitsys.model.Curso"%>


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

		//Recupera da Session a lista de Cursos criada pelo Controller01
		ArrayList<Curso> listaCursos = (ArrayList<Curso>) session.getAttribute("listaCursos");
		int n = listaCursos.size();
	
		String format1 = "<option style=\"background: #5cb85c; color: #fff;\" value = \"";
		String format2 = "\">";
		String format3 = "</option>";
	
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
			<p><%out.print("<p> <font color=\"green\">"+"<b> Selecione abaixo o curso desejado: </b></p>");%>
			
				<div class="container">
				   <form class="form-inline" action="controller02" method = "post">
				   
				     <div class="container"> 
	    		  	  	<select name="idcurso" id="idcurso" class="selectpicker" data-style="btn-success">
							<%  
								for (int i=0; i<n; i++)  
									out.println(format1 + 
										listaCursos.get(i).getIdCurso() + format2 + 
										listaCursos.get(i).getNomeCurso() + format3);
							%>
		 		       	</select>
		 		     
		 		       <br><br>
				     </div>
				      
		 		     <div class="container">
		 			   		<button name = "idcurso" type="submit" class="btn btn-success">Submit para Definição da Grade</button>
		 			 </div>
			      </form> 
			    </div>
			    
			    <br><br><br>
			    <div class="container"> 
					 <form class="form-inline" action="jspfim.jsp">
			     		<button type="submit" class="btn btn-primary">Logout</button>
			  		</form>
			  	</div>
		</div>
	</div>
	
 </body>
</html>

