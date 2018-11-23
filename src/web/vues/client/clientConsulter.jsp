<%-- 
    Document   : consulterClient
    Created on : 23 juin 2017, 10:33:23
    Author     : MartinJ
--%>

<%@page import="modele.CategVente"%>
<%@page import="modele.DirecteurGeneral"%>
<%@page import="modele.Utilisateur"%>
<%@page import="modele.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vues/include/header.jsp" />

<%
        Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");
        Client unClient = (Client) request.getAttribute("pClient");
%>

<div class="row">
    <div class="col s12 l6 right valign-wrapper">
        <img src="<%= request.getContextPath()%>/img/1.jpg" class="responsive-img "/>
    </div>

    <div class="col s12 l6 left">
        <h2><%= unClient.getNom() + " " + unClient.getPrenom()%></h2>
        <p><%= unClient.getRue()%></p>
        <p><%= unClient.getCopos() + " " + unClient.getVille()%></p>
        <p><a href="mailto:<%= unClient.getMail()%>"><%= (unClient.getMail() != null) ? unClient.getMail() : ""%></a></p>
        <p><%= unClient.getPays().getNom()%></p>
        <p>Catégorie de vente : </p>
        <%
                        if (unClient.getLesCategVentes() != null) {
                                for (CategVente categ : unClient.getLesCategVentes()) {
        %>

        <p>		- <%= categ.getLibelle()%></p>

        <%
                        }
                } else {
        %>
        <p>Ce client n'est inscrit à aucune catégorie de vente</p>
        <%
                        }
        %>


        <% if (user instanceof DirecteurGeneral) {
                                //TODO afficher si le client est archivé ou non
                        }%>
    </div>

    <div class="row">
        <div class="col s12">
            <h3>Chevaux en attente d'une vente</h3>
            <p>Aucun cheval n'est en attente d'être associé à une vente</p>
        </div>
    </div>

    <div class="row">
        <div class="col s12">
            <h3>Chevaux en attente de validation</h3>
            <p>Aucun cheval n'est en attente de validation</p>
        </div>
    </div>
</div>

<jsp:include page="/vues/include/footer.jsp" />