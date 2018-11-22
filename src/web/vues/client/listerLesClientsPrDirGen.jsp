<%-- 
    Document   : listerLesClientsPrDirGen
    Created on : 12 nov. 2018, 09:38:26
    Author     : BottonL
--%>

<%@page import="servlets.ServletClient"%>
<%@page import="modele.Client"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modele.Utilisateur"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vues/include/header.jsp" />

<h1 class="center-align">Liste des clients</h1>

<%
        ArrayList<Client> lesClients = (ArrayList) request.getAttribute("pLesClients");
        Utilisateur user = (Utilisateur) session.getAttribute("user");
%>

<div class="row">
    <table class="col s12 table table-bordered table-striped table-condensed">  
        <thead>
            <tr>             
                <th>Nom</th>
                <th>Prénom</th>
                <th>Pays</th>  
                <th>Rue</th>
                <th>Ville</th>
                <th>Code Postal</th>
                <th>Mail</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <%
                                        for (int i = 0; i < lesClients.size(); i++) {

                                                Client unClient = lesClients.get(i);

                                                out.println("<tr><td><a href='" + ServletClient.URL_CONSULTER_CLIENT + "?id=" + unClient.getId() + "'>");
                                                out.println(unClient.getNom());
                                                out.println("</td>");

                                                out.println("<td>");
                                                out.println(unClient.getPrenom());
                                                out.println("</td>");

                                                out.println("<td>");
                                                out.println(unClient.getPays().getNom());
                                                out.println("</td>");

                                                out.println("<td>");
                                                out.println(unClient.getRue());
                                                out.println("</td>");

                                                out.println("<td>");
                                                out.println(unClient.getVille());
                                                out.println("</td>");

                                                out.println("<td>");
                                                out.println(unClient.getCopos());
                                                out.println("</td>");

                                                out.println("<td>");
                                                out.println(unClient.getMail());
                                                out.println("</td>");

                                                out.println("<td><a href ='../ServletClient/clientModifier?id=" + unClient.getId() + "'>");
                                                out.println("Modifier");
                                                out.println("</td>");

                                                out.println("<td><a href='" + ServletClient.URL_ARCHIVER_CLIENT + "?id=" + unClient.getId() + "'>");
                                                out.println("Archiver");
                                                out.println("</td>");
                                        }
                %>
            </tr>
        </tbody>
    </table>
</div>

<jsp:include page="/vues/include/footer.jsp" />