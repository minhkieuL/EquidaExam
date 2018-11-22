<%-- 
    Document   : LieuAjouter
    Created on : 16 oct. 2018, 16:04:08
    Author     : Maxence
--%>

<%@page import="servlets.ServletBase"%>
<%@page import="formulaires.LieuForm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
        LieuForm form = null;
        try {
                form = (LieuForm) ServletBase.getForm(request);
        } catch (ClassCastException e) {

        }

        request.setAttribute("form", form);
%>

<jsp:include page="/vues/include/header.jsp" />
<jsp:include page="/vues/include/erreurs_form.jsp" />

<h1 class="center-align">Ajout d'un nouveau lieu</h1>

<div class="row">
    <form class="col s10 push-s1 l8 push-l2 center-align" action="ajouterLieu" method="POST">
        <div class="row">
            <div class="input-field col s12 l6">
                <input id="ville" type="text"  name="ville" size="50" minlength="3" maxlength="50" class="validate">      
                <label for="ville">Ville : </label>
            </div>

            <div class="input-field col s12 l6">
                <input id="nbBoxes" type="number" name="nbBoxes" size="2" maxlength="2" class="validate">
                <label for="nbBoxes">Nombre de boxes : </label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <input id="commentaire" type="text" name="commentaire" size="200" minlength="5" maxlength="200" class="validate">      
                <label for="commentaire">Commentaire : </label>
            </div>
        </div>
        <input type="submit" name="valider" id="valider" value="Valider"/>
    </form>
</div>

<jsp:include page="/vues/include/footer.jsp" />