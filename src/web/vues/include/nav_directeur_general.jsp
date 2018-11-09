<%-- 
    Document   : nav_client
    Created on : 27 oct. 2018, 13:09:52
    Author     : MartinJ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script>
$( document ).ready(function() {
    $(".dropdown-button").dropdown();
});
</script>

<nav>
  <div class="nav-wrapper light-green lighten-2">
    <ul class="right hide-on-med-and-down">
      <!-- Dropdown Trigger -->
      <li><a class="dropdown-button" href="#!" data-target="dropdown2">Client<i class="material-icons right">arrow_drop_down</i></a></li>
      <li><a class="dropdown-button" href="#!" data-target="dropdown3">Cheval<i class="material-icons right">arrow_drop_down</i></a></li>
      <li><a class="dropdown-button" href="#!" data-target="dropdown1">Vente<i class="material-icons right">arrow_drop_down</i></a></li>
      <li><a class="dropdown-button" href="#!" data-target="dropdown4">Autres<i class="material-icons right">arrow_drop_down</i></a></li>
    </ul>
  </div>
</nav>

<!-- Dropdown Structure -->
<ul id="dropdown1" class="dropdown-content">
  <li><a href="/EquidaWebG2/ServletCategVente/listerLesCategVentes">Lister catégorie de vente</a></li>
  <li class="divider"></li>
  <li><a href="/EquidaWebG2/ServletCategVente/categorieVenteAjouter">Ajouter catégorie de vente</a></li>
</ul>
<ul id="dropdown2" class="dropdown-content">
  <li><a href="/EquidaWebG2/ServletClient/listerLesClients">Lister</a></li>
  <li class="divider"></li>
  <li><a href="/EquidaWebG2/ServletClient/ajouterClient">Ajouter</a></li>
</ul>
<ul id="dropdown3" class="dropdown-content">
  <li><a href="/EquidaWebG2/ServletTypeCheval/typeChevalAjouter">Ajouter une race</a></li>
</ul>
<ul id="dropdown4" class="dropdown-content">
  <li><a href="/EquidaWebG2/ServletPays/ajouterPays">Ajouter un pays</a></li>
</ul>