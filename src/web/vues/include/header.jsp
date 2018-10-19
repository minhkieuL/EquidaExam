<%-- 
    Document   : header
    Created on : 19 oct. 2018, 06:29:28
    Author     : Maxence
--%>

<%@page import="modele.Utilisateur"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
Utilisateur user = (Utilisateur)request.getSession().getAttribute("user");
String title = (String)request.getAttribute("title");

title = (title == null) ? "" : " - " + title; 
%>

<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8" />
		<title>Equida <%= title %></title>
    </head>    
    
    <body>
		
        <nav>
            <ul>
                <li><a href="/EquidaWebG2/">Accueil</a></li>
				<%
					if(user == null) {
				%>
                <li><a href="/EquidaWebG2/ServletAuthentification/connexion">Connexion</a></li>
				<%
					} else {
				%>
                <li><a href="/EquidaWebG2/ServletAuthentification/deconnexion">Deconnexion</a></li>
				<%
					}
				%>
                <li><a href="/EquidaWebG2/ServletVentes/listerLesVentes">Lister les ventes</a></li>
                <li><a href="/EquidaWebG2/ServletClient/ajouterClient">Ajouter un client</a></li>
                <li><a href="/EquidaWebG2/ServletDirecteur/categorieVenteAjouter">Ajouter une catégorie de vente</a></li>
                <li><a href="/EquidaWebG2/ServletDirecteur/paysAjouter">Ajouter un pays</a></li>
                <li><a href="/EquidaWebG2/ServletCheval/ajouterCheval">Ajouter un cheval</a></li>
            </ul>
        </nav>