<%-- 
    Document   : lotsNonValider
    Created on : 16 nov. 2018, 08:33:50
    Author     : Paul Collet
--%>

<%@page import="modele.Lot"%>
<%@page import="modele.Cheval"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modele.Utilisateur"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vues/include/header.jsp" />

<%
        Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");
        ArrayList<Lot> lotsNonValides = (ArrayList<Lot>) request.getAttribute("pLots");
%>


<h2 class="center-align">Lots non validés</h2>

<div class="row">
    <div class="col s12">
        <%
                        for (Lot lot : lotsNonValides) {
                                request.setAttribute("lot", lot);

        %>
        <jsp:include page="/vues/cheval/chevalEmbed.jsp"/>
        <%						}

        %>
    </div>
</div>

<jsp:include page="/vues/include/footer.jsp" />