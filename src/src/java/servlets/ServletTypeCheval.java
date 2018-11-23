package servlets;

import database.TypeChevalDAO;
import formulaires.TypeChevalForm;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.DirecteurGeneral;
import modele.TypeCheval;
import modele.Utilisateur;


/**
    Document   : ServletDirecteur
    Created on : 12 oct. 2018, 09:00:00
    Author     : corentin
 */
public class ServletTypeCheval extends ServletBase {
    
	public static final String URL_AJOUTER_TYPE_CHEVAL = "/EquidaWebG2/ServletTypeCheval/typeChevalAjouter";
	public static final String URL_LISTER_TYPE_CHEVAL = "/EquidaWebG2/ServletTypeCheval/listerLesTypeCheval";
	public static final String URL_MODIFIER_TYPE_CHEVAL = "/EquidaWebG2/ServletTypeCheval/typeChevalModifier";
	
    Connection connection ;
    
    @Override
    public void init()
    {     
        ServletContext servletContext=getServletContext();
        connection=(Connection)servletContext.getAttribute("connection");
    }


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
       if(url.equals(URL_AJOUTER_TYPE_CHEVAL)) { 
			if(user instanceof DirecteurGeneral) {
				changerTitrePage("Ajouter race cheval", request);
				getServletContext().getRequestDispatcher("/vues/type_cheval/typeChevalAjouter.jsp" ).forward( request, response );
			} else {
				redirigerVersAcceuil(response);
			}
        }
	   
	   if (url.equals(URL_LISTER_TYPE_CHEVAL)) {
		   if(user instanceof DirecteurGeneral) {
				ArrayList<TypeCheval> lesTypeCheval = TypeChevalDAO.getLesTypeCheval(connection);

				request.setAttribute("pLesTypeCheval", lesTypeCheval);
				changerTitrePage("Lister les types de chevaux", request);

				getServletContext().getRequestDispatcher("/vues/type_cheval/listerLesTypeCheval.jsp").forward(request, response);
		   } else {
			   redirigerVersAcceuil(response);
		   }
		}
		
		if (url.equals(URL_MODIFIER_TYPE_CHEVAL)) {
			if(user instanceof DirecteurGeneral) {
				int idTypeCheval = Integer.valueOf(request.getParameter("id"));
				TypeCheval unTypeCheval = TypeChevalDAO.getTypeCheval(connection, idTypeCheval);

				request.setAttribute("pTypeCheval", unTypeCheval);
				changerTitrePage("Modifier un type de cheval", request);

				this.getServletContext().getRequestDispatcher("/vues/type_cheval/typeChevalModifier.jsp").forward(request, response);
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
        if(url.equals(URL_AJOUTER_TYPE_CHEVAL)){
			if(user instanceof DirecteurGeneral) {
				TypeChevalForm formTypeCheval = new TypeChevalForm();
				TypeCheval unTypeCheval = formTypeCheval.getTypeCheval(request);

				if (formTypeCheval.getErreurs().isEmpty()){
					TypeChevalDAO.ajouterTypeCheval(connection, unTypeCheval);
					response.sendRedirect(URL_LISTER_TYPE_CHEVAL);
				} else { 
					request.getSession().setAttribute("form", formTypeCheval);
					response.sendRedirect(URL_AJOUTER_TYPE_CHEVAL);
				}
			} else {
				redirigerVersAcceuil(response);
			}
        }
		
		if (url.equals(URL_MODIFIER_TYPE_CHEVAL)) {
			if(user instanceof DirecteurGeneral) {
				/* Préparation de l'objet formulaire */
				TypeChevalForm form = new TypeChevalForm();

				/* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
				TypeCheval unTypeCheval = form.getTypeCheval(request);

				if (form.getErreurs().isEmpty()) {
					TypeChevalDAO.modifierTypeCheval(connection, unTypeCheval, form.getTypeChevalOrigin(request));
					response.sendRedirect(URL_LISTER_TYPE_CHEVAL);

				} else {
					request.getSession().setAttribute("form", form);
					response.sendRedirect(URL_MODIFIER_TYPE_CHEVAL+"?id="+unTypeCheval.getId());
				}
			} else {
				redirigerVersAcceuil(response);
			}
		}
        
    }
}
