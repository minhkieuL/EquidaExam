/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import static database.PaysDAO.requete;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.Lieu;

/**
 *
 * @author slam
 */
public class LieuDAO {

	
	Connection connection = null;
	static PreparedStatement requete = null;
	static ResultSet rs = null;
	
		public static ArrayList<Lieu> getLesLieux(Connection connection) {
		ArrayList<Lieu> lesLieux = new ArrayList<Lieu>();
		try {
			//preparation de la requete     
			requete = connection.prepareStatement("SELECT * FROM lieu");

			//executer la requete
			rs = requete.executeQuery();

			//On hydrate l'objet métier Client avec les résultats de la requête
			while (rs.next()) {
				Lieu unLieu = new Lieu();
				unLieu.setId(rs.getInt("id"));
				unLieu.setVille(rs.getString("ville"));
				unLieu.setNbBoxes(rs.getInt("nbBoxes"));
				unLieu.setCommentaire(rs.getString("commentaire"));
				lesLieux.add(unLieu);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			//out.println("Erreur lors de l’établissement de la connexion");
		}
		return lesLieux;
	}
	
	public static Lieu ajouterLieu(Connection connection, Lieu unLieu) {

		try {
			requete = connection.prepareStatement("INSERT INTO `lieu`(`ville`, `nbBoxes`, `commentaire`)\n"
					+ " VALUES (?,?,?)", requete.RETURN_GENERATED_KEYS);
			
			requete.setString(1, unLieu.getVille());
			requete.setInt(2, unLieu.getNbBoxes());
			requete.setString(3, unLieu.getCommentaire());

			requete.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			//out.println("Erreur lors de l’établissement de la connexion");
		}
		return unLieu;
	}
	
		

}
