<%-- 
    Document   : index
    Created on : 19 oct. 2018, 18:05:48
    Author     : MartinJ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vues/include/header.jsp" />

<div class="container">
    <h1 class="center-align">Bienvenue sur Equida!</h1>

    <div class="row">
        <div class="col s12 carousel carousel-slider">
            <a class="carousel-item" href="#one!"><img src="<%=request.getContextPath()%>/img/1.jpg"></a>
            <a class="carousel-item" href="#two!"><img src="<%=request.getContextPath()%>/img/2.jpg"></a>
            <a class="carousel-item" href="#three!"><img src="<%=request.getContextPath()%>/img/3.jpg"></a>
        </div>
    </div>

    <div class="row">
        <ul class="col s12 l6 left collection with-header">
            <li class="collection-header"><h4>Nouveaux chevaux inscrits</h4></li>
            <li class="collection-item">Alvin</li>
            <li class="collection-item">Alvin</li>
            <li class="collection-item">Alvin</li>
            <li class="collection-item">Alvin</li>
            <li class="collection-item"><a href="#">Plus</a></li>
        </ul>

        <ul class="col s12 l6 right collection with-header">
            <li class="collection-header"><h4>Ventes à venir</h4></li>
            <li class="collection-item">Alvin</li>
            <li class="collection-item">Alvin</li>
            <li class="collection-item">Alvin</li>
            <li class="collection-item">Alvin</li>
            <li class="collection-item"><a href="/EquidaWebG2/ServletVentes/listerLesVentesAVenir">Plus d'infos</a></li>
        </ul>
    </div>
</div> 

<jsp:include page="/vues/include/footer.jsp" />
