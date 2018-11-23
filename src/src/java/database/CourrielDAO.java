package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.Courriel;
import modele.PieceJointe;

/**
 *
 * @author MartinJ
 */
public class CourrielDAO {

	public static ArrayList<Courriel> getLesCourriels(Connection connection, String codeVente) {
		ArrayList<Courriel> lesCourriels = new ArrayList<Courriel>();
		try {
			PreparedStatement requete = connection.prepareStatement("SELECT courriel.*, vente.* FROM courriel, vente WHERE courriel.vente=vente.id AND vente.id=?");
			requete.setString(1, codeVente);
			ResultSet rs = requete.executeQuery();

			//On hydrate l'objet métier Client avec les résultats de la requête
			while (rs.next()) {
				Courriel courriel = new Courriel();
				courriel.setId(rs.getInt("id"));
				courriel.setDate(rs.getString("date"));
				courriel.setObjet(rs.getString("objet"));
				courriel.setCorps(rs.getString("corps"));

				requete = connection.prepareStatement("SELECT piecejointe.* FROM joint, piecejointe WHERE piecejointe.id=joint.pieceJointe AND joint.courriel=?");
				requete.setInt(1, courriel.getId());
				ResultSet rsPieceJointe = requete.executeQuery();

				while (rsPieceJointe.next()) {
					PieceJointe p = new PieceJointe();
					p.setId(rsPieceJointe.getInt("id"));
					p.setChemin(rsPieceJointe.getString("chemin"));
					p.setDescription(rsPieceJointe.getString("description"));

					courriel.addPieceJointe(p);
				}

				lesCourriels.add(courriel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			//out.println("Erreur lors de l’établissement de la connexion");
		}
		return lesCourriels;
	}

	public static int ajouterCourriel(Connection pConnection, Courriel pCourriel) {
		int idCourriel = -1;
		try {
			//preparation de la requete
			// id (clé primaire de la table client) est en auto_increment,donc on ne renseigne pas cette valeur
			// la paramètre RETURN_GENERATED_KEYS est ajouté à la requête afin de pouvoir récupérer l'id généré par la bdd (voir ci-dessous)
			// supprimer ce paramètre en cas de requête sans auto_increment.
			PreparedStatement requete = pConnection.prepareStatement("INSERT INTO courriel (date, objet, corps, vente)\n"
					+ "VALUES (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			requete.setString(1, pCourriel.getDate());
			requete.setString(2, pCourriel.getObjet());
			requete.setString(3, pCourriel.getCorps());
			if (pCourriel.getVente() != null) {
				requete.setInt(4, pCourriel.getVente().getId());
			}

			/* Exécution de la requête */
			requete.executeUpdate();

			// Récupération de id auto-généré par la bdd dans la table utilisateur
			ResultSet rs = requete.getGeneratedKeys();
			while (rs.next()) {
				idCourriel = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			//out.println("Erreur lors de l’établissement de la connexion");
		}
		return idCourriel;
	}

	public static int ajouterPieceJointe(Connection pConnection, PieceJointe pPieceJointe, int pIdCourriel) {
		int idPieceJointe = -1;
		try {
			PreparedStatement requetePj = pConnection.prepareStatement("INSERT INTO piecejointe (chemin, description)\n"
					+ "VALUES (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			requetePj.setString(1, pPieceJointe.getChemin());
			requetePj.setString(2, pPieceJointe.getDescription());
			requetePj.executeUpdate();

			// Récupération de id auto-généré par la bdd dans la table utilisateur
			ResultSet rsPj = requetePj.getGeneratedKeys();
			rsPj.next();
			idPieceJointe = rsPj.getInt(1);

			PreparedStatement requeteJoint = pConnection.prepareStatement("INSERT INTO joint (courriel, pieceJointe)\n"
					+ "VALUES (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);

			requeteJoint.setInt(1, pIdCourriel);
			requeteJoint.setInt(2, idPieceJointe);
			requeteJoint.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			//out.println("Erreur lors de l’établissement de la connexion");
		}
		return idPieceJointe;
	}

}
