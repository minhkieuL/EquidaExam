package servlets;

import database.Autorisations;
import database.CategVenteDAO;
import database.ChevalDAO;
import database.LotDAO;
import database.TypeChevalDAO;
import formulaires.CategorieForm;
import formulaires.ChevalForm;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.CategVente;
import modele.Cheval;
import modele.Client;
import modele.DirecteurGeneral;
import modele.Lot;
import modele.TypeCheval;
import modele.Utilisateur;

/**
 *
 * @author slam
 */
public class ServletCheval extends ServletBase {

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
		if (url.equals("/EquidaWebG2/ServletCheval/ajouterCheval")) {
			if(user instanceof Client) {
				ArrayList<TypeCheval> lesTypeCheval = TypeChevalDAO.getLesTypeCheval(connection);

				request.setAttribute("pLesTypeCheval", lesTypeCheval);
				changerTitrePage("Ajouter un cheval", request);

				this.getServletContext().getRequestDispatcher("/vues/cheval/chevalForm.jsp").forward(request, response);
			} else {
				redirigerVersAcceuil(response);
			}
		}
		
		if (url.equals("/EquidaWebG2/ServletCheval/consulterCheval")) {
			int idCheval = 0;
			try {
				idCheval = Integer.valueOf(request.getParameter("id"));
			} catch(Exception e) {
				redirigerVersAcceuil(response);
				return;
			}
			
			Cheval cheval = ChevalDAO.getCheval(connection, idCheval);

			request.setAttribute("pCheval", cheval);
			changerTitrePage("Cheval " + cheval.getNom(), request);

			getServletContext().getRequestDispatcher("/vues/cheval/chevalConsulter.jsp").forward(request, response);
		}
        
        if (url.equals("/EquidaWebG2/ServletCheval/chevalModifier")) {	
			int idCheval = 0;
			try {
				idCheval = Integer.valueOf(request.getParameter("id"));
			} catch(Exception e) {
				redirigerVersAcceuil(response);
				return;
			}
			Cheval unCheval = ChevalDAO.getCheval(connection, idCheval);
			ArrayList<TypeCheval> lesTypeCheval = TypeChevalDAO.getLesTypeCheval(connection);

			request.setAttribute("pCheval", unCheval);
			request.setAttribute("pLesTypeCheval", lesTypeCheval);
			changerTitrePage("Modifier un cheval", request);

			this.getServletContext().getRequestDispatcher("/vues/cheval/chevalForm.jsp").forward(request, response);
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
        if (url.equals("/EquidaWebG2/ServletCheval/ajouterCheval")) {
			if(user instanceof Client) {
				/* Préparation de l'objet formulaire */
				ChevalForm form = new ChevalForm();

				/* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
				Cheval unCheval = form.getCheval(request, connection);


				/* Stockage du formulaire et de l'objet dans l'objet request */
				request.setAttribute("form", form);
				request.setAttribute("pCheval", unCheval);

				if (form.getErreurs().isEmpty()) {
					// Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos du client 
					int idCheval = ChevalDAO.ajouterCheval(connection, unCheval, request);
					response.sendRedirect("/EquidaWebG2/ServletCheval/consulterCheval?id="+idCheval);
				} else {
					// il y a des erreurs. On réaffiche le formulaire avec des messages d'erreurs
					ArrayList<TypeCheval> lesTypeCheval = TypeChevalDAO.getLesTypeCheval(connection);
					request.setAttribute("pLesTypeCheval", lesTypeCheval);

					/*ArrayList<Lot> lesLots = LotDAO.getLesLots(connection);
					request.setAttribute("pLesLots", lesLots);*/
					response.sendRedirect("/vues/cheval/chevalForm.jsp");
				}
			} else {
				redirigerVersAcceuil(response);
			}
        }
        if (url.equals("/EquidaWebG2/ServletCheval/chevalModifier")) {
            /* Préparation de l'objet formulaire */
            ChevalForm form = new ChevalForm();

            /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
            Cheval unCheval = form.getCheval(request, connection);

            /* Stockage du formulaire et de l'objet dans l'objet request */
            request.setAttribute("form", form);
            request.setAttribute("pCheval", unCheval);
            
            if (form.getErreurs().isEmpty()) {
				// Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos du client 
				
				ChevalDAO.modifierChevalOrigin(connection, unCheval);
				response.sendRedirect("/EquidaWebG2/ServletCheval/consulterCheval?id="+unCheval.getId());

			} else {
				this.getServletContext().getRequestDispatcher("/vues/cheval/chevalForm.jsp").forward(request, response);
			}
		}
	
	}
        
}
