package modele;

import java.util.ArrayList;

public class Role {

	private int id;
	private String libelle;
	private ArrayList<Compte> comptes;
	private ArrayList<Permission> permissions;

	public Role() {
		this(0, "", null, null);
	}

	public Role(int id, String libelle, ArrayList<Compte> comptes, ArrayList<Permission> permissions) {
		this.id = id;
		this.libelle = libelle;
		this.comptes = comptes;
		this.permissions = permissions;

		if (this.comptes == null) {
			this.comptes = new ArrayList<>();
		}

		if (this.permissions == null) {
			this.permissions = new ArrayList<>();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public ArrayList<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(ArrayList<Compte> comptes) {
		this.comptes = comptes;
	}

	public void addCompte(Compte compte) {
		this.comptes.add(compte);
	}

	public ArrayList<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(ArrayList<Permission> permissions) {
		this.permissions = permissions;
	}

	public void addPermission(Permission permission) {
		this.permissions.add(permission);
	}
}
