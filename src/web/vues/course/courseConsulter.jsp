<%-- 
    Document   : courseConsulter
    Created on : 9 nov. 2018, 08:02:51
    Author     : slam
--%>

<%@page import="modele.Course"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vues/include/header.jsp" />
<h1>Infos sur l'Ajout de Course</h1>

<%
Course uneCourse = (Course)request.getAttribute("pCourse");
%>

<table class="table table-bordered table-striped table-condensed">
    <tr>
        <td>Nom :</td>
        <td><%  out.println(uneCourse.getNom());%></td>
    </tr>
	<tr>
        <td>Date :</td>
        <td><%  out.println(uneCourse.getDate());%></td>
    </tr>
	<tr>
        <td>Ville :</td>
        <td><%  out.println(uneCourse.getVille());%></td>
    </tr>
</table>
<jsp:include page="/vues/include/footer.jsp" />
