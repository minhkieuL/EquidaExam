<%-- 
    Document   : listerLesCourses
    Created on : 9 nov. 2018, 09:39:30
    Author     : slam
--%>

<%@page import="modele.Course"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vues/include/header.jsp" />
<h1>LISTE DES COURSES</h1>

<%
ArrayList<Course> lesCourses = (ArrayList)request.getAttribute("pLesCourses");
%>

<table  class="table table-bordered table-striped table-condensed">  
    <thead>
        <tr>             
            <th>nom</th>
            <th>date</th>
			<th>ville</th>
            
        </tr>
    </thead>
    <tbody>
        <tr>
            <%
            for(int i = 0; i < lesCourses.size();i++)
            {

                Course uneCourse = lesCourses.get(i);
                out.println("<td>");
                out.println(uneCourse.getNom());
                out.println("</td>");

                out.println("<td>");
                out.println(uneCourse.getDate());
                out.println("</td>");
				
				out.println("<td>");
                out.println(uneCourse.getVille());
                out.println("</td>");

                out.println("<td><a href ='../ServletCourse/courseModifier?code="+ uneCourse.getId() + "'>");
                out.println("Modifier");
                out.println("</td>");
				
				out.println("</tr>");

            }
            %>
        </tr>
    </tbody>
</table>

<jsp:include page="/vues/include/footer.jsp" />