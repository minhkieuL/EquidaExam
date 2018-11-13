package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modele.Client;
import modele.Compte;
import modele.DirecteurGeneral;
import modele.Utilisateur;

/**
 *
 * @author MartinJ
 */
public class UtilisateurDAO {

	public static Utilisateur getUtilisateurParCompte(Compte compte, Connection connection) {
		Utilisateur utilisateur = null;
		try {
			//preparation de la requete
			PreparedStatement requeteUtilisateur = connection.prepareStatement("SELECT * FROM utilisateur, compte WHERE utilisateur.compte=compte.id AND compte.id = ?");
			requeteUtilisateur.setInt(1, compte.getId());
			ResultSet rsUtilisateur = requeteUtilisateur.executeQuery();
			rsUtilisateur.next();

			PreparedStatement requeteDirecteur = connection.prepareStatement("SELECT * FROM utilisateur, directeurgeneral WHERE utilisateur.id=directeurgeneral.id AND directeurgeneral.id = ?");
			requeteDirecteur.setInt(1, rsUtilisateur.getInt("id"));
			ResultSet rsDirecteur = requeteDirecteur.executeQuery();
			rsDirecteur.last();
			boolean estDirecteur = rsDirecteur.getRow() > 0;
			rsDirecteur.beforeFirst();

			PreparedStatement requeteClient = connection.prepareStatement("SELECT * FROM utilisateur, client WHERE utilisateur.id=client.id AND client.id = ?");
			requeteClient.setInt(1, rsUtilisateur.getInt("id"));
			ResultSet rsClient = requeteClient.executeQuery();
			rsClient.last();
            
			boolean estClient = rsClient.getRow() > 0;
			rsClient.beforeFirst();
            
			if (estDirecteur) {
				utilisateur = new DirecteurGeneral();
			} else if (estClient) {
				utilisateur = new Client();
			}

			utilisateur.setArchiver(rsUtilisateur.getBoolean("archiver"));
			utilisateur.setCompte(compte);
			utilisateur.setCopos(rsUtilisateur.getString("copos"));
			utilisateur.setId(rsUtilisateur.getInt("id"));
			utilisateur.setMail(rsUtilisateur.getString("mail"));
			utilisateur.setNom(rsUtilisateur.getString("nom"));
			utilisateur.setPrenom(rsUtilisateur.getString("prenom"));
			utilisateur.setRue(rsUtilisateur.getString("rue"));
			utilisateur.setVille(rsUtilisateur.getString("ville"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return utilisateur;
	}
	
	public static void archiverUtilisateur(Connection connection, int idUtilisateur) {
		try {
			PreparedStatement requete = connection.prepareStatement("UPDATE utilisateur SET archiver = 1 WHERE id =?;");
			requete.setInt(1, idUtilisateur);

			requete.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			//out.println("Erreur lors de l’établissement de la connexion");
		}
	}
	
}
