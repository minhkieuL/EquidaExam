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
import java.util.ArrayList;
import modele.Courriel;
import modele.PieceJointe;

/**
 *
 * @author Zakina
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

}
