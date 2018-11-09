/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import static database.CategVenteDAO.requete;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Types;
import javax.servlet.http.HttpServletRequest;
import modele.CategVente;
import modele.Cheval;
import modele.Client;
import modele.TypeCheval;
import modele.Utilisateur;

/**
 *
 * @author slam
 */
public class ChevalDAO {

	Connection connection = null;
	static PreparedStatement requete = null;
	static ResultSet rs = null;

	// Méthode permettant d'insérer un client en base à partir de l'objet client passé en paramètre
	// Cette méthode renvoie l'objet client
	public static Cheval ajouterCheval(Connection connection, Cheval unCheval, HttpServletRequest request) {
		int idGenere = -1;
		try {
			//preparation de la requete
			// id (clé primaire de la table client) est en auto_increment,donc on ne renseigne pas cette valeur
			// la paramètre RETURN_GENERATED_KEYS est ajouté à la requête afin de pouvoir récupérer l'id généré par la bdd (voir ci-dessous)
			// supprimer ce paramètre en cas de requête sans auto_increment.
			requete = connection.prepareStatement("INSERT INTO cheval ( nom, sexe, sire, typeCheval, pere, mere, client)\n"
					+ "VALUES (?,?,?,?,?,?,?)", requete.RETURN_GENERATED_KEYS);
			requete.setString(1, unCheval.getNom());
			requete.setInt(2, unCheval.getMale() ? 1 : 0);
			requete.setString(3, unCheval.getSire());
			requete.setInt(4, unCheval.getTypeCheval().getId());
			if(unCheval.getPere() != null)
				requete.setInt(5, unCheval.getPere().getId());
			else 
				requete.setNull(5, Types.INTEGER);
			
			if(unCheval.getMere() != null)
				requete.setInt(6, unCheval.getMere().getId());
			else
				requete.setNull(6, Types.INTEGER);
                        
			Utilisateur user = (Utilisateur)request.getSession().getAttribute("user");
			if(user != null) {
				requete.setInt(7, user.getId() );
			}
			/* Exécution de la requête */
			requete.executeUpdate();

			// Récupération de id auto-généré par la bdd dans la table cheval
			rs = requete.getGeneratedKeys();
			while (rs.next()) {
				idGenere = rs.getInt(1);
				unCheval.setId(idGenere);
			}

			// ajout des enregistrements dans la table lot
			/* for (int i=0;i<unCheval.getLots().size();i++){
                PreparedStatement requete2=connection.prepareStatement("INSERT INTO lot(idCheval)\n" +
                    "VALUES (?)");
                 requete2.setInt(2, unCheval.getLots().get(i).getId());
                 requete2.executeUpdate();
            }*/
		} catch (SQLException e) {
			e.printStackTrace();
			//out.println("Erreur lors de l’établissement de la connexion");
		}
		return unCheval;
	}

	public static Cheval getCheval(Connection connection, int idCheval) {
        ArrayList<Cheval> lesChevaux = new ArrayList<Cheval>();
        
		Cheval unCheval = new Cheval();
        Cheval chevalPere = new Cheval();
        Cheval chevalMere = new Cheval();
        
        TypeCheval unTypeCheval = new TypeCheval();
		try {
			//preparation de la requete     
			requete = connection.prepareStatement("SELECT * FROM cheval WHERE id=?");
			requete.setInt(1, idCheval);

			//executer la requete
			rs = requete.executeQuery();

			//On hydrate l'objet métier Cheval avec les résultats de la requête
			while (rs.next()) {
				unCheval.setId(rs.getInt("id"));
				unCheval.setNom(rs.getString("nom"));
                unTypeCheval.setId(rs.getInt("typeCheval"));
                unCheval.setSire(rs.getString("sire"));
                unCheval.setTypeCheval(unTypeCheval);
                unCheval.setMale(rs.getBoolean("sexe"));
                chevalPere.setId(rs.getInt("pere"));
                unCheval.setPere(chevalPere);
                chevalMere.setId(rs.getInt("mere"));
                unCheval.setMere(chevalMere);
                
                lesChevaux.add(unCheval);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			//out.println("Erreur lors de l’établissement de la connexion");
		}
		return unCheval;
	}
        public static Cheval modifierChevalOrigin(Connection connection, Cheval unCheval){      
           
        try
        {
            //preparation de la requete 
            requete=connection.prepareStatement(" UPDATE cheval SET nom = ? sexe = ? sire = ? typeCheval = ? mere = ? pere = ? WHERE id = ?; ");
      
            requete.setString(1, unCheval.getNom());
			requete.setBoolean(2, unCheval.getMale());
            requete.setString(3, unCheval.getSire());
            requete.setInt(4, unCheval.getTypeCheval().getId());
            requete.setInt(5, unCheval.getMere().getId());
            requete.setInt(6, unCheval.getPere().getId());
            requete.setInt(7, unCheval.getId());
                        
            /* Exécution de la requête */
            requete.executeUpdate();
            
            //System.out.println("requete " +requete);
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return unCheval; 
    }
}
