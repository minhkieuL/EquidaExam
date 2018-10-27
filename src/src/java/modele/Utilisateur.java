package modele;

import java.util.ArrayList;

public abstract class Utilisateur {

	private int id;
	private String nom;
	private String prenom;
	private String rue;
	private String copos;
	private String ville;
	private String mail;
	private boolean archiver;
	private Pays pays;
	private Compte compte;
	//TODO Implémenter une véritable gestion des roles
	protected ArrayList<Integer> autorisations;

	public Utilisateur() {
		this(0, "", "", "", "", "", "", false, null, null);
	}

	public Utilisateur(int id, String nom, String prenom, String rue, String copos, String ville, String mail, boolean archiver, Pays pays, Compte compte) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.rue = rue;
		this.copos = copos;
		this.ville = ville;
		this.mail = mail;
		this.archiver = archiver;
		this.pays = pays;
		this.compte = compte;
		//TODO Implémenter une véritable gestion des roles
		this.autorisations = new ArrayList<>();
	}

	public boolean estAutoriseA(int autorisation) {
		//TODO Implémenter une véritable gestion des roles
		return this.autorisations.contains(autorisation);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCopos() {
		return copos;
	}

	public void setCopos(String copos) {
		this.copos = copos;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public boolean isArchiver() {
		return archiver;
	}

	public void setArchiver(boolean archiver) {
		this.archiver = archiver;
	}

	public Pays getPays() {
		return pays;
	}

	public void setPays(Pays pays) {
		this.pays = pays;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}
}
