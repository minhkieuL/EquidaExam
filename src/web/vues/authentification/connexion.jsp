<%-- 
    Document   : Connexion au site
    Created on : 17/10/18
    Author     : MartinJ
--%>

<%@page import="servlets.ServletBase"%>
<%@page import="formulaires.CompteForm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
	CompteForm form = null;
	try {
		form = (CompteForm) ServletBase.getForm(request);
	} catch (ClassCastException e) {
		
	}
	
	request.setAttribute("form", form);
%>

<jsp:include page="/vues/include/header.jsp" />

<h2 class="center-align">Connexion</h2>

<jsp:include page="/vues/include/erreurs_form.jsp" />

<div class="row">
    <form action="connexion" method="POST" class="col s10 push-s1 l8 push-l2 center-align">
        <div class="row">
            <div class="input-field col s12">
                <input type="text" id="login" name="login" class="validate"/>
                <label for="login">Nom d'utilisateur</label>
            </div>
        </div>

        <div class="row">
            <div class="input-field col s12">
                <input type="password" id="passwd" name="passwd" class="validate"/>
                <label for="passwd">Mot de passe</label>
            </div>
        </div>

        <input type="submit" value="Valider"/>
    </form>
</div>

<jsp:include page="/vues/include/footer.jsp" />