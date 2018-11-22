<%-- 
    Document   : paysModifier
    Created on : 19 oct. 2018, 06:35:24
    Author     : slam
--%>

<%@page import="servlets.ServletBase"%>
<%@page import="modele.Pays"%>
<%@page import="formulaires.PaysForm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
        Pays unPays = (Pays) request.getAttribute("pPays");
        PaysForm form = null;
        try {
                form = (PaysForm) ServletBase.getForm(request);
        } catch (ClassCastException e) {

        }

        request.setAttribute("form", form);
%>

<jsp:include page="/vues/include/header.jsp" />

<h1 class="center-align">Modifier un Pays</h1>

<jsp:include page="/vues/include/erreurs_form.jsp" />

<div class="row">
    <form class="col s10 push-s1 l8 push-l2 center-align" action="paysModifier" method="POST">
        <input id="codeOrigin" type="hidden" name="codeOrigin" size="30" maxlength="30" value="<%= unPays.getCode()%>">

        <div class="row">
            <div class="input-field col s12">
                <input id="code" type="text" name="code" size="3" minlength="3" maxlength="3" value="<%= unPays.getCode()%>" class="validate">
                <label for="code">Code : </label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <input id="nom"  type="text"  name="nom" size="30" minlength="3" maxlength="30" value="<%= unPays.getNom()%>" class="validate">      
                <label for="nom">Nom : </label>
            </div>
        </div>

        <input type="submit" name="valider" id="valider" value="Valider"/>
    </form>
</div>

<jsp:include page="/vues/include/footer.jsp" />
