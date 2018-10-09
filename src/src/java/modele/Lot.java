package modele;

public class Lot {
    private int id;
    private float prixDepart;
    private Vente vente;
    private Cheval cheval; 

    public Lot() {
    }

    public Lot(int id, float prixDepart, Vente vente, Cheval cheval) {
        this.id = id;
        this.prixDepart = prixDepart;
        this.vente = vente;
        this.cheval = cheval;
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
    
    
}
