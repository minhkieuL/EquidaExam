package tests;

import java.util.*;
import modele.CategVente;
import modele.Courriel;
import modele.Lieu;
import modele.PieceJointe;
import modele.Vente;

/**
 *
 * @author MartinJ
 */
public class PieceJointeTest {
    
    public static void main(String[] args) {
        PieceJointe pieceJointe = new PieceJointe(1, "chemin", "desc", new ArrayList(Arrays.asList(new Courriel())));
        System.out.println(pieceJointe.getChemin() + " " + pieceJointe.getId() + " " + pieceJointe.getDescription() + " " + pieceJointe.getListeCourriels().size());
    }
    
}
