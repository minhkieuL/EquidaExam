package modele;

import java.util.ArrayList;

/**
 *
 * @author JustineM
 */
public class Lieu {

	private int id;
	private String ville;
	private int nbBoxes;
	private String commentaire;
	private ArrayList<Vente> listeVente;

	public Lieu() {
		this(0, "", 0, "", null);
	}

	public Lieu(int id, String ville, int nbBoxes, String commentaire, ArrayList<Vente> listeVente) {
		this.id = id;
		this.ville = ville;
		this.nbBoxes = nbBoxes;
		this.commentaire = commentaire;
		this.listeVente = listeVente;

		if (this.listeVente == null) {
			this.listeVente = new ArrayList<>();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public int getNbBoxes() {
		return nbBoxes;
	}

	public void setNbBoxes(int nbBoxes) {
		this.nbBoxes = nbBoxes;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public ArrayList<Vente> getVente() {
		return listeVente;
	}

	public void setVente(ArrayList<Vente> listeVente) {
		this.listeVente = listeVente;
	}

	public void addVente(Vente vente) {
		listeVente.add(vente);
	}
}
