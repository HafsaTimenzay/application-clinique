package com.example.gestionclinique.model;

import java.sql.Time;

public class Disponibilite {
    private int idDisponibilite;
    private long medecinId;
    private String jour;
    private Time hourDebut;
    private Time hourFin;

    // Constructeurs, getters et setters

    public Disponibilite(int idDisponibilite, long medecinId, String jour, Time hourDebut, Time hourFin) {
        this.idDisponibilite = idDisponibilite;
        this.medecinId = medecinId;
        this.jour = jour;
        this.hourDebut = hourDebut;
        this.hourFin = hourFin;
    }

    public int getIdDisponibilite() {
        return idDisponibilite;
    }

    public void setIdDisponibilite(int idDisponibilite) {
        this.idDisponibilite = idDisponibilite;
    }

    public long getMedecinId() {
        return medecinId;
    }

    public void setMedecinId(long medecinId) {
        this.medecinId = medecinId;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public Time getHourDebut() {
        return hourDebut;
    }

    public void setHourDebut(Time hourDebut) {
        this.hourDebut = hourDebut;
    }

    public Time getHourFin() {
        return hourFin;
    }

    public void setHourFin(Time hourFin) {
        this.hourFin = hourFin;
    }
}
