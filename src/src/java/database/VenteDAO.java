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
 * @author Zakina
 * 22/06/2017
 * Classe faisant la liaison entre la table Vente et la classe Vente
 */
public class VenteDAO {
    
    Connection connection=null;
    static PreparedStatement requete=null;
    static ResultSet rs=null;
    
    /* @author Zakina - 22/06/2017
    /* Méthode permettant de lister toutes les ventes enregistrées en base, triées par date décroissante.
    /* Pour chaque vente, on récupère aussi sa catégorie.
    /* La liste des vente est stockée dans une ArrayList
    */
    public static ArrayList<Vente>  getLesVentes(Connection connection){      
        return getLesVentes(connection, null);
    } 
    
    public static ArrayList<Vente> getLesVentes(Connection connection, String catVente) {
        ArrayList<Vente> lesVentes = new  ArrayList<Vente>();
        try
        {
            String selectionCatVente = (catVente != null) ? "AND code='"+catVente+"'" : "";
            //preparation de la requete     
            requete=connection.prepareStatement("SELECT * FROM vente, categvente WHERE codeCategVente=code " + selectionCatVente + " ORDER BY dateDebut DESC");          
            //executer la requete
            rs=requete.executeQuery();
            
            //On hydrate l'objet métier Client avec les résultats de la requête
            while ( rs.next() ) {  
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
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return lesVentes ;   
    }
    
    
    public static ArrayList<CategVente> getLesCatVentes(Connection connection) {
        ArrayList<CategVente> lesCatVentes = new  ArrayList<CategVente>();
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("SELECT * FROM categvente ORDER BY libelle");          
            //executer la requete
            rs=requete.executeQuery();
            
            //On hydrate l'objet métier Client avec les résultats de la requête
            while ( rs.next() ) {  
                CategVente catVente = new CategVente();
                catVente.setCode(rs.getString("code"));
                catVente.setLibelle(rs.getString("libelle"));
                
                lesCatVentes.add(catVente);
            }
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return lesCatVentes; 
    }
    
    /* @author Zakina - 22/06/2017
    /* Méthode permettant de lister les clients interessés par la catégorie de la vente selectionnée (passée en paramètre de la méthode)
    /* Pour chaque client, on récupère aussi le nom de son pays
    /* La liste des clients est stockée dans une ArrayList
    */
    public static ArrayList<Client>  getLesClients(Connection connection, String codeCateg){      
        ArrayList<Client> lesClients = new  ArrayList<Client>();
        try
        {
            //preparation de la requete     
            //codeCateg="ETE";
            requete=connection.prepareStatement("SELECT u.*, p.nom AS nomPays, cv.libelle FROM client c, pays p, clientcategvente cc, categvente cv, utilisateur u WHERE u.id = c.id AND u.codePays=p.code AND cc.codeClient=c.id AND cv.code=cc.codeCategVente AND codeCategVente= ? ");
            requete.setString(1, codeCateg);
            //executer la requete
            rs=requete.executeQuery();
             
            //On hydrate l'objet métier Client avec les résultats de la requête
            while ( rs.next() ) {  
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
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return lesClients ;    
    } 
    
}