package formulaires;

import static formulaires.Form.getDataForm;
import javax.servlet.http.HttpServletRequest;
import modele.CategVente;
import modele.Lieu;
import modele.Vente;

/**
 * Document : VenteForm Created on : 12 nov. 2018, 10:48:00 Author :
 * BottonL
 */
public class VenteForm extends Form {

	public Vente getVente(HttpServletRequest request) {
		Vente uneVente = new Vente();

		String nom = getDataForm(request, "nom");
		String dateDebut = getDataForm(request, "dateDebut");
		String dateFin = getDataForm(request, "dateFin");
		String dateVente = getDataForm(request, "dateVente");
		int idLieu = Integer.valueOf(getDataForm(request, "lieu"));
		String codeCategVente = getDataForm(request, "categVente");
		
		uneVente.setNom(nom);
		uneVente.setDateDebut(dateDebut);
		uneVente.setDateFin(dateFin);
		uneVente.setDateVente(dateVente);
		
		Lieu unLieu = new Lieu();
		unLieu.setId(idLieu);
		uneVente.setLieu(unLieu);
		
		CategVente uneCategVente = new CategVente();
		uneCategVente.setCode(codeCategVente);
		uneVente.setUneCategVente(uneCategVente);

		return uneVente;
	}

    public String getIdVenteOrigin(HttpServletRequest request) {
        return getDataForm(request, "codeOrigin");
    }
}
