package modele;

import java.util.ArrayList;

public class Course {

	private int id;
	private String nom;
	private String date;
	private String ville;
	private ArrayList<Participer> participation;

	public Course() {
		this(0, "", "", "", null);
	}

	public Course(int id, String nom, String date, String ville, ArrayList<Participer> participation) {
		this.id = id;
		this.nom = nom;
		this.date = date;
		this.ville = ville;

		this.participation = participation;
		if (this.participation == null) {
			this.participation = new ArrayList<>();
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public ArrayList<Participer> getParticipation() {
		return participation;
	}

	public void setParticipation(ArrayList<Participer> participation) {
		this.participation = participation;
	}

	public void addParticipation(Participer participation) {
		this.participation.add(participation);
	}
}
