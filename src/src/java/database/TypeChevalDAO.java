package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.TypeCheval;

/**
 *
 * @author slam
 */
public class TypeChevalDAO {

	public static ArrayList<TypeCheval> getLesTypeCheval(Connection connection) {
		ArrayList<TypeCheval> lesTypeCheval = new ArrayList<TypeCheval>();
		try {
			//preparation de la requete     
			PreparedStatement requete = connection.prepareStatement("SELECT * FROM typecheval");

			//executer la requete
			ResultSet rs = requete.executeQuery();

			//On hydrate l'objet métier TypeCheval avec les résultats de la requête
			while (rs.next()) {
				TypeCheval unTypeCheval = new TypeCheval();
				unTypeCheval.setId(rs.getInt("id"));
				unTypeCheval.setLibelle(rs.getString("libelle"));
				unTypeCheval.setDesc(rs.getString("description"));
				lesTypeCheval.add(unTypeCheval);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			//out.println("Erreur lors de l’établissement de la connexion");
		}
		return lesTypeCheval;
	}

	public static TypeCheval ajouterTypeCheval(Connection connection, TypeCheval unTypeCheval) {
		try {
			//preparation de la requete

			PreparedStatement requete = connection.prepareStatement("INSERT INTO typecheval (libelle, description) VALUES (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			requete.setString(1, unTypeCheval.getLibelle());
			requete.setString(2, unTypeCheval.getDesc());

			/* Exécution de la requête */
			requete.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			//out.println("Erreur lors de l’établissement de la connexion");
		}
		return unTypeCheval;
	}

	public static TypeCheval getTypeCheval(Connection connection, int idTypeCheval) {
		TypeCheval unTypeCheval = new TypeCheval();
		try {
			//preparation de la requete     
			PreparedStatement requete = connection.prepareStatement("SELECT * FROM typecheval WHERE id=?;");
			requete.setInt(1, idTypeCheval);

			//executer la requete
			ResultSet rs = requete.executeQuery();

			//On hydrate l'objet métier TypeCheval avec les résultats de la requête
			while (rs.next()) {
				unTypeCheval.setId(idTypeCheval);
				unTypeCheval.setLibelle(rs.getString("libelle"));
				unTypeCheval.setDesc(rs.getString("description"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			//out.println("Erreur lors de l’établissement de la connexion");
		}
		return unTypeCheval;
	}

	public static TypeCheval modifierTypeCheval(Connection connection, TypeCheval unTypeCheval, int idTypeCheval) {

		try {
			//preparation de la requete 
			PreparedStatement requete = connection.prepareStatement(" UPDATE typecheval SET libelle = ?, description = ? WHERE id = ?; ");

			requete.setString(1, unTypeCheval.getLibelle());
			requete.setString(2, unTypeCheval.getDesc());
			requete.setInt(3, idTypeCheval);
			/* Exécution de la requête */
			requete.executeUpdate();

			//System.out.println("requete " +requete);
		} catch (SQLException e) {
			e.printStackTrace();
			//out.println("Erreur lors de l’établissement de la connexion");
		}
		return unTypeCheval;
	}
}
