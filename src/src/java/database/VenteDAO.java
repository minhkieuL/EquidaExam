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
import modele.CategVente;
import modele.Vente;

/**
 *
 * @author Zakina 22/06/2017 Classe faisant la liaison entre la table Vente et
 * la classe Vente
 */
public class VenteDAO {

	/* @author Zakina - 22/06/2017
    /* Méthode permettant de lister toutes les ventes enregistrées en base, triées par date décroissante.
    /* Pour chaque vente, on récupère aussi sa catégorie.
    /* La liste des vente est stockée dans une ArrayList
	 */
	public static ArrayList<Vente> getLesVentes(Connection connection) {
		return getLesVentes(connection, null);
	}

	public static ArrayList<Vente> getLesVentes(Connection connection, String catVente) {
		ArrayList<Vente> lesVentes = new ArrayList<Vente>();
		try {
			String selectionCatVente = (catVente != null) ? "AND code='" + catVente + "'" : "";
			//preparation de la requete     
			PreparedStatement requete = connection.prepareStatement("SELECT * FROM vente, categvente WHERE codeCategVente=code " + selectionCatVente + " ORDER BY dateDebut DESC");
			//executer la requete
			ResultSet rs = requete.executeQuery();

			//On hydrate l'objet métier Client avec les résultats de la requête
			while (rs.next()) {
				Vente uneVente = new Vente();
				uneVente.setId(rs.getInt("id"));
				uneVente.setNom(rs.getString("nom"));
				uneVente.setDateDebut(rs.getString("dateDebut"));

				CategVente uneCateg = new CategVente();
				uneCateg.setCode(rs.getString("code"));  // on aurait aussi pu prendre CodeCateg
				uneCateg.setLibelle(rs.getString("libelle"));

				uneVente.setUneCategVente(uneCateg);
				lesVentes.add(uneVente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lesVentes;
	}
	
	
	public static ArrayList<Vente> getLesVentesAVenir(Connection connection, String catVente) {
		ArrayList<Vente> lesVentes = new ArrayList<Vente>();
		try {
			String selectionCatVente = (catVente != null) ? "AND code='" + catVente + "'" : "";
			//preparation de la requete     
			PreparedStatement requete = connection.prepareStatement("SELECT * FROM vente, categvente WHERE vente.dateDebut>NOW() AND codeCategVente=code " + selectionCatVente + "  ORDER BY dateDebut DESC");
			//executer la requete
			ResultSet rs = requete.executeQuery();

			//On hydrate l'objet métier Client avec les résultats de la requête
			while (rs.next()) {
				Vente uneVente = new Vente();
				uneVente.setId(rs.getInt("id"));
				uneVente.setNom(rs.getString("nom"));
				uneVente.setDateDebut(rs.getString("dateDebut"));

				CategVente uneCateg = new CategVente();
				uneCateg.setCode(rs.getString("code"));  // on aurait aussi pu prendre CodeCateg
				uneCateg.setLibelle(rs.getString("libelle"));

				uneVente.setUneCategVente(uneCateg);
				lesVentes.add(uneVente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lesVentes;
	}
	
	public static Vente getVente(Connection connection, int idVente) {
		Vente vente = null;
		try {
			PreparedStatement requete = connection.prepareStatement("SELECT * FROM vente, categvente WHERE codeCategVente=code AND id= ?");
			requete.setInt(1, idVente);
			//executer la requete
			ResultSet rs = requete.executeQuery();

			//On hydrate l'objet métier Client avec les résultats de la requête
			while (rs.next()) {
				vente = new Vente();
				vente.setId(rs.getInt("id"));
				vente.setNom(rs.getString("nom"));
				vente.setDateDebut(rs.getString("dateDebut"));
				vente.setDateFin(rs.getString("dateFin"));
				vente.setDateDebutInscription(rs.getString("dateDebutInscription"));
				vente.setLieu(LieuDAO.getLieu(connection, rs.getInt("lieu")));
				vente.setLots(LotDAO.getLesLotPourVente(connection, vente.getId()));
				vente.setUneCategVente(CategVenteDAO.getCategVente(connection, rs.getString("codeCategVente")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vente;
	}
}