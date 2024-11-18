package com.example.gestionclinique.model;


import java.sql.Date;

public class Medecin {
    private long idMedecin;
    private String nom;
    private String prenom;
    private String adresse;
    private Date dateNaissance;
    private int specialiteId;
    private long compteId;


    public Medecin(long idMedecin, String nom, String prenom, String adresse, Date dateNaissance, int specialiteId, long compteId) {
        this.idMedecin = idMedecin;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.dateNaissance = dateNaissance;
        this.specialiteId = specialiteId;
        this.compteId = compteId;
    }

    public long getIdMedecin() {
        return idMedecin;
    }

    public void setIdMedecin(long idMedecin) {
        this.idMedecin = idMedecin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public int getSpecialiteId() {
        return specialiteId;
    }

    public void setSpecialiteId(int specialiteId) {
        this.specialiteId = specialiteId;
    }

    public long getCompteId() {
        return compteId;
    }

    public void setCompteId(long compteId) {
        this.compteId = compteId;
    }
}
