<%-- 
    Document   : Lister les Ventes 
    Created on : 22/06/2017, 07:43
    Author     : Zakina
--%>

<%@page import="java.util.regex.Pattern"%>
<%@page import="modele.PieceJointe"%>
<%@page import="modele.Courriel"%>
<%@page import="modele.CategVente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modele.Vente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vues/include/header.jsp" />

<h1 class="center-align">Liste des courriels pour une vente</h1>

<%
        ArrayList<Courriel> lesCourriels = (ArrayList) request.getAttribute("pLesCourriels");

        for (Courriel courriel : lesCourriels) {
                out.println("<div>");
                out.println("Le " + courriel.getDate() + " : <br/>");
                out.println("<br/>");
                out.println("<b>" + courriel.getObjet() + "</b><br/>");
                out.println("<br/>");
                out.println(courriel.getCorps() + "<br/>");
                out.println("<br/>");

                ArrayList<PieceJointe> pieceJointes = courriel.getPiecesJointes();
                if (pieceJointes.size() > 0) {
                        out.println("<b>Pièces jointes : </b>");
                        out.println("<br/>");

                        for (PieceJointe pj : courriel.getPiecesJointes()) {
                                Pattern r = Pattern.compile("^(.){0,}\\.(?:png|gif|bmp|jpg|jpeg)$");

                                if (r.matcher(pj.getChemin()).matches()) {
                                        out.println("<img src=\"" + pj.getChemin() + "\" class=\"responsive-img\"/>");
                                } else {
                                        out.println("<a href=\"" + pj.getChemin() + "\" target=\"_blank\">" + pj.getChemin() + "</a>");
                                }
                                out.println("<br/>");
                                out.println(pj.getDescription());
                                out.println("<br/>");
                        }
                }

                out.println("<hr size=\"1\"/>");
                out.println("</div>");
        }
%>
<jsp:include page="/vues/include/footer.jsp" />