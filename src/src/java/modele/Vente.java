/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;

/**
 *
 * @author Zakina
 */
public class Vente {
    private int id;
    private String nom;
    private String dateDebutVente;
    private CategVente uneCategVente;
    private Lieu lieu;
    private ArrayList<Courriel> listeCourriels;

    public Vente() {
        this(0, "", "");
    }

    public Vente(int id, String nom, String dateDebutVente) {
        this(0, "", "", null, null, null); 
    }
    
    public Vente(int id, String nom, String dateDebutVente, CategVente catVente, Lieu lieu, ArrayList<Courriel> listeCourriels) {
        this.id = id;
        this.nom = nom;
        this.dateDebutVente = dateDebutVente;
        this.uneCategVente = catVente;
        this.lieu = lieu;
        this.listeCourriels = listeCourriels;
        
        if(this.listeCourriels == null)
            this.listeCourriels = new ArrayList<>();
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

    public String getDateDebutVente() {
        return dateDebutVente;
    }

    public void setDateDebutVente(String dateDebutVente) {
        this.dateDebutVente = dateDebutVente;
    }

    public CategVente getUneCategVente() {
        return uneCategVente;
    }

    public void setUneCategVente(CategVente uneCategVente) {
        this.uneCategVente = uneCategVente;
    }

    public Lieu getLieu() {
        return lieu;
    }

    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }

    public ArrayList<Courriel> getListeCourriels() {
        return listeCourriels;
    }

    public void setListeCourriels(ArrayList<Courriel> listeCourriels) {
        this.listeCourriels = listeCourriels;
    }

    public void addCourriel(Courriel courriel) {
        listeCourriels.add(courriel);
    }
}
