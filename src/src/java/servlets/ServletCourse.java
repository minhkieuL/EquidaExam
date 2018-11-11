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

	private static final String URL_AJOUTER_COURSE = "/EquidaWebG2/ServletCourse/courseAjouter";
	private static final String URL_MODIFIER_COURSE = "/EquidaWebG2/ServletCourse/courseModifier";
	private static final String URL_LISTER_COURSES = "/EquidaWebG2/ServletCourse/listerLesCourses";
	
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
		if (url.equals(URL_AJOUTER_COURSE)) {
			if(user instanceof DirecteurGeneral) {
				changerTitrePage("Ajouter une Course", request);

				getServletContext().getRequestDispatcher("/vues/course/courseAjouter.jsp").forward(request, response);
			} else {
				redirigerVersAcceuil(response);
			}			
		}
		
		if (url.equals(URL_MODIFIER_COURSE)) {
			if (user instanceof DirecteurGeneral) {
				int idCourse = Integer.valueOf(request.getParameter("code"));
				Course uneCourse = CourseDAO.getCourse(connection, idCourse);

				request.setAttribute("pCourse", uneCourse);
				changerTitrePage("Modifier une course", request);

				this.getServletContext().getRequestDispatcher("/vues/course/courseModifier.jsp").forward(request, response);
			} else {
				redirigerVersAcceuil(response);
			}
		}
		
		if (url.equals(URL_LISTER_COURSES)) {
			if(user instanceof DirecteurGeneral) {
				ArrayList <Course> lesCourses = CourseDAO.getLesCourses(connection);

				request.setAttribute("pLesCourses", lesCourses);
				changerTitrePage("Lister les courses", request);

				getServletContext().getRequestDispatcher("/vues/course/listerLesCourses.jsp").forward(request, response);
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
        if (url.equals(URL_AJOUTER_COURSE)) {
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

					response.sendRedirect(URL_LISTER_COURSES);

				} else {
					response.sendRedirect(URL_AJOUTER_COURSE);
				}
			} else {
				redirigerVersAcceuil(response);
			}
		}
		
		if (url.equals(URL_MODIFIER_COURSE)) {
			if(user instanceof DirecteurGeneral) {
				/* Préparation de l'objet formulaire */
				CourseForm form = new CourseForm();

				/* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
				Course uneCourse = form.getCourse(request);

				/* Stockage du formulaire et de l'objet dans l'objet request */
				request.setAttribute("form", form);
				request.setAttribute("pCourse", uneCourse);

				if (form.getErreurs().isEmpty()) {
					// Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos du client 

					CourseDAO.modifierCourse(connection, uneCourse, form.getCourseOrigin(request));
					response.sendRedirect(URL_LISTER_COURSES);
				} else {
					response.sendRedirect(URL_MODIFIER_COURSE+"?code="+uneCourse.getId());
				}
			} else {
				redirigerVersAcceuil(response);
			}
		}
	}
}
