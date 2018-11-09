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
		String url = request.getRequestURI();
                
                
                
		if (url.equals("/EquidaWebG2/ServletCheval/ajouterCheval")) {
                    Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");
                    if(user != null && user.estAutoriseA(Autorisations.CLIENT_AJOUTER)) {
                        ArrayList<TypeCheval> lesTypeCheval = TypeChevalDAO.getLesTypeCheval(connection);

			request.setAttribute("pLesTypeCheval", lesTypeCheval);
			changerTitrePage("Ajouter un cheval", request);

			this.getServletContext().getRequestDispatcher("/vues/cheval/chevalAjouter.jsp").forward(request, response);
                    } else {
                        redirigerVersAcceuil(response);
                    }
			
		}
		
		if (url.equals("/EquidaWebG2/ServletCheval/listerLesChevauxParVentes")) {
			String idVente = (String) request.getParameter("idVente");
			ArrayList<Lot> lesLots = LotDAO.getLesLotPourVente(connection, idVente);

			request.setAttribute("pLesLots", lesLots);
			changerTitrePage("Lister les chevaux par ventes", request);

			getServletContext().getRequestDispatcher("/vues/ventes/listerLesChevauxParVentes.jsp").forward(request, response);
		}
        
        if (url.equals("/EquidaWebG2/ServletCheval/chevalModifier")) {
           /* Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");
            if(user != null && user.estAutoriseA(Autorisations.CLIENT_AJOUTER)) {
*/
                int idCheval = Integer.valueOf(request.getParameter("id"));
                Cheval unCheval = ChevalDAO.getCheval(connection, idCheval);
                ArrayList<TypeCheval> lesTypeCheval = TypeChevalDAO.getLesTypeCheval(connection);
			
                request.setAttribute("pCheval", unCheval);
                request.setAttribute("pLesTypeCheval", lesTypeCheval);
                changerTitrePage("Modifier un cheval", request);

			this.getServletContext().getRequestDispatcher("/vues/cheval/chevalModifier.jsp").forward(request, response);
                    /*} else {
                        redirigerVersAcceuil(response);
                    }*/
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
        
        String url = request.getRequestURI();
        if (url.equals("/EquidaWebG2/ServletCheval/ajouterCheval")) {
            /* Préparation de l'objet formulaire */
            ChevalForm form = new ChevalForm();

            /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
            Cheval unCheval = form.getCheval(request, connection);
            

            /* Stockage du formulaire et de l'objet dans l'objet request */
            request.setAttribute("form", form);
            request.setAttribute("pCheval", unCheval);

            if (form.getErreurs().isEmpty()) {
                // Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos du client 
                ChevalDAO.ajouterCheval(connection, unCheval, request);
                this.getServletContext().getRequestDispatcher("/vues/cheval/chevalConsulter.jsp").forward(request, response);
            } else {
                // il y a des erreurs. On réaffiche le formulaire avec des messages d'erreurs
                ArrayList<TypeCheval> lesTypeCheval = TypeChevalDAO.getLesTypeCheval(connection);
                request.setAttribute("pLesTypeCheval", lesTypeCheval);

                /*ArrayList<Lot> lesLots = LotDAO.getLesLots(connection);
                request.setAttribute("pLesLots", lesLots);*/
                this.getServletContext().getRequestDispatcher("/vues/cheval/chevalAjouter.jsp").forward(request, response);
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
				this.getServletContext().getRequestDispatcher("/vues/cheval/chevalConsulter.jsp").forward(request, response);

			} else {
				this.getServletContext().getRequestDispatcher("/vues/cheval/chevalAjouter.jsp").forward(request, response);
			}
		}
	
	}
        
}
