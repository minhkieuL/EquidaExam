<%-- 
    Document   : Connexion au site
    Created on : 17/10/18
    Author     : MartinJ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Equida Connexion</title>
    </head>
    <body>
        <h1>CONNEXION</h1>
		<form action="connexion" method="POST">
			<label for="login">Nom d'utilisateur</label>
			<input type="text" id="login" name="login"/><br/>
			<label for="passwd">Mot de passe</label>
			<input type="password" id="passwd" name="passwd"/><br/>
			<input type="submit"/>
		</form>
    </body>
</html>
