/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.LieuDAO;
import formulaires.LieuForm;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.DirecteurGeneral;
import modele.Lieu;
import modele.Utilisateur;

/**
 *
 * @author slam
 */
public class ServletLieu extends ServletBase {
	
	private static final String URL_AJOUTER_LIEU = "/EquidaWebG2/ServletLieu/ajouterLieu";
	private static final String URL_LISTER_LIEU = "/EquidaWebG2/ServletLieu/lieuLister";
	private static final String URL_MODIFIER_LIEU = "/EquidaWebG2/ServletLieu/lieuModifier";
	
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
		if (url.equals(URL_AJOUTER_LIEU)) {
			if(user instanceof DirecteurGeneral) {
				changerTitrePage("Ajouter un lieu", request);

				getServletContext().getRequestDispatcher("/vues/lieu/lieuAjouter.jsp").forward(request, response);
			} else {
				redirigerVersAcceuil(response);
			}
		} 	
		
		if (url.equals(URL_LISTER_LIEU)) {
			ArrayList<Lieu> lesLieux = LieuDAO.getLesLieux(connection);

			request.setAttribute("pLesLieux", lesLieux);
			changerTitrePage("Lister un lieu", request);
			getServletContext().getRequestDispatcher("/vues/lieu/lieuLister.jsp").forward(request, response);

		}
		if (url.equals(URL_MODIFIER_LIEU)) {
			if(user instanceof DirecteurGeneral) {
				int idLieu = Integer.valueOf(request.getParameter("id"));
				Lieu unLieu = LieuDAO.getLieu(connection, idLieu);

				request.setAttribute("pLieu", unLieu);
				changerTitrePage("Modifier un lieu", request);
				getServletContext().getRequestDispatcher("/vues/lieu/lieuModifier.jsp").forward(request, response);
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
        if (url.equals(URL_AJOUTER_LIEU)) {
			if(user instanceof DirecteurGeneral) {
				/* Préparation de l'objet formulaire */
				LieuForm formLieu = new LieuForm();
				/* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */            
				Lieu unLieu = formLieu.getLieu(request);

				if (formLieu.getErreurs().isEmpty()) {
					// Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos du client 
					LieuDAO.ajouterLieu(connection, unLieu);

					/* Stockage du formulaire et de l'objet dans l'objet request */
					request.setAttribute("form", formLieu);
					request.setAttribute("pLieu", unLieu);

					response.sendRedirect(URL_LISTER_LIEU);

				} else {

					response.sendRedirect(URL_AJOUTER_LIEU);
				}
			} else {
				redirigerVersAcceuil(response);
			}
			
		}
		if (url.equals(URL_MODIFIER_LIEU)) {
			
			if(user instanceof DirecteurGeneral) {
				/* Préparation de l'objet formulaire */
				LieuForm form = new LieuForm();

				/* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
				Lieu unLieu = form.getLieu(request);

				/* Stockage du formulaire et de l'objet dans l'objet request */
				request.setAttribute("form", form);
				request.setAttribute("pLieu", unLieu);

				if (form.getErreurs().isEmpty()) {
					// Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos du client 

					LieuDAO.modifierLieu(connection, unLieu, form.getLieuOrigin(request));
					response.sendRedirect(URL_LISTER_LIEU);

				} else {
					response.sendRedirect(URL_MODIFIER_LIEU);
				}
			} else {
				redirigerVersAcceuil(response);
			}
		}
	}
	
}