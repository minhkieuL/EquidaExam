package servlets;

import database.PaysDAO;
import formulaires.PaysForm;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.DirecteurGeneral;
import modele.Pays;
import modele.Utilisateur;

/**
 *
 * @author slam
 */
public class ServletPays extends ServletBase {

	public static final String URL_AJOUTER_PAYS = "/EquidaWebG2/ServletPays/paysAjouter";
	public static final String URL_MODIFIER_PAYS = "/EquidaWebG2/ServletPays/paysModifier";
	public static final String URL_LISTER_PAYS = "/EquidaWebG2/ServletPays/listerLesPays";
	
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

		if (url.equals(URL_AJOUTER_PAYS)) {
			if (user instanceof DirecteurGeneral) {
				changerTitrePage("Ajouter un pays", request);

				getServletContext().getRequestDispatcher("/vues/pays/paysAjouter.jsp").forward(request, response);
			} else {
				redirigerVersAcceuil(response);
			}
		}

		if (url.equals(URL_MODIFIER_PAYS)) {
			if (user instanceof DirecteurGeneral) {
				String codePays = request.getParameter("code");
				Pays unPays = PaysDAO.getPays(connection, codePays);

				request.setAttribute("pPays", unPays);
				changerTitrePage("Modifier un pays", request);

				this.getServletContext().getRequestDispatcher("/vues/pays/paysModifier.jsp").forward(request, response);
			} else {
				redirigerVersAcceuil(response);
			}
		}

		if (url.equals(URL_LISTER_PAYS)) {
			if(user instanceof DirecteurGeneral) {
				ArrayList<Pays> lesPays = PaysDAO.getLesPays(connection);

				request.setAttribute("pLesPays", lesPays);
				changerTitrePage("Lister les pays", request);

				getServletContext().getRequestDispatcher("/vues/pays/listerLesPays.jsp").forward(request, response);
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
		if (url.equals(URL_AJOUTER_PAYS)) {
			if(user instanceof DirecteurGeneral) {
				/* Préparation de l'objet formulaire */
				PaysForm formPays = new PaysForm();
				/* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
				Pays unPays = formPays.getPays(request);

				if (formPays.getErreurs().isEmpty()) {
					PaysDAO.ajouterPays(connection, unPays);
					response.sendRedirect(URL_LISTER_PAYS);
				} else {
					request.getSession().setAttribute("form", formPays);
					response.sendRedirect(URL_AJOUTER_PAYS);
				}
			} else {
				redirigerVersAcceuil(response);
			}
		}

		if (url.equals(URL_MODIFIER_PAYS)) {
			if(user instanceof DirecteurGeneral) {
				/* Préparation de l'objet formulaire */
				PaysForm form = new PaysForm();

				/* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
				Pays unPays = form.getPays(request);

				if (form.getErreurs().isEmpty()) {
					PaysDAO.modifierPays(connection, unPays, form.getPaysOrigin(request));
					response.sendRedirect(URL_LISTER_PAYS);
				} else {
					request.getSession().setAttribute("form", form);
					response.sendRedirect(URL_MODIFIER_PAYS+"?code="+form.getPaysOrigin(request));
				}
			} else {
				redirigerVersAcceuil(response);
			}
		}
	}
}
