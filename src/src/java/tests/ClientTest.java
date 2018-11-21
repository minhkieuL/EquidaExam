package tests;

import modele.Client;
import modele.Pays;

/**
 *
 * @author Zakina
 */
public class ClientTest {

	public static void main(String[] args) {

		Client unClient = new Client(null, 1, "dupont", "luc", "", "", "", "", false, null, null);
		unClient.setPays(new Pays("FRA", "FRANCE", null));
		System.out.println(unClient.getNom() + "  " + unClient.getPays().getNom());

	}

}
