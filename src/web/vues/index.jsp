<%-- 
    Document   : index
    Created on : 19 oct. 2018, 18:05:48
    Author     : MartinJ
--%>

<%@page import="servlets.ServletCheval"%>
<%@page import="servlets.ServletLot"%>
<%@page import="modele.Lot"%>
<%@page import="modele.Vente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="servlets.ServletVentes"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vues/include/header.jsp" />

<%
	ArrayList<Vente> ventesAVenir = (ArrayList<Vente>) request.getAttribute("pVentesAVenir");
	ArrayList<Lot> nouveauxLots = (ArrayList<Lot>) request.getAttribute("pNouveauxLots");
%>

<h1 class="center-align">Bienvenue sur Equida!</h1>

<div class="row">
	<div class="col s12 carousel carousel-slider">
		<a class="carousel-item" href="#one!"><img class="responsive-img" src="<%=request.getContextPath()%>/img/1.jpg"></a>
		<a class="carousel-item" href="#two!"><img class="responsive-img" src="<%=request.getContextPath()%>/img/2.jpg"></a>
		<a class="carousel-item" href="#three!"><img class="responsive-img" src="<%=request.getContextPath()%>/img/3.jpg"></a>
	</div>
</div>

<div class="row">
	<ul class="col s12 l6 left collection with-header">
		<li class="collection-header"><h4>Nouveaux chevaux</h4></li>
			<%
				for (Lot lot : nouveauxLots) {%>
		<li class="collection-item">
			<p><a href="<%= ServletCheval.URL_CONSULTER_CHEVAL + "?id=" + lot.getCheval().getId()%>"><%= lot.getCheval().getNom()%></a><br/>
				<%= lot.getCheval().getTypeCheval().getLibelle()%><br/>
				<%= lot.getPrixDepart()%>€
			</p>
		</li>
		<%
			}
		%>
		<li class="collection-item"><a href="<%= ServletLot.URL_LISTER_LOTS%>">Plus d'infos</a></li>
	</ul>

	<ul class="col s12 l6 right collection with-header">
		<li class="collection-header"><h4>Ventes à venir</h4></li>
			<%
				for (Vente vente : ventesAVenir) {%>

		<li class="collection-item">
			<p><a href="<%= ServletVentes.URL_CONSULTER_VENTE + "?id=" + vente.getId()%>"><%= vente.getNom()%></a><br/>
				<%= vente.getUneCategVente().getLibelle()%><br/>
				<%= vente.getDateDebut()%>
			</p>
		</li>
		<%
			}
		%>
		<li class="collection-item"><a href="<%= ServletVentes.URL_LISTER_VENTES%>">Plus d'infos</a></li>
	</ul>
</div>

<jsp:include page="/vues/include/footer.jsp" />
