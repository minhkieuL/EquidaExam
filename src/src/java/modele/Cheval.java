package modele;

import java.util.ArrayList;

public class Cheval {
    private int id;
    private String nom;
    private boolean sexe;
    private String sire;
    private TypeCheval cheval;
    private ArrayList<Lot> lots;

    public Cheval() {
        this(0, "", false, "", null, null);
    }
    
    public Cheval(int id, String nom, boolean sexe, String sire, TypeCheval cheval, ArrayList<Lot> lots) {
        this.id = id;
        this.nom = nom;
        this.sexe = sexe;
        this.sire = sire;
        this.cheval = cheval;
        this.lots = lots;
        
        if(this.lots == null)
            this.lots = new ArrayList<>();
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

    public TypeCheval getCheval() {
        return cheval;
    }

    public void setCheval(TypeCheval cheval) {
        this.cheval = cheval;
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

    public boolean estMale() {
        return this.sexe;
    }
    
    public void setSexe(boolean sexe) {
        this.sexe = sexe;
    }
}
