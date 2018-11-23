package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.Client;
import modele.Enchere;

public class EnchereDAO {

	public static void ajouterEnchere(Connection connection, Enchere enchere) {
		try {
			PreparedStatement requete = connection.prepareStatement("INSERT INTO enchere(montant, lot, client) VALUES (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);

			requete.setFloat(1, enchere.getMontant());
			requete.setInt(2, enchere.getLot().getId());
			requete.setInt(3, enchere.getClient().getId());

			requete.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			//out.println("Erreur lors de l’établissement de la connexion");
		}
	}

	public static ArrayList<Enchere> getLesEncheres(Connection connection, int idCheval) {
			ArrayList<Enchere> lesEncheres = new ArrayList<Enchere>();
			
			try {

			PreparedStatement requete = connection.prepareStatement("SELECT * FROM enchere, lot WHERE enchere.lot=lot.id AND lot.idCheval = ? AND enchere.montant<> 0 ORDER BY enchere.montant DESC ");
			requete.setInt(1, idCheval);
			ResultSet rs = requete.executeQuery();

			//On hydrate l'objet métier Client avec les résultats de la requête
			while (rs.next()) {
				Enchere uneEnchere = new Enchere();
				uneEnchere.setId(rs.getInt("id"));
				uneEnchere.setMontant(rs.getFloat("montant"));
				uneEnchere.setClient(ClientDAO.getClient(connection,rs.getInt("id")));
				
				lesEncheres.add(uneEnchere);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			//out.println("Erreur lors de l’établissement de la connexion");
		}
		
		return lesEncheres;
	}
}