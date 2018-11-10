<%-- 
    Document   : chevalAjouter
    Created on : 12 oct. 2018, 09:03:08
    Author     : slam
--%>

<%@page import="modele.Cheval"%>
<%@page import="modele.Lot"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modele.TypeCheval"%>
<%@page import="formulaires.ChevalForm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vues/include/header.jsp" />

<h1 class="center-align">Nouveau cheval</h1>

<%
	//Client client=(Client)request.getAttribute("client");
	ChevalForm form = (ChevalForm) request.getAttribute("form");
	ArrayList<TypeCheval> lesTypeCheval = (ArrayList) request.getAttribute("pLesTypeCheval");
%>

<div class="row">
	<form class="col s10 push-s1 l6 push-l3 center-align" action="ajouterCheval" method="POST">
		<div class="row">
			<div class="input-field col l6 s12">
				<input id="nom" type="text" name="nom" size="30" maxlength="30" class="validate">
				<label for="nom">Nom : </label>
			</div>

			<div class="input-field col l6 s12">
				<select id="sexe" name="sexe">
					<option value="0">Femelle</option>
					<option value="1">Male</option>
				</select>
				<label for="sexe">Sexe : </label>
			</div>
		</div>
		
		<div class="row">
			<div class="input-field col s12">
				<input id="sire" type="text" name="sire" size="30" maxlength="50" class="validate">
				<label for="sire">SIRE : </label>
			</div>
		</div>
		
		<%-- Champ Liste des typeCheval --%>
		<div class="row">
			<div class="input-field col s12">
				<select name="typeCheval">

					<%
						for (int i = 0; i < lesTypeCheval.size(); i++) {
							TypeCheval tc = lesTypeCheval.get(i);
					%>
					<option value="<%=tc.getId()%>"><%=tc.getLibelle()%></option>
					<%
						}
					%>
				</select>
				<label for="typeCheval">Race: </label>
			</div>
		</div>
				
		<div class="row">
			 <div class="input-field col l6 s12">
				<input id="pere" type="text" name="pere" size="30" maxlength="50" class="validate">
				<label for="pere">Père : </label>
			</div>

			<div class="input-field col l6 s12">
				<input id="mere" type="text" name="mere" size="30" maxlength="50" class="validate">
				<label for="mere">Mère : </label>
			</div>
		</div>
				
		<input type="submit" name="valider" id="valider" value="Valider"/>
	</form>
</div>

<jsp:include page="/vues/include/footer.jsp" />