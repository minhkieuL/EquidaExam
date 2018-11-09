package servlets;


import database.CourseDAO;
import formulaires.CourseForm;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Course;
import modele.DirecteurGeneral;
import modele.Utilisateur;

/**
 *
 * @author slam
 */
public class ServletCourse extends ServletBase {

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
		if (url.equals("/EquidaWebG2/ServletCourse/courseAjouter")) {
			if(user instanceof DirecteurGeneral) {
				changerTitrePage("Ajouter une Course", request);

				getServletContext().getRequestDispatcher("/vues/course/courseAjouter.jsp").forward(request, response);
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
        if (url.equals("/EquidaWebG2/ServletCourse/courseAjouter")) {
			if(user instanceof DirecteurGeneral) {
				/* Préparation de l'objet formulaire */
				CourseForm formCourse = new CourseForm();
				/* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */            
				Course uneCourse = formCourse.getCourse(request);

				if (formCourse.getErreurs().isEmpty()) {
					// Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos du client 
					CourseDAO.ajouterCourse(connection, uneCourse);

					/* Stockage du formulaire et de l'objet dans l'objet request */
					request.setAttribute("form", formCourse);
					request.setAttribute("pCourse", uneCourse);

					this.getServletContext().getRequestDispatcher("/vues/course/courseConsulter.jsp").forward(request, response);

				} else {

					this.getServletContext().getRequestDispatcher("/vues/course/courseAjouter.jsp").forward(request, response);
				}
			} else {
				redirigerVersAcceuil(response);
			}
		}
	}
}
