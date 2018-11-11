package servlets;

import database.LotDAO;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Lot;

/**
 *
 * @author slam
 */
public class ServletLot extends ServletBase {

	public static final String URL_LISTER_LOTS = "/EquidaWebG2/ServletLot/listerLesLots";
	
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

		String url = request.getRequestURI();
		if (url.equals(URL_LISTER_LOTS)) {
			ArrayList<Lot> lesLots = LotDAO.getLesLotsNonVendu(connection);

			request.setAttribute("pLots", lesLots);
			changerTitrePage("Lister les lots", request);

			getServletContext().getRequestDispatcher("/vues/lot/listerLots.jsp").forward(request, response);

		}
	}
}
