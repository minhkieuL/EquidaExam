package modele;

import java.util.ArrayList;

public class CategVente {

	private String code;
	private String libelle;
	private ArrayList<Vente> ventes;
	private ArrayList<Client> clients;

	public CategVente() {
		this("", "", null, null);
	}

	public CategVente(String code, String libelle, ArrayList<Vente> ventes, ArrayList<Client> clients) {
		this.code = code;
		this.libelle = libelle;
		this.ventes = ventes;
		this.clients = clients;

		if (this.ventes == null) {
			this.ventes = new ArrayList<>();
		}

		if (this.clients == null) {
			this.clients = new ArrayList<>();
		}
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public ArrayList<Vente> getVentes() {
		return ventes;
	}

	public void setVentes(ArrayList<Vente> ventes) {
		this.ventes = ventes;
	}

	public void addVente(Vente vente) {
		this.ventes.add(vente);
	}

	public ArrayList<Client> getClients() {
		return clients;
	}

	public void setClients(ArrayList<Client> clients) {
		this.clients = clients;
	}

	public void addClient(Client client) {
		this.clients.add(client);
	}
}
