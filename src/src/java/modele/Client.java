package modele;

import database.Autorisations;
import java.util.ArrayList;

public class Client extends Utilisateur {

	private ArrayList<CategVente> lesCategVentes;

	public Client() {
		this(null);
	}

	public Client(ArrayList<CategVente> lesCategVentes) {
		this(null, 0, "", "", "", "", "", "", false, null, null);
	}

	public Client(ArrayList<CategVente> lesCategVentes, int id, String nom, String prenom, String rue, String copos, String ville, String mail, boolean archiver, Pays pays, Compte compte) {
		super(id, nom, prenom, rue, copos, ville, mail, archiver, pays, compte);

		//TODO Implémenter une véritable gestion des roles
		this.lesCategVentes = lesCategVentes;
		this.autorisations.add(Autorisations.CLIENT_AJOUTER);
		this.autorisations.add(Autorisations.CLIENT_CONSULTER);
		this.autorisations.add(Autorisations.CLIENT_MODIFIER);
		this.autorisations.add(Autorisations.CLIENT_SUPPRIMER);
	}

	public ArrayList<CategVente> getLesCategVentes() {
		return lesCategVentes;
	}

	public void setLesCategVentes(ArrayList<CategVente> lesCategVentes) {
		this.lesCategVentes = lesCategVentes;
	}

	public void addUneCategVente(CategVente uneCategVente) {
		if (lesCategVentes == null) {
			lesCategVentes = new ArrayList<CategVente>();
		}
		lesCategVentes.add(uneCategVente);
	}
}
