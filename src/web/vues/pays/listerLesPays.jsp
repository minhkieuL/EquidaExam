<%-- 
    Document   : listerLesPays
    Created on : 6 nov. 2018, 13:48:53
    Author     : Dorian
--%>

<%@page import="modele.Pays"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vues/include/header.jsp" />
<h1 class="center-align">Liste des pays</h1>

<%
        ArrayList<Pays> lesPays = (ArrayList) request.getAttribute("pLesPays");
%>

<table  class="table table-bordered table-striped table-condensed">  
    <thead>
        <tr>             
            <th>Code</th>
            <th>Nom</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <%
                                for (int i = 0; i < lesPays.size(); i++) {

                                        Pays unPays = lesPays.get(i);
                                        out.println("<tr><td>");
                                        out.println(unPays.getCode());
                                        out.println("</a></td>");

                                        out.println("<td>");
                                        out.println(unPays.getNom());
                                        out.println("</td>");

                                        out.println("<td><a href ='../ServletPays/paysModifier?code=" + unPays.getCode() + "'>");
                                        out.println("Modifier");
                                        out.println("</td>");

                                }
            %>
        </tr>
    </tbody>
</table>

<jsp:include page="/vues/include/footer.jsp" />
