<%-- 
    Document   : Lister les Ventes 
    Created on : 22/06/2017, 07:43
    Author     : Zakina
--%>

<%@page import="servlets.ServletVentes"%>
<%@page import="modele.DirecteurGeneral"%>
<%@page import="modele.Utilisateur"%>
<%@page import="modele.CategVente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modele.Vente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vues/include/header.jsp" />

<h1 class="center-align">Liste des ventes</h1>

<%
	ArrayList<Vente> lesVentes = (ArrayList) request.getAttribute("pLesVentes");
	ArrayList<CategVente> lesCatVentes = (ArrayList) request.getAttribute("pLesCatVentes");

	Utilisateur user = (Utilisateur) session.getAttribute("user");
%>
<form method="GET">
    <select
        name="catVente">
        <%
			for (CategVente catVente : lesCatVentes) {
				String catCode = catVente.getCode();
				String catLib = catVente.getLibelle();

				String catVenteSelect = "";
				if (request.getParameter("catVente") != null) {
					catVenteSelect = (request.getParameter("catVente").equals(catCode)) ? "selected" : "";
				}

				out.println("<option value=\"" + catCode + "\"" + catVenteSelect + ">" + catLib + "</option>)");
			}
        %>
    </select>

    <input type="submit"/>
</form>

<a href="listerLesVentes"><button>Reset</button></a>
<table  class="table table-bordered table-striped table-condensed">  
    <thead>
        <tr>             
            <th>Id</th>
            <th>Nom</th>
            <th>Date début</th>
            <th>Catégorie</th>  
        </tr>
    </thead>
    <tbody>
        <tr>
            <%
				for (int i = 0; i < lesVentes.size(); i++) {

					Vente uneVente = lesVentes.get(i);
					out.println("<tr><td>");
					out.println(uneVente.getId());
					out.println("</a></td>");

					out.println("<td>");
					out.println(uneVente.getNom());
					out.println("</td>");

					out.println("<td>");
					out.println(uneVente.getDateDebut());
					out.println("</td>");

					out.println("<td>");
					out.println(uneVente.getUneCategVente().getLibelle());
					out.println("</td>");

					out.println("<td><a href='"+ServletVentes.URL_CONSULTER_VENTE+"?id=" + uneVente.getId() + "'>");
					out.println("Voir plus");
					out.println("</td>");

				}
            %>
        </tr>
    </tbody>
</table>

<jsp:include page="/vues/include/footer.jsp" />