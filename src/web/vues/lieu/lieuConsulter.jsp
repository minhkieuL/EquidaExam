<%-- 
    Document   : paysConsulter
    Created on : 16 oct. 2018, 16:34:12
    Author     : slam
--%>

<%@page import="modele.Lieu"%>
<%@page import="modele.Pays"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vues/include/header.jsp" />
<h1 class="center-align">Infos sur l'ajout du lieu</h1>

<%
	Lieu unLieu = (Lieu) request.getAttribute("pLieu");
%>
<div class="row">
	<table class="col s12 table table-bordered table-striped table-condensed">
		<tr>
			<td>Ville :</td>
			<td><% out.println(unLieu.getVille());%></td>
		</tr>
		<tr>
			<td>Nombre de Boxes :</td>
			<td><%  out.println(unLieu.getNbBoxes());%></td>
		</tr>
		<tr>
			<td>Commentaire :</td>
			<td><%  out.println(unLieu.getCommentaire());%></td>
		</tr>
	</table>
</div>

<jsp:include page="/vues/include/footer.jsp" />