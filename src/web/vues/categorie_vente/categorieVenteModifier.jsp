<%-- 
    Document   : categorieVenteModifier
    Created on : 12 oct. 2018, 08:50:00
    Author     : paul_collet
--%>

<%@page import="modele.CategVente"%>
<%@page import="formulaires.CategorieForm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vues/include/header.jsp" />
<h1>Modifier une catégorie de vente</h1>

<%
	CategorieForm form = (CategorieForm) request.getAttribute("form");
        CategVente uneCategVente = (CategVente)request.getAttribute("pCategVente"); 
%>

<form class="form-inline" action="categorieVenteModifier" method="POST">
    
    <input value="<% out.println(uneCategVente.getCode());%>" id="codeOrigin" name="codeOrigin" type="hidden" size="4" maxlength="4"></br>
    <label for="code">Code : </label><input value="<% out.println(uneCategVente.getCode());%>" id="code" type="text" name="code"  size="4" maxlength="4"></br>
    <label for="libelle">Libelle : </label><input value="<% out.println(uneCategVente.getLibelle());%>" id="libelle"  type="text"  name="libelle" size="15" maxlength="30"></br>
    
    <input type="submit" name="valider" id="valider" value="Valider"/>
</form>

<jsp:include page="/vues/include/footer.jsp" />