<%-- 
    Document   : chevalConsulter
    Created on : 16 oct. 2018, 12:54:38
    Author     : slam
--%>

<%@page import="modele.DirecteurGeneral"%>
<%@page import="servlets.ServletCheval"%>
<%@page import="modele.Utilisateur"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modele.Cheval"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vues/include/header.jsp" />

<%
	Cheval unCheval = (Cheval)request.getAttribute("pCheval");

	ArrayList<Cheval> lesChevaux = (ArrayList) request.getAttribute("pLesChevaux");
	
	Utilisateur user = (Utilisateur) session.getAttribute("user");
%>

<div class="row">
	<div class="col s12 l6 right valign-wrapper">
		<img src="<%= request.getContextPath()%>/img/1.jpg" class="responsive-img "/>
	</div>
	
	<div class="col s12 l6 left">
		<h2><%= unCheval.getNom()%></h2>
		<p class="tooltipped" data-position="bottom" data-tooltip="<%= unCheval.getTypeCheval().getDesc()%>">Race : <%= unCheval.getTypeCheval().getLibelle() %></p>
		<p>Sexe : <%= (unCheval.getMale()) ? "Male" : "Femelle"%></p>
		<p>Sire : <%= (unCheval.getSire() != null) ? unCheval.getSire() : "Non renseigné" %></p>
		<p>Mère : <%= (unCheval.getMere() != null) ? "<a href=?id="+unCheval.getMere().getId()+">"+unCheval.getMere().getSire()+"</a>" : "Non renseignée"%></p>
		<p>Père : <%= (unCheval.getPere() != null) ? "<a href=?id="+unCheval.getPere().getId()+">"+unCheval.getPere().getSire()+"</a>" : "Non renseigné"%></p>
		<% 
			if(user instanceof DirecteurGeneral) {
				out.println("<p><a href='"+ ServletCheval.URL_ARCHIVER_CHEVAL +"?id=" + unCheval.getId() + "'>");
				out.println("Archiver");
				out.println("</a></p>");
			} else {	
			}
		%>
	</div>
	
	<div class="row">
		<div class="col s12">
			<h3>Les courses</h3>
			<p>Aucune course n'a été enregistré pour ce cheval</p>
		</div>
	</div>
	
	<div class="row">
		<div class="col s12">
			<h3>Infos Vente</h3>
			<p>Ce cheval n'est pas encore en vente</p>
		</div>
	</div>
</div>

<jsp:include page="/vues/include/footer.jsp" />