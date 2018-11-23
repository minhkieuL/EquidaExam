package outils;

import database.CourrielDAO;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import modele.Courriel;
import modele.PieceJointe;

/**
 *
 * @author MartinJ
 */
public class EnvoieMail {
	
	public static void envoyerMail(Connection pConnection, HttpServletRequest request, Courriel pCourriel) {
		//Sauvegarde BDD
		int idCourriel = CourrielDAO.ajouterCourriel(pConnection, pCourriel);
		
		//Stocker fichier		
		String filePath = "upload"+File.separator+"mail"+File.separator+idCourriel;
		ArrayList<File> pieceJointePaths = new ArrayList<>();
		try {
			pieceJointePaths = EnvoieFichier.sauvegarderFichiers(request, "file", filePath);
		} catch(IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
		
		for(File f : pieceJointePaths) {
			PieceJointe pj = new PieceJointe();
			pj.setChemin(f.getPath());
			pj.setDescription("");
			CourrielDAO.ajouterPieceJointe(pConnection, pj, idCourriel);
		}
		
		
		//TODO envoyer vraiment le mail
	}
	
}
