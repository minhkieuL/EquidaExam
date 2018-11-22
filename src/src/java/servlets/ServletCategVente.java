package servlets;

import database.CategVenteDAO;
import formulaires.CategorieForm;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.CategVente;
import modele.DirecteurGeneral;
import modele.Utilisateur;

/**
 *
 * @author slam
 */
public class ServletCategVente extends ServletBase {
	
	public static final String URL_AJOUTER_CATEG_VENTE = "/EquidaWebG2/ServletCategVente/categorieVenteAjouter";
	public static final String URL_LISTER_CATEG_VENTE = "/EquidaWebG2/ServletCategVente/listerLesCategVentes";
	public static final String URL_MODIFIER_CATEG_VENTE = "/EquidaWebG2/ServletCategVente/categorieVenteModifier";
	
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
		
		Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");
		String url = request.getRequestURI();
		if (url.equals(URL_AJOUTER_CATEG_VENTE)) {
			if(user instanceof DirecteurGeneral) {
				changerTitrePage("Ajouter une catégorie de vente", request);

				getServletContext().getRequestDispatcher("/vues/categorie_vente/categorieVenteAjouter.jsp").forward(request, response);
			} else {
				redirigerVersAcceuil(response);
			}
		}
        
        if (url.equals(URL_LISTER_CATEG_VENTE)) {
			if(user instanceof DirecteurGeneral) {
				ArrayList<CategVente> lesCategVentes = CategVenteDAO.getLesCategVentes(connection);

				request.setAttribute("pLesCategVentes", lesCategVentes);
				changerTitrePage("Lister les catégories de vente", request);

				getServletContext().getRequestDispatcher("/vues/categorie_vente/listerLesCategVentes.jsp").forward(request, response);
			} else {
				redirigerVersAcceuil(response);
			}
		}
		
		if (url.equals(URL_MODIFIER_CATEG_VENTE)) {
			if(user instanceof DirecteurGeneral) {
				String codeCateg = request.getParameter("code");
				CategVente uneCategVente = CategVenteDAO.getCategVente(connection, codeCateg);

				request.setAttribute("pCategVente", uneCategVente);
				changerTitrePage("Modifier une catégorie de vente", request);

				this.getServletContext().getRequestDispatcher("/vues/categorie_vente/categorieVenteModifier.jsp").forward(request, response);
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
        if (url.equals(URL_AJOUTER_CATEG_VENTE)) {
			if(user instanceof DirecteurGeneral) {
				/* Préparation de l'objet formulaire */
				CategorieForm formCategorie = new CategorieForm();		
				/* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
				CategVente uneCategVente = formCategorie.getCategVente(request);

				if (formCategorie.getErreurs().isEmpty()) {
					CategVenteDAO.ajouterCategVente(connection, uneCategVente);

					response.sendRedirect(URL_LISTER_CATEG_VENTE);
				} else {
					request.getSession().setAttribute("form", formCategorie);
					response.sendRedirect(URL_AJOUTER_CATEG_VENTE);
				}
			} else {
				redirigerVersAcceuil(response);
			}
		}
        
        if (url.equals(URL_MODIFIER_CATEG_VENTE)) {
			if(user instanceof DirecteurGeneral) {
				/* Préparation de l'objet formulaire */
				CategorieForm formCategorie = new CategorieForm();

				/* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
				CategVente uneCategVente = formCategorie.getCategVente(request);

				if (formCategorie.getErreurs().isEmpty()) {
					CategVenteDAO.modifierCategVente(connection, uneCategVente, formCategorie.getCategVenteOrigin(request));
					response.sendRedirect(URL_LISTER_CATEG_VENTE);
				} else {
					request.getSession().setAttribute("form", formCategorie);
					response.sendRedirect(URL_MODIFIER_CATEG_VENTE+"?code="+formCategorie.getCategVenteOrigin(request));
				}
			} else {
				redirigerVersAcceuil(response);
			}
		}
	}
}
