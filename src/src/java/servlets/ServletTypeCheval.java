package servlets;

import database.TypeChevalDAO;

import formulaires.TypeChevalForm;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.DirecteurGeneral;
import modele.TypeCheval;
import modele.Utilisateur;


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
	   
	   Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");
       String url = request.getRequestURI();       
       if(url.equals("/EquidaWebG2/ServletTypeCheval/typeChevalAjouter")) { 
			if(user instanceof DirecteurGeneral) {
				changerTitrePage("Ajouter race cheval", request);
				getServletContext().getRequestDispatcher("/vues/type_cheval/typeChevalAjouter.jsp" ).forward( request, response );
			} else {
				redirigerVersAcceuil(response);
			}
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
		
		Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");
        String url = request.getRequestURI();
        if(url.equals("/EquidaWebG2/ServletTypeCheval/typeChevalAjouter")){
			if(user instanceof DirecteurGeneral) {
				TypeChevalForm formTypeCheval = new TypeChevalForm();
				TypeCheval unTypeCheval = formTypeCheval.getTypeCheval(request);

				if (formTypeCheval.getErreurs().isEmpty()){
					// Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos du client 

					TypeChevalDAO.ajouterTypeCheval(connection, unTypeCheval);
					request.setAttribute( "pTypeCheval", unTypeCheval );
					this.getServletContext().getRequestDispatcher("/vues/type_cheval/typeChevalConsulter.jsp" ).forward( request, response );

				} else { 
				   this.getServletContext().getRequestDispatcher("/vues/type_cheval/typeChevalAjouter.jsp" ).forward( request, response );
				}
			} else {
				redirigerVersAcceuil(response);
			}
        }
        
    }
}
