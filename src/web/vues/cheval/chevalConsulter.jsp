<%-- 
    Document   : chevalConsulter
    Created on : 16 oct. 2018, 12:54:38
    Author     : slam
--%>

<%@page import="modele.Lot"%>
<%@page import="servlets.ServletClient"%>
<%@page import="modele.Cheval"%>
<%@page import="modele.Utilisateur"%>
<%@page import="modele.DirecteurGeneral"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vues/include/header.jsp" />

<%
Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");
Cheval unCheval = (Cheval)request.getAttribute("pCheval");
Lot unLot = (Lot) request.getAttribute("pLot");
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
		<% if(user instanceof DirecteurGeneral){ %>
		<p>Propriétaire : <%= (unCheval.getClient() != null) ? "<a href="+ServletClient.URL_CONSULTER_CLIENT+"?id="+unCheval.getClient().getId()+">"+unCheval.getClient().getNom()+"</a>" : "Non renseigné"%></p>
		<% 
		} else { %>
		<p>Propriétaire : <%= (unCheval.getClient() != null) ? unCheval.getClient().getNom() : "Non renseigné"%></p>
		<% } %>
		<p>Prix de départ : <%= unLot.getPrixDepart() %>€</p>
		
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