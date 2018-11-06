/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.CategVenteDAO;
import database.ClientDAO;
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
			ArrayList<CategVente> lesCatVentes = CategVenteDAO.getLesCategVentes(connection);

			request.setAttribute("pLesCatVentes", lesCatVentes);
			request.setAttribute("pLesVentes", lesVentes);
			changerTitrePage("Lister les ventes", request);

			getServletContext().getRequestDispatcher("/vues/ventes/listerLesVentes.jsp").forward(request, response);
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
