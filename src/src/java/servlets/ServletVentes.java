package servlets;

import database.CategVenteDAO;
import database.ChevalDAO;
import database.ClientDAO;
import database.LieuDAO;
import database.Utilitaire;
import database.VenteDAO;
import formulaires.VenteForm;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.CategVente;
import modele.Cheval;
import modele.Client;
import modele.DirecteurGeneral;
import modele.Lieu;
import modele.Utilisateur;
import modele.Vente;

/**
 *
 * @author Zakina Classe Servlet permettant d'executer les fonctionnalités
 * relatives aux ventes : Fonctionnalités implémentées : lister les ventes
 * lister les clients d'une vente passée en paramètre
 */
public class ServletVentes extends ServletBase {

	public static final String URL_LISTER_VENTES = "/EquidaWebG2/ServletVentes/listerLesVentes";
	public static final String URL_AJOUTER_VENTE = "/EquidaWebG2/ServletVentes/venteAjouter";
	public static final String URL_CONSULTER_VENTE = "/EquidaWebG2/ServletVentes/venteConsulter";
	public static final String URL_MODIFIER_VENTE = "/EquidaWebG2/ServletVentes/venteModifier";
	
	
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");
		String url = request.getRequestURI();

		// Récup et affichage par date décroissante de toutes les ventes   
		if (url.equals(URL_LISTER_VENTES)) {
			String catVente = (String) request.getParameter("catVente");
			ArrayList<Vente> lesVentes = VenteDAO.getLesVentes(connection, catVente);
			ArrayList<CategVente> lesCatVentes = CategVenteDAO.getLesCategVentes(connection);

			request.setAttribute("pLesCatVentes", lesCatVentes);
			request.setAttribute("pLesVentes", lesVentes);
			changerTitrePage("Lister les ventes", request);

			getServletContext().getRequestDispatcher("/vues/ventes/listerLesVentes.jsp").forward(request, response);
		}
		 
		if (url.equals(URL_CONSULTER_VENTE)) {
			int idVente = 0;
			try {
				idVente = Integer.valueOf(request.getParameter("id"));
			} catch(Exception e) {
				redirigerVersAcceuil(response);
				return;
			}
			Vente vente = VenteDAO.getVente(connection, idVente);
			int idClient = (user != null) ? user.getId() : 0;
			ArrayList<Cheval> chevauxClient = ChevalDAO.getChevauxClientDispoVente(connection, idClient);
			ArrayList<Client> lesClients = ClientDAO.getLesClientsPrDirGen(connection);

			request.setAttribute("pClients", lesClients);
			request.setAttribute("pChevauxClient", chevauxClient);
			request.setAttribute("pVente", vente);
			changerTitrePage("Vente "+vente.getNom(), request);

			getServletContext().getRequestDispatcher("/vues/ventes/venteConsulter.jsp").forward(request, response);
		}
		
		if (url.equals(URL_AJOUTER_VENTE)) {
			if(user instanceof DirecteurGeneral) {
				ArrayList<Lieu> lesLieux = LieuDAO.getLesLieux(connection);
				ArrayList<CategVente> lesCategVentes = CategVenteDAO.getLesCategVentes(connection);

				request.setAttribute("pLesLieux", lesLieux);
				request.setAttribute("pLesCategVente", lesCategVentes);
				changerTitrePage("Ajouter une vente", request);

				this.getServletContext().getRequestDispatcher("/vues/ventes/venteAjouter.jsp").forward(request, response);
			} else {
				redirigerVersAcceuil(response);
			}
		}
		
		if (url.equals(URL_MODIFIER_VENTE)){
			if(user instanceof DirecteurGeneral) {
				
				ArrayList<Lieu> lesLieux = LieuDAO.getLesLieux(connection);
				ArrayList<CategVente> lesCategVentes = CategVenteDAO.getLesCategVentes(connection);
				
				int idVente = Integer.valueOf(request.getParameter("id"));
				Vente uneVente = VenteDAO.getUneVente(connection, idVente);
				
				request.setAttribute("pVente", uneVente);
				request.setAttribute("pLesLieux", lesLieux);
				request.setAttribute("pLesCategVente", lesCategVentes);
				changerTitrePage("Modification d'un vente", request);
				
				this.getServletContext().getRequestDispatcher("/vues/ventes/venteModifier.jsp").forward(request, response);	
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
        if (url.equals(URL_AJOUTER_VENTE)) {
			if(user instanceof DirecteurGeneral) {
				/* Préparation de l'objet formulaire */
				VenteForm form = new VenteForm();

				/* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
				Vente uneVente = form.getVente(request);

				/* Stockage du formulaire et de l'objet dans l'objet request */
				request.setAttribute("form", form);
				request.setAttribute("pVente", uneVente);

				if (form.getErreurs().isEmpty()) {
					// Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos de la vente
					int idVente = VenteDAO.ajouterVente(connection, uneVente);
					response.sendRedirect(URL_CONSULTER_VENTE+"?id="+idVente);
				} else {
					response.sendRedirect(URL_AJOUTER_VENTE);
				}
			} else {
				redirigerVersAcceuil(response);
			}
        }
	}

	public void destroy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
