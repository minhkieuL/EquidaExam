<%@page import="modele.Lot"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vues/include/header.jsp" />
<div class="container">
<h1>Liste des chevaux pour une vente</h1>
</div>
<%
ArrayList<Lot> lesLots = (ArrayList)request.getAttribute("pLesLots");
%>
<table  class="table table-bordered table-striped table-condensed">  
    
        <!--<tr>          
            <th>Id cheval</th>
            <th>Prix départ</th>                
            <th>Nom cheval</th>
            <th>Sexe cheval</th>
            <th>Type cheval</th>
            <th>Description type cheval</th>
        </tr>-->
    <tbody>

        <%
        for(int i = 0; i < lesLots.size();i++)
        {
            Lot lot = lesLots.get(i);
        
            out.println("<h3>");
            out.println(lot.getCheval().getNom());
            out.println("</h3>");
            
            out.println("<ul>");
            
           
                out.println("Prix : "+lot.getPrixDepart()+" €");
                
                out.println("<Br>");
                out.println("Sexe : "+((lot.getCheval().getMale()) ? "Male" : "Femelle"));
                
                out.println("<Br>");
                out.println("Race : "+lot.getCheval().getTypeCheval().getLibelle());
                     
                /*out.println("<Br>");
                out.println("<option value=\"</option>)");*/

            out.println("</ul>");
            out.println("<hr>");
            
        }
%>
        
    </tbody>
</table>
<jsp:include page="/vues/include/footer.jsp" />