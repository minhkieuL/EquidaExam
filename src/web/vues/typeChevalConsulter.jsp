<%-- 
    Document   : typeChevalConsulter
    Created on : 19 oct. 2018, 09:00:50
    Author     : slam
--%>

<%@page import="modele.TypeCheval"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consultation Nouveau Type Cheval</title>
    </head>
    <body>
        
        
        <h1>Infos sur l'Ajout d'un Type Cheval</h1>
        
         <%
        TypeCheval unTypeCheval = (TypeCheval)request.getAttribute("pTypeCheval");
        %>
        
        
         <table class="table table-bordered table-striped table-condensed">
            <tr><td>Libelle :</td><td><%  out.println(unTypeCheval.getLibelle());%></td>  </tr>
            <tr><td>Description :</td><td><%  out.println(unTypeCheval.getDesc());%></td>  </tr>
           
              </td></tr>
        </table>
    </body>
</html>
