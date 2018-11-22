<%-- 
    Document   : lieuLister
    Created on : 9 nov. 2018, 08:34:55
    Author     : Maxence
--%>
<%@page import="modele.Lieu"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vues/include/header.jsp" />
<%
        ArrayList<Lieu> lesLieux = (ArrayList) request.getAttribute("pLesLieux");
%>

<h2 class="center-align">Lister les lieux</h2>

<div class="row">
    <table  class="table table-bordered table-striped table-condensed"> 
        </head>
        <thead>
            <tr>             
                <th>Ville</th>
                <th>Nombre de Boxes</th>
                <th>Commentaire</th>  
            </tr>
        </thead>
        <tbody>
            <tr>
                <%
                                        for (int i = 0; i < lesLieux.size(); i++) {

                                                Lieu unLieu = lesLieux.get(i);
                                                out.println("<td>");
                                                out.println(unLieu.getVille());
                                                out.println("</td>");

                                                out.println("<td>");
                                                out.println(unLieu.getNbBoxes());
                                                out.println("</td>");

                                                out.println("<td>");
                                                out.println(unLieu.getCommentaire());
                                                out.println("</td>");

                                                out.println("<td><a href ='../ServletLieu/lieuModifier?id=" + unLieu.getId() + "'>");
                                                out.println("Modifier");
                                                out.println("</td>");

                                                out.println("</tr>");

                                        }

                %>

        <tbody>
    </table>	
</div>
<jsp:include page="/vues/include/footer.jsp" />