<%-- 
    Document   : categorieVenteModifier
    Created on : 12 oct. 2018, 08:50:00
    Author     : Mk_Luong
--%>

<%@page import="formulaires.ChevalForm"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modele.TypeCheval"%>
<%@page import="modele.Cheval"%>
<%@page import="modele.CategVente"%>
<%@page import="formulaires.CategorieForm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vues/include/header.jsp" />
<h1>Modifier un cheval</h1>

<%
	ChevalForm form = (ChevalForm) request.getAttribute("form");
        Cheval unCheval = (Cheval)request.getAttribute("pCheval"); 
%>

<form class="form-inline" action="chevalModifier" method="POST">
    
    <label for="nom">Nom : </label><input value="<% out.println(unCheval.getNom());%>" id="nom" type="text" name="nom"  size="30" maxlength="30">
    </br>
    </br>
    <label for="sexe"> Sexe: </label>
    <select id="sexe" name="sexe" >
        <option value="0" <%= (!unCheval.getMale()) ? "selected" : ""%>>Femelle</option>
        <option value="1" <%= (unCheval.getMale()) ? "selected" : ""%>>Male</option>
    </select>
	</br>
	</br>

    <label for="sire">SIRE : </label>
    <input value="<% out.println(unCheval.getSire());%>" id="sire"  type="text"  name="sire" size="30" maxlength="50">
	</br>
	</br>


    <%-- Champ Liste des typeCheval --%>

    <label for="typeCheval"> Race: </label>
    <select name="typeCheval">
        
        <%
            ArrayList<TypeCheval> lesTypeCheval = (ArrayList)request.getAttribute("pLesTypeCheval");
            for (int i=0; i<lesTypeCheval.size();i++){
                TypeCheval tc = lesTypeCheval.get(i);
                %>
                <option value="<%=tc.getId()%>" <%=(unCheval.getTypeCheval().getId() == tc.getId()) ? "selected" : ""%>><%=tc.getLibelle()%></option>
                <%       
            }
        %>
    </select></br> 
    </br>            
    </br>

    <label for="pere">Père : </label>
    <input value="<%if(unCheval.getPere() != null) out.println(unCheval.getPere().getId());%>" id="pere"  type="text"  name="pere" size="30" maxlength="50">
	</br>
	</br>

	<label for="mere">Mère : </label>
        <input value="<% if(unCheval.getMere()!= null) out.println(unCheval.getMere().getId());%>" id="mere"  type="text"  name="mere" size="30" maxlength="50">
	</br>
	</br>
    <input type="submit" name="valider" id="valider" value="Valider"/>
</form>
<jsp:include page="/vues/include/footer.jsp" />