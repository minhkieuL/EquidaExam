package modele;

public class Compte {

	private int id;
	private String login;
	private String mdp;
	private Utilisateur utilisateur;
	private Role role;

	public Compte() {
		this(0, "", "", null, null);
	}

	public Compte(int id, String login, String mdp, Utilisateur utilisateur, Role role) {
		this.id = id;
		this.login = login;
		this.mdp = mdp;
		this.utilisateur = utilisateur;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
