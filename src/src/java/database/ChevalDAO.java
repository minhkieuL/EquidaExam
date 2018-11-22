package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import modele.Cheval;
import modele.Utilisateur;

/**
 *
 * @author slam
 */
public class ChevalDAO {

	// Méthode permettant d'insérer un client en base à partir de l'objet client passé en paramètre
	// Cette méthode renvoie l'objet client
	public static int ajouterCheval(Connection connection, Cheval unCheval, HttpServletRequest request) {
		int idGenere = -1;
		try {
			//preparation de la requete
			// id (clé primaire de la table client) est en auto_increment,donc on ne renseigne pas cette valeur
			// la paramètre RETURN_GENERATED_KEYS est ajouté à la requête afin de pouvoir récupérer l'id généré par la bdd (voir ci-dessous)
			// supprimer ce paramètre en cas de requête sans auto_increment.
			PreparedStatement requete = connection.prepareStatement("INSERT INTO cheval ( nom, sexe, sire, typeCheval, pere, mere, client)\n"
				+ "VALUES (?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			requete.setString(1, unCheval.getNom());
			requete.setInt(2, unCheval.getMale() ? 1 : 0);
			requete.setString(3, unCheval.getSire());
			requete.setInt(4, unCheval.getTypeCheval().getId());
			if (unCheval.getPere() != null) {
				requete.setInt(5, unCheval.getPere().getId());
			} else {
				requete.setNull(5, Types.INTEGER);
			}

			if (unCheval.getMere() != null) {
				requete.setInt(6, unCheval.getMere().getId());
			} else {
				requete.setNull(6, Types.INTEGER);
			}

			Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");
			if (user != null) {
				requete.setInt(7, user.getId());
			}
			/* Exécution de la requête */
			requete.executeUpdate();

			// Récupération de id auto-généré par la bdd dans la table cheval
			ResultSet rs = requete.getGeneratedKeys();
			while (rs.next()) {
				idGenere = rs.getInt(1);
				unCheval.setId(idGenere);
			}

			// ajout des enregistrements dans la table lot
			/* for (int i=0;i<unCheval.getLots().size();i++){
                PreparedStatement requete2=connection.prepareStatement("INSERT INTO lot(idCheval)\n" +
                    "VALUES (?)");
                 requete2.setInt(2, unCheval.getLots().get(i).getId());
                 requete2.executeUpdate();
            }*/
		} catch (SQLException e) {
			e.printStackTrace();
			//out.println("Erreur lors de l’établissement de la connexion");
		}
		return idGenere;
	}

	public static Cheval getCheval(Connection connection, int idCheval) {
		Cheval unCheval = null;

		try {
			//preparation de la requete     
			PreparedStatement requete = connection.prepareStatement("SELECT * FROM cheval WHERE id=?;");
			requete.setInt(1, idCheval);

			//executer la requete
			ResultSet rs = requete.executeQuery();

			//On hydrate l'objet métier Cheval avec les résultats de la requête
			while (rs.next()) {
				unCheval = new Cheval();
				unCheval.setId(idCheval);
				unCheval.setNom(rs.getString("nom"));
				unCheval.setSire(rs.getString("sire"));
				unCheval.setMale(rs.getBoolean("sexe"));

				int typeCheval = rs.getInt("typeCheval");
				if (typeCheval != 0) {
					unCheval.setTypeCheval(TypeChevalDAO.getTypeCheval(connection, typeCheval));
				}

				int idPere = rs.getInt("pere");
				if (idPere != 0) {
					unCheval.setPere(getCheval(connection, idPere));
				}

				int idMere = rs.getInt("mere");
				if (idMere != 0) {
					unCheval.setMere(getCheval(connection, idMere));
				}

				unCheval.setClient(ClientDAO.getClient(connection, rs.getInt("client")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			//out.println("Erreur lors de l’établissement de la connexion");
		}
		return unCheval;
	}

	public static Cheval getChevalParSire(Connection connection, String sireCheval) {
		Cheval unCheval = null;

		try {
			//preparation de la requete     
			PreparedStatement requete = connection.prepareStatement("SELECT * FROM cheval WHERE sire=?;");
			requete.setString(1, sireCheval);

			//executer la requete
			ResultSet rs = requete.executeQuery();

			//On hydrate l'objet métier Cheval avec les résultats de la requête
			while (rs.next()) {
				unCheval = new Cheval();
				unCheval.setId(rs.getInt("id"));
				unCheval.setNom(rs.getString("nom"));
				unCheval.setSire(rs.getString("sire"));
				unCheval.setMale(rs.getBoolean("sexe"));

				int typeCheval = rs.getInt("typeCheval");
				if (typeCheval != 0) {
					unCheval.setTypeCheval(TypeChevalDAO.getTypeCheval(connection, typeCheval));
				}

				int idPere = rs.getInt("pere");
				if (idPere != 0) {
					unCheval.setPere(getCheval(connection, idPere));
				}

				int idMere = rs.getInt("mere");
				if (idMere != 0) {
					unCheval.setMere(getCheval(connection, idMere));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			//out.println("Erreur lors de l’établissement de la connexion");
		}
		return unCheval;
	}

	public static Cheval modifierChevalOrigin(Connection connection, Cheval unCheval) {
		try {
			//preparation de la requete 
			PreparedStatement requete = connection.prepareStatement("UPDATE cheval SET nom = ?, sexe = ?, sire = ?, typeCheval = ?, mere = ?, pere = ? WHERE id = ?;");

			requete.setString(1, unCheval.getNom());
			requete.setBoolean(2, unCheval.getMale());
			requete.setString(3, unCheval.getSire());
			requete.setInt(4, unCheval.getTypeCheval().getId());
			if (unCheval.getMere() != null) {
				requete.setInt(5, unCheval.getMere().getId());
			} else {
				requete.setNull(5, Types.INTEGER);
			}
			if (unCheval.getPere() != null) {
				requete.setInt(6, unCheval.getPere().getId());
			} else {
				requete.setNull(6, Types.INTEGER);
			}
			requete.setInt(7, unCheval.getId());

			/* Exécution de la requête */
			requete.executeUpdate();

			//System.out.println("requete " +requete);
		} catch (SQLException e) {
			e.printStackTrace();
			//out.println("Erreur lors de l’établissement de la connexion");
		}
		return unCheval;
	}

	public static ArrayList<Cheval> getChevauxClient(Connection connection, int idClient) {
		ArrayList<Cheval> chevaux = new ArrayList<>();
		try {
			PreparedStatement requete = connection.prepareStatement("SELECT * FROM cheval WHERE client = ?");
			requete.setInt(1, idClient);

			ResultSet rs = requete.executeQuery();
			while (rs.next()) {
				Cheval cheval = new Cheval();
				cheval.setId(rs.getInt("id"));
				cheval.setSire(rs.getString("sire"));
				cheval.setNom(rs.getString("nom"));
				chevaux.add(cheval);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return chevaux;
	}

	public static ArrayList<Cheval> getChevauxClientDispoVente(Connection connection, int idClient) {
		ArrayList<Cheval> chevaux = new ArrayList<>();
		try {
			PreparedStatement requete = connection.prepareStatement("SELECT * FROM cheval WHERE cheval.id NOT IN (SELECT lot.idCheval FROM lot WHERE lot.id NOT IN (SELECT enchere.lot FROM enchere) UNION SELECT lot.idCheval FROM lot, enchere WHERE lot.id = enchere.lot AND enchere.montant <> 0) AND client = ?");
			requete.setInt(1, idClient);

			ResultSet rs = requete.executeQuery();
			while (rs.next()) {
				Cheval cheval = new Cheval();
				cheval.setId(rs.getInt("id"));
				cheval.setSire(rs.getString("sire"));
				cheval.setNom(rs.getString("nom"));
				chevaux.add(cheval);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return chevaux;
	}

	public static void archiverCheval(Connection connection, int idCheval) {
		try {
			PreparedStatement requete = connection.prepareStatement("UPDATE cheval SET archiver = 1 WHERE id =?;");
			requete.setInt(1, idCheval);

			requete.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			//out.println("Erreur lors de l’établissement de la connexion");
		}
	}

	public static void validerCheval(Connection connection, int idCheval) {
		try {
			PreparedStatement requete = connection.prepareStatement("UPDATE lot SET validation = (NOW()) WHERE id = ?;");
			requete.setInt(1, idCheval);

			requete.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			//out.println("Erreur lors de l’établissement de la connexion");
		}
	}
}
