<%-- 
    Document   : courseModifier
    Created on : 9 nov. 2018, 09:31:06
    Author     : slam
--%>

<%@page import="modele.Course"%>
<%@page import="formulaires.CourseForm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vues/include/header.jsp" />
<h1>Modifier une Course</h1>

<%
	CourseForm form = (CourseForm) request.getAttribute("form");
    Course uneCourse = (Course)request.getAttribute("pCourse"); 
%>

<form class="form-inline" action="courseModifier" method="POST">
    
    <input value="<% out.println(uneCourse.getId());%>" id="codeOrigin" name="codeOrigin" type="hidden" size="4" maxlength="4"></br>
    <label for="nom">Nom : </label><input value="<% out.println(uneCourse.getNom());%>" id="nom"  type="text"  name="nom" size="15" maxlength="30"></br>
	<label for="date">Date (AAAA/MM/JJ): </label><input value="<% out.println(uneCourse.getDate());%>" id="date" type="text" name="date"></br>
	<label for="ville">Ville : </label><input value="<% out.println(uneCourse.getVille());%>" id="ville" type="text" name="ville"  size="15" maxlength="30"></br>
    
    <input type="submit" name="valider" id="valider" value="Valider"/>
</form>

<jsp:include page="/vues/include/footer.jsp" />
