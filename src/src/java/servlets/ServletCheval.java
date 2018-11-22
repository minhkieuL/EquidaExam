package servlets;

import database.ChevalDAO;
import database.ClientDAO;
import database.EnchereDAO;
import database.LotDAO;
import database.ParticiperDAO;
import database.TypeChevalDAO;
import formulaires.ChevalForm;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Cheval;
import modele.Client;
import modele.DirecteurGeneral;
import modele.Enchere;
import modele.Lot;
import modele.Participer;
import modele.TypeCheval;
import modele.Utilisateur;

/**
 *
 * @author slam
 */
public class ServletCheval extends ServletBase {

	public static final String URL_AJOUTER_CHEVAL = "/EquidaWebG2/ServletCheval/ajouterCheval";
	public static final String URL_CONSULTER_CHEVAL = "/EquidaWebG2/ServletCheval/consulterCheval";
	public static final String URL_MODIFIER_CHEVAL = "/EquidaWebG2/ServletCheval/chevalModifier";
	public static final String URL_ARCHIVER_CHEVAL = "/EquidaWebG2/ServletCheval/chevalArchiver";
	public static final String URL_LISTER_LOTS = "/EquidaWebG2/ServletLot/listerLesLots";
	public static final String URL_VALIDER_CHEVAL = "/EquidaWebG2/ServletCheval/chevalValider";

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
		if (url.equals(URL_AJOUTER_CHEVAL)) {
			if (user instanceof Client) {
				ArrayList<TypeCheval> lesTypeCheval = TypeChevalDAO.getLesTypeCheval(connection);

				request.setAttribute("pLesTypeCheval", lesTypeCheval);
				changerTitrePage("Ajouter un cheval", request);

				this.getServletContext().getRequestDispatcher("/vues/cheval/chevalForm.jsp").forward(request, response);
			} else {
				redirigerVersAcceuil(response);
			}
		}

		if (url.equals(URL_CONSULTER_CHEVAL)) {
			int idCheval = 0;
			try {
				idCheval = Integer.valueOf(request.getParameter("id"));
			} catch (Exception e) {
				redirigerVersAcceuil(response);
				return;
			}

			Cheval cheval = ChevalDAO.getCheval(connection, idCheval);
			ArrayList<Participer> lesParticipations = ParticiperDAO.getLesParticipationsCoursesCheval(connection, idCheval);
			ArrayList<Enchere> lesEncheres = EnchereDAO.getLesEncheres(connection, idCheval);
			Lot lot = LotDAO.getLotCheval(connection, idCheval);
			ArrayList<Client> lesClients = ClientDAO.getLesClientsPrDirGen(connection);
			
			request.setAttribute("pClients", lesClients);
			request.setAttribute("pLot", lot);
			request.setAttribute("pEncheres", lesEncheres);

			request.setAttribute("pParticipations", lesParticipations);
			request.setAttribute("pCheval", cheval);
			changerTitrePage("Cheval " + cheval.getNom(), request);

			getServletContext().getRequestDispatcher("/vues/cheval/chevalConsulter.jsp").forward(request, response);
		}

		if (url.equals(URL_MODIFIER_CHEVAL)) {

			if (user instanceof DirecteurGeneral || user instanceof Client) {
				int idCheval = 0;
				try {
					idCheval = Integer.valueOf(request.getParameter("id"));
				} catch (Exception e) {
					redirigerVersAcceuil(response);
					return;
				}

				Cheval unCheval = ChevalDAO.getCheval(connection, idCheval);
				if (user instanceof Client) {
					if (unCheval.getClient().getId() != user.getId()) {
						redirigerVersAcceuil(response);
						return;
					}
				}

				ArrayList<TypeCheval> lesTypeCheval = TypeChevalDAO.getLesTypeCheval(connection);

				request.setAttribute("pCheval", unCheval);
				request.setAttribute("pLesTypeCheval", lesTypeCheval);
				changerTitrePage("Modifier un cheval", request);

				this.getServletContext().getRequestDispatcher("/vues/cheval/chevalForm.jsp").forward(request, response);
			} else {
				redirigerVersAcceuil(response);
			}
		}

		if (url.equals(URL_VALIDER_CHEVAL)) {
			if (user instanceof DirecteurGeneral) {
				int idCheval = 0;
				try {
					idCheval = Integer.valueOf(request.getParameter("id"));
				} catch (Exception e) {
					redirigerVersAcceuil(response);
					return;
				}
				ChevalDAO.validerCheval(connection, idCheval);
				response.sendRedirect(ServletLot.URL_LISTER_NONVALIDER);
			} else {
				redirigerVersAcceuil(response);
			}
		}

		if (url.equals(URL_ARCHIVER_CHEVAL)) {
			if (user instanceof DirecteurGeneral) {
				int idCheval = 0;
				try {
					idCheval = Integer.valueOf(request.getParameter("id"));
				} catch (Exception e) {
					redirigerVersAcceuil(response);
					return;
				}
				ChevalDAO.archiverCheval(connection, idCheval);
				response.sendRedirect(URL_LISTER_LOTS);
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
		if (url.equals(URL_AJOUTER_CHEVAL)) {
			if (user instanceof Client) {
				/* Préparation de l'objet formulaire */
				ChevalForm form = new ChevalForm();

				/* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
				Cheval unCheval = form.getCheval(request, connection);

				if (form.getErreurs().isEmpty()) {
					int idCheval = ChevalDAO.ajouterCheval(connection, unCheval, request);
					response.sendRedirect(URL_CONSULTER_CHEVAL + "?id=" + idCheval);
				} else {
					request.getSession().setAttribute("form", form);
					response.sendRedirect(URL_AJOUTER_CHEVAL);
				}
			} else {
				redirigerVersAcceuil(response);
			}
		}

		if (url.equals(URL_MODIFIER_CHEVAL)) {

			if (user instanceof DirecteurGeneral || user instanceof Client) {
				/* Préparation de l'objet formulaire */
				ChevalForm form = new ChevalForm();

				/* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
				Cheval unCheval = form.getCheval(request, connection);

				if (user instanceof Client) {
					if (ChevalDAO.getCheval(connection, unCheval.getId()).getClient().getId() != user.getId()) {
						redirigerVersAcceuil(response);
						return;
					}
				}

				if (form.getErreurs().isEmpty()) {
					ChevalDAO.modifierChevalOrigin(connection, unCheval);
					response.sendRedirect(URL_CONSULTER_CHEVAL + "?id=" + unCheval.getId());
				} else {
					request.getSession().setAttribute("form", form);
					response.sendRedirect(URL_MODIFIER_CHEVAL + "?id=" + unCheval.getId());
				}
			} else {
				redirigerVersAcceuil(response);
			}
		}

	}

}
