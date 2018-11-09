<%-- 
    Document   : Lister les Ventes 
    Created on : 22/06/2017, 07:43
    Author     : Zakina
--%>

<%@page import="modele.Utilisateur"%>
<%@page import="modele.CategVente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modele.Vente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vues/include/header.jsp" />
<div class="container">
<h1>Liste des ventes à venir</h1>
</div>
<%
ArrayList<Vente> lesVentes = (ArrayList)request.getAttribute("pLesVentes");
ArrayList<CategVente> lesCatVentes = (ArrayList)request.getAttribute("pLesCatVentes");

        Utilisateur user = (Utilisateur)session.getAttribute("user");
        if(user != null)
                out.println("Bienvenue " + user.getNom() + " " + user.getPrenom());
%>
<form method="GET">
    <select
        name="catVente">
        <%
            for(CategVente catVente : lesCatVentes) {
                String catCode = catVente.getCode();
                String catLib = catVente.getLibelle();

                String catVenteSelect = "";
                if(request.getParameter("catVente") != null)
                    catVenteSelect = (request.getParameter("catVente").equals(catCode)) ? "selected" : "";

                out.println("<option value=\""+catCode+"\"" + catVenteSelect +">"+catLib+"</option>)");
            }
        %>
    </select>

    <input type="submit"/>
</form>

<a href="listerLesVentesAVenir"><button>Reset</button></a>
<table  class="table table-bordered table-striped table-condensed">  
    <thead>
        <tr>             
            <th>Id</th>
            <th>Nom</th>
            <th>Date début</th>
            <th>Catégorie</th>  
        </tr>
    </thead>
    <tbody>
        <tr>
            <%
            for(int i = 0; i < lesVentes.size();i++)
            {

                Vente uneVente = lesVentes.get(i);
                out.println("<tr><td>");
                out.println(uneVente.getId());
                out.println("</a></td>");

                out.println("<td>");
                out.println(uneVente.getNom());
                out.println("</td>");

                out.println("<td>");
                out.println(uneVente.getDateDebut());
                out.println("</td>");

                out.println("<td>");
                out.println(uneVente.getUneCategVente().getLibelle());
                out.println("</td>");

                out.println("<td><a href ='../ServletClient/listerLesClients?codeCat="+ uneVente.getUneCategVente().getCode()+ "'>");
                out.println("Lister les clients interessés");
                out.println("</td>");

                out.println("<td><a href ='../ServletCourriel/listerLesCourriels?codeVente="+ uneVente.getId() + "'>");
                out.println("Lister les courriels");
                out.println("</td>");

                out.println("<td><a href ='../ServletCheval/listerLesChevauxParVentes?idVente="+ uneVente.getId() + "'>");
                out.println("Lister les chevaux");
                out.println("</td>");

            }
            %>
        </tr>
    </tbody>
</table>

<jsp:include page="/vues/include/footer.jsp" />