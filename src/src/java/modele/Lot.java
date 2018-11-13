package modele;

import java.util.ArrayList;

public class Lot {

	private int id;
	private float prixDepart;
	private String validation;
	private Vente vente;
	private Cheval cheval;
	private ArrayList<Enchere> encheres;

	public Lot() {
		this(0, 0, "", null, null, null);
	}

	public Lot(int id, float prixDepart, String validation, Vente vente, Cheval cheval, ArrayList<Enchere> encheres) {
		this.id = id;
		this.prixDepart = prixDepart;
		this.validation = validation;
		this.vente = vente;
		this.cheval = cheval;
		this.encheres = encheres;

		if (this.encheres == null) {
			this.encheres = new ArrayList<>();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getPrixDepart() {
		return prixDepart;
	}

	public void setPrixDepart(float prixDepart) {
		this.prixDepart = prixDepart;
	}

	public String getValidation() {
		return validation;
	}

	public void setValidation(String validation) {
		this.validation = validation;
	}

	public Vente getVente() {
		return vente;
	}

	public void setVente(Vente vente) {
		this.vente = vente;
	}

	public Cheval getCheval() {
		return cheval;
	}

	public void setCheval(Cheval cheval) {
		this.cheval = cheval;
	}

	public ArrayList<Enchere> getEncheres() {
		return encheres;
	}

	public void setEncheres(ArrayList<Enchere> encheres) {
		this.encheres = encheres;
	}

	public void addEnchere(Enchere enchere) {
		this.encheres.add(enchere);
	}
}
