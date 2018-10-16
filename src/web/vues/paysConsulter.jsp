<%-- 
    Document   : paysConsulter
    Created on : 16 oct. 2018, 16:34:12
    Author     : slam
--%>

<%@page import="modele.Pays"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consultation Nouveau Pays</title>
    </head>
    <body>
        
        
        <h1>Infos sur l'Ajout de Pays</h1>
        
         <%
        Pays unPays = (Pays)request.getAttribute("pPays");
        %>
        
        
         <table class="table table-bordered table-striped table-condensed">
            <tr><td>Code :</td><td><% out.println(unPays.getCode());%></td></tr>
            <tr><td>Nom :</td><td><%  out.println(unPays.getNom());%></td>  </tr>
           
              </td></tr>
        </table>
    </body>
</html>
