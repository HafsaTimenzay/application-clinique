package com.example.gestionclinique.model;

public class Specialite {
    private int id;
    private String titre;

    public Specialite() {}

    public Specialite(int id, String titre) {
        this.id = id;
        this.titre = titre;
    }

    // Getters and Setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }
}
