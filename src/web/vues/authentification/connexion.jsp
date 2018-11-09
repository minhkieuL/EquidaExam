<%-- 
    Document   : Connexion au site
    Created on : 17/10/18
    Author     : MartinJ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/vues/include/header.jsp" />
<div class="container">
<h1>Connexion</h1>
</div>
<form action="connexion" method="POST">
    <label for="login">Nom d'utilisateur</label><input type="text" id="login" name="login"/><br/>
    <label for="passwd">Mot de passe</label><input type="password" id="passwd" name="passwd"/><br/>
    <input type="submit"/>
</form>

<jsp:include page="/vues/include/footer.jsp" />