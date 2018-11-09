/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

		String code = getDataForm(request, "code");
		String nom = getDataForm(request, "nom");

		unPays.setCode(code);
		unPays.setNom(nom);

		return unPays;
	}
	
	public String getPaysOrigin(HttpServletRequest request) {
        return getDataForm(request, "codeOrigin");
    }
}
