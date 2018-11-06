<%-- 
    Document   : typeChevalAjouter
    Created on : 16 oct. 2018, 16:15:46
    Author     : slam
--%>

<%@page import="formulaires.TypeChevalForm"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vues/include/header.jsp" />
        <h1>Ajouter un type de cheval</h1>
        
        <%
                
                TypeChevalForm form = (TypeChevalForm)request.getAttribute("form");
            %>
            
                <form class="form-inline" action="typeChevalAjouter" method="POST">
                <!--<input id="codeOrigne" type="hidden" name="code"  size="4" maxlength="4">-->
                <label for="libelle">Libelle : </label>
                <input id="libelle" type="text" name="libelle"  size="15" maxlength="50">
                </br>
                
                <label for="description">Description : </label>
                <input id="description"  type="text"  name="description" size="30" maxlength="100">      
                </br>
                
                <input type="submit" name="valider" id="valider" value="Valider"/>
                </form>
			
<jsp:include page="/vues/include/footer.jsp" />