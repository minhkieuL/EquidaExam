package servlets;

import database.CategVenteDAO;
import database.ClientDAO;
import database.PaysDAO;
import database.Utilitaire;
import formulaires.ClientForm;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.CategVente;
import modele.Client;
import modele.Pays;

/**
 *
 * @author Zakina Servlet Client permettant d'excéuter les fonctionnalités
 * relatives au clients Fonctionnalités implémentées : ajouter un nouveau client
 */
public class ServletClient extends ServletBase {

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

		if (url.equals("/EquidaWebG2/ServletClient/ajouterClient")) {
			ArrayList<Pays> lesPays = PaysDAO.getLesPays(connection);
			ArrayList<CategVente> lesCategVentes = CategVenteDAO.getLesCategVentes(connection);

			request.setAttribute("pLesPays", lesPays);
			request.setAttribute("pLesCategVente", lesCategVentes);
			changerTitrePage("Ajouter un client", request);

			this.getServletContext().getRequestDispatcher("/vues/client/clientAjouter.jsp").forward(request, response);
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
		/* Préparation de l'objet formulaire */
		ClientForm form = new ClientForm();

		/* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
		Client unClient = form.getClient(request);

		/* Stockage du formulaire et de l'objet dans l'objet request */
		request.setAttribute("form", form);
		request.setAttribute("pClient", unClient);

		if (form.getErreurs().isEmpty()) {
			// Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos du client 
			ClientDAO.ajouterClient(connection, unClient);
			this.getServletContext().getRequestDispatcher("/vues/client/clientConsulter.jsp").forward(request, response);
		} else {
			// il y a des erreurs. On réaffiche le formulaire avec des messages d'erreurs
			ArrayList<Pays> lesPays = PaysDAO.getLesPays(connection);
			request.setAttribute("pLesPays", lesPays);

			ArrayList<CategVente> lesCategVentes = CategVenteDAO.getLesCategVentes(connection);
			request.setAttribute("pLesCategVente", lesCategVentes);
			this.getServletContext().getRequestDispatcher("/vues/client/clientAjouter.jsp").forward(request, response);
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
