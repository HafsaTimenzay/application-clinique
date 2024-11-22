package com.example.gestionclinique.model;

public class Specialite {
    private int id;
    private String titre;

    // Constructeur
    public Specialite(int id, String titre) {
        this.id = id;
        this.titre = titre;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    @Override
    public String toString() {
        return titre;  // Display the name in the ComboBox
    }
}
