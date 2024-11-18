package com.example.gestionclinique.model;

import java.sql.Date;
import java.sql.Time;

public class RendezVous {
    private int id;
    private String type;
    private long medecinId;
    private int patientId;
    private Date dateRendezVous;
    private Time horaire;


    public RendezVous(int id, String type, long medecinId, int patientId, Date dateRendezVous, Time horaire) {
        this.id = id;
        this.type = type;
        this.medecinId = medecinId;
        this.patientId = patientId;
        this.dateRendezVous = dateRendezVous;
        this.horaire = horaire;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getMedecinId() {
        return medecinId;
    }

    public void setMedecinId(long medecinId) {
        this.medecinId = medecinId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public Date getDateRendezVous() {
        return dateRendezVous;
    }

    public void setDateRendezVous(Date dateRendezVous) {
        this.dateRendezVous = dateRendezVous;
    }

    public Time getHoraire() {
        return horaire;
    }

    public void setHoraire(Time horaire) {
        this.horaire = horaire;
    }
}

