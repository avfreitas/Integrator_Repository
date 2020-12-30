<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
 
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <title>Servlet, Maven, JSF e Bootstrap</title>

</head>
<body>
<div class="jumbotron jumbotron-fluid">
  <div class="container">
    <h1>QualitSys Consultoria de Informática Ltda</h1>      
     <p>Servlet com informações do Request com JSP e Bootstrap...</p>
     <hr>
     <p><%= request.getAttribute("execucaoem") %></p>
     <p><%= request.getAttribute("contextoRequest") %></p>
     <p><%= request.getAttribute("requestURI") %></p>
     <p><%= request.getAttribute("requestURL") %></p>
     <p><%= request.getAttribute("servletPath") %></p>
     <p><%= request.getAttribute("servletMapping") %></p>
     <hr>
     <p><%= request.getAttribute("method") %></p>
     <p><%= request.getAttribute("serverName") %></p>
     <p><%= request.getAttribute("ip") %></p>
     <hr> 
     <p><%= request.getAttribute("servletName") %></p>
     <p><%= request.getAttribute("pathInfo") %></p>
     <p><%= request.getAttribute("protocol") %></p>
     <p><%= request.getAttribute("remotePort") %></p>
     <p><%= request.getAttribute("queryString") %></p>
     <p><%= request.getAttribute("userPrincipal") %></p>
     <p><%= request.getAttribute("contentLength") %></p>
     <p><%= request.getAttribute("locale") %></p>
     <p><%= request.getAttribute("scheme") %></p>
     <p><%= request.getAttribute("localName") %></p>
     <p><%= request.getAttribute("portLocal") %></p>
     <p><%= request.getAttribute("remoteHost") %></p>
     <p><%= request.getAttribute("session") %></p>
     <p><%= request.getAttribute("serverPort") %></p>
     <p><%= request.getAttribute("authType") %></p>
		 
  </div>
</div>

</body>
</html>