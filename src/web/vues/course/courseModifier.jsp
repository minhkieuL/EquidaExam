<%-- 
    Document   : courseModifier
    Created on : 9 nov. 2018, 09:31:06
    Author     : slam
--%>

<%@page import="servlets.ServletBase"%>
<%@page import="modele.Course"%>
<%@page import="formulaires.CourseForm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
	CourseForm form = null;
	try {
		form = (CourseForm) ServletBase.getForm(request);
	} catch (ClassCastException e) {
		
	}
	
	request.setAttribute("form", form);
	Course uneCourse = (Course) request.getAttribute("pCourse");
%>

<jsp:include page="/vues/include/header.jsp" />
<jsp:include page="/vues/include/erreurs_form.jsp" />

<h1 class="center-align">Modifier une course</h1>

<div class="row">
	<form class="col s10 push-s1 l8 push-l2 center-align" action="courseModifier" method="POST">
		<input value="<% out.println(uneCourse.getId());%>" id="codeOrigin" name="codeOrigin" type="hidden" size="4" maxlength="4"></br>
		<div class="row">
			<div class="input-field col s12">
				<input value="<% out.println(uneCourse.getNom());%>" id="nom"  type="text" name="nom" size="30" minlength="5" maxlength="32" class="validate">
				<label for="nom">Nom : </label>
			</div>
		</div>
			
		<div class="row">
			<div class="input-field col s12 l6">
				<input value="<% out.println(uneCourse.getDate());%>" id="date" type="text" name="date" class="datepicker validate">
				<label for="date">Date : </label>
			</div>
				
			<div class="input-field col s12 l6">
				<input value="<% out.println(uneCourse.getVille());%>" id="ville" type="text" name="ville" size="15" minlength="3" maxlength="32" class="validate">
				<label for="ville">Ville : </label>
			</div>
		</div>
				
		<input type="submit" name="valider" id="valider" value="Valider"/>
	</form>
</div>

<jsp:include page="/vues/include/footer.jsp" />
