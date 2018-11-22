package servlets;

import database.CategVenteDAO;
import database.CourrielDAO;
import database.VenteDAO;
import formulaires.CourrielForm;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.CategVente;
import modele.Courriel;
import modele.DirecteurGeneral;
import modele.Utilisateur;
import modele.Vente;
import outils.EnvoieMail;

/**
 *
 * @author slam
 */
@MultipartConfig
public class ServletCourriel extends ServletBase {

	public static final String URL_LISTER_COURIELS = "/EquidaWebG2/ServletCourriel/listerLesCourriels";
	public static final String URL_AJOUTER_COURIEL = "/EquidaWebG2/ServletCourriel/ajouterCourriel";
	
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
		if (url.equals(URL_LISTER_COURIELS)) {
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
		
		if(url.equals(URL_AJOUTER_COURIEL)) {
			if(user instanceof DirecteurGeneral) {
				ArrayList<Vente> lesVentes = VenteDAO.getLesVentes(connection);

				request.setAttribute("pVentes", lesVentes);
				changerTitrePage("Envoyer un courriel", request);

				getServletContext().getRequestDispatcher("/vues/courriel/courrielAjouter.jsp").forward(request, response);
			} else {
				redirigerVersAcceuil(response);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
				
		Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");
		String url = request.getRequestURI();
		if(url.equals(URL_AJOUTER_COURIEL)) {
			if(user instanceof DirecteurGeneral) {
				CourrielForm form = new CourrielForm();
				Courriel courriel = form.getCourriel(request);
				if(form.getErreurs().isEmpty()) {
					EnvoieMail.envoyerMail(connection, request, courriel);
					response.sendRedirect(URL_LISTER_COURIELS+"?codeVente="+courriel.getVente().getId());
				} else {
					response.sendRedirect(URL_AJOUTER_COURIEL);
				}
				
			} else {
				redirigerVersAcceuil(response);
			}
		}
	}
}
