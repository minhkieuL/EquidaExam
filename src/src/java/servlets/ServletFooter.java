package servlets;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author slam
 */
public class ServletFooter extends ServletBase {

	private static final String URL_MENTIONS_LEGALES = "/EquidaWebG2/ServletFooter/mentions_legales";
	private static final String URL_QUI_SOMMES_NOUS = "/EquidaWebG2/ServletFooter/qui_sommes_nous";
	private static final String URL_CONTACT = "/EquidaWebG2/ServletFooter/contact";
	
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

		if (url.equals(URL_MENTIONS_LEGALES)) {
			changerTitrePage("Mentions legales", request);

			getServletContext().getRequestDispatcher("/vues/informations/mentions_legales.jsp").forward(request, response);
		}
		
		if (url.equals(URL_QUI_SOMMES_NOUS)) {
			changerTitrePage("Qui sommes-nous", request);

			getServletContext().getRequestDispatcher("/vues/informations/qui_sommes_nous.jsp").forward(request, response);
		}
		
		if (url.equals(URL_CONTACT)) {
			changerTitrePage("Contact", request);

			getServletContext().getRequestDispatcher("/vues/informations/contact.jsp").forward(request, response);
		}
	}
}
