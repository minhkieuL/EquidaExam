package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.Cheval;
import modele.Lot;
import modele.Pays;
import modele.TypeCheval;
import modele.Vente;

public class LotDAO {
    
    Connection connection=null;
    static PreparedStatement requete=null;
    static ResultSet rs=null;
 
    public static ArrayList<Lot> getLesLotPourVente(Connection connection, String idVente) {
        ArrayList<Lot> lesLots = new  ArrayList<Lot>();
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("SELECT lot.id AS lotId, lot.idCheval, lot.idVente, lot.prixDepart, vente.nom AS nomVente, vente.dateDebut, cheval.nom AS nomCheval, cheval.sexe, cheval.sire, typeCheval.libelle AS libelleTypeCheval, typecheval.description AS descTypeCheval FROM lot, vente, cheval, typecheval WHERE lot.idVente=vente.id AND lot.idCheval=cheval.id AND typecheval.id = cheval.typeCheval AND lot.idVente = '" + idVente + "';");          
            //executer la requete
            rs=requete.executeQuery();
            
            //On hydrate l'objet métier Client avec les résultats de la requête
            while ( rs.next() ) {  
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
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return lesLots ;   
    }
  
     public static ArrayList<Lot>  getLesLots(Connection connection){      
        ArrayList<Lot> lesLots = new  ArrayList<Lot>();
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("select * from lot");
            
            //executer la requete
            rs=requete.executeQuery();
            
            //On hydrate l'objet métier Client avec les résultats de la requête
            while ( rs.next() ) {  
                Lot unLot = new Lot();
                unLot.setId(rs.getInt("Id"));
                unLot.setPrixDepart(rs.getFloat("prixDepart"));
               
            }
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return lesLots;    
    } 
}
