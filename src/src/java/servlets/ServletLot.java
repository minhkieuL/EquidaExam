package servlets;

import database.LotDAO;
import formulaires.LotForm;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Client;
import modele.DirecteurGeneral;
import modele.Lot;
import modele.Utilisateur;

/**
 *
 * @author slam
 */
public class ServletLot extends ServletBase {

	public static final String URL_LISTER_LOTS = "/EquidaWebG2/ServletLot/listerLesLots";
	public static final String URL_LISTER_NONVALIDER = "/EquidaWebG2/ServletLot/listerLesNonValides";
	public static final String URL_AJOUTER_LOTS = "/EquidaWebG2/ServletLot/ajouterLot";
	
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
		if (url.equals(URL_LISTER_LOTS)) {
			ArrayList<Lot> lesLots = LotDAO.getLesLotsNonVendu(connection);

			request.setAttribute("pLots", lesLots);
			changerTitrePage("Lister les lots", request);

			getServletContext().getRequestDispatcher("/vues/lot/listerLots.jsp").forward(request, response);

		}
		
		
		if (url.equals(URL_LISTER_NONVALIDER)) {
			if(user instanceof DirecteurGeneral) {
				ArrayList<Lot> lesLots = LotDAO.getlesLotsNonValides(connection);

				request.setAttribute("pLots", lesLots);
				changerTitrePage("Lister les ventes non validés", request);

				getServletContext().getRequestDispatcher("/vues/lot/lotsNonValides.jsp").forward(request, response);
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
		if(url.equals(URL_AJOUTER_LOTS)) {
			if(user instanceof Client) {
				LotForm form = new LotForm();
				Lot lot = form.getLot(request);
				LotDAO.ajouterLot(connection, lot);
				
				response.sendRedirect(ServletVentes.URL_CONSULTER_VENTE+"?id="+lot.getVente().getId());
			} else {
				redirigerVersAcceuil(response);
			}
		}
	}
	
	
}
