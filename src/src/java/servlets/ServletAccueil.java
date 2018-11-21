package servlets;

import database.LotDAO;
import database.VenteDAO;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Lot;
import modele.Vente;

/**
 *
 * @author MartinJ
 */
public class ServletAccueil extends ServletBase {
	
	private Connection connection;

	@Override
	public void init() throws ServletException {
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
		changerTitrePage("Accueil", request);
		
		ArrayList<Vente> ventesAVenir = VenteDAO.getLesVentesAVenir(connection);
		ArrayList<Lot> nouveauxLots = LotDAO.getLesNouveauxLots(connection);

		request.setAttribute("pVentesAVenir", ventesAVenir);
		request.setAttribute("pNouveauxLots", nouveauxLots);
		this.getServletContext().getRequestDispatcher("/vues/index.jsp").forward(request, response);
	}

}
