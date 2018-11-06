package modele;

import java.util.ArrayList;

public class TypeCheval {
    private int id;
    private String libelle;
    private String desc;
    private ArrayList<Cheval> chevaux;

    public TypeCheval() {
        this(0, "", "", null);
    }

    public TypeCheval(int id, String libelle, String desc, ArrayList<Cheval> chevaux) {
        this.id = id;
        this.libelle = libelle;
        this.desc = desc;
        this.chevaux = chevaux;
        
        if(this.chevaux == null)
            this.chevaux = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ArrayList<Cheval> getChevaux() {
        return chevaux;
    }

    public void setChevaux(ArrayList<Cheval> chevaux) {
        this.chevaux = chevaux;
    }
    
    public void addCheval(Cheval cheval) {
        this.chevaux.add(cheval);
    }
}
