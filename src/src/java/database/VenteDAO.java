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

			//On hydrate l'objet métier Vente avec les résultats de la requête
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
	
	
	public static ArrayList<Vente> getLesVentesAVenir(Connection connection) {
		ArrayList<Vente> lesVentes = new ArrayList<Vente>();
		try {
			//preparation de la requete     
			PreparedStatement requete = connection.prepareStatement("SELECT * FROM vente ORDER BY dateDebut DESC LIMIT 5");
			//executer la requete
			ResultSet rs = requete.executeQuery();

			//On hydrate l'objet métier Vente avec les résultats de la requête
			while (rs.next()) {
				Vente uneVente = new Vente();
				uneVente.setId(rs.getInt("id"));
				uneVente.setNom(rs.getString("nom"));
				uneVente.setDateDebut(rs.getString("dateDebut"));
				uneVente.setUneCategVente(CategVenteDAO.getCategVente(connection, rs.getString("codeCategVente")));
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
				vente.setDateVente(rs.getString("dateVente"));
				vente.setLieu(LieuDAO.getLieu(connection, rs.getInt("lieu")));
				vente.setLots(LotDAO.getLesLotPourVente(connection, vente.getId()));
				vente.setUneCategVente(CategVenteDAO.getCategVente(connection, rs.getString("codeCategVente")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vente;
	}

	public static int ajouterVente(Connection connection, Vente uneVente) {
		int idGenere = -1;
		try {
			//preparation de la requete
			// id (clé primaire de la table vente) est en auto_increment, donc on ne renseigne pas cette valeur
			// la paramètre RETURN_GENERATED_KEYS est ajouté à la requête afin de pouvoir récupérer l'id généré par la bdd (voir ci-dessous)
			// supprimer ce paramètre en cas de requête sans auto_increment.
			PreparedStatement requete = connection.prepareStatement("INSERT INTO vente ( nom, dateDebut, dateFin, dateVente, lieu, codeCategVente)\n"
					+ "VALUES (?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			requete.setString(1, uneVente.getNom());
			requete.setString(2, uneVente.getDateDebut());
			requete.setString(3, uneVente.getDateFin());
			requete.setString(4, uneVente.getDateVente());
			requete.setInt(5, uneVente.getLieu().getId());
			requete.setString(6, uneVente.getUneCategVente().getCode());

			/* Exécution de la requête */
			requete.executeUpdate();

			// Récupération de id auto-généré par la bdd dans la table utilisateur
			ResultSet rs = requete.getGeneratedKeys();
			while (rs.next()) {
				idGenere = rs.getInt(1);
				uneVente.setId(idGenere);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			//out.println("Erreur lors de l’établissement de la connexion");
		}
		return idGenere;
	}

	public static Vente getUneVente(Connection connection, int idVente) {
		Vente vente = null;
		try {
			//preparation de la requete
			// id (clé primaire de la table vente) est en auto_increment, donc on ne renseigne pas cette valeur
			// la paramètre RETURN_GENERATED_KEYS est ajouté à la requête afin de pouvoir récupérer l'id généré par la bdd (voir ci-dessous)
			// supprimer ce paramètre en cas de requête sans auto_increment.
			PreparedStatement requete = connection.prepareStatement("SELECT * FROM vente WHERE id=?");
			requete.setInt(1, idVente);
			ResultSet rs = requete.executeQuery();
			while (rs.next()) {
				vente = new Vente();
				vente.setId(rs.getInt("id"));
				vente.setNom(rs.getString("nom"));
				vente.setDateDebut(rs.getString("dateDebut"));
				vente.setDateFin(rs.getString("dateFin"));
				vente.setDateVente(rs.getString("dateVente"));
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