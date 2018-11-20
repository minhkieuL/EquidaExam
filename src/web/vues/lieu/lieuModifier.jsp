<%-- 
    Document   : lieuModifier
    Created on : 9 nov. 2018, 07:33:01
    Author     : MartinJ
--%>
<%@page import="modele.Lieu"%>
<%@page import="formulaires.LieuForm"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/vues/include/header.jsp" />
<h1 class="center-align">Modifier Lieu</h1>

<%
	LieuForm form = (LieuForm) request.getAttribute("form");
    Lieu unLieu = (Lieu)request.getAttribute("pLieu"); 
%>

<div class="row">
	<form class="col s10 push-s1 l8 push-l2 center-align" action="lieuModifier" method="POST">
		<input value="<% out.println(unLieu.getId());%>" id="codeOrigin" name="codeOrigin" type="hidden" size="4" maxlength="4">
   
		<div class="row">
			<div class="input-field col s12 l6">
				<input id="ville"  type="text"  name="ville" size="30" maxlength="30" class="validate" value="<%= unLieu.getVille()%>">      
				<label for="ville">Ville : </label>
			</div>

			<div class="input-field col s12 l6">
				<input id="nbBoxes" type="number" name="nbBoxes"  size="30" maxlength="30" class="validate" value="<%= unLieu.getNbBoxes()%>">
				<label for="nbBoxes">Nombre de Boxes : </label>
			</div>

		</div>
		<div class="row">
			<div class="input-field col s12">
			<input id="commentaire"  type="text"  name="commentaire" size="30" maxlength="30" class="validate" value="<%= unLieu.getCommentaire()%>">
			<label for="commentaire">Commentaire : </label>
			</div>
		</div>
		<input type="submit" name="valider" id="valider" value="Valider"/>
	</form>
</div>

<jsp:include page="/vues/include/footer.jsp" />