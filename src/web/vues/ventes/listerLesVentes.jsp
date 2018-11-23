<%-- 
    Document   : Lister les Ventes 
    Created on : 22/06/2017, 07:43
    Author     : Zakina
--%>

<%@page import="servlets.ServletVentes"%>
<%@page import="modele.DirecteurGeneral"%>
<%@page import="modele.Utilisateur"%>
<%@page import="modele.CategVente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modele.Vente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vues/include/header.jsp" />

<h1 class="center-align">Liste des ventes</h1>

<%
        ArrayList<Vente> lesVentes = (ArrayList) request.getAttribute("pLesVentes");
        ArrayList<CategVente> lesCatVentes = (ArrayList) request.getAttribute("pLesCatVentes");

        Utilisateur user = (Utilisateur) session.getAttribute("user");
%>
<div class="row">
    <div class="col s12">
        <form method="GET">
            <select
                name="catVente">
                <%
                                        for (CategVente catVente : lesCatVentes) {
                                                String catCode = catVente.getCode();
                                                String catLib = catVente.getLibelle();

                                                String catVenteSelect = "";
                                                if (request.getParameter("catVente") != null) {
                                                        catVenteSelect = (request.getParameter("catVente").equals(catCode)) ? "selected" : "";
                                                }

                                                out.println("<option value=\"" + catCode + "\"" + catVenteSelect + ">" + catLib + "</option>)");
                                        }
                %>
            </select>

            <input type="submit"/>
        </form>

        <a href="<%= ServletVentes.URL_LISTER_VENTES%>"><button>Toutes les ventes</button></a>
    </div>
</div>

<div class="row">
    <div class="col s12">
        <%
                        for (int i = 0; i < lesVentes.size(); i++) {

                                Vente uneVente = lesVentes.get(i);
        %>
        <h2><%= uneVente.getNom()%></h2>
        <p><%= uneVente.getUneCategVente().getLibelle()%></a></p>
        <p><%= "Date de la vente : " + uneVente.getDateDebut()%></p>
        <p><a href="<%= ServletVentes.URL_CONSULTER_VENTE%>?id=<%=uneVente.getId()%>">Plus d'informations</a></p>
        <hr/>
        <% }
        %>
    </div>
</div>



<jsp:include page="/vues/include/footer.jsp" />