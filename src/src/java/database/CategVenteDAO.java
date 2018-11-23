package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.CategVente;

/**
 *
 * @author Zakina
 */
public class CategVenteDAO {

	public static ArrayList<CategVente> getLesCategVentes(Connection connection) {
		ArrayList<CategVente> lesCategVentes = new ArrayList<CategVente>();
		try {
			//preparation de la requete     
			PreparedStatement requete = connection.prepareStatement("SELECT * FROM categvente");

			//executer la requete
			ResultSet rs = requete.executeQuery();

			//On hydrate l'objet métier CategVente avec les résultats de la requête
			while (rs.next()) {
				CategVente uneCategVente = new CategVente();
				uneCategVente.setCode(rs.getString("code"));
				uneCategVente.setLibelle(rs.getString("libelle"));
				lesCategVentes.add(uneCategVente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			//out.println("Erreur lors de l’établissement de la connexion");
		}
		return lesCategVentes;
	}

	public static CategVente ajouterCategVente(Connection connection, CategVente uneCategVente) {
		try {
			//preparation de la requete

			PreparedStatement requete = connection.prepareStatement("INSERT INTO categvente ( code, libelle)\n"
					+ "VALUES (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			requete.setString(1, uneCategVente.getCode());
			requete.setString(2, uneCategVente.getLibelle());

			/* Exécution de la requête */
			requete.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			//out.println("Erreur lors de l’établissement de la connexion");
		}
		return uneCategVente;
	}
    
    public static CategVente getCategVente(Connection connection, String codeCateg){
		CategVente uneCategVente = new CategVente();
        try
        {
            //preparation de la requete 
            PreparedStatement requete=connection.prepareStatement(" SELECT * FROM categvente WHERE code = ?; ");
            requete.setString(1, codeCateg);
            /* Exécution de la requête */
            //executer la requete
			ResultSet rs = requete.executeQuery();

			//On hydrate l'objet métier CategVente avec les résultats de la requête
			while (rs.next()) {
				uneCategVente.setCode(rs.getString("code"));
				uneCategVente.setLibelle(rs.getString("libelle"));
			}
            //System.out.println("requete " +requete);
        }
		catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return uneCategVente ; 
    }
	
	public static CategVente modifierCategVente(Connection connection, CategVente uneCategVente, String codeCateg){      
        
        try
        {
            //preparation de la requete 
            PreparedStatement requete=connection.prepareStatement(" UPDATE categvente SET libelle = ? WHERE code = ?; ");
      
            requete.setString(1, uneCategVente.getLibelle());
			requete.setString(2, codeCateg);
            /* Exécution de la requête */
            requete.executeUpdate();
            
            //System.out.println("requete " +requete);
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return uneCategVente ; 
    }
}
