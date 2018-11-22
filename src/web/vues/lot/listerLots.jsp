<%-- 
    Document   : listerChevaux
    Created on : 11 nov. 2018, 14:56:26
    Author     : MartinJ
--%>

<%@page import="modele.Lot"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modele.Utilisateur"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vues/include/header.jsp" />

<%
        Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");
        ArrayList<Lot> lots = (ArrayList<Lot>) request.getAttribute("pLots");
%>


<h2 class="center-align">Chevaux en vente</h2>

<div class="row">
    <div class="col s12">
        <%
                        for (Lot lot : lots) {
                                request.setAttribute("lot", lot);
        %>
        <jsp:include page="/vues/cheval/chevalEmbed.jsp"/>
        <%
                        }
        %>
    </div>
</div>

<jsp:include page="/vues/include/footer.jsp" />
