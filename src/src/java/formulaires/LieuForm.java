package formulaires;

import javax.servlet.http.HttpServletRequest;
import modele.Lieu;

/**
 *
 * @author slam
 */
public class LieuForm extends Form{
		public Lieu getLieu(HttpServletRequest request) {
		Lieu unLieu = new Lieu();

		String ville = getDataForm(request, "ville");
		int nbBoxes = Integer.valueOf(getDataForm(request, "nbBoxes"));
		String commentaire = getDataForm(request, "commentaire");

	
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
