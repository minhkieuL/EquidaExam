package servlets;

import database.EnchereDAO;
import formulaires.EnchereForm;
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.DirecteurGeneral;
import modele.Enchere;
import modele.Utilisateur;
import modele.Vente;

/**
 *
 * @author slam
 */
public class ServletEnchere extends ServletBase {

	public static final String URL_AJOUTER_ENCHERE = "/EquidaWebG2/ServletEnchere/ajouterEnchere";
	
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
				
		Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");
		
		String url = request.getRequestURI();
		if(url.equals(URL_AJOUTER_ENCHERE)) {
			
			int idCheval = Integer.valueOf(request.getParameter("idCheval"));
				
			if(user instanceof DirecteurGeneral) {
				EnchereForm form = new EnchereForm();
				Enchere uneEnchere = form.getEnchere(request);
				EnchereDAO.ajouterEnchere(connection, uneEnchere);
				
				response.sendRedirect(ServletCheval.URL_CONSULTER_CHEVAL+"?id="+idCheval);
			} else {
				response.sendRedirect(ServletCheval.URL_CONSULTER_CHEVAL+"?id="+idCheval);
			}
		}
	}
	
	
}
