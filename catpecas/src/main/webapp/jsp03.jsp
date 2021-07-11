<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page
	import="java.time.LocalDateTime,java.time.format.DateTimeFormatter"%>
<%@ page import="java.util.ArrayList"%>
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
	<%! @SuppressWarnings("unchecked") %>
	<%
		if (session.getAttribute("usuario") == null)
			getServletContext().getRequestDispatcher("/jsperrologin.jsp").forward(request, response);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy   HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String dataHoraExecucao = "Execução processada em:   " + dtf.format(now);

		ArrayList<Montadora> listaMontadoras = (ArrayList<Montadora>) session.getAttribute("listaMontadoras");
		int n = listaMontadoras.size();
	
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
			<p><%out.print("Usuário logado: <b> <font color=\"blue\">" + session.getAttribute("usuario") + "</b>");%>
			
			<hr>
			<p><%out.print("<font color=\"black\"> Categoria solicitada: <b> <font color=\"blue\">" + session.getAttribute("nomeCategoriaEscolhida") + "</b>");%>
			
			<hr>
			<p><%out.print("<p> <font color=\"green\">"+"<b> Selecione a Montadora desejada: </b></p>");%>
			
				<div class="container">
				   <form class="form-inline" action="controller03" method = "post">
				   
				     <div class="container"> 
	    		  	  	<select name="codmontadora" id="codmontadora" class="selectpicker" data-style="btn-success">
							<%  
							for (int i=0; i<n; i++)  
								out.println(format1 + 
									listaMontadoras.get(i).getCodMontadora() + format2 + 
									listaMontadoras.get(i).getDescMontadora() + format3);
							%>
		 		       	</select>
		 		       <br><br>
				     </div>
				      
		 		     <div class="container">
		 			   		<button name = "codmontadora" type="submit" class="btn btn-success">Submit para Geração do Catálogo</button>
		 			 </div>
			      </form> 
			    </div>
			    
			    <br><hr>
			    
			     <div class="container"> 
					 <form class="form-inline" action="jsp02.jsp">
			     		<button type="submit" class="btn btn-warning">Retorna para outra Categoria </button>
			  		</form>
			  	</div>
			    <hr>
			    <div class="container"> 
					 <form class="form-inline" action="jspfim.jsp">
			     		<button type="submit" class="btn btn-primary">Logout</button>
			  		</form>
			  	</div>
			  	<hr>
		</div>
	</div>
	 
 </body>
</html>

