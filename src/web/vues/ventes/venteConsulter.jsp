<%-- 
    Document   : consulterClient
    Created on : 23 juin 2017, 10:33:23
    Author     : MartinJ
--%>

<%@page import="servlets.ServletVentes"%>
<%@page import="modele.Lot"%>
<%@page import="modele.Vente"%>
<%@page import="modele.CategVente"%>
<%@page import="modele.DirecteurGeneral"%>
<%@page import="modele.Utilisateur"%>
<%@page import="modele.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vues/include/header.jsp" />

<%
	Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");
	Vente uneVente = (Vente) request.getAttribute("pVente");
		
%>

<div class="row">
	<div class="col s12 l6 right">
		<%
			if (user instanceof DirecteurGeneral) {%>
		<p><a href="../ServletCourriel/listerLesCourriels?codeVente=<%=uneVente.getId()%>">Lister les courriels</a></p>
		<p><a href="<%= ServletVentes.URL_MODIFIER_VENTE%>?id=<%=uneVente.getId()%>">Modifier la vente</a></p>
		<%
			}
		%>
	</div>

	<div class="col s12 l6 left">
		<h2><%= uneVente.getNom()%></h2>
		<p><%= uneVente.getLieu().getVille() + ", " + uneVente.getLieu().getNbBoxes() + " boxes disponibles"%></p>
		<p><%= uneVente.getUneCategVente().getLibelle()%></a></p>
		<p><%= "Début d'inscription : " + uneVente.getDateDebut()%></p>
		<p><%= "Date de la vente : " + uneVente.getDateVente()%></p>
	</div>

	<div class="row">
		<div class="col s12">
			<h3>Chevaux en ventes</h3>
			<%
				for (Lot lot : uneVente.getLots()) {
					request.setAttribute("lot", lot);
			%>
			<jsp:include page="/vues/cheval/chevalEmbed.jsp"/>
			<%
				}

				if (uneVente.getLots().size() == 0) {
			%>
			<p>Aucun cheval n'est actuellement en vente</p>		
			<%
				}
			%>
		</div>
	</div>
</div>


<jsp:include page="/vues/include/footer.jsp" />
