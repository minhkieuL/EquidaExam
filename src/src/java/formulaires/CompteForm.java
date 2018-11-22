package formulaires;

import database.Utilitaire;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import modele.Compte;

/**
 *
 * @author MartinJ
 */
public class CompteForm extends Form {

	public CompteForm() {

	}

	public Compte getCompte(HttpServletRequest request) {
		Compte compte = new Compte();
		
		String nomChampLogin = "login";
		String nomChampPasswd = "passwd";

		String login = getDataForm(request, nomChampLogin);
		String passwd = getDataForm(request, nomChampPasswd);
		
		if (login == null) {
			ajouterErreur(nomChampLogin, "Le champ nom d'utilisateur est obligatoire");
		} else {
			if (login.length() < 3 || login.length() > 32) {
				ajouterErreur(nomChampLogin, "La longueur du nom d'utilisateur doit être compris entre 3 et 32 charactères");
			}
		}
		
		if (passwd == null) {
			ajouterErreur(nomChampPasswd, "Le champ mot de passe est obligatoire");
		} else {
			if (passwd.length() < 3) {
				ajouterErreur(nomChampPasswd, "La longueur du mot de passe doit faire au minimum 3 charactères");
			}
		}

		compte.setLogin(login);

		try {
			compte.setMdp(Utilitaire.hashToSHA256(passwd));
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(CompteForm.class.getName()).log(Level.SEVERE, null, ex);
			compte.setMdp("");
		}

		return compte;
	}
}
