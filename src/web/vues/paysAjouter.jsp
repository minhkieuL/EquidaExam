<%-- 
    Document   : paysAjouter
    Created on : 16 oct. 2018, 16:04:08
    Author     : slam
--%>
<%@page import="formulaires.PaysForm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pays Ajouter</title>
    </head>
    <body>
        <h1>NOUVEAU PAYS</h1>
        
         <%
                
                PaysForm form = (PaysForm)request.getAttribute("form");
            %>
        
        <form class="form-inline" action="paysAjouter" method="POST">
                <label for="code">Code : </label>
                <input id="code" type="text" name="code"  size="30" maxlength="30">
                </br>
                
                <br/>
                <label for="nom">Nom : </label>
                <input id="nom"  type="text"  name="nom" size="30" maxlength="30">      
                 </br>
                 
            <br/>
            <input type="submit" name="valider" id="valider" value="Valider"/>
            </form>
        
    </body>
</html>