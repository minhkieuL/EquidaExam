package formulaires;

import static formulaires.Form.getDataForm;
import javax.servlet.http.HttpServletRequest;
import modele.Pays;

/**
 *
 * @author slam
 */
public class PaysForm extends Form {

	public Pays getPays(HttpServletRequest request) {
		Pays unPays = new Pays();
		
		String nomChampCode = "code";
		String nomChampNom = "nom";

		String code = getDataForm(request, nomChampCode);
		String nom = getDataForm(request, nomChampNom);

		if(code == null) {
			ajouterErreur(nomChampCode, "Le champ code est obligatoire.");
		} else {
			if(code.length() != 3) {
				ajouterErreur(nomChampCode, "La longueur du code doit être de 3 charactères.");
			}
		}
		
		if(nom == null) {
			ajouterErreur(nomChampNom, "Le champ nom est obligatoire.");
		} else {
			if(nom.length() < 3 || nom.length() > 30) {
				ajouterErreur(nomChampNom, "La longueur du nom doit être compris entre 3 et 30 charactères.");
			}
		}
		
		unPays.setCode(code);
		unPays.setNom(nom);

		return unPays;
	}
	
	public String getPaysOrigin(HttpServletRequest request) {
        return getDataForm(request, "codeOrigin");
    }
}
