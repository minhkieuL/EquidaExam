<%-- 
    Document   : lieuModifier
    Created on : 9 nov. 2018, 07:33:01
    Author     : MartinJ
--%>
<%@page import="servlets.ServletBase"%>
<%@page import="modele.Lieu"%>
<%@page import="formulaires.LieuForm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
        LieuForm form = null;
        try {
                form = (LieuForm) ServletBase.getForm(request);
        } catch (ClassCastException e) {

        }

        request.setAttribute("form", form);
        Lieu unLieu = (Lieu) request.getAttribute("pLieu");
%>

<jsp:include page="/vues/include/header.jsp" />
<jsp:include page="/vues/include/erreurs_form.jsp" />

<h1 class="center-align">Modifier un lieu</h1>

<div class="row">
    <form class="col s10 push-s1 l8 push-l2 center-align" action="lieuModifier" method="POST">
        <input value="<% out.println(unLieu.getId());%>" id="codeOrigin" name="codeOrigin" type="hidden" size="4" maxlength="4">

        <div class="row">
            <div class="input-field col s12 l6">
                <input id="ville" type="text" name="ville" size="50" minlength="3" maxlength="50" class="validate" value="<%= unLieu.getVille()%>">      
                <label for="ville">Ville : </label>
            </div>

            <div class="input-field col s12 l6">
                <input id="nbBoxes" type="number" name="nbBoxes" size="2" maxlength="2" class="validate" value="<%= unLieu.getNbBoxes()%>">
                <label for="nbBoxes">Nombre de Boxes : </label>
            </div>
        </div>
                
        <div class="row">
            <div class="input-field col s12">
                <input id="commentaire" type="text" name="commentaire" size="200" minlength="5" maxlength="200" class="validate" value="<%= unLieu.getCommentaire()%>">
                <label for="commentaire">Commentaire : </label>
            </div>
        </div>
        <input type="submit" name="valider" id="valider" value="Valider"/>
    </form>
</div>

<jsp:include page="/vues/include/footer.jsp" />