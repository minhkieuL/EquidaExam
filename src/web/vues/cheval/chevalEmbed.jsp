<%-- 
    Document   : chevalEmbed
    Created on : 11 nov. 2018, 14:10:42
    Author     : MartinJ
--%>

<%@page import="modele.Utilisateur"%>
<%@page import="modele.Cheval"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modele.Lot"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
	Lot lot = (Lot) request.getAttribute("lot");
%>

<div class="col s12 m4 l3">
	<div class="card">
		<div class="card-image">
			<img src="<%= request.getContextPath() %>/img/2.jpg">
			<span class="card-title"><%= lot.getCheval().getNom()%></span>
		</div>
		<div class="card-content">
			<p>Race : <%= lot.getCheval().getTypeCheval().getLibelle() %></p>
			<p>Sexe : <%= (lot.getCheval().getMale()) ? "Male" : "Femelle" %></p>
			<p>Prix de départ : <%= lot.getPrixDepart() %>€</p>
		</div>
		<div class="card-action">
			<a href="<%= request.getContextPath()%>/ServletCheval/consulterCheval?id=<%= lot.getCheval().getId()%>">Voir plus</a>
		</div>
	</div>
</div>