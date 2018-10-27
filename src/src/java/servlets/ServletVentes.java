/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.CourrielDAO;
import database.LotDAO;
import database.Utilitaire;
import database.VenteDAO;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.CategVente;
import modele.Client;
import modele.Courriel;
import modele.Lot;
import modele.Vente;

/**
 *
 * @author Zakina Classe Servlet permettant d'executer les fonctionnalités
 * relatives aux ventes : Fonctionnalités implémentées : lister les ventes
 * lister les clients d'une vente passée en paramètre
 */
public class ServletVentes extends ServletBase {

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
		String url = request.getRequestURI();

		// Récup et affichage par date décroissante de toutes les ventes   
		if (url.equals("/EquidaWebG2/ServletVentes/listerLesVentes")) {
			String catVente = (String) request.getParameter("catVente");
			ArrayList<Vente> lesVentes = VenteDAO.getLesVentes(connection, catVente);
			ArrayList<CategVente> lesCatVentes = VenteDAO.getLesCatVentes(connection);

			request.setAttribute("pLesCatVentes", lesCatVentes);
			request.setAttribute("pLesVentes", lesVentes);
			changerTitrePage("Lister les ventes", request);

			getServletContext().getRequestDispatcher("/vues/ventes/listerLesVentes.jsp").forward(request, response);
		}

		// Récup et affichage des clients interessés par une certaine catégorie de ventes
		if (url.equals("/EquidaWebG2/ServletVentes/listerLesClients")) {
			String codeCat = (String) request.getParameter("codeCat");
			ArrayList<Client> lesClients = VenteDAO.getLesClients(connection, codeCat);

			request.setAttribute("pLesClients", lesClients);
			changerTitrePage("Lister les clients", request);

			getServletContext().getRequestDispatcher("/vues/ventes/listerLesClients.jsp").forward(request, response);
		}

		if (url.equals("/EquidaWebG2/ServletVentes/listerLesCourriels")) {
			String codeVente = (String) request.getParameter("codeVente");
			ArrayList<Courriel> lesCourriels = CourrielDAO.getLesCourriels(connection, codeVente);

			request.setAttribute("pLesCourriels", lesCourriels);
			changerTitrePage("Lister les courriels", request);

			getServletContext().getRequestDispatcher("/vues/ventes/listerLesCourriels.jsp").forward(request, response);
		}

		if (url.equals("/EquidaWebG2/ServletVentes/listerLesChevauxParVentes")) {
			String idVente = (String) request.getParameter("idVente");
			ArrayList<Lot> lesLots = LotDAO.getLesLotPourVente(connection, idVente);

			request.setAttribute("pLesLots", lesLots);
			changerTitrePage("Lister les chevaux par ventes", request);

			getServletContext().getRequestDispatcher("/vues/ventes/listerLesChevauxParVentes.jsp").forward(request, response);
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
