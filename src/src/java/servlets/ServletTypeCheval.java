package servlets;

import database.TypeChevalDAO;

import formulaires.TypeChevalForm;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.TypeCheval;


/**
    Document   : ServletDirecteur
    Created on : 12 oct. 2018, 09:00:00
    Author     : corentin
 */
public class ServletTypeCheval extends ServletBase {
    
    Connection connection ;
    
    @Override
    public void init()
    {     
        ServletContext servletContext=getServletContext();
        connection=(Connection)servletContext.getAttribute("connection");
    }


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       super.doGet(request, response);
	   
       String url = request.getRequestURI();       
       if(url.equals("/EquidaWebG2/ServletTypeCheval/typeChevalAjouter")) { 
			changerTitrePage("Ajouter race cheval", request);
            getServletContext().getRequestDispatcher("/vues/type_cheval/typeChevalAjouter.jsp" ).forward( request, response );
        }
	   
	   if (url.equals("/EquidaWebG2/ServletTypeCheval/listerLesTypeCheval")) {
			ArrayList<TypeCheval> lesTypeCheval = TypeChevalDAO.getLesTypeCheval(connection);

			request.setAttribute("pLesTypeCheval", lesTypeCheval);
			changerTitrePage("Lister les types de chevaux", request);

			getServletContext().getRequestDispatcher("/vues/ventes/listerLesTypeCheval.jsp").forward(request, response);
		}
		
		if (url.equals("/EquidaWebG2/ServletTypeCheval/typeChevalModifier")) {
			int idTypeCheval = Integer.valueOf(request.getParameter("id"));
			TypeCheval unTypeCheval = TypeChevalDAO.getTypeCheval(connection, idTypeCheval);
			
			request.setAttribute("pTypeCheval", unTypeCheval);
			changerTitrePage("Modifier un type de cheval", request);

			this.getServletContext().getRequestDispatcher("/vues/type_cheval/typeChevalModifier.jsp").forward(request, response);
		}
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
		
        String url = request.getRequestURI();
        if(url.equals("/EquidaWebG2/ServletTypeCheval/typeChevalAjouter")){
            TypeChevalForm formTypeCheval = new TypeChevalForm();
            TypeCheval unTypeCheval = formTypeCheval.getTypeCheval(request);
        
            if (formTypeCheval.getErreurs().isEmpty()){
                // Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos du client 
                
                TypeChevalDAO.ajouterTypeCheval(connection, unTypeCheval);
                request.setAttribute( "pTypeCheval", unTypeCheval );
                this.getServletContext().getRequestDispatcher("/vues/type_cheval/typeChevalConsulter.jsp" ).forward( request, response );

            }
            else
            { 

               this.getServletContext().getRequestDispatcher("/vues/type_cheval/typeChevalAjouter.jsp" ).forward( request, response );
            }
        }
		
		if (url.equals("/EquidaWebG2/ServletTypeCheval/typeChevalModifier")) {
            /* Préparation de l'objet formulaire */
            TypeChevalForm form = new TypeChevalForm();

            /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
            TypeCheval unTypeCheval = form.getTypeCheval(request);

            /* Stockage du formulaire et de l'objet dans l'objet request */
            request.setAttribute("form", form);
            request.setAttribute("pTypeCheval", unTypeCheval);
            
            if (form.getErreurs().isEmpty()) {
				// Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos du client 
				
				TypeChevalDAO.modifierTypeCheval(connection, unTypeCheval, form.getTypeChevalOrigin(request));
				this.getServletContext().getRequestDispatcher("/vues/type_cheval/typeChevalConsulter.jsp").forward(request, response);

			} else {
				this.getServletContext().getRequestDispatcher("/vues/type_cheval/typeChevalAjouter.jsp").forward(request, response);
			}
		}
        
    }
}
