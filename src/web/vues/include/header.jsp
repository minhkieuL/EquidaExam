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
        <link rel="stylesheet" href="<%= request.getContextPath()%>/css/materialize.min.css">
        <link href="<%= request.getContextPath()%>/css/google_font.css" rel="stylesheet">
        <link href="<%= request.getContextPath()%>/css/equida.css" rel="stylesheet">
        <link rel="icon" href="<%= request.getContextPath()%>/img/favicon.ico">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    </head>    

    <body>
        <header>
            <nav class="nav-extended">
                <div class="nav-wrapper light-green darken-4">
                    <a href="<%=request.getContextPath()%>/" class="brand-logo"><img class="brand-logo" src="<%= request.getContextPath()%>/img/logo_blanc.png"/></a>
                    <ul id="nav-mobile" class="right hide-on-med-and-down">
                        <a href="#" data-target="mobileNav" class="sidenav-trigger"><i class="material-icons">menu</i></a>
                        <ul class="right hide-on-med-and-down nav-mobile">
                            <%
                                                                //Si l'utilisateur n'est pas connecté on affiche le lien de connexion sinon, on affiche celui de la déconnexion
                                                                if (user == null) {
                            %>
                            <li><a href="/EquidaWebG2/ServletAuthentification/connexion"><i class="material-icons">account_circle</i></a></li>
                                <%
                                                                } else {
                                %>
                            <li><a href="/EquidaWebG2/ServletAuthentification/deconnexion"><i class="material-icons">exit_to_app</i></a></li>

                            <%
                                                                }
                            %>
                        </ul>
                </div>
                <div class="nav-wrapper light-green darken-3">
                    <div class="nav-mobile">
                        <ul class="left hide-on-med-and-down">
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
								}%>
                        </ul>
                        <ul class="right hide-on-med-and-down">
                            <jsp:include page="/vues/include/nav_public.jsp" />	
                        </ul>
                    </div>
                </div>
            </nav>
        </header>

		<main>
			<div class="container">