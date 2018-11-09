<%-- 
    Document   : index
    Created on : 19 oct. 2018, 18:05:48
    Author     : MartinJ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vues/include/header.jsp" />

<div class="container">
    <h1>Bienvenue sur Equida!</h1>
</div>

<div class="carousel">
    <a class="carousel-item" href="#one!"><img src="<%=request.getContextPath()%>/img/1.jpg"></a>
    <a class="carousel-item" href="#two!"><img src="<%=request.getContextPath()%>/img/2.jpg"></a>
    <a class="carousel-item" href="#three!"><img src="<%=request.getContextPath()%>/img/3.jpg"></a>
</div>

<script> 
    $(document).ready(function(){
    $('.carousel').carousel();
  });
</script>

<ul class="left collection with-header">
    <li class="collection-header"><h4>Nouveaux chevaux inscrits</h4></li>
    <li class="collection-item">Alvin</li>
    <li class="collection-item">Alvin</li>
    <li class="collection-item">Alvin</li>
    <li class="collection-item">Alvin</li>
    <li class="collection-item"><a href="#">Plus</a></li>
</ul>

<ul class="collection with-header">
    <li class="collection-header"><h4>Ventes à venir</h4></li>
    <li class="collection-item">Alvin</li>
    <li class="collection-item">Alvin</li>
    <li class="collection-item">Alvin</li>
    <li class="collection-item">Alvin</li>
    <li class="collection-item"><a href="/EquidaWebG2/ServletVentes/listerLesVentesAVenir">Plus d'infos</a></li>
</ul>
            

<jsp:include page="/vues/include/footer.jsp" />
