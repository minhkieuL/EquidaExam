package servlets;

import database.CategVenteDAO;
import database.ChevalDAO;
import database.PaysDAO;
import database.TypeChevalDAO;
import formulaires.CategorieForm;
import formulaires.ChevalForm;
import formulaires.PaysForm;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.CategVente;
import modele.Cheval;
import modele.Pays;
import modele.TypeCheval;

/**
 *
 * @author slam
 */
public class ServletCategVente extends ServletBase {

	Connection connection;

	@Override
	public void init() {
		ServletContext servletContext = getServletContext();
		connection = (Connection) servletContext.getAttribute("connection");
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

		if (url.equals("/EquidaWebG2/ServletCategVente/categorieVenteAjouter")) {
			changerTitrePage("Ajouter une catégorie de vente", request);

			getServletContext().getRequestDispatcher("/vues/categorie_vente/categorieVenteAjouter.jsp").forward(request, response);
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
        if (url.equals("/EquidaWebG2/ServletCategVente/categorieVenteAjouter")) {
            /* Préparation de l'objet formulaire */
            CategorieForm formCategorie = new CategorieForm();		
            /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
            CategVente uneCategVente = formCategorie.getCategVente(request);
            
			if (formCategorie.getErreurs().isEmpty()) {
				// Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos du client 
				CategVenteDAO.ajouterCategVente(connection, uneCategVente);
                /* Stockage du formulaire et de l'objet dans l'objet request */
                
                request.setAttribute("form", formCategorie);
                request.setAttribute("pCategVente", uneCategVente);
                
				this.getServletContext().getRequestDispatcher("/vues/categorie_vente/categorieVenteConsulter.jsp").forward(request, response);
			} else {
				this.getServletContext().getRequestDispatcher("/vues/categorie_vente/categorieVenteAjouter.jsp").forward(request, response);
			}
		}
	}
}
