package modele;

import database.Autorisations;

public class DirecteurGeneral extends Utilisateur {

	public DirecteurGeneral() {
		super();

		//TODO Implémenter une véritable gestion des roles
		this.autorisations.add(Autorisations.DIRECTEUR_AJOUTER);
		this.autorisations.add(Autorisations.DIRECTEUR_CONSULTER);
		this.autorisations.add(Autorisations.DIRECTEUR_MODIFIER);
		this.autorisations.add(Autorisations.DIRECTEUR_SUPPRIMER);
	}

}
