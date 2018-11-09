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
        String url = request.getRequestURI();
	Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");
	String title = (String) request.getAttribute("title");

	title = (title == null) ? "" : " - " + title;
%>

<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8" />
		<title>Equida <%= title%></title>
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
                <script type = "text/javascript" src = "https://code.jquery.com/jquery-2.1.1.min.js"></script>
		<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
	</head>    

    <body>

    <nav class="nav-extended">
        <div class="nav-wrapper light-green darken-4">
            <a href="#" class="brand-logo">Logo</a>
                <%
		//Si l'utilisateur n'est pas connecté on affiche le lien de connexion sinon, on affiche celui de la déconnexion
		if (user == null) {
                %>
                    <a href="/EquidaWebG2/ServletAuthentification/connexion" class="brand-logo right"><i class="large material-icons">account_circle</i></a>
		<%
		} else {
		%>
                    <a href="/EquidaWebG2/ServletAuthentification/deconnexion" class="brand-logo right"><i class="large material-icons">exit_to_app</i>
        </div>
                <%
                    }
                    //On verifie si un dircteur general ou un client est connecte et on affiche le menu correspondant
                    if (user instanceof DirecteurGeneral) {
                    %>
                        <div class="nav-content">
			<jsp:include page="/vues/include/nav_directeur_general.jsp" />	
                        </div>
			<%
			} else if (user instanceof Client) {
			%>
                            <div class="nav-content">
				<jsp:include page="/vues/include/nav_client.jsp" />	
                            </div>
			<%
                            }
			
			%>
                        <%             
                        if (!url.equals("/EquidaWebG2/index.jsp")) {
                        %> 
                            <ul id="nav-mobile" class="left offset-1 hide-on-med-and-down">
				<li><a href="/EquidaWebG2/">Accueil</a></li>
                        <% } %>
                                
                                
    
    </nav>