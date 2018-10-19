<%-- 
    Document   : header
    Created on : 19 oct. 2018, 06:29:28
    Author     : Maxence
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vues/MiseEnForme/CSS/css.css" />
    </head>    
    
    <body>
        <header>
          <img class="logo" src="https://via.placeholder.com/275x75" alt="";>
          <a > <em>Charte graphique EQUIDA</em> </a>
          <div id="authentification">
          <a href="#"> Connexion </a>
          <br/>
          <br/>
          <a href="#"> Inscription </a>

        </header>
        <nav>
            
		<a href="/EquidaWebG2/ServletVentes/listerLesVentes">Lister les ventes</a>
		<a href="/EquidaWebG2/ServletClient/ajouterClient">Ajouter un client</a>
		<a href="/EquidaWebG2/ServletDirecteur/categorieVenteAjouter">Ajouter une catégorie de vente</a>
		<a href="/EquidaWebG2/ServletDirecteur/paysAjouter">Ajouter un pays</a>
                <a href="/EquidaWebG2/ServletCheval/ajouterCheval">Ajouter un cheval</a>

    </nav>
    </body>