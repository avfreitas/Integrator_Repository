<%@page contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>MVC - Sem acesso ao Banco de Dados</title>
    <link rel="stylesheet" href="main.css" type="text/css"/>    
</head>

<body BGCOLOR="#7799FF">
    <h1>Grato pelo cadastramento!</h1>

   <hr>
    <p>Aqui está a informação entrada no sistema:</p>

    <label>Email:</label>
    <span>${user.email}</span><br>
    <label>Nome:</label>
    <span>${user.firstName}</span><br>
    <label>Sobrenome:</label>
    <span>${user.lastName}</span><br>

    <hr>

    <form action="" method="get">
        <input type="hidden" name="action" value="join">
        <input type="submit" value="Retorno">
    </form>

</body>
</html>