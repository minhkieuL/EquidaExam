package servlets;

import formulaires.Form;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MartinJ
 */
public abstract class ServletBase extends HttpServlet {

	public ServletBase() {

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
	}

	public void changerTitrePage(String titre, HttpServletRequest request) {
		request.setAttribute("title", titre);
	}

	public void redirigerVersAcceuil(HttpServletResponse response) {
		try {
			response.sendRedirect("/EquidaWebG2");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Form getForm(HttpServletRequest request) {
		Form form = (Form) request.getSession().getAttribute("form");
		request.getSession().removeAttribute("form");
		return form;
	}
}
