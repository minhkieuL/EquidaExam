<%@page import="modele.Lot"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>LISTE LES CHEVAUX POUR UNE VENTE</title>
    </head>
    <body>
        <h1>LISTE LES CHEVAUX POUR UNE VENTE</h1>
         <%
        ArrayList<Lot> lesLots = (ArrayList)request.getAttribute("pLesLots");
        %>
        <table  class="table table-bordered table-striped table-condensed">  
            <thead>
                <tr>          
                    <th>Id Lot</th>
                    <th>Prix départ</th>
                    <th>Id cheval</th>                    
                    <th>Nom cheval</th>
                    <th>Sexe cheval</th>
                    <th>Sire cheval</th>
                    <th>Libelle type cheval</th>
                    <th>Desc type cheval</th>
                </tr>
            </thead>
            <tbody>
                
                    <%
                    for(int i = 0; i < lesLots.size();i++)
                    {
                        Lot lot = lesLots.get(i);
                        out.println("<tr>");
                                
                        out.println("<td>");
                        out.println(lot.getId());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(lot.getPrixDepart());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(lot.getCheval().getId());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(lot.getCheval().getNom());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println((lot.getCheval().estMale()) ? "Male" : "Femelle");
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(lot.getCheval().getSire());
                        out.println("</td>");                        
                        
                        out.println("<td>");
                        out.println(lot.getCheval().getTypeCheval().getLibelle());
                        out.println("</td>");                 
                        
                        out.println("<td>");
                        out.println(lot.getCheval().getTypeCheval().getDesc());
                        out.println("</td>");
                        
                        out.println("</tr>");
                    }
                    %>
            </tbody>
        </table>
    </body>
</html>
