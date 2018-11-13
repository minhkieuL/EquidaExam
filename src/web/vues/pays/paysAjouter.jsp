<%-- 
    Document   : paysAjouter
    Created on : 16 oct. 2018, 16:04:08
    Author     : slam
--%>
<%@page import="formulaires.PaysForm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vues/include/header.jsp" />

<h1 class="center-align">Nouveau pays</h1>

<%
	PaysForm form = (PaysForm) request.getAttribute("form");
%>
<div class="row">
	<form class="col s10 push-s1 l8 push-l2 center-align" action="paysAjouter" method="POST">
		<div class="row">
			<div class="input-field col s12">
				<input id="code" type="text" name="code"  size="30" maxlength="30" class="validate">
				<label for="code">Code : </label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<input id="nom"  type="text"  name="nom" size="30" maxlength="30" class="validate">      
				<label for="nom">Nom : </label>
			</div>
		</div>

		<input type="submit" name="valider" id="valider" value="Valider"/>
	</form>
</div>
<jsp:include page="/vues/include/footer.jsp" />