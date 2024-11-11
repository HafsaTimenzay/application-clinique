package com.example.gestionclinique.model;

import java.util.Date;

public class RendezVous {
    private int id;
    private Date date;
    private String type;
    private Long patientId;

    public RendezVous() {}

    public RendezVous(int id, Date date, String type, Long patientId) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.patientId = patientId;
    }

    // Getters and Setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }
}
