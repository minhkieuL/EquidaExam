package tests;

import java.util.*;
import modele.Lieu;
import modele.Vente;

/**
 *
 * @author MartinJ
 */
public class LieuTest {

	public static void main(String[] args) {
		Lieu lieu = new Lieu(1, "ville", 10, "commentaire", new ArrayList<>(Arrays.asList(new Vente())));
		System.out.println(lieu.getCommentaire() + " " + lieu.getVille() + " " + lieu.getId() + " " + lieu.getNbBoxes() + " " + lieu.getVente().size());
	}

}
