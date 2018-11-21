<%-- 
    Document   : courseAjouter
    Created on : 9 nov. 2018, 07:53:26
    Author     : MartinJ
--%>

<%@page import="servlets.ServletCourriel"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modele.Vente"%>
<%@page import="formulaires.CourseForm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vues/include/header.jsp" />

<h1 class="center-align">Envoyer un mail</h1>

<%
	ArrayList<Vente> pVente = (ArrayList<Vente>) request.getAttribute("pVentes");
%>

<div class="row">
	<form class="col s10 push-s1 l8 push-l2 center-align" action="<%= ServletCourriel.URL_AJOUTER_COURIEL%>" method="POST"  enctype="multipart/form-data">
		<div class="row">
			<div class="input-field col s12 l6">
				<input id="objet" type="text"  name="objet" class="validate">   
				<label for="objet">Objet : </label>   
			</div>

			<div class="input-field col s12 l6">
				<select name="vente">
					<%
						for (Vente v : pVente) {
					%>
					<option value="<%= v.getId()%>"><%= v.getNom()%></option>
					<%
						}
					%>
				</select>
				<label for="vente">Vente : </label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<textarea id="corps" name="corps" class="materialize-textarea"></textarea>
				<label for="corps">Corps : </label>
			</div>
		</div>

		<div class="row">
			<div class="col s12 input-field">
				<input type="file" name="file" multiple="true" />
			</div>
		</div>

		<input type="submit" value="Envoyer"/>
	</form>
</div>

<jsp:include page="/vues/include/footer.jsp" />
