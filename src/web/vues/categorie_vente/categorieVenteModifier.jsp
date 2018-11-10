<%-- 
    Document   : categorieVenteModifier
    Created on : 12 oct. 2018, 08:50:00
    Author     : BottonL
--%>

<%@page import="modele.CategVente"%>
<%@page import="formulaires.CategorieForm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vues/include/header.jsp" />

<h2 class="center-align">Modifier une catégorie de vente</h2>

<%
	CategorieForm form = (CategorieForm) request.getAttribute("form");
        CategVente uneCategVente = (CategVente)request.getAttribute("pCategVente"); 
%>

<div class="row">
	<form class="col s10 push-s1 l6 push-l3 center-align" action="categorieVenteModifier" method="POST">
		<input value="<% out.println(uneCategVente.getCode());%>" id="codeOrigin" name="codeOrigin" type="hidden" size="4" maxlength="4" >
		<div class="row">
			<div class="input-field col s12">
				<input value="<% out.println(uneCategVente.getCode());%>" id="code" type="text" name="code" size="4" maxlength="4" class="validate">
				<label for="code">Code : </label>
			</div>
		</div>
				
		<div class="row">
			<div class="input-field col s12">
				<input value="<% out.println(uneCategVente.getLibelle());%>" id="libelle" type="text" name="libelle" size="15" maxlength="30" class="validate">
				<label for="libelle">Libelle : </label>
			</div>
		</div>
				
		<input type="submit" name="valider" id="valider" value="Valider"/>
	</form>
</div>
			
<jsp:include page="/vues/include/footer.jsp" />