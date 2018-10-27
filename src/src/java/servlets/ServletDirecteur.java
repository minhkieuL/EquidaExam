package servlets;

import database.CategVenteDAO;
import database.PaysDAO;
import database.Utilitaire;
import formulaires.CategorieForm;
import formulaires.PaysForm;
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.CategVente;
import modele.Pays;


/**
    Document   : ServletDirecteur
    Created on : 12 oct. 2018, 09:00:00
    Author     : paul_collet
 */
public class ServletDirecteur extends ServletBase {
    
    Connection connection ;
    
    @Override
    public void init() {     
        ServletContext servletContext=getServletContext();
        connection=(Connection)servletContext.getAttribute("connection");
    }

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

		if(url.equals("/EquidaWebG2/ServletDirecteur/categorieVenteAjouter")) {     
			changerTitrePage("Ajouter une catégorie de vente", request);
			
			getServletContext().getRequestDispatcher("/vues/categorieVenteAjouter.jsp" ).forward( request, response );
		}

		if(url.equals("/EquidaWebG2/ServletDirecteur/paysAjouter")) {     
			changerTitrePage("Ajouter un pays", request);
			
			getServletContext().getRequestDispatcher("/vues/paysAjouter.jsp" ).forward( request, response );
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            
            throws ServletException, IOException {
        super.doPost(request, response);
        String url = request.getRequestURI();
        /* Préparation de l'objet formulaire */
        CategorieForm form = new CategorieForm();
        PaysForm formPays = new PaysForm();
		
        /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
        CategVente uneCategVente = form.getCategVente(request);
        Pays unPays = formPays.getPays(request);
        
        /* Stockage du formulaire et de l'objet dans l'objet request */
        request.setAttribute( "form", form );
        request.setAttribute( "pCategVente", uneCategVente );
        request.setAttribute( "pPays", unPays);
		
        if(url.equals("/EquidaWebG2/ServletDirecteur/categorieVenteAjouter")){
            if (form.getErreurs().isEmpty()){
                // Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos du client 
                CategVenteDAO.ajouterCategVente(connection, uneCategVente);
                this.getServletContext().getRequestDispatcher("/vues/categorieVenteConsulter.jsp" ).forward( request, response );

            } else { 
               this.getServletContext().getRequestDispatcher("/vues/categorieVenteAjouter.jsp" ).forward( request, response );
            }
        }
    
        if(url.equals("/EquidaWebG2/ServletDirecteur/paysAjouter")) {
            if (form.getErreurs().isEmpty()){
                // Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos du client 
                PaysDAO.ajouterPays(connection, unPays);
                this.getServletContext().getRequestDispatcher("/vues/paysConsulter.jsp" ).forward( request, response );

            } else { 

               this.getServletContext().getRequestDispatcher("/vues/paysAjouter.jsp" ).forward( request, response );
            }
        }
    }
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
	}
    
    public void destroy(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        try {
            //fermeture
            System.out.println("Connexion fermée");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l’établissement de la connexion");
        } finally {
            //Utilitaire.fermerConnexion(rs);
            //Utilitaire.fermerConnexion(requete);
            Utilitaire.fermerConnexion(connection);
        }
    }
}
