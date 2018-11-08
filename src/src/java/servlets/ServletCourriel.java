package servlets;

import database.ChevalDAO;
import database.CourrielDAO;
import database.PaysDAO;
import database.TypeChevalDAO;
import formulaires.ChevalForm;
import formulaires.PaysForm;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Cheval;
import modele.Courriel;
import modele.DirecteurGeneral;
import modele.Pays;
import modele.TypeCheval;
import modele.Utilisateur;

/**
 *
 * @author slam
 */
public class ServletCourriel extends ServletBase {

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
		if (url.equals("/EquidaWebG2/ServletCourriel/listerLesCourriels")) {
			if(user instanceof DirecteurGeneral) {
				String codeVente = (String) request.getParameter("codeVente");
				ArrayList<Courriel> lesCourriels = CourrielDAO.getLesCourriels(connection, codeVente);

				request.setAttribute("pLesCourriels", lesCourriels);
				changerTitrePage("Lister les courriels", request);

				getServletContext().getRequestDispatcher("/vues/ventes/listerLesCourriels.jsp").forward(request, response);
			} else {
				redirigerVersAcceuil(response);
			}
		}
	}
}
