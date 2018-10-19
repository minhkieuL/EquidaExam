<%-- 
    Document   : categorieVenteConsulter
    Created on : 12 oct. 2018, 10:05:00
    Author     : paul_collet
--%>

<%@page import="modele.CategVente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vues/include/header.jsp" />


<h1>Infos sur l'Ajout de Categorie</h1>

 <%
CategVente uneCategVente = (CategVente)request.getAttribute("pCategVente");
%>


 <table class="table table-bordered table-striped table-condensed">
    <tr>
        <td>Code :</td>
        <td><% out.println(uneCategVente.getCode());%></td>
    </tr>
    <tr>
        <td>Libelle :</td>
        <td><%  out.println(uneCategVente.getLibelle());%></td>  
    </tr>
</table>
<jsp:include page="/vues/include/footer.jsp" />