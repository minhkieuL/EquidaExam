/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulaires;

import static formulaires.Form.getDataForm;
import javax.servlet.http.HttpServletRequest;
import modele.Cheval;
import modele.Client;
import modele.Enchere;
import modele.Lot;
import modele.Vente;

/**
 * Document : CategorieForm Created on : 12 oct. 2018, 09:25:00 Author :
 * paul_collet
 */
public class EnchereForm extends Form {

	public Enchere getEnchere(HttpServletRequest request) {
		Enchere	uneEnchere = new Enchere();
		
		int idLot = Integer.valueOf(getDataForm(request, "idLot"));
		int idClient = Integer.valueOf(getDataForm(request, "encherisseur"));
		float montantEnchere = Float.valueOf(getDataForm(request, "montantEnchere"));
		
		Lot lot = new Lot();
		lot.setId(idLot);
		
		Client client = new Client();
		client.setId(idClient);
		
		uneEnchere.setLot(lot);
		uneEnchere.setClient(client);
		uneEnchere.setMontant(montantEnchere);

		return uneEnchere;
	}
}
