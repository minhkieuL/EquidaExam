<%-- 
    Document   : clientModifier
    Created on : 12 nov. 2018, 09:57:46
    Author     : Leah
--%>

<%@page import="servlets.ServletBase"%>
<%@page import="modele.CategVente"%>
<%@page import="modele.Pays"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modele.Client"%>
<%@page import="formulaires.ClientForm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
	ClientForm form = null;
	try {
		form = (ClientForm) ServletBase.getForm(request);
	} catch (ClassCastException e) {

	}

	request.setAttribute("form", form);
	Client unClient = (Client) request.getAttribute("pClient");
%>

<jsp:include page="/vues/include/header.jsp" />
<jsp:include page="/vues/include/erreurs_form.jsp" />

<h1 class="center-align">Modifier un client</h1>

<div class="row">
    <form class="col s10 push-s1 l8 push-l2 center-align" action="clientModifier" method="POST">
        <input id="idOrigin" type="hidden" name="idOrigin" size="30" maxlength="30" value="<%= unClient.getId()%>">

        <div class="row">
            <div class="input-field col s12 l6">
                <input id="nom" type="text" name="nom" size="30" minlength="3" maxlength="40" value="<%= unClient.getNom()%>" class="validate">
                <label for="nom">Nom : </label>
            </div>

            <div class="input-field col s12 l6">
                <input id="prenom"  type="text"  name="prenom" size="30" minlength="2" maxlength="40" value="<%= unClient.getPrenom()%>" class="validate">      
                <label for="prenom">Prénom : </label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
                <input id="mail"  type="email"  name="mail" size="60" minlength="3" maxlength="60" value="<%= unClient.getMail()%>" class="validate">
                <label for="mail">Mail : </label>
            </div>
        </div>

        <div class="row">
            <div class="input-field col s12">
                <input id="rue"  type="text"  name="rue" size="30" minlength="5" maxlength="60" value="<%= unClient.getRue()%>" class="validate">
                <label for="rue">Adresse : </label>
            </div>
        </div>

        <div class="row">
            <div class="input-field col s12 l6">
                <label for="copos">Code postal : </label>
                <input id="copos"  type="text"  name="copos" size="5" minlength="5" maxlength="5" value="<%= unClient.getCopos()%>" class="validate">
            </div>

            <div class="input-field col s12 l6">
                <input id="ville"  type="text"  name="ville" size="40" minlength="3" maxlength="40" value="<%= unClient.getVille()%>" class="validate">
                <label for="ville">Ville : </label>
            </div>
        </div>

        <div class="row">
            <div class="input-field col s12 l6">
                <select name="pays" id="choix_pays">
                    <%
						ArrayList<Pays> lesPays = (ArrayList) request.getAttribute("pLesPays");
						for (int i = 0; i < lesPays.size(); i++) {
							Pays p = lesPays.get(i);
							out.println("<option value='" + p.getCode() + "'" + ((p.getCode().equals(unClient.getPays().getCode())) ? "selected" : "") + ">" + p.getNom() + "</option>");
						}
                    %>
                </select>
                <label for="pays">Pays : </label>
            </div>

            <div class="input-field col s12 l6">
                <select name="categVente" size="5" multiple>
                    <%
						ArrayList<CategVente> lesCategVente = (ArrayList) request.getAttribute("pLesCategVente");
						for (int i = 0; i < lesCategVente.size(); i++) {
							CategVente cv = lesCategVente.get(i);
							boolean clientHasCategVente = false;
							for (CategVente clientCategVente : unClient.getLesCategVentes()) {
								if (cv.getCode().equals(clientCategVente.getCode())) {
									clientHasCategVente = true;
									break;
								}
							}
							out.println("<option value ='" + cv.getCode() + "'" + ((clientHasCategVente) ? "selected" : "") + ">" + cv.getLibelle() + "</option>");

						}
                    %>
                </select>
                <label for="categVente">Catégorie de vente : </label>
            </div>

        </div>

        <input type="submit" name="valider" id="valider" value="Valider"/>
    </form>
</div>

<jsp:include page="/vues/include/footer.jsp" />