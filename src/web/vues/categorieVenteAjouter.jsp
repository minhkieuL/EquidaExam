<%-- 
    Document   : categorieVenteAjouter
    Created on : 12 oct. 2018, 08:50:00
    Author     : paul_collet
--%>
<%@page import="formulaires.CategorieForm"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/vues/include/header.jsp" />
        <h1>Ajouter une catégorie de vente</h1>
        
        <%
                
                CategorieForm form = (CategorieForm)request.getAttribute("form");
            %>
            
                <form class="form-inline" action="categorieVenteAjouter" method="POST">
                <!--<input id="codeOrigne" type="hidden" name="code"  size="4" maxlength="4">-->
                <label for="code">Code : </label>
                <input id="code" type="text" name="code"  size="4" maxlength="4">
                </br>
                
                <label for="libelle">Libelle : </label>
                <input id="libelle"  type="text"  name="libelle" size="15" maxlength="30">      
                </br>
                
                <input type="submit" name="valider" id="valider" value="Valider"/>
                </form>
<jsp:include page="/vues/include/footer.jsp" />