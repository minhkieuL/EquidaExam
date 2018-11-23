<%-- 
    Document   : listerLesChevauxParClient
    Created on : 20 nov. 2018, 12:56:43
    Author     : slam
--%>

<%@page import="modele.Lot"%>
<%@page import="modele.Cheval"%>
<%@page import="servlets.ServletLot"%>
<%@page import="modele.Client"%>
<%@page import="servlets.ServletLot"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modele.Cheval"%>
<%@page import="servlets.ServletVentes"%>
<%@page import="modele.Vente"%>
<%@page import="modele.CategVente"%>
<%@page import="modele.DirecteurGeneral"%>
<%@page import="modele.Utilisateur"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vues/include/header.jsp" />
<%
	Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");
	ArrayList<Cheval> mesChevaux = (ArrayList<Cheval>) request.getAttribute("pMesChevaux");

%>
<div class="row">
	<div class="col s12">
		<h1 class="center-align">Mes chevaux</h1>
		<%				
			if (user instanceof Client) {
				if (mesChevaux.size() != 0) {
		%>
		<%
			ArrayList<Client> lesClients = (ArrayList) request.getAttribute("pLesClients");
		%>

		<div class="row">
			<table class="col s12 table table-bordered table-striped table-condensed">  
				<thead>
					<tr>             
						<th>Nom</th>
						<th>Sexe</th>
						<th>Sire</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<%
							for (int i = 0; i < mesChevaux.size(); i++) {

								Cheval unCheval = mesChevaux.get(i);

								out.println("<tr><td>");
								out.println(unCheval.getNom());
								out.println("</td>");

								out.println("<td>");
								if(unCheval.getMale() != true){
									out.println("Femelle");
								} else{
									out.println("Male");
								}
								out.println("</td>");

								out.println("<td>");
								out.println(unCheval.getSire());
								out.println("</td>");
								
								out.println("<td><a href ='../ServletCheval/consulterCheval?id=" + unCheval.getId() + "'>");
								out.println("Voir plus");
								out.println("</td></tr>");
							}
						%>
					</tr>
				</tbody>
			</table>
			<%
					}
				}
			%>
		</div>
		<jsp:include page="/vues/include/footer.jsp" />
		</body>
		</html>
