package com.example.gestionclinique.model;

public class Disponibilite {
    private int idDisponibilite;
    private int hour;
    private String jour;
    private Long medecinId;

    public Disponibilite() {}

    public Disponibilite(int idDisponibilite, int hour, String jour, Long medecinId) {
        this.idDisponibilite = idDisponibilite;
        this.hour = hour;
        this.jour = jour;
        this.medecinId = medecinId;
    }

    // Getters and Setters

    public int getIdDisponibilite() { return idDisponibilite; }
    public void setIdDisponibilite(int idDisponibilite) { this.idDisponibilite = idDisponibilite; }

    public int getHour() { return hour; }
    public void setHour(int hour) { this.hour = hour; }

    public String getJour() { return jour; }
    public void setJour(String jour) { this.jour = jour; }

    public Long getMedecinId() { return medecinId; }
    public void setMedecinId(Long medecinId) { this.medecinId = medecinId; }
}
