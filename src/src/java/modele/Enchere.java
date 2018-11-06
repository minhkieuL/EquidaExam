package modele;

public class Enchere {

	private int id;
	private float montant;
	private Client client;
	private Lot lot;

	public Enchere() {
		this(0, 0, null, null);
	}

	public Enchere(int id, float montant, Client client, Lot lot) {
		this.id = id;
		this.montant = montant;
		this.client = client;
		this.lot = lot;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getMontant() {
		return montant;
	}

	public void setMontant(float montant) {
		this.montant = montant;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Lot getLot() {
		return lot;
	}

	public void setLot(Lot lot) {
		this.lot = lot;
	}
}
