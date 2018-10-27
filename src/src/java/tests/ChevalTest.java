package tests;

import modele.Cheval;
import modele.Lot;
import modele.TypeCheval;

public class ChevalTest {

	public static void main(String[] args) {
		Cheval c = new Cheval();
		c.setId(0);
		c.addLot(new Lot());
		c.setNom("nom");
		c.setMale(true);
		c.setSire("sire");
		c.setTypeCheval(new TypeCheval());

		System.out.println("id : " + c.getId());
		System.out.println("lot : " + c.getLots().get(0));
		System.out.println("nom : " + c.getNom());
		System.out.println("male : " + c.getMale());
		System.out.println("sire : " + c.getSire());
		System.out.println("type cheval : " + c.getTypeCheval());
	}

}
