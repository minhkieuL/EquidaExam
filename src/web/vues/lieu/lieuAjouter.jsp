<%-- 
    Document   : LieuAjouter
    Created on : 16 oct. 2018, 16:04:08
    Author     : Maxence
--%>

<%@page import="formulaires.LieuForm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vues/include/header.jsp" />
<h1>NOUVEAU LIEU</h1>

<%
    LieuForm form = (LieuForm)request.getAttribute("form");
%>

<form class="form-inline" action="ajouterLieu" method="POST">

    <label for="ville">Ville : </label><input id="ville"  type="text"  name="ville" size="30" maxlength="30">      
    </br>
    <br/>
	
	<label for="nbBoxes">Nombre de Boxes : </label><input id="nbBoxes" type="text" name="nbBoxes"  size="30" maxlength="30">
    </br>
    <br/>

    <label for="commentaire">Commentaire : </label><input id="commentaire"  type="text"  name="commentaire" size="30" maxlength="30">      
    </br>
    <br/>

    <input type="submit" name="valider" id="valider" value="Valider"/>
</form>

<jsp:include page="/vues/include/footer.jsp" />