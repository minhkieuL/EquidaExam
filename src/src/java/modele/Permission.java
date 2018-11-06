package modele;

import java.util.ArrayList;

public class Permission {

	private int id;
	private String libelle;
	private ArrayList<Role> roles;

	public Permission() {
		this(0, "", null);
	}

	public Permission(int id, String libelle, ArrayList<Role> roles) {
		this.id = id;
		this.libelle = libelle;
		this.roles = roles;

		if (this.roles == null) {
			this.roles = new ArrayList<>();
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

	public ArrayList<Role> getRoles() {
		return roles;
	}

	public void setRoles(ArrayList<Role> roles) {
		this.roles = roles;
	}

	public void addRole(Role role) {
		this.roles.add(role);
	}
}
