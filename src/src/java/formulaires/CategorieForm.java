/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

		String code = getDataForm(request, "code");
		String libelle = getDataForm(request, "libelle");

		uneCategVente.setCode(code);
		uneCategVente.setLibelle(libelle);

		return uneCategVente;
	}

    public String getCategVenteOrigin(HttpServletRequest request) {
        return getDataForm(request, "codeOrigin");
    }
}
