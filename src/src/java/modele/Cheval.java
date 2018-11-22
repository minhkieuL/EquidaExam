package modele;

import java.util.ArrayList;

public class Cheval {

	private int id;
	private String nom;
	private boolean male;
	private String sire;
	private TypeCheval typeCheval;
	private ArrayList<Lot> lots;
	private Cheval pere;
	private Cheval mere;
	private Client client;
	
	public Cheval() {
		this(0, "", false, "", null, null, null, null);
	}

	public Cheval(int id, String nom, boolean male, String sire, TypeCheval typeCheval, ArrayList<Lot> lots, Cheval pere, Cheval mere) {
		this.id = id;
		this.nom = nom;
		this.male = male;
		this.sire = sire;
		this.typeCheval = typeCheval;
		this.lots = lots;
		this.pere = pere;
		this.mere = mere;

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

	public String getSire() {
		return sire;
	}

	public void setSire(String sire) {
		this.sire = sire;
	}

	public TypeCheval getTypeCheval() {
		return typeCheval;
	}

	public void setTypeCheval(TypeCheval typeCheval) {
		this.typeCheval = typeCheval;
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

	public boolean getMale() {
		return this.male;
	}

	public void setMale(boolean male) {
		this.male = male;
	}

	public Cheval getPere() {
		return pere;
	}

	public void setPere(Cheval pere) {
		this.pere = pere;
	}

	public Cheval getMere() {
		return mere;
	}

	public void setMere(Cheval mere) {
		this.mere = mere;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
}
