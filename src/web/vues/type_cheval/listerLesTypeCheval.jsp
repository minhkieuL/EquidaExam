<%-- 
    Document   : listerLesTypesChevaux
    Created on : 6 nov. 2018, 16:31:50
    Author     : slam
--%>

<%@page import="modele.TypeCheval"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vues/include/header.jsp" />

<h1 class="center-align">Liste des types de chevaux</h1>
<%
        ArrayList<TypeCheval> lesTypeCheval = (ArrayList) request.getAttribute("pLesTypeCheval");
%>

<table  class="table table-bordered table-striped table-condensed">  
    <thead>
        <tr>             
            <th>Libellé</th>
            <th>Description</th>
            <th>Modifier</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <%
                                for (int i = 0; i < lesTypeCheval.size(); i++) {

                                        TypeCheval unTypeCheval = lesTypeCheval.get(i);
                                        out.println("<tr>");

                                        out.println("<td>");
                                        out.println(unTypeCheval.getLibelle());
                                        out.println("</td>");

                                        out.println("<td>");
                                        out.println(unTypeCheval.getDesc());
                                        out.println("</td>");

                                        out.println("<td><a href ='../ServletTypeCheval/typeChevalModifier?id=" + unTypeCheval.getId() + "'>");
                                        out.println("Modifier");
                                        out.println("</td>");
                                        out.println("</tr>");
                                }
            %>
        </tr>
    </tbody>
</table>
<jsp:include page="/vues/include/footer.jsp" />