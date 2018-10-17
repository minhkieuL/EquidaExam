package servlets;

import database.CompteDAO;
import database.UtilisateurDAO;
import database.Utilitaire;
import formulaires.CompteForm;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Compte;
import modele.Utilisateur;

public class ServletAuthentification extends HttpServlet {
    
    Connection connection ;
      
        
    @Override
    public void init()
    {     
        ServletContext servletContext=getServletContext();
        connection=(Connection)servletContext.getAttribute("connection");
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletClient</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletClient at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
       String url = request.getRequestURI();
       
		if(url.equals("/EquidaWebG2/ServletAuthentification/connexion")) {
			this.getServletContext().getRequestDispatcher("/vues/authentification/connexion.jsp" ).forward( request, response );
		} else if(url.equals("/EquidaWebG2/ServletAuthentification/deconnexion")) {
			request.getSession().invalidate();
			response.sendRedirect("/EquidaWebG2");
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
        String url = request.getRequestURI();
		
		if(url.equals("/EquidaWebG2/ServletAuthentification/connexion")) {
			CompteForm compteForm = new CompteForm();
			Compte compte = compteForm.getCompte(request);
			Compte compteBdd = CompteDAO.getCompteParLogin(compte.getLogin(), connection);
			
			boolean showError = false;
			
			try {
				if(compte.getMdp().equals(compteBdd.getMdp()) && compteForm.getErreurs().isEmpty()) {
					Utilisateur user = UtilisateurDAO.getUtilisateurParCompte(compte, connection);
					request.getSession().setAttribute("user", user);
					response.sendRedirect("/EquidaWebG2");
				} else {
					showError = true;
				}
			} catch(NullPointerException e) {
				//Aucun compte en bdd ne correspond au login
				showError = true;
			}
			
			if(showError) {
				response.sendRedirect("/EquidaWebG2/ServletAuthentification/connexion");
			}
			
		}
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
 public void destroy(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
        try
        {
            //fermeture
            System.out.println("Connexion fermée");
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            System.out.println("Erreur lors de l’établissement de la connexion");
        }
        finally
        {
            //Utilitaire.fermerConnexion(rs);
            //Utilitaire.fermerConnexion(requete);
            Utilitaire.fermerConnexion(connection);
        }
    }
}
