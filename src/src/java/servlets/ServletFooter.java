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

		if (url.equals("/EquidaWebG2/ServletFooter/mentions_legales")) {
			changerTitrePage("Mentions legales", request);

			getServletContext().getRequestDispatcher("/vues/informations/mentions_legales.jsp").forward(request, response);
		}
		
		if (url.equals("/EquidaWebG2/ServletFooter/qui_sommes_nous")) {
			changerTitrePage("Qui sommes-nous", request);

			getServletContext().getRequestDispatcher("/vues/informations/qui_sommes_nous.jsp").forward(request, response);
		}
	}
}
