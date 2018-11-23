package formulaires;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author MartinJ
 */
public abstract class Form {

	private String resultat;
	private Map<String, ArrayList<String>> erreurs = new HashMap<String, ArrayList<String>>();

	public String getResultat() {
		return resultat;
	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
	}

	public Map<String, ArrayList<String>> getErreurs() {
		return erreurs;
	}

	public void setErreurs(Map<String, ArrayList<String>> erreurs) {
		this.erreurs = erreurs;
	}

	public void ajouterErreur(String champ, String message) {
		if(!erreurs.containsKey(champ)) {
			erreurs.put(champ, new ArrayList<String>());
		}
		
		erreurs.get(champ).add(message);
	}
	
	protected ArrayList<String> getErreurChamp(String champ) {
		return erreurs.get(champ);
	}

	protected static String getDataForm(HttpServletRequest request, String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur.trim();
		}
	}
	
}
