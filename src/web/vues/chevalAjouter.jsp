<%-- 
    Document   : chevalAjouter
    Created on : 12 oct. 2018, 09:03:08
    Author     : slam
--%>

<%@page import="modele.Cheval"%>
<%@page import="modele.Lot"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modele.TypeCheval"%>
<%@page import="formulaires.ChevalForm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/vues/MiseEnForme/header.jsp" />
        <h1>NOUVEAU CHEVAL</h1>
        
         <%
                //Client client=(Client)request.getAttribute("client");
                ChevalForm form = (ChevalForm)request.getAttribute("form");
            %>
        
            
        <form class="form-inline" action="ajouterCheval" method="POST">
                <label for="nom">Nom : </label>
                <input id="nom" type="text" name="nom"  size="30" maxlength="30">
                </br>
                </br>
                <label for="sexe"> Sexe: </label> 
                
                <select id="sexe" name="sexe">
                    <option value="0">Femelle</option>
                    <option value="1">Male</option>
                </select>
                 </br>
                 </br>
                 
                <label for="sire">SIRE : </label>
                <input id="sire"  type="text"  name="sire" size="30" maxlength="50">
                 </br>
                 </br>
                               
                
                <%-- Champ Liste des typeCheval --%>
                
               <label for="typeCheval"> Race: </label>
                <select name="typeCheval">
                
                    <%
                        ArrayList<TypeCheval> lesTypeCheval = (ArrayList)request.getAttribute("pLesTypeCheval");
                        for (int i=0; i<lesTypeCheval.size();i++){
                            TypeCheval tc = lesTypeCheval.get(i);
                            out.println("<option value='" + tc.getId()+"'>" + tc.getLibelle()+"</option>" );
                        }
                    %>
                </select></br> 
                </br>            
                </br>
                
                <label for="pere">Père : </label>
                <input id="pere"  type="text"  name="pere" size="30" maxlength="50">
                 </br>
                 </br>
                 
                 <label for="mere">Mère : </label>
                <input id="mere"  type="text"  name="mere" size="30" maxlength="50">
                 </br>
                 </br>
                
               
                <%--<label for="lot"> : </label>
                <select name="lot" size="3" multiple>
                <%
                        ArrayList<Lot> lesLots = (ArrayList)request.getAttribute("pLesLots");
                        for (int i=0; i<lesLots.size();i++){
                            Lot l = lesLots.get(i);
                            out.println("<option value ='" + l.getId() + "'>" + l.getPrixDepart() + "</option>"); 
                           
                        }
                </select></br>                    %>--%>

                </br>            
                </br>
                
                
                <%-- Cases à cocher permettant de choisir les différentes catégories de vente auxquelles le client souhaite s'inscrire 
                <label for="categVente">Categories de vente : </label></br>
                 <%
                        ArrayList<CategVente> lesCategVente = (ArrayList)request.getAttribute("pLesCategVente");
                        for (int i=0; i<lesCategVente.size();i++){
                            CategVente cv = lesCategVente.get(i);
                            out.println("<input type='checkbox' id='cb" + i + "' name='" + cv.getCode() + "'>"); 
                            out.println(cv.getLibelle()); 
                            out.println("</label></br>");
                        }
                    %>
                    </br>
                    --%>
                 
                
            <input type="submit" name="valider" id="valider" value="Valider"/>
            </form>
<jsp:include page="/vues/MiseEnForme/Footer.jsp" />