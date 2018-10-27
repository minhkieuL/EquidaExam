package modele;

import java.util.ArrayList;

/**
 *
 * @author MartinJ
 */
public class Courriel {

	private int id;
	private String date;
	private String objet;
	private String corps;
	private Vente vente;
	private ArrayList<PieceJointe> listePiecesJointes;

	public Courriel() {
		this(0, "", "", "", null, null);
	}

	public Courriel(int id, String date, String objet, String corps, Vente vente, ArrayList<PieceJointe> listePiecesJointes) {
		this.id = id;
		this.date = date;
		this.objet = objet;
		this.corps = corps;
		this.vente = vente;
		this.listePiecesJointes = listePiecesJointes;

		if (this.listePiecesJointes == null) {
			this.listePiecesJointes = new ArrayList<>();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getObjet() {
		return objet;
	}

	public void setObjet(String objet) {
		this.objet = objet;
	}

	public String getCorps() {
		return corps;
	}

	public void setCorps(String corps) {
		this.corps = corps;
	}

	public Vente getVente() {
		return vente;
	}

	public void setVente(Vente vente) {
		this.vente = vente;
	}

	public ArrayList<PieceJointe> getPiecesJointes() {
		return listePiecesJointes;
	}

	public void setPiecesJointes(ArrayList<PieceJointe> piecesJointes) {
		this.listePiecesJointes = piecesJointes;
	}

	public void addPieceJointe(PieceJointe pieceJointe) {
		this.listePiecesJointes.add(pieceJointe);
	}
}
