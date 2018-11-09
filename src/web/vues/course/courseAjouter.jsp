<%-- 
    Document   : courseAjouter
    Created on : 9 nov. 2018, 07:53:26
    Author     : slam
--%>

<%@page import="formulaires.CourseForm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vues/include/header.jsp" />
<h1>NOUVELLE COURSE</h1>

<%
    CourseForm form = (CourseForm)request.getAttribute("form");
%>

<form class="form-inline" action="courseAjouter" method="POST">
    <label for="nom">Nom : </label><input id="nom"  type="text"  name="nom" size="30" maxlength="30">      
    </br>
    <br/>
	
	<label for="date">Date (AAAA/MM/JJ): </label><input id="date"  type="text"  name="date">      
    </br>
    <br/>
	
	<label for="ville">Ville : </label><input id="ville"  type="text"  name="ville" size="30" maxlength="30">      
    </br>
    <br/>

    <input type="submit" name="valider" id="valider" value="Valider"/>
</form>

<jsp:include page="/vues/include/footer.jsp" />
