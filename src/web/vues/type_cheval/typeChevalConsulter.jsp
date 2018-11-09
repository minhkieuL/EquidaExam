<%-- 
    Document   : typeChevalConsulter
    Created on : 19 oct. 2018, 09:00:50
    Author     : slam
--%>

<%@page import="modele.TypeCheval"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vues/include/header.jsp" />
        <div class="container">
        <h1>Infos sur l'Ajout d'un Type Cheval</h1>
        </div>
        
         <%
        TypeCheval unTypeCheval = (TypeCheval)request.getAttribute("pTypeCheval");
        %>
        
        
         <table class="table table-bordered table-striped table-condensed">
            <tr><td>Libelle :</td><td><%  out.println(unTypeCheval.getLibelle());%></td>  </tr>
            <tr><td>Description :</td><td><%  out.println(unTypeCheval.getDesc());%></td>  </tr>
            
        </table>
<jsp:include page="/vues/include/footer.jsp" />