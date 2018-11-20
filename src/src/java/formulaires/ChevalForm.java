/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
		
		String idChevalStr = getDataForm(request, "id");
		String nom = getDataForm(request, "nom");
		boolean male = Integer.valueOf(getDataForm(request, "sexe")) == 1;
		String sire = getDataForm(request, "sire");
		int idTypeCheval = Integer.valueOf(getDataForm(request, "typeCheval"));
		String pereSire = getDataForm(request, "pere");
		String mereSire = getDataForm(request, "mere");

		// Traitement de la liste à choix multiple
		//Pour chq catégorie selectionné, on instancie une nouvelle catégorie et on l'ajoute au client
		/* Lot unLot ;
        String[] lot = request.getParameterValues("lot");
        
        for (int i=0; i<lot.length; i++){
            unLot = new Lot();
            unLot.setId(Integer.valueOf(lot[i]));
            unCheval.addLot(unLot);
        }*/
		try {
			validationNom(nom);
		} catch (Exception e) {
			setErreur("nom", e.getMessage());
		}
		if (this.getErreurs().isEmpty()) {
			this.setResultat("Succès de l'ajout.");
		} else {
			this.setResultat("Échec de l'ajout.");
		}
		
		if(idChevalStr != null)
			unCheval.setId(Integer.valueOf(idChevalStr));

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
