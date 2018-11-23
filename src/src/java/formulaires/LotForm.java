package formulaires;

import static formulaires.Form.getDataForm;
import javax.servlet.http.HttpServletRequest;
import modele.Cheval;
import modele.Lot;
import modele.Vente;

/**
 * Document : CategorieForm Created on : 12 oct. 2018, 09:25:00 Author :
 * paul_collet
 */
public class LotForm extends Form {

	public Lot getLot(HttpServletRequest request) {
		Lot lot = new Lot();
		
		int idCheval = Integer.valueOf(getDataForm(request, "chevalClient"));
		int idVente = Integer.valueOf(getDataForm(request, "idVente"));
		float prixLot = Float.valueOf(getDataForm(request, "prixLot"));
		
		Cheval cheval = new Cheval();
		cheval.setId(idCheval);
		
		Vente vente = new Vente();
		vente.setId(idVente);
		
		lot.setCheval(cheval);
		lot.setVente(vente);
		lot.setPrixDepart(prixLot);

		return lot;
	}
}
