package modele;

import java.util.ArrayList;

public class Vente {

	private int id;
	private String nom;
	private String dateDebut;
	private String dateFin;
	private String dateVente;
	private CategVente uneCategVente;
	private Lieu lieu;
	private ArrayList<Courriel> listeCourriels;
	private ArrayList<Lot> lots;

	public Vente() {
		this(0, "", "", "", "", null, null, null, null);
	}

	public Vente(int id, String nom, String dateDebut, String dateFin, String dateVente, CategVente catVente, Lieu lieu, ArrayList<Courriel> listeCourriels, ArrayList<Lot> lots) {
		this.id = id;
		this.nom = nom;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.dateVente = dateVente;
		this.uneCategVente = catVente;
		this.lieu = lieu;
		this.listeCourriels = listeCourriels;
		this.lots = lots;

		if (this.listeCourriels == null) {
			this.listeCourriels = new ArrayList<>();
		}

		if (this.lots == null) {
			this.lots = new ArrayList<>();
		}
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

	public String getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	public String getDateVente() {
		return dateVente;
	}

	public void setDateVente(String dateVente) {
		this.dateVente = dateVente;
	}

	public CategVente getUneCategVente() {
		return uneCategVente;
	}

	public void setUneCategVente(CategVente uneCategVente) {
		this.uneCategVente = uneCategVente;
	}

	public Lieu getLieu() {
		return lieu;
	}

	public void setLieu(Lieu lieu) {
		this.lieu = lieu;
	}

	public ArrayList<Courriel> getListeCourriels() {
		return listeCourriels;
	}

	public void setListeCourriels(ArrayList<Courriel> listeCourriels) {
		this.listeCourriels = listeCourriels;
	}

	public void addCourriel(Courriel courriel) {
		listeCourriels.add(courriel);
	}

	public ArrayList<Lot> getLots() {
		return lots;
	}

	public void setLots(ArrayList<Lot> lots) {
		this.lots = lots;
	}

	public void addLot(Lot lot) {
		this.lots.add(lot);
	}
}
