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
		if (url.equals("/EquidaWebG2/ServletLieu/ajouterLieu")) {
			if(user instanceof DirecteurGeneral) {
				changerTitrePage("Ajouter un lieu", request);

				getServletContext().getRequestDispatcher("/vues/lieu/lieuAjouter.jsp").forward(request, response);
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
        if (url.equals("/EquidaWebG2/ServletLieu/ajouterLieu")) {
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

					this.getServletContext().getRequestDispatcher("/vues/lieu/lieuConsulter.jsp").forward(request, response);

				} else {

					this.getServletContext().getRequestDispatcher("/vues/lieu/lieuAjouter.jsp").forward(request, response);
				}
			} else {
				redirigerVersAcceuil(response);
			}
			
		}
	}
	
}