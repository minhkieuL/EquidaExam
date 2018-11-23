package formulaires;

import static formulaires.Form.getDataForm;
import javax.servlet.http.HttpServletRequest;
import modele.TypeCheval;

/**
 *
 * @author slam
 */
public class TypeChevalForm extends Form {

	public TypeCheval getTypeCheval(HttpServletRequest request) {
		TypeCheval unTypeCheval = new TypeCheval();

		String nomChampLibelle = "libelle";
		String nomChampDesc = "description";

		String libelle = getDataForm(request, nomChampLibelle);
		String desc = getDataForm(request, nomChampDesc);

		if (libelle == null) {
			ajouterErreur(nomChampLibelle, "Le champ libellé est obligatoire.");
		} else {
			if (libelle.length() < 3 || libelle.length() > 50) {
				ajouterErreur(nomChampLibelle, "La longueur du libellé doit être comprise entre 3 et 50 caractères.");
			}
		}

		if (desc == null) {
			ajouterErreur(nomChampDesc, "Le champ description est obligatoire.");
		} else {
			if (desc.length() < 3 || desc.length() > 255) {
				ajouterErreur(nomChampDesc, "La longueur du description doit être compris entre 3 et 255 charactères.");
			}
		}

		//unTypeCheval.setId(Integer.valueOf(getDataForm(request, "codeOrigin")));
		unTypeCheval.setLibelle(libelle);
		unTypeCheval.setDesc(desc);

		return unTypeCheval;
	}

	public int getTypeChevalOrigin(HttpServletRequest request) {
		int idTypeCheval = Integer.valueOf(getDataForm(request, "codeOrigin"));
		return idTypeCheval;
	}
}
