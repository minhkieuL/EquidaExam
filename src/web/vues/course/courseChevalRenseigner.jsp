<%-- 
    Document   : courseChevalRenseigner
    Created on : 12 nov. 2018, 21:42:59
    Author     : BottonL
--%>

<%@page import="servlets.ServletBase"%>
<%@page import="formulaires.ParticipationForm"%>
<%@page import="modele.Course"%>
<%@page import="java.util.ArrayList"%>

<%
	ParticipationForm form = null;
	try {
		form = (ParticipationForm) ServletBase.getForm(request);
	} catch (ClassCastException e) {
		
	}
	
	request.setAttribute("form", form);
%>

<jsp:include page="/vues/include/header.jsp" />

<h1 class="center-align">Classement à une course</h1>

<jsp:include page="/vues/include/erreurs_form.jsp" />

<div class="row">
    <form class="col s10 push-s1 l8 push-l2 center-align" action="courseChevalRenseigner" method="POST">
        <input name="idCheval" type="hidden" value="<%= request.getParameter("id")%>"/>
        <div class="row">
            <div class="input-field col s12 l6">
                <select name="idCourse" id="choix_pays">
                    <%
                                                ArrayList<Course> lesCourses = (ArrayList) request.getAttribute("pLesCourses");
                                                for (int i = 0; i < lesCourses.size(); i++) {
                                                        Course c = lesCourses.get(i);
                                                        out.println("<option value='" + c.getId() + "'>" + c.getNom() + "</option>");
                                                }
                    %>
                </select>
                <label for="course">Course : </label>
            </div>
            <div class="input-field col s12 l6">
                <input id="classement" type="number" name="classement" min="1" max="3" class="validate">
                <label for="classement">Classement : </label>
            </div>

        </div>

        <input type="submit" name="valider" id="valider" value="Valider"/>
    </form>
</div>

<jsp:include page="/vues/include/footer.jsp" />
