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

	public Client getClient(HttpServletRequest request) {
		Client unClient = new Client();

		String id = getDataForm(request, "idOrigin");

		String nomChampNom = "nom";
		String nomChampPrenom = "prenom";
		String nomChampRue = "rue";
		String nomChampCopos = "copos";
		String nomChampVille = "ville";
		String nomChampPays = "pays";
		String nomChampMail = "mail";
		String nomChampCategVente = "categVente";

		String nom = getDataForm(request, nomChampNom);
		String prenom = getDataForm(request, nomChampPrenom);
		String rue = getDataForm(request, nomChampRue);
		String copos = getDataForm(request, nomChampCopos);
		String ville = getDataForm(request, nomChampVille);
		String pays = getDataForm(request, nomChampPays);
		String mail = getDataForm(request, nomChampMail);

		// Traitement de la liste à choix multiples
		//Pour chaque catégorie selectionnée, on instancie une nouvelle catégorie et on l'ajoute au client
		CategVente uneCategVente;
		String[] categVente = request.getParameterValues(nomChampCategVente);

		if (categVente == null) {
			ajouterErreur(nomChampCategVente, "Le champ categorie vente est obligatoire.");
		} else {
			for (int i = 0; i < categVente.length; i++) {
				uneCategVente = new CategVente();
				uneCategVente.setCode(categVente[i]);
				unClient.addUneCategVente(uneCategVente);
			}
		}

		if (nom == null) {
			ajouterErreur(nomChampNom, "Le champ nom est obligatoire.");
		} else {
			if (nom.length() < 3 || nom.length() > 40) {
				ajouterErreur(nomChampNom, "La longueur du nom doit être comprise entre 3 et 40 caractères.");
			}
		}

		if (prenom == null) {
			ajouterErreur(nomChampPrenom, "Le champ prénom est obligatoire.");
		} else {
			if (prenom.length() < 3 || prenom.length() > 40) {
				ajouterErreur(nomChampPrenom, "La longueur du prénom doit être comprise entre 3 et 40 caractères.");
			}
		}

		if (rue == null) {
			ajouterErreur(nomChampRue, "Le champ rue est obligatoire.");
		} else {
			if (rue.length() < 3 || rue.length() > 60) {
				ajouterErreur(nomChampRue, "La longueur de la rue doit être comprise entre 3 et 60 charactères.");
			}
		}

		if (copos == null) {
			ajouterErreur(nomChampCopos, "Le champ code postal est obligatoire.");
		} else {
			if (copos.length() > 5) {
				ajouterErreur(nomChampCopos, "La longueur du code postal ne peut excéder 5 caractères.");
			}
		}

		if (ville == null) {
			ajouterErreur(nomChampVille, "Le champ ville est obligatoire.");
		} else {
			if (ville.length() < 3 || ville.length() > 40) {
				ajouterErreur(nomChampVille, "La longueur de la ville doit être comprise entre 3 et 40 caractères.");
			}
		}

		if (id != null) {
			int idClient = Integer.parseInt(id);
			unClient.setId(idClient);
		}

		unClient.setNom(nom);
		unClient.setPrenom(prenom);
		unClient.setRue(rue);
		unClient.setCopos(copos);
		unClient.setVille(ville);
		unClient.setPays(new Pays(pays));
		unClient.setMail(mail);

		return unClient;
	}

}
