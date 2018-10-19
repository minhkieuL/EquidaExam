<%-- 
    Document   : categorieVenteConsulter
    Created on : 12 oct. 2018, 10:05:00
    Author     : paul_collet
--%>

<%@page import="modele.CategVente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/vues/MiseEnForme/header.jsp" />
    <body>
        
        
        <h1>Infos sur l'Ajout de Categorie</h1>
        
         <%
        CategVente uneCategVente = (CategVente)request.getAttribute("pCategVente");
        %>
        
        
         <table class="table table-bordered table-striped table-condensed">
            <tr><td>Code :</td><td><% out.println(uneCategVente.getCode());%></td></tr>
            <tr><td>Libelle :</td><td><%  out.println(uneCategVente.getLibelle());%></td>  </tr>
           
              </td></tr>
        </table>
<jsp:include page="/vues/MiseEnForme/Footer.jsp" />