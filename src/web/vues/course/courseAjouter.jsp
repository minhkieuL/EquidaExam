<%-- 
    Document   : courseAjouter
    Created on : 9 nov. 2018, 07:53:26
    Author     : slam
--%>

<%@page import="formulaires.CourseForm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vues/include/header.jsp" />

<h1 class="center-align">Nouvelle Course</h1>

<%
	CourseForm form = (CourseForm) request.getAttribute("form");
%>

<div class="row">
	<form class="col s10 push-s1 l8 push-l2 center-align" action="courseAjouter" method="POST">
		<div class="row">
			<div class="input-field col s12">
				<input id="nom" type="text"  name="nom" size="30" maxlength="30" class="validate">   
				<label for="nom">Nom : </label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12 l6">
				<input id="date" type="text"  name="date" class="datepicker validate">   
				<label for="date">Date : </label>   
			</div>

			<div class="input-field col s12 l6">
				<input id="ville" type="text"  name="ville" size="30" maxlength="30" class="validate">      
				<label for="ville">Ville : </label>
			</div>
		</div>

		<input type="submit" name="valider" id="valider" value="Valider"/>
	</form>
</div>

<jsp:include page="/vues/include/footer.jsp" />
