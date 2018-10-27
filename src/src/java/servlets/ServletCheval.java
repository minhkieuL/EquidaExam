package servlets;

import database.ChevalDAO;
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
import modele.TypeCheval;

/**
 *
 * @author slam
 */
public class ServletCheval extends ServletBase {

    Connection connection ;
      
        
    @Override
    public void init() {     
        ServletContext servletContext=getServletContext();
        connection=(Connection)servletContext.getAttribute("connection");
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
       
		if(url.equals("/EquidaWebG2/ServletCheval/ajouterCheval")) {                             
            ArrayList<TypeCheval> lesTypeCheval = TypeChevalDAO.getLesTypeCheval(connection);
			
            request.setAttribute("pLesTypeCheval", lesTypeCheval);
			changerTitrePage("Ajouter un cheval", request);
			
            this.getServletContext().getRequestDispatcher("/vues/chevalAjouter.jsp" ).forward( request, response );
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
         /* Préparation de l'objet formulaire */
        ChevalForm form = new ChevalForm();
		
        /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
        Cheval unCheval = form.getCheval(request, connection);
        
        /* Stockage du formulaire et de l'objet dans l'objet request */
        request.setAttribute( "form", form );
        request.setAttribute( "pCheval", unCheval );
		
        if (form.getErreurs().isEmpty()){
            // Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos du client 
            ChevalDAO.ajouterCheval(connection, unCheval);
            this.getServletContext().getRequestDispatcher("/vues/chevalConsulter.jsp" ).forward( request, response );
        } else { 
			// il y a des erreurs. On réaffiche le formulaire avec des messages d'erreurs
            ArrayList<TypeCheval> lesTypeCheval = TypeChevalDAO.getLesTypeCheval(connection);
            request.setAttribute("pLesTypeCheval", lesTypeCheval);
            
            /*ArrayList<Lot> lesLots = LotDAO.getLesLots(connection);
            request.setAttribute("pLesLots", lesLots);*/
           this.getServletContext().getRequestDispatcher("/vues/chevalAjouter.jsp" ).forward( request, response );
        }
    }
}
