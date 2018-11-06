package tests;

import modele.Cheval;
import modele.Lot;
import modele.Vente;

public class LotTest {

	public static void main(String[] args) {
		Lot l = new Lot();
		l.setCheval(new Cheval());
		l.setId(0);
		l.setPrixDepart(30);
		l.setVente(new Vente());

		System.out.println("id : " + l.getId());
		System.out.println("cheval : " + l.getCheval());
		System.out.println("prix : " + l.getPrixDepart());
		System.out.println("vente : " + l.getVente());
	}

}
