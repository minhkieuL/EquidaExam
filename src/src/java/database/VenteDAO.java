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
import modele.Client;
import modele.Pays;
import modele.Vente;

/**
 *
 * @author Zakina 22/06/2017 Classe faisant la liaison entre la table Vente et
 * la classe Vente
 */
public class VenteDAO {

	Connection connection = null;
	static PreparedStatement requete = null;
	static ResultSet rs = null;

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
			requete = connection.prepareStatement("SELECT * FROM vente, categvente WHERE codeCategVente=code " + selectionCatVente + " ORDER BY dateDebut DESC");
			//executer la requete
			rs = requete.executeQuery();

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
}