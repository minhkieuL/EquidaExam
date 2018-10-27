package tests;

import java.util.*;
import modele.CategVente;
import modele.Courriel;
import modele.Lieu;
import modele.Vente;

/**
 *
 * @author MartinJ
 */
public class CourrielTest {

	public static void main(String[] args) {
		Vente vente = new Vente();
		vente.setDateDebut("dateDebut");
		vente.setId(1);
		vente.setLieu(new Lieu(1, "ville", 10, "commentaire", null));
		vente.setListeCourriels(new ArrayList<Courriel>(Arrays.asList(new Courriel())));
		vente.setNom("nom");
		vente.setUneCategVente(new CategVente("code", "libelle", null, null));

		System.out.println("Date debut : " + vente.getDateDebut());
		System.out.println("Id : " + vente.getId());
		System.out.println("Lieu : " + vente.getLieu());
		System.out.println("Liste Courriels: " + vente.getListeCourriels().size());
		System.out.println("Nom : " + vente.getNom());
		System.out.println("Categorie vente : " + vente.getUneCategVente());
	}

}
