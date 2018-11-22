package formulaires;

import database.ChevalDAO;
import database.TypeChevalDAO;
import static formulaires.Form.getDataForm;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import modele.Cheval;

/**
 *
 * @author slam
 */
public class ChevalForm extends Form {

	//méthode de validation du champ de saisie nom
	private void validationNom(String nom) throws Exception {
		if (nom != null && nom.length() < 3) {
			throw new Exception("Le nom d'utilisateur doit contenir au moins 3 caractères.");
		}
	}

	public Cheval getCheval(HttpServletRequest request, Connection connection) {
		Cheval unCheval = new Cheval();

		String nomChampId = "id";
		String nomChampNom = "nom";
		String nomChampSexe = "sexe";
		String nomChampSire = "sire";
		String nomChampTypeCheval = "typeCheval";
		String nomChampPere = "pere";
		String nomChampMere = "mere";

		String idChevalStr = getDataForm(request, nomChampId);
		String nom = getDataForm(request, nomChampNom);
		boolean male = Integer.valueOf(getDataForm(request, nomChampSexe)) == 1;
		String sire = getDataForm(request, nomChampSire);
		int idTypeCheval = Integer.valueOf(getDataForm(request, nomChampTypeCheval));
		String pereSire = getDataForm(request, nomChampPere);
		String mereSire = getDataForm(request, nomChampMere);

		if (idChevalStr != null) {
			unCheval.setId(Integer.valueOf(idChevalStr));
		}

		if (nom == null) {
			ajouterErreur(nomChampNom, "Le champ nom est obligatoire.");
		} else {
			if (nom.length() < 3 || nom.length() > 50) {
				ajouterErreur(nomChampNom, "La longueur du nom doit être comprise entre 3 et 50 caractères.");
			}
		}

		if (sire == null) {
			ajouterErreur(nomChampNom, "Le champ SIRE est obligatoire.");
		} else {
			if (sire.length() < 3 || sire.length() > 100) {
				ajouterErreur(nomChampNom, "Le numéro de SIRE semble invalide.");
			}
		}

		if (pereSire != null) {
			if (pereSire.length() < 3 || pereSire.length() > 100) {
				ajouterErreur(nomChampNom, "Le numéro de SIRE du père semble invalide.");
			}
		}

		if (mereSire != null) {
			if (mereSire.length() < 3 || mereSire.length() > 100) {
				ajouterErreur(nomChampNom, "Le numéro de SIRE de la mère semble invalide.");
			}
		}

		unCheval.setNom(nom);
		unCheval.setMale(male);
		unCheval.setSire(sire);
		unCheval.setTypeCheval(TypeChevalDAO.getTypeCheval(connection, idTypeCheval));
		unCheval.setPere(ChevalDAO.getChevalParSire(connection, pereSire));
		unCheval.setMere(ChevalDAO.getChevalParSire(connection, mereSire));

		return unCheval;
	}

	public String getChevalOrigin(HttpServletRequest request) {
		return getDataForm(request, "idOrigin");
	}
}