package com.example.gestionclinique.model;

public class RendezVous {
    private String date;
    private String specialite;
    private String doctor;
    private String diagnosis;

    // Constructeur
    public RendezVous(String date, String specialite, String doctor, String diagnosis) {
        this.date = date;
        this.specialite = specialite;
        this.doctor = doctor;
        this.diagnosis = diagnosis;
    }

    // Getters
    public String getDate() {
        return date;
    }

    public String getSpecialite() {
        return specialite;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getDiagnosis() {
        return diagnosis;
    }
}
