package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.Cheval;
import modele.Lot;
import modele.TypeCheval;
import modele.Vente;

public class LotDAO {

	public static ArrayList<Lot> getLesLotPourVente(Connection connection, int idVente) {
		ArrayList<Lot> lesLots = new ArrayList<Lot>();
		try {
			//preparation de la requete     
			PreparedStatement requete = connection.prepareStatement("SELECT lot.id AS lotId, lot.idCheval, lot.idVente, lot.prixDepart, vente.nom AS nomVente, vente.dateDebut, cheval.nom AS nomCheval, cheval.sexe, cheval.sire, typecheval.libelle AS libelleTypeCheval, typecheval.description AS descTypeCheval FROM lot, vente, cheval, typecheval WHERE lot.idVente=vente.id AND lot.idCheval=cheval.id AND typecheval.id = cheval.typeCheval AND lot.validation IS NOT NULL AND lot.idVente = '" + idVente + "';");
			//executer la requete
			ResultSet rs = requete.executeQuery();

			//On hydrate l'objet métier Lot avec les résultats de la requête
			while (rs.next()) {
				Lot lot = new Lot();
				lot.setId(rs.getInt("lotId"));
				lot.setPrixDepart(rs.getFloat("prixDepart"));

				Vente vente = new Vente();
				vente.setDateDebut("dateDebut");
				vente.setId(Integer.valueOf(idVente));
				vente.setNom("nomVente");
				lot.setVente(vente);

				TypeCheval typeCheval = new TypeCheval();
				typeCheval.setDesc(rs.getString("descTypeCheval"));
				typeCheval.setLibelle(rs.getString("libelleTypeCheval"));

				Cheval cheval = new Cheval();
				cheval.setId(rs.getInt("idCheval"));
				cheval.setMale(rs.getBoolean("sexe"));
				cheval.setNom(rs.getString("nomCheval"));
				cheval.setSire(rs.getString("sire"));
				cheval.setTypeCheval(typeCheval);
				lot.setCheval(cheval);

				lesLots.add(lot);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lesLots;
	}

	public static ArrayList<Lot> getLesLotsNonVendu(Connection connection) {
		ArrayList<Lot> lesLots = new ArrayList<Lot>();
		try {
			//preparation de la requete     
			PreparedStatement requete = connection.prepareStatement("SELECT * FROM lot, cheval WHERE lot.idCheval = cheval.id AND lot.id NOT IN (SELECT lot FROM enchere) AND cheval.archiver = 0 AND lot.validation IS NOT NULL ORDER BY prixDepart DESC");

			//executer la requete
			ResultSet rs = requete.executeQuery();

			//On hydrate l'objet métier Lot avec les résultats de la requête
			while (rs.next()) {
				Lot unLot = new Lot();
				unLot.setId(rs.getInt("id"));
				unLot.setCheval(ChevalDAO.getCheval(connection, rs.getInt("idCheval")));
				unLot.setVente(VenteDAO.getVente(connection, rs.getInt("idVente")));
				unLot.setPrixDepart(rs.getFloat("prixDepart"));

				lesLots.add(unLot);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			//out.println("Erreur lors de l’établissement de la connexion");
		}
		return lesLots;
	}
	
	public static ArrayList<Lot> getLesNouveauxLots(Connection connection) {
		ArrayList<Lot> lesLots = new ArrayList<Lot>();
		try {
			//preparation de la requete     
			PreparedStatement requete = connection.prepareStatement("SELECT * FROM lot ORDER BY validation DESC LIMIT 5");

			//executer la requete
			ResultSet rs = requete.executeQuery();

			//On hydrate l'objet métier Lot avec les résultats de la requête
			while (rs.next()) {
				Lot unLot = new Lot();
				unLot.setId(rs.getInt("id"));
				unLot.setCheval(ChevalDAO.getCheval(connection, rs.getInt("idCheval")));
				unLot.setVente(VenteDAO.getVente(connection, rs.getInt("idVente")));
				unLot.setPrixDepart(rs.getFloat("prixDepart"));

				lesLots.add(unLot);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			//out.println("Erreur lors de l’établissement de la connexion");
		}
		return lesLots;
	}
	
	public static ArrayList<Lot> getlesLotsNonValides(Connection connection) {
		ArrayList<Lot> lesLots = new ArrayList<>();
		try {
			//preparation de la requete     
			PreparedStatement requete = connection.prepareStatement("SELECT * FROM cheval,lot WHERE lot.idcheval = cheval.id AND lot.validation IS NULL");

			//executer la requete
			ResultSet rs = requete.executeQuery();
			
			while (rs.next()) {
				Lot unLot = new Lot();
				unLot.setId(rs.getInt("id"));
				unLot.setCheval(ChevalDAO.getCheval(connection, rs.getInt("idCheval")));
				unLot.setVente(VenteDAO.getVente(connection, rs.getInt("idVente")));
				unLot.setPrixDepart(rs.getFloat("prixDepart"));

				lesLots.add(unLot);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return lesLots;
	}
			
	public static void ajouterLot(Connection connection, Lot lot) {
		try {
			PreparedStatement requete = connection.prepareStatement("INSERT INTO lot(idVente, idCheval, prixDepart) VALUES (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);

			requete.setInt(1, lot.getVente().getId());
			requete.setInt(2, lot.getCheval().getId());
			requete.setFloat(3, lot.getPrixDepart());

			requete.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			//out.println("Erreur lors de l’établissement de la connexion");
		}
	}
	
	public static Lot getLotCheval(Connection connection, int idCheval) {
		Lot lot = null;
		try {
			PreparedStatement requete = connection.prepareStatement("SELECT * FROM `lot` WHERE `idCheval` = ? AND id NOT IN (SELECT enchere.lot FROM enchere WHERE enchere.montant = 0)", PreparedStatement.RETURN_GENERATED_KEYS);
			requete.setInt(1, idCheval);
			
			ResultSet rs = requete.executeQuery();
			while (rs.next()) {
				lot = new Lot();
				lot.setId(rs.getInt("id"));
				lot.setPrixDepart(rs.getFloat("prixDepart"));
				lot.setValidation(rs.getString("validation"));
				lot.setVente(VenteDAO.getUneVente(connection, rs.getInt("idVente")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lot;
	}
}
