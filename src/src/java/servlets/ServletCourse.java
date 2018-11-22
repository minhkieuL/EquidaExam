package servlets;


import formulaires.ParticipationForm;
import database.ChevalDAO;
import database.CourseDAO;
import database.ParticiperDAO;
import formulaires.CourseForm;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Cheval;
import modele.Client;
import modele.Course;
import modele.DirecteurGeneral;
import modele.Participer;
import modele.Utilisateur;


/**
 *
 * @author slam
 */
public class ServletCourse extends ServletBase {

	public static final String URL_AJOUTER_COURSE = "/EquidaWebG2/ServletCourse/courseAjouter";
	public static final String URL_RENSEIGNER_COURSE_POUR_CHEVAL = "/EquidaWebG2/ServletCourse/courseChevalRenseigner";
	public static final String URL_MODIFIER_COURSE = "/EquidaWebG2/ServletCourse/courseModifier";
	public static final String URL_LISTER_COURSES = "/EquidaWebG2/ServletCourse/listerLesCourses";
	public static final String URL_SUPPRIMER_CLASSEMENT_CHEVAL = "/EquidaWebG2/ServletCourse/courseSupprimer";
	
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
		
		if (url.equals(URL_RENSEIGNER_COURSE_POUR_CHEVAL)) {
			if(user instanceof Client || user instanceof DirecteurGeneral) {
				int idCheval = Integer.valueOf(request.getParameter("id"));
				Cheval unCheval = ChevalDAO.getCheval(connection, idCheval);
				ArrayList <Course> lesCourses = CourseDAO.getLesCourses(connection);

				request.setAttribute("pCheval", unCheval);
				request.setAttribute("pLesCourses", lesCourses);
				changerTitrePage("Ajouter le classement de votre cheval à une course", request);

				getServletContext().getRequestDispatcher("/vues/course/courseChevalRenseigner.jsp").forward(request, response);
			} else {
				redirigerVersAcceuil(response);
			}
		}
		if (url.equals(URL_SUPPRIMER_CLASSEMENT_CHEVAL)) {
			if(user instanceof Client) {
				int idCheval = Integer.valueOf(request.getParameter("idCheval"));
				int idCourse = Integer.valueOf(request.getParameter("idCourse"));
				
				ParticiperDAO.supprimerChevalVente(connection, idCheval, idCourse);
			
				response.sendRedirect(ServletCheval.URL_CONSULTER_CHEVAL+"?id="+idCheval);
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
					CourseDAO.ajouterCourse(connection, uneCourse);
					response.sendRedirect(URL_LISTER_COURSES);
				} else {
					request.getSession().setAttribute("form", formCourse);
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

				if (form.getErreurs().isEmpty()) {
					CourseDAO.modifierCourse(connection, uneCourse, form.getCourseOrigin(request));
					response.sendRedirect(URL_LISTER_COURSES);
				} else {
					request.getSession().setAttribute("form", form);
					response.sendRedirect(URL_MODIFIER_COURSE+"?code="+form.getCourseOrigin(request));
				}
			} else {
				redirigerVersAcceuil(response);
			}
		}
		
		if (url.equals(URL_RENSEIGNER_COURSE_POUR_CHEVAL )) {
			if(user instanceof Client || user instanceof DirecteurGeneral) {
				/* Préparation de l'objet formulaire */
				ParticipationForm form = new ParticipationForm();

				/* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
				Participer uneParticipation = form.getParticipation(request);

				/* Stockage du formulaire et de l'objet dans l'objet request */
				request.setAttribute("form", form);
				
				if (form.getErreurs().isEmpty()) {
					ParticiperDAO.renseignerCourseCheval(connection, uneParticipation);
					response.sendRedirect(ServletCheval.URL_CONSULTER_CHEVAL+"?id="+uneParticipation.getCheval().getId());
				} else {
					request.getSession().setAttribute("form", form);
					response.sendRedirect(URL_RENSEIGNER_COURSE_POUR_CHEVAL+"?id="+uneParticipation.getCheval().getId());
				}
			} else {
				redirigerVersAcceuil(response);
			}
		}
	}
}
