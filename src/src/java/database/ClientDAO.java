package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.CategVente;
import modele.Client;
import modele.Pays;

/**
 *
 * @author Zakina 23/06/2017 Classe faisant la liaison entre la table client et
 * la classe Client
 */
public class ClientDAO {

	// Méthode permettant d'insérer un client en base à partir de l'objet client passé en paramètre
	// Cette méthode renvoie l'objet client
	public static int ajouterClient(Connection connection, Client unClient) {
		int idGenere = -1;
		try {
			//preparation de la requete
			// id (clé primaire de la table client) est en auto_increment,donc on ne renseigne pas cette valeur
			// la paramètre RETURN_GENERATED_KEYS est ajouté à la requête afin de pouvoir récupérer l'id généré par la bdd (voir ci-dessous)
			// supprimer ce paramètre en cas de requête sans auto_increment.
			PreparedStatement requete = connection.prepareStatement("INSERT INTO utilisateur ( nom, prenom, rue, copos, ville, codePays, mail)\n"
					+ "VALUES (?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			requete.setString(1, unClient.getNom());
			requete.setString(2, unClient.getPrenom());
			requete.setString(3, unClient.getRue());
			requete.setString(4, unClient.getCopos());
			requete.setString(5, unClient.getVille());
			requete.setString(6, unClient.getPays().getCode());
			requete.setString(7, unClient.getMail());

			/* Exécution de la requête */
			requete.executeUpdate();

			// Récupération de id auto-généré par la bdd dans la table utilisateur
			ResultSet rs = requete.getGeneratedKeys();
			while (rs.next()) {
				idGenere = rs.getInt(1);
				unClient.setId(idGenere);
			}

			// ajout des enregistrement dans la table clientcategvente
			for (int i = 0; i < unClient.getLesCategVentes().size(); i++) {
				PreparedStatement requete2 = connection.prepareStatement("INSERT INTO clientcategvente (codeClient, codeCategVente )\n"
						+ "VALUES (?,?)");
				requete2.setInt(1, unClient.getId());
				requete2.setString(2, unClient.getLesCategVentes().get(i).getCode());
				requete2.executeUpdate();
			}

			//ajout de l'utilisateur comme client
			PreparedStatement clientAjoutPreparedStatement = connection.prepareStatement("INSERT INTO client (id) VALUES (?)");
			clientAjoutPreparedStatement.setInt(1, unClient.getId());
			clientAjoutPreparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			//out.println("Erreur lors de l’établissement de la connexion");
		}
		return idGenere;
	}
	
	public static ArrayList<Client> getLesClients(Connection connection, String codeCateg) {
		ArrayList<Client> lesClients = new ArrayList<Client>();
		try {
			//preparation de la requete     
			//codeCateg="ETE";
			PreparedStatement requete = connection.prepareStatement("SELECT u.*, p.nom AS nomPays, cv.libelle FROM client c, pays p, clientcategvente cc, categvente cv, utilisateur u WHERE u.id = c.id AND u.codePays=p.code AND cc.codeClient=c.id AND cv.code=cc.codeCategVente AND codeCategVente= ? AND u.archiver=0");
			requete.setString(1, codeCateg);
			//executer la requete
			ResultSet rs = requete.executeQuery();

			//On hydrate l'objet métier Client avec les résultats de la requête
			while (rs.next()) {
				Client unClient = new Client();
				unClient.setId(rs.getInt("id"));
				unClient.setNom(rs.getString("nom"));
				unClient.setPrenom(rs.getString("prenom"));
				unClient.setCopos(rs.getString("copos"));
				unClient.setRue(rs.getString("rue"));
				unClient.setVille(rs.getString("ville"));
				unClient.setMail(rs.getString("mail"));

				Pays p = new Pays();
				p.setCode(rs.getString("codePays"));
				p.setNom(rs.getString("nomPays"));

				unClient.setPays(p);
				/*CategVente uneCateg = new CategVente();
                uneCateg.setCode(rs.getString("code"));  // on aurait aussi pu prendre CodeCateg
                uneCateg.setLibelle(rs.getString("libelle"));*/

				lesClients.add(unClient);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			//out.println("Erreur lors de l’établissement de la connexion");
		}
		return lesClients;
	}
	
	public static Client getClient(Connection connection, int idClient) {
		Client client = null;
		
		try {

			PreparedStatement requete = connection.prepareStatement("SELECT * FROM utilisateur WHERE id= ?");
			requete.setInt(1, idClient);
			//executer la requete
			ResultSet rs = requete.executeQuery();

			//On hydrate l'objet métier Client avec les résultats de la requête
			while (rs.next()) {
				client = new Client();
				client.setId(rs.getInt("id"));
				client.setNom(rs.getString("nom"));
				client.setPrenom(rs.getString("prenom"));
				client.setCopos(rs.getString("copos"));
				client.setRue(rs.getString("rue"));
				client.setVille(rs.getString("ville"));
				client.setMail(rs.getString("mail"));
				
				client.setPays(new Pays());
				client.setLesCategVentes(new ArrayList<CategVente>());
				
				PreparedStatement requeteCategvente = connection.prepareStatement("SELECT * FROM utilisateur, categvente, clientcategvente WHERE utilisateur.id=codeClient AND codeCategVente=categvente.code AND utilisateur.id=?");
				requeteCategvente.setInt(1, idClient);
				ResultSet rsCategVente = requeteCategvente.executeQuery();
				while(rsCategVente.next()) {
					CategVente categVente = new CategVente();
					categVente.setCode(rsCategVente.getString("code"));
					categVente.setLibelle(rsCategVente.getString("libelle"));
					client.addUneCategVente(categVente);
				}
								
				client.setPays(PaysDAO.getPays(connection, rs.getString("codePays")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			//out.println("Erreur lors de l’établissement de la connexion");
		}
		
		return client;
	}

	public static ArrayList<Client> getLesClientsPrDirGen(Connection connection) {
		ArrayList<Client> lesClients = new ArrayList<Client>();
		
		try {

			PreparedStatement requete = connection.prepareStatement("SELECT * FROM utilisateur WHERE archiver=0");
			//executer la requete
			ResultSet rs = requete.executeQuery();

			//On hydrate l'objet métier Client avec les résultats de la requête
			while (rs.next()) {
				Client unClient = new Client();
				unClient.setId(rs.getInt("id"));
				unClient.setNom(rs.getString("nom"));
				unClient.setPrenom(rs.getString("prenom"));
				unClient.setCopos(rs.getString("copos"));
				unClient.setRue(rs.getString("rue"));
				unClient.setVille(rs.getString("ville"));
				unClient.setMail(rs.getString("mail"));								
				unClient.setPays(PaysDAO.getPays(connection, rs.getString("codePays")));
				
				lesClients.add(unClient);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			//out.println("Erreur lors de l’établissement de la connexion");
		}
		
		return lesClients;
	}
	
	public static Client modifierClient(Connection connection, Client unClient){      
        
        try
        {
            //preparation de la requete 
            PreparedStatement requete=connection.prepareStatement(" UPDATE utilisateur SET nom = ?, prenom = ?,rue = ?,copos = ?,ville = ?,mail = ?, codePays = ? WHERE id = ?; ");
      
            requete.setString(1, unClient.getNom());
			requete.setString(2, unClient.getPrenom());
			requete.setString(3, unClient.getRue());
			requete.setString(4, unClient.getCopos());
			requete.setString(5, unClient.getVille());
			requete.setString(6, unClient.getVille());
			requete.setString(7, unClient.getPays().getCode());
			requete.setString(6, unClient.getMail());
			requete.setInt(8, unClient.getId());
            /* Exécution de la requête */
            requete.executeUpdate();
			
			PreparedStatement requeteDeleteCategVente=connection.prepareStatement(" delete FROM clientcategvente WHERE codeClient = ? ; ");
			requeteDeleteCategVente.setInt(1, unClient.getId());
			requeteDeleteCategVente.executeUpdate();
			
            
			for (int i = 0; i < unClient.getLesCategVentes().size(); i++) {
				PreparedStatement requete2 = connection.prepareStatement("INSERT INTO clientcategvente (codeClient, codeCategVente )\n"
						+ "VALUES (?,?)");
				requete2.setInt(1, unClient.getId());
				requete2.setString(2, unClient.getLesCategVentes().get(i).getCode());
				requete2.executeUpdate();
			}
            //System.out.println("requete " +requete);
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return unClient ; 
    }
	
	

}
