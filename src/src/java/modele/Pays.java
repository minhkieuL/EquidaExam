package modele;

import java.util.ArrayList;

/**
 *
 * @author Zakina
 */
public class Pays {

	private String code;
	private String nom;
	private ArrayList<Utilisateur> utilisateurs;

	public Pays() {
		this("");
	}

	public Pays(String code) {
		this(code, "", null);
	}

	public Pays(String code, String nom, ArrayList<Utilisateur> utilisateurs) {
		this.code = code;
		this.nom = nom;
		this.utilisateurs = utilisateurs;

		if (this.utilisateurs == null) {
			this.utilisateurs = new ArrayList<>();
		}
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public ArrayList<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}

	public void setUtilisateurs(ArrayList<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

	public void addUtilisateur(Utilisateur utilisateur) {
		this.utilisateurs.add(utilisateur);
	}
}
