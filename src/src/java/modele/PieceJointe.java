package modele;

import java.util.ArrayList;

/**
 *
 * @author MartinJ
 */
public class PieceJointe {

	private int id;
	private String chemin;
	private String description;
	private ArrayList<Courriel> listeCourriels;

	public PieceJointe() {
		this(0, "", "", null);
	}

	public PieceJointe(int id, String chemin, String description, ArrayList<Courriel> listeCourriels) {
		this.id = id;
		this.chemin = chemin;
		this.description = description;
		this.listeCourriels = listeCourriels;

		if (this.listeCourriels == null) {
			this.listeCourriels = new ArrayList<>();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getChemin() {
		return chemin;
	}

	public void setChemin(String chemin) {
		this.chemin = chemin;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<Courriel> getListeCourriels() {
		return listeCourriels;
	}

	public void setListeCourriels(ArrayList<Courriel> listeCourriels) {
		this.listeCourriels = listeCourriels;
	}

	public void addPieceJointe(Courriel courriel) {
		listeCourriels.add(courriel);
	}
}
