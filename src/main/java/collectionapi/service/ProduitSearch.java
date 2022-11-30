package collectionapi.service;


import collectionapi.metier.Caracteristique;
import collectionapi.metier.ProduitCaracteristique;
import collectionapi.metier.Referenciel;
import collectionapi.metier.Type;

import java.util.ArrayList;

public class ProduitSearch {

    private int id;

    private String libelle;

    private Type type;

    private Caracteristique caracteristique;

    private ArrayList<ProduitCaracteristique> listproduitCaracteristiques;

    private Referenciel referenciel;


    public ProduitSearch() {
        type = new Type();
        caracteristique = new Caracteristique();
        referenciel = new Referenciel();
        listproduitCaracteristiques = new ArrayList<>();
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Caracteristique getCaracteristique() {
        return caracteristique;
    }

    public void setCaracteristique(Caracteristique caracteristique) {
        this.caracteristique = caracteristique;
    }

    public ArrayList<ProduitCaracteristique> getListproduitCaracteristiques() {
        return listproduitCaracteristiques;
    }

    public void setListproduitCaracteristiques(ArrayList<ProduitCaracteristique> listproduitCaracteristiques) {
        this.listproduitCaracteristiques = listproduitCaracteristiques;
    }

    public Referenciel getReferenciel() {
        return referenciel;
    }

    public void setReferenciel(Referenciel referenciel) {
        this.referenciel = referenciel;
    }
}
