<%-- 
    Document   : chevalConsulter
    Created on : 16 oct. 2018, 12:54:38
    Author     : slam
--%>

<%@page import="modele.Cheval"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vues/include/header.jsp" />

<%
Cheval unCheval = (Cheval)request.getAttribute("pCheval");
%>

<div class="row">
	<div class="col s12 l6 right valign-wrapper">
		<img src="<%= request.getContextPath()%>/img/1.jpg" class="responsive-img "/>
	</div>
	<div class="col s12 l6 left">
		<h2><%= unCheval.getNom()%></h2>
		<p class="tooltipped" data-position="bottom" data-tooltip="<%= unCheval.getTypeCheval().getDesc()%>">Race : <%= unCheval.getTypeCheval().getLibelle() %></p>
		<p>Sexe : <%= (unCheval.getMale()) ? "Male" : "Femelle"%></p>
		<p>Sire : <%= unCheval.getSire() %></p>
		<p>Mère : <%= (unCheval.getMere() != null) ? "<a href=?id="+unCheval.getMere().getId()+">"+unCheval.getMere().getSire()+"</a>" : "Non renseignée"%></p>
		<p>Père : <%= (unCheval.getPere() != null) ? "<a href=?id="+unCheval.getPere().getId()+">"+unCheval.getPere().getSire()+"</a>" : "Non renseigné"%></p>
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