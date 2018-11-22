package formulaires;

import javax.servlet.http.HttpServletRequest;
import modele.CategVente;
import modele.Client;
import modele.Pays;

/**
 *
 * @author Zakina
 */
public class ClientForm extends Form {

	//méthode de validation du champ de saisie nom
	private void validationNom(String nom) throws Exception {
		if (nom != null && nom.length() < 3) {
			throw new Exception("Le nom d'utilisateur doit contenir au moins 3 caractères.");
		}
	}

	public Client getClient(HttpServletRequest request) {
		Client unClient = new Client();

		String id = getDataForm(request, "idOrigin");

		String nom = getDataForm(request, "nom");
		String prenom = getDataForm(request, "prenom");
		String rue = getDataForm(request, "rue");
		String copos = getDataForm(request, "copos");
		String ville = getDataForm(request, "ville");
		String pays = getDataForm(request, "pays");
		String mail = getDataForm(request, "mail");

		// Traitement de la liste à choix multiples
		//Pour chaque catégorie selectionnée, on instancie une nouvelle catégorie et on l'ajoute au client
		CategVente uneCategVente;
		String[] categVente = request.getParameterValues("categVente");
		for (int i = 0; i < categVente.length; i++) {
			uneCategVente = new CategVente();
			uneCategVente.setCode(categVente[i]);
			unClient.addUneCategVente(uneCategVente);
		}

		try {
			validationNom(nom);
		} catch (Exception e) {
			//setErreur("nom", e.getMessage());
		}
		unClient.setNom(nom);

		if (this.getErreurs().isEmpty()) {
			this.setResultat("Succès de l'ajout.");
		} else {
			this.setResultat("Échec de l'ajout.");
		}

		if (id != null) {
			int idClient = Integer.parseInt(id);
			unClient.setId(idClient);
		}

		unClient.setPrenom(prenom);
		unClient.setRue(rue);
		unClient.setCopos(copos);
		unClient.setVille(ville);
		unClient.setPays(new Pays(pays));
		unClient.setMail(mail);

		return unClient;
	}

}
