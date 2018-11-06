package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modele.Compte;

/**
 *
 * @author MartinJ
 */
public class CompteDAO {

	public static Compte getCompteParLogin(String login, Connection connection) {
		Compte compte = null;
		try {
			//preparation de la requete     
			PreparedStatement requete = connection.prepareStatement("SELECT * FROM compte WHERE login=?");
			requete.setString(1, login);

			//executer la requete
			ResultSet rs = requete.executeQuery();

			//On hydrate l'objet métier Client avec les résultats de la requête
			while (rs.next()) {
				compte = new Compte();
				compte.setId(rs.getInt("id"));
				compte.setLogin(rs.getString("login"));
				compte.setMdp(rs.getString("mdp"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return compte;
	}
}
