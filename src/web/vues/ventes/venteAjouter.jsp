<%-- 
    Document   : venteAjouter
    Created on : 12 nov. 2018, 10:45:38
    Author     : Leah
--%>

<%@page import="modele.Lieu"%>
<%@page import="modele.CategVente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="formulaires.VenteForm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vues/include/header.jsp" />

<h1 class="center-align">Ajout d'une nouvelle vente</h1>

<%
        VenteForm form = (VenteForm) request.getAttribute("form");
%>
<div class="row">
    <form class="col s10 push-s1 l8 push-l2 center-align" action="venteAjouter" method="POST">
        <div class="row">
            <div class="input-field col s12">
                <input id="nom" type="text" name="nom" size="40" minlength="3" maxlength="40" class="validate">
                <label for="nom">Nom : </label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12 l6">
                <select name="categVente" size="5">
                    <%
                                                ArrayList<CategVente> lesCategVente = (ArrayList) request.getAttribute("pLesCategVente");
                                                for (int i = 0; i < lesCategVente.size(); i++) {
                                                        CategVente cv = lesCategVente.get(i);
                                                        out.println("<option value ='" + cv.getCode() + "'>" + cv.getLibelle() + "</option>");

                                                }
                    %>
                </select>
                <label for="categVente">Catégorie de la vente : </label>
            </div>
            <div class="input-field col s12 l6">
                <select name="lieu" size="5" multiple>
                    <%
                                                ArrayList<Lieu> lesLieux = (ArrayList) request.getAttribute("pLesLieux");
                                                for (int i = 0; i < lesLieux.size(); i++) {
                                                        Lieu lieu = lesLieux.get(i);
                                                        out.println("<option value ='" + lieu.getId() + "'>" + lieu.getVille() + "</option>");

                                                }
                    %>
                </select>
                <label for="lieu">Lieu : </label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <input id="dateDebut" type="text" name="dateDebut" class="datepicker validate">
                <label for="dateDebut">Date de début : </label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <input id="dateFin" type="text" name="dateFin" class="datepicker validate">
                <label for="dateFin">Date de fin : </label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <input id="dateVente" type="text" name="dateVente" class="datepicker validate">
                <label for="dateVente">Date de la vente : </label>
            </div>
        </div>
        <input type="submit" name="valider" id="valider" value="Valider"/>
    </form>
</div>
<jsp:include page="/vues/include/footer.jsp" />
