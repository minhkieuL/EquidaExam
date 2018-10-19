package servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author MartinJ
 */
public abstract class ServletBase extends HttpServlet {

	public ServletBase() {

	}

	public void changerTitrePage(String titre, HttpServletRequest request) {
		request.setAttribute("title", titre);
	}
	
}
