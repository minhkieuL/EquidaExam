<%@page import="modele.Cheval"%>
<%@page import="modele.Lot"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vues/include/header.jsp" />

<h1 class="center-align">Liste des chevaux pour une vente</h1>

<%
	ArrayList<Lot> lesLots = (ArrayList) request.getAttribute("pLesLots");
%>

<div class="row">
	<table class="col s12 table table-bordered table-striped table-condensed">  

		<!--<tr>          
			<th>Id cheval</th>
			<th>Prix départ</th>                
			<th>Nom cheval</th>
			<th>Sexe cheval</th>
			<th>Type cheval</th>
			<th>Description type cheval</th>
		</tr>-->
		<tbody>

			<%
				for (int i = 0; i < lesLots.size(); i++) {
					Lot lot = lesLots.get(i);
					Cheval unCheval = new Cheval();

					out.println("<h3>");
					out.println(lot.getCheval().getNom());
					out.println("</h3>");

					out.println("<ul>");

					out.println("Prix : " + lot.getPrixDepart() + " €");

					out.println("<Br>");
					out.println("Sexe : " + ((lot.getCheval().getMale()) ? "Male" : "Femelle"));

					out.println("<Br>");
					out.println("Race : " + lot.getCheval().getTypeCheval().getLibelle());

					/*out.println("<Br>");
					out.println("<td><a href ='../ServletCheval/chevalModifier?id="+lot.getCheval().getId() +"'>");
					out.println("modifier");
					out.println("</td>"); */
					out.println("</ul>");
					out.println("<hr>");

				}
			%>

		</tbody>
	</table>
</div>

<jsp:include page="/vues/include/footer.jsp" />