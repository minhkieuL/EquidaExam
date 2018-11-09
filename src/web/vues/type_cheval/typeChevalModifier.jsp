<%-- 
    Document   : typeChevalModifier
    Created on : 6 nov. 2018, 14:53:50
    Author     : slam
--%>

<%@page import="formulaires.TypeChevalForm"%>
<%@page import="modele.TypeCheval"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vues/include/header.jsp" />
		<h1>Modifier un Type de Cheval</h1>

<%
	TypeChevalForm form = (TypeChevalForm) request.getAttribute("form");
        TypeCheval unTypeCheval = (TypeCheval)request.getAttribute("pTypeCheval"); 
%>

<form class="form-inline" action="typeChevalModifier" method="POST">
    
    <input value="<% out.println(unTypeCheval.getId());%>" id="codeOrigin" name="codeOrigin" type="hidden" size="4" maxlength="4"></br>
    <label for="libelle">Libelle : </label><input value="<% out.println(unTypeCheval.getLibelle());%>" id="libelle" type="text" name="libelle"  size="15" maxlength="50"></br>
    <label for="description">Description : </label><input value="<% out.println(unTypeCheval.getDesc());%>" id="description"  type="text"  name="description" size="30" maxlength="100"></br>
    
    <input type="submit" name="valider" id="valider" value="Valider"/>
</form>
	
<jsp:include page="/vues/include/footer.jsp" />