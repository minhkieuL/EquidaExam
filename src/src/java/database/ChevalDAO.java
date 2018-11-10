/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.servlet.http.HttpServletRequest;
import modele.Cheval;
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
	public static int ajouterCheval(Connection connection, Cheval unCheval, HttpServletRequest request) {
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
		return idGenere;
	}

	public static Cheval getCheval(Connection connection, int idCheval) {
		Cheval unCheval = new Cheval();
		try {
			//preparation de la requete     
			requete = connection.prepareStatement("select * from cheval WHERE id=?");
			requete.setInt(1, idCheval);

			//executer la requete
			rs = requete.executeQuery();

			while (rs.next()) {
				unCheval.setId(idCheval);
				unCheval.setNom(rs.getString("nom"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			//out.println("Erreur lors de l’établissement de la connexion");
		}
		return unCheval;
	}
}
