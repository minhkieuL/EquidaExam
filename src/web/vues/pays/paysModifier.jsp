<%-- 
    Document   : paysModifier
    Created on : 19 oct. 2018, 06:35:24
    Author     : slam
--%>

<%@page import="modele.Pays"%>
<%@page import="formulaires.PaysForm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vues/include/header.jsp" />
<h1>Modifier un Pays</h1>

<%
	PaysForm form = (PaysForm) request.getAttribute("form");
    Pays unPays = (Pays)request.getAttribute("pPays"); 
%>

<form class="form-inline" action="paysModifier" method="POST">
    
    <input value="<% out.println(unPays.getCode());%>" id="codeOrigin" name="codeOrigin" type="hidden" size="4" maxlength="4"></br>
    <label for="code">Code : </label><input value="<% out.println(unPays.getCode());%>" id="code" type="text" name="code"  size="4" maxlength="4"></br>
    <label for="nom">Nom : </label><input value="<% out.println(unPays.getNom());%>" id="nom"  type="text"  name="nom" size="15" maxlength="30"></br>
    
    <input type="submit" name="valider" id="valider" value="Valider"/>
</form>

<jsp:include page="/vues/include/footer.jsp" />
