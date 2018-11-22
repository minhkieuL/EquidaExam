<%-- 
    Document   : paysAjouter
    Created on : 16 oct. 2018, 16:04:08
    Author     : slam
--%>
<%@page import="servlets.ServletBase"%>
<%@page import="formulaires.PaysForm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%
        PaysForm form = null;
        try {
                form = (PaysForm) ServletBase.getForm(request);
        } catch (ClassCastException e) {

        }

        request.setAttribute("form", form);
%>

<jsp:include page="/vues/include/header.jsp" />

<h1 class="center-align">Nouveau pays</h1>

<jsp:include page="/vues/include/erreurs_form.jsp" />

<div class="row">
    <form class="col s10 push-s1 l8 push-l2 center-align" action="paysAjouter" method="POST">
        <div class="row">
            <div class="input-field col s12">
                <input id="code" type="text" name="code" size="3" minlength="3" maxlength="3" class="validate">
                <label for="code">Code : </label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <input id="nom" type="text" name="nom" size="30" minlength="3" maxlength="30" class="validate">      
                <label for="nom">Nom : </label>
            </div>
        </div>

        <input type="submit" name="valider" id="valider" value="Valider"/>
    </form>
</div>
<jsp:include page="/vues/include/footer.jsp" />