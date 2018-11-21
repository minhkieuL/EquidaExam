<%-- 
    Document   : chevalConsulter
    Created on : 16 oct. 2018, 12:54:38
    Author     : slam
--%>

<%@page import="modele.Lot"%>
<%@page import="servlets.ServletCourse"%>
<%@page import="modele.DirecteurGeneral"%>
<%@page import="modele.Participer"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modele.Client"%>
<%@page import="modele.Utilisateur"%>
<%@page import="servlets.ServletCheval"%>
<%@page import="modele.Utilisateur"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modele.Cheval"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vues/include/header.jsp" />

<%
    Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");
    Cheval unCheval = (Cheval) request.getAttribute("pCheval");
    ArrayList<Participer> lesParticipations = (ArrayList<Participer>) request.getAttribute("pParticipations");
    Lot lot = (Lot) request.getAttribute("pLot");
%>

<div class="row">
    <div class="col s12 l6 right valign-wrapper">
        <img src="<%= request.getContextPath()%>/img/1.jpg" class="responsive-img "/>
    </div>

    <div class="col s12 l6 left">
        <h2><%= unCheval.getNom()%></h2>
        <p class="tooltipped" data-position="bottom" data-tooltip="<%= unCheval.getTypeCheval().getDesc()%>">Race : <%= unCheval.getTypeCheval().getLibelle()%></p>
        <p>Sexe : <%= (unCheval.getMale()) ? "Male" : "Femelle"%></p>
        <p>Sire : <%= (unCheval.getSire() != null) ? unCheval.getSire() : "Non renseigné"%></p>
        <p>Mère : <%= (unCheval.getMere() != null) ? "<a href=?id=" + unCheval.getMere().getId() + ">" + unCheval.getMere().getSire() + "</a>" : "Non renseignée"%></p>
        <p>Père : <%= (unCheval.getPere() != null) ? "<a href=?id=" + unCheval.getPere().getId() + ">" + unCheval.getPere().getSire() + "</a>" : "Non renseigné"%></p>
        <%
            if (user instanceof DirecteurGeneral) {
                out.println("<p><a href='" + ServletCheval.URL_ARCHIVER_CHEVAL + "?id=" + unCheval.getId() + "'>");
                out.println("Archiver");
                out.println("</a></p>");

                if (lot.getValidation() == null) {
                    //TODO Verifier que le cheval n'est pas deja ajouté
                    out.println("Voulez-vous valider le cheval afin de l'ajouter à la vente ?");
                    out.println("<p><a href='" + ServletCheval.URL_VALIDER_CHEVAL + "?id=" + lot.getId() + "'>");
                    out.println("Valider");
                    out.println("</a></p>");
                }
            }

        %>


    </div>

    <div class="row">
        <div class="col s12">
            <h3>Les courses</h3>
            <% if (lesParticipations.size() != 0) {
            %>
            <table><table class="col s12 table table-bordered table-striped table-condensed">  
                    <thead>
                        <tr>             
                            <th>Course</th>
                            <th>Date</th>
                            <th>Classement</th>  
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <%
                                for (int i = 0; i < lesParticipations.size(); i++) {

                                    Participer uneParticipation = lesParticipations.get(i);

                                    out.println("<tr><td>");
                                    out.println(uneParticipation.getCourse().getNom());
                                    out.println("</td>");

                                    out.println("<td>");
                                    out.println(uneParticipation.getCourse().getDate());
                                    out.println("</td>");

                                    out.println("<td>");
                                    out.println(uneParticipation.getPlace());
                                    out.println("</td>");

                                    if (user instanceof Client || user instanceof DirecteurGeneral) {
                                        out.println("<td><a href ='../ServletClient/clientModifier?id=" + uneParticipation.getCheval() + "'>");
                                        out.println("Modifier");
                                        out.println("</td>");
                                    }

                                    if (user instanceof Client) {
                                        out.println("<td><a href ='" + ServletCourse.URL_SUPPRIMER_CLASSEMENT_CHEVAL + "?idCheval=" + uneParticipation.getCheval().getId() + "&idCourse=" + uneParticipation.getCourse().getId() + "'>");
                                        out.println("Supprimer");
                                        out.println("</td>");
                                    }
                                }
                            %>
                        </tr>
                    </tbody>
                </table>
                <%
				} else { %>
                <p>Aucune course n'a été enregistrée pour ce cheval</p>
                <%
                    }
                    if (user instanceof Client || user instanceof DirecteurGeneral) {
                %>
                <a href ='../ServletCourse/courseChevalRenseigner?id=<%= unCheval.getId()%>'>Ajouter une course</a>
                <%
                    }
                %>
        </div>
    </div>

    <div class="row">
        <div class="col s12">
            <h3>Infos Vente</h3>
            <p>Ce cheval n'est pas encore en vente</p>
        </div>
    </div>
</div>

<jsp:include page="/vues/include/footer.jsp" />