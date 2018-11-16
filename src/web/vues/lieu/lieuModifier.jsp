<%-- 
    Document   : lieuModifier
    Created on : 9 nov. 2018, 07:33:01
    Author     : Maxence
--%>
<%@page import="modele.Lieu"%>
<%@page import="formulaires.LieuForm"%>


<jsp:include page="/vues/include/header.jsp" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modification d'un lieu</title>
    </head>
    <body>      
		<h1>Modification un Lieu</h1>
<%
	LieuForm form = (LieuForm) request.getAttribute("form");
    Lieu unLieu = (Lieu)request.getAttribute("pLieu"); 
%>

<form class="form-inline" action="lieuModifier" method="POST">
	<input value="<% out.println(unLieu.getId());%>" id="codeOrigin" name="codeOrigin" type="hidden" size="4" maxlength="4"></br>
   
    <label for="ville">Ville : </label><input id="ville"  type="text"  name="ville" size="30" maxlength="30" value="<% out.println(unLieu.getVille());%>">      
    </br>
    <br/>
	
	<label for="nbBoxes">Nombre de Boxes : </label><input id="nbBoxes" name="nbBoxes"  size="30" maxlength="30" value="<% out.println(unLieu.getNbBoxes());%>">
    </br>
    <br/>

    <label for="commentaire">Commentaire : </label><input id="commentaire"  type="text"  name="commentaire" size="30" maxlength="30" value="<% out.println(unLieu.getCommentaire());%>">      
    </br>
    <br/>

    <input type="submit" name="valider" id="valider" value="Valider"/>
</form>	
	
	
	
    </body>

</html>
<jsp:include page="/vues/include/footer.jsp" />