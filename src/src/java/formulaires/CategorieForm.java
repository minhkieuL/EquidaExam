package formulaires;

import static formulaires.Form.getDataForm;
import javax.servlet.http.HttpServletRequest;
import modele.CategVente;

/**
 * Document : CategorieForm Created on : 12 oct. 2018, 09:25:00 Author :
 * paul_collet
 */
public class CategorieForm extends Form {

	public CategVente getCategVente(HttpServletRequest request) {
		CategVente uneCategVente = new CategVente();

		String nomChampCode = "code";
		String nomChampLibelle = "libelle";

		String code = getDataForm(request, nomChampCode);
		String libelle = getDataForm(request, nomChampLibelle);

		if (code == null) {
			ajouterErreur(nomChampCode, "Le champ code est obligatoire.");
		} else {
			if (code.length() == 0 || code.length() > 5) {
				ajouterErreur(nomChampCode, "La longueur du code doit être comprise entre 1 et 5 caractères.");
			}
		}

		if (libelle == null) {
			ajouterErreur(nomChampLibelle, "Le champ libellé est obligatoire.");
		} else {
			if (libelle.length() < 3 || libelle.length() > 30) {
				ajouterErreur(nomChampLibelle, "La longueur du libellé doit être comprise entre 3 et 30 caractères.");
			}
		}

		uneCategVente.setCode(code);
		uneCategVente.setLibelle(libelle);

		return uneCategVente;
	}

	public String getCategVenteOrigin(HttpServletRequest request) {
		return getDataForm(request, "codeOrigin");
	}
}
