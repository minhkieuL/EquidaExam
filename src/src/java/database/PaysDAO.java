package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.Pays;

/**
 *
 * @author Zakina
 */
public class PaysDAO {

	public static ArrayList<Pays> getLesPays(Connection connection) {
		ArrayList<Pays> lesPays = new ArrayList<Pays>();
		try {
			//preparation de la requete     
			PreparedStatement requete = connection.prepareStatement("SELECT * FROM pays");

			//executer la requete
			ResultSet rs = requete.executeQuery();

			//On hydrate l'objet métier Pays avec les résultats de la requête
			while (rs.next()) {
				Pays unPays = new Pays();
				unPays.setCode(rs.getString("code"));
				unPays.setNom(rs.getString("nom"));
				lesPays.add(unPays);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			//out.println("Erreur lors de l’établissement de la connexion");
		}
		return lesPays;
	}

	public static Pays ajouterPays(Connection connection, Pays unPays) {

		try {
			PreparedStatement requete = connection.prepareStatement("INSERT INTO pays (code, nom)\n"
				+ "VALUES (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			requete.setString(1, unPays.getCode());
			requete.setString(2, unPays.getNom());

			requete.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			//out.println("Erreur lors de l’établissement de la connexion");
		}
		return unPays;
	}

	public static Pays getPays(Connection connection, String codePays) {
		Pays unPays = new Pays();
		try {
			//preparation de la requete 
			PreparedStatement requete = connection.prepareStatement(" SELECT * FROM pays WHERE code = ?; ");
			requete.setString(1, codePays);
			/* Exécution de la requête */
			//executer la requete
			ResultSet rs = requete.executeQuery();

			//On hydrate l'objet métier Pays avec les résultats de la requête
			while (rs.next()) {
				unPays.setCode(rs.getString("code"));
				unPays.setNom(rs.getString("nom"));
			}
			//System.out.println("requete " +requete);
		} catch (SQLException e) {
			e.printStackTrace();
			//out.println("Erreur lors de l’établissement de la connexion");
		}
		return unPays;
	}

	public static Pays modifierPays(Connection connection, Pays unPays, String codePays) {

		try {
			//preparation de la requete 
			PreparedStatement requete = connection.prepareStatement(" UPDATE pays SET nom = ? WHERE code = ?; ");

			requete.setString(1, unPays.getNom());
			requete.setString(2, codePays);
			/* Exécution de la requête */
			requete.executeUpdate();

			//System.out.println("requete " +requete);
		} catch (SQLException e) {
			e.printStackTrace();
			//out.println("Erreur lors de l’établissement de la connexion");
		}
		return unPays;
	}

}
