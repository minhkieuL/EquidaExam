package formulaires;

import javax.servlet.http.HttpServletRequest;
import modele.Lieu;

/**
 *
 * @author slam
 */
public class LieuForm extends Form {

	public Lieu getLieu(HttpServletRequest request) {
		Lieu unLieu = new Lieu();

		String nomChampVille = "ville";
		String nomChampNbBoxes = "nbBoxes";
		String nomChampCommentaire = "commentaire";

		String ville = getDataForm(request, nomChampVille);
		String nbBoxesStr = getDataForm(request, nomChampNbBoxes);
		int nbBoxes = 0;
		String commentaire = getDataForm(request, nomChampCommentaire);

		if (ville == null) {
			ajouterErreur(nomChampVille, "Le champ ville est obligatoire");
		} else {
			if (ville.length() < 3 || ville.length() > 50) {
				ajouterErreur(nomChampVille, "La longueur de la ville doit être compris entre 3 et 50 charactères");
			}
		}

		if (nbBoxesStr == null) {
			ajouterErreur(nomChampNbBoxes, "Le champ nombre de boxes est obligatoire");
		} else {
			try {
				nbBoxes = Integer.valueOf(nbBoxesStr);
			} catch (NumberFormatException e) {
				ajouterErreur(nomChampNbBoxes, "Le nombre de boxes doit être un nombre entier");
			}
		}
		
		if (commentaire == null) {
			ajouterErreur(nomChampCommentaire, "Le champ commentaire est obligatoire");
		} else {
			if (commentaire.length() < 3 || commentaire.length() > 255) {
				ajouterErreur(nomChampCommentaire, "La longueur du commentaire doit être compris entre 3 et 255 charactères");
			}
		}

		unLieu.setVille(ville);
		unLieu.setNbBoxes(nbBoxes);
		unLieu.setCommentaire(commentaire);

		return unLieu;
	}

	public int getLieuOrigin(HttpServletRequest request) {
		int idLieu = Integer.valueOf(getDataForm(request, "codeOrigin"));
		return idLieu;
	}

}
