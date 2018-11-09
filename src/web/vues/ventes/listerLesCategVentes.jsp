<%-- 
    Document   : listerLesCategVentes
    Created on : 6 nov. 2018, 14:33:53
    Author     : slam
--%>

<%@page import="modele.CategVente"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vues/include/header.jsp" />
<div class="container">
<h1>Liste des catégories de vente</h1>
</div>
<%
ArrayList<CategVente> lesCategVentes = (ArrayList)request.getAttribute("pLesCategVentes");
%>

<table  class="table table-bordered table-striped table-condensed">  
    <thead>
        <tr>             
            <th>Code</th>
            <th>Libelle</th>
            <th>Modifier</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <%
            for(int i = 0; i < lesCategVentes.size();i++)
            {

                CategVente uneCategVente = lesCategVentes.get(i);
                out.println("<tr><td>");
                out.println(uneCategVente.getCode());
                out.println("</a></td>");

                out.println("<td>");
                out.println(uneCategVente.getLibelle());
                out.println("</td>");

                out.println("<td><a href ='../ServletCategVente/categorieVenteModifier?code="+ uneCategVente.getCode() + "'>");
                out.println("Modifier");
                out.println("</td>");

            }
            %>
        </tr>
    </tbody>
</table>

<jsp:include page="/vues/include/footer.jsp" />
