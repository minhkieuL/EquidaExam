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
import modele.TypeCheval;
import modele.Cheval;
/**
 *
 * @author slam
 */
public class TypeChevalDAO {
    
    Connection connection=null;
    static PreparedStatement requete=null;
    static ResultSet rs=null;
    
     public static ArrayList<TypeCheval>  getLesTypeCheval(Connection connection){      
        ArrayList<TypeCheval> lesTypeCheval = new  ArrayList<TypeCheval>();
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("SELECT * FROM typecheval");
            
            //executer la requete
            rs=requete.executeQuery();
            
            //On hydrate l'objet métier Client avec les résultats de la requête
            while ( rs.next() ) {  
                TypeCheval  unTypeCheval = new TypeCheval();
                unTypeCheval.setId(rs.getInt("id"));
                unTypeCheval.setLibelle(rs.getString("libelle"));
                unTypeCheval.setDesc(rs.getString("desc"));
                lesTypeCheval.add(unTypeCheval);
            }
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return lesTypeCheval ;    
    } 
     
     public static TypeCheval ajouterTypeCheval(Connection connection, TypeCheval unTypeCheval){      
        try
        {
            //preparation de la requete
            
            requete=connection.prepareStatement("INSERT INTO typecheval (libelle, description) VALUES (?,?)", requete.RETURN_GENERATED_KEYS );
            requete.setString(1, unTypeCheval.getLibelle());
            requete.setString(2, unTypeCheval.getDesc());
          
            
           /* Exécution de la requête */
            requete.executeUpdate();
            
            
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return unTypeCheval ;    
    }
}