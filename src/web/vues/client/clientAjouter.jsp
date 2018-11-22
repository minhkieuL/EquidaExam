<%-- 
    Document   : ClientAjouter
    Created on : 22/06, 16:35:27
    Author     : Zakina
--%>

<%@page import="servlets.ServletBase"%>
<%@page import="modele.CategVente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modele.Pays"%>
<%@page import="formulaires.ClientForm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
	ClientForm form = null;
	try {
		form = (ClientForm) ServletBase.getForm(request);
	} catch (ClassCastException e) {
		
	}
	
	request.setAttribute("form", form);
%>

<jsp:include page="/vues/include/header.jsp" />
<jsp:include page="/vues/include/erreurs_form.jsp" />

<h2 class="center-align">Ajout d'un nouveau client</h2>

<div class="row">
    <form class="col s10 push-s1 l8 push-l2 center-align" action="ajouterClient" method="POST">
        <div class="row">
            <div class="input-field col s12 l6">
                <input id="nom" type="text" name="nom" size="40" minlength="3" maxlength="40" class="validate">
                <label for="nom">Nom : </label>
            </div>

            <div class="input-field col s12 l6">
                <input id="prenom" type="text"  name="prenom" size="40" minlength="2" maxlength="40" class="validate">      
                <label for="prenom">Prénom : </label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
                <label for="mail">Mail : </label>
                <input id="mail" type="email"  name="mail" size="60" minlength="3" maxlength="60" class="validate">
            </div>
        </div>

        <div class="row">
            <div class="input-field col s12">
                <input id="rue" type="text"  name="rue" size="60" minlength="5" maxlength="60" class="validate">
                <label for="rue">Adresse : </label>
            </div>
        </div>

        <div class="row">
            <div class="input-field col s12 l6">
                <label for="copos">Code postal : </label>
                <input id="copos" type="text"  name="copos" size="5" minlength="5" maxlength="5" class="validate">
            </div>

            <div class="input-field col s12 l6">
                <input id="ville" type="text"  name="ville" size="40" minlength="3" maxlength="40" class="validate">
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
                                                        out.println("<option value='" + p.getCode() + "'>" + p.getNom() + "</option>");
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
                                                        out.println("<option value ='" + cv.getCode() + "'>" + cv.getLibelle() + "</option>");
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
