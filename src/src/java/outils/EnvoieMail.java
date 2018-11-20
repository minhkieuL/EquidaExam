package outils;

import database.CourrielDAO;
import java.sql.Connection;
import modele.Courriel;

/**
 *
 * @author MartinJ
 */
public class EnvoieMail {
	
	public static void envoyerMail(Connection pConnection, Courriel pCourriel) {
		//Sauvegarde BDD
		CourrielDAO.ajouterCourriel(pConnection, pCourriel);
		
		//TODO Stocker fichier
		
		//TODO envoyer vraiment le mail
	}
	
}
