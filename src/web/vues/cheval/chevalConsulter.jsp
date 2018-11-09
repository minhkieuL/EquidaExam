<%-- 
    Document   : chevalConsulter
    Created on : 16 oct. 2018, 12:54:38
    Author     : slam
--%>

<%@page import="modele.Cheval"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vues/include/header.jsp" />
<div class="container">
    <h1>Infos cheval</h1>
</div>

<%
Cheval unCheval = (Cheval)request.getAttribute("pCheval");
%>


<table class="table table-bordered table-striped table-condensed">
    <tr>
        <td>Nom :</td>
        <td><% out.println(unCheval.getNom());%></td>
    </tr>
    <tr>
        <td>Sexe :</td><td><%  
            if(unCheval.getMale()) {
                out.println("Mâle");
            } else {
                out.println("Femelle");
            }
            %></td>  </tr>
    <tr>
        <td>SIRE :</td>
        <td><%  out.println(unCheval.getSire());%></td>  
    </tr>
    <tr>
        <td>Race :</td>
        <td><%  out.println(unCheval.getTypeCheval().getLibelle());%></td>  
    </tr>
    <tr>
        <td>Pere  :</td>
        <td><%  if(unCheval.getPere() != null)
					out.println(unCheval.getPere().getId());%></td>  
    </tr>
    <tr>
        <td>Mere  :</td>
        <td><%  if(unCheval.getMere() != null)
				out.println(unCheval.getMere().getId());%></td>  

    </tr>

</table>


<jsp:include page="/vues/include/footer.jsp" />