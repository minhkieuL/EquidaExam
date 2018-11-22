package formulaires;

import static formulaires.Form.getDataForm;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import modele.Courriel;
import modele.Vente;

/**
 * Document : CategorieForm 
 * Created on : 12 oct. 2018, 09:25:00 
 * Author : MartinJ
 */
public class CourrielForm extends Form {

	public Courriel getCourriel(HttpServletRequest request) {
		Courriel courriel = new Courriel();

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		
		String objet = getDataForm(request, "objet");
		int venteId = Integer.parseInt(getDataForm(request, "vente"));
		String corps = getDataForm(request, "corps");
		
		Vente v = new Vente();
		v.setId(venteId);
		
		courriel.setObjet(objet);
		courriel.setCorps(corps);
		courriel.setVente(v);
		courriel.setDate(dateFormat.format(date));
		
		return courriel;
	}
}
