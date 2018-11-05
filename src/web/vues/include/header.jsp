<%-- 
    Document   : header
    Created on : 19 oct. 2018, 06:29:28
    Author     : Maxence
--%>

<%@page import="modele.Client"%>
<%@page import="modele.DirecteurGeneral"%>
<%@page import="modele.Utilisateur"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
	Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");
	String title = (String) request.getAttribute("title");

	title = (title == null) ? "" : " - " + title;
%>

<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8" />
		<title>Equida <%= title%></title>
    </head>    

    <body>

        <nav>
            <ul>
                <li><a href="/EquidaWebG2/">Accueil</a></li>
					<%
						//Si l'utilisateur n'est pas connecté on affiche le lien de connexion sinon, on affiche celui de la déconnexion
						if (user == null) {
					%>
				<li><a href="/EquidaWebG2/ServletAuthentification/connexion">Connexion</a></li>
					<%
					} else {
					%>
                <li><a href="/EquidaWebG2/ServletAuthentification/deconnexion">Deconnexion</a></li>
					<%
						//On verifie si un dircteur general ou un client est connecte et on affiche le menu correspondant
						if (user instanceof DirecteurGeneral) {
					%>
					<jsp:include page="/vues/include/nav_directeur_general.jsp" />			
					<%
					} else if (user instanceof Client) {
					%>

				<jsp:include page="/vues/include/nav_client.jsp" />		
				<%
						}
					}
				%>

				<jsp:include page="/vues/include/nav_public.jsp" />

            </ul>
        </nav>