package servlets;

import database.CategVenteDAO;
import database.ClientDAO;
import database.PaysDAO;
import database.UtilisateurDAO;
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
import modele.DirecteurGeneral;
import modele.Pays;
import modele.Utilisateur;

/**
 *
 * @author Zakina Servlet Client permettant d'excéuter les fonctionnalités
 * relatives au clients Fonctionnalités implémentées : ajouter un nouveau client
 */
public class ServletClient extends ServletBase {

	public static final String URL_AJOUTER_CLIENT = "/EquidaWebG2/ServletClient/ajouterClient";
	public static final String URL_LISTER_CLIENTS = "/EquidaWebG2/ServletClient/listerLesClients";
	public static final String URL_CONSULTER_CLIENT = "/EquidaWebG2/ServletClient/clientConsulter";
	public static final String URL_LISTER_CLIENTS_DIR_GEN = "/EquidaWebG2/ServletClient/listerLesClientsPrDirGen";
	public static final String URL_MODIFIER_CLIENT = "/EquidaWebG2/ServletClient/clientModifier";
	public static final String URL_ARCHIVER_CLIENT = "/EquidaWebG2/ServletClient/clientArchiver";
	
	
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
		if (url.equals(URL_AJOUTER_CLIENT)) {
			if(user instanceof DirecteurGeneral) {
				ArrayList<Pays> lesPays = PaysDAO.getLesPays(connection);
				ArrayList<CategVente> lesCategVentes = CategVenteDAO.getLesCategVentes(connection);

				request.setAttribute("pLesPays", lesPays);
				request.setAttribute("pLesCategVente", lesCategVentes);
				changerTitrePage("Ajouter un client", request);

				this.getServletContext().getRequestDispatcher("/vues/client/clientAjouter.jsp").forward(request, response);
			} else {
				redirigerVersAcceuil(response);
			}
		}
        
		if (url.equals(URL_LISTER_CLIENTS)) {
			if(user instanceof DirecteurGeneral) {
				String codeCat = (String) request.getParameter("codeCat");
				ArrayList<Client> lesClients = ClientDAO.getLesClients(connection, codeCat);

				request.setAttribute("pLesClients", lesClients);
				changerTitrePage("Lister les clients", request);

				getServletContext().getRequestDispatcher("/vues/categorie_vente/listerLesClients.jsp").forward(request, response);
			} else {
				redirigerVersAcceuil(response);
			}
		}
		
		if (url.equals(URL_LISTER_CLIENTS_DIR_GEN)) {
			if(user instanceof DirecteurGeneral) {
				ArrayList<Client> lesClients = ClientDAO.getLesClientsPrDirGen(connection);

				request.setAttribute("pLesClients", lesClients);
				changerTitrePage("Lister les clients", request);

				getServletContext().getRequestDispatcher("/vues/client/listerLesClientsPrDirGen.jsp").forward(request, response);
			} else {
				redirigerVersAcceuil(response);
			}
		}
		
		if (url.equals(URL_CONSULTER_CLIENT)) {
			if(user instanceof DirecteurGeneral) {
				int idClient = 0;
				try {
					idClient = Integer.valueOf(request.getParameter("id"));
				} catch(Exception e) {
					redirigerVersAcceuil(response);
					return;
				}
				
				Client client = ClientDAO.getClient(connection, idClient);

				request.setAttribute("pClient", client);
				changerTitrePage("Client " + client.getNom() + " " + client.getPrenom(), request);

				getServletContext().getRequestDispatcher("/vues/client/clientConsulter.jsp").forward(request, response);
			} else {
				redirigerVersAcceuil(response);
			}
		}
		
		if (url.equals(URL_MODIFIER_CLIENT)) {
			if(user instanceof DirecteurGeneral || user instanceof Client) {
				String idStr = request.getParameter("id");
				int idClient = -1;
				
				if(user instanceof Client) {
					idClient = user.getId();
				} else {
					idClient= Integer.valueOf(request.getParameter("id"));
				}
				
				
				Client unClient = ClientDAO.getClient(connection, idClient);
				ArrayList<Pays> lesPays = PaysDAO.getLesPays(connection);
				ArrayList<CategVente> lesCategVentes = CategVenteDAO.getLesCategVentes(connection);

				request.setAttribute("pLesPays", lesPays);
				request.setAttribute("pLesCategVente", lesCategVentes);
				request.setAttribute("pClient", unClient);
				changerTitrePage("Modifier un client", request);

				getServletContext().getRequestDispatcher("/vues/client/clientModifier.jsp").forward(request, response);
			} else {
				redirigerVersAcceuil(response);
			}
		}
		
		if (url.equals(URL_ARCHIVER_CLIENT)) {
			if(user instanceof DirecteurGeneral) {
				int idUtilisateur = 0;
				try {
					idUtilisateur = Integer.valueOf(request.getParameter("id"));
				} catch(Exception e) {
					redirigerVersAcceuil(response);
					return;
				}
				UtilisateurDAO.archiverUtilisateur(connection, idUtilisateur);
				response.sendRedirect(URL_LISTER_CLIENTS_DIR_GEN);
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
        if (url.equals(URL_AJOUTER_CLIENT)) {
			if(user instanceof DirecteurGeneral) {
				/* Préparation de l'objet formulaire */
				ClientForm form = new ClientForm();

				/* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
				Client unClient = form.getClient(request);

				if (form.getErreurs().isEmpty()) {
					int idClient = ClientDAO.ajouterClient(connection, unClient);
					response.sendRedirect(URL_CONSULTER_CLIENT+"?id="+idClient);
				} else {
					request.getSession().setAttribute("form", form);
					response.sendRedirect(URL_AJOUTER_CLIENT);
				}
			} else {
				redirigerVersAcceuil(response);
			}
        }
		
		 if (url.equals(URL_MODIFIER_CLIENT)) {
			if(user instanceof DirecteurGeneral || user instanceof Client) {				
				/* Préparation de l'objet formulaire */
				ClientForm form = new ClientForm();

				/* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
				Client unClient = form.getClient(request);
				if(user instanceof Client) {
					if(unClient.getId() != user.getId()) {
						redirigerVersAcceuil(response);
						return;
					}
				}

				if (form.getErreurs().isEmpty()) {
					ClientDAO.modifierClient(connection, unClient);
					response.sendRedirect(URL_LISTER_CLIENTS_DIR_GEN);
				} else {
					request.getSession().setAttribute("form", form);
					response.sendRedirect(URL_MODIFIER_CLIENT+"?id="+unClient.getId());
				}
			} else {
				redirigerVersAcceuil(response);
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
