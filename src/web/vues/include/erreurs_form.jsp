<%-- 
    Document   : erreursForm.jsp
    Created on : 21 nov. 2018, 20:52:26
    Author     : MartinJ
--%>

<%@page import="formulaires.Form"%>
<%@page import="servlets.ServletBase"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
        Form form = (Form) request.getAttribute("form");
        if (form != null) {
                if (form.getErreurs().size() != 0) {
%>

<div class="row">
    <div class="col s12 card-panel red accent-4">
        <ul>
            <%
                                for (String key : form.getErreurs().keySet()) {
                                        for (String msg : form.getErreurs().get(key)) {
            %>
            <li class="white-text"><%= msg%></li>
                <%
                                                }
                                        }
                %>
        </ul>
    </div>
</div>

<%
                }
        }
%>