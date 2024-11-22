package com.example.gestionclinique.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RendezVous {
    private StringProperty date;
    private StringProperty specialite;
    private StringProperty doctor;
    private StringProperty diagnosis;

    // Constructor
    public RendezVous(String date, String specialite, String doctor, String diagnosis) {
        this.date = new SimpleStringProperty(date);
        this.specialite = new SimpleStringProperty(specialite);
        this.doctor = new SimpleStringProperty(doctor);
        this.diagnosis = new SimpleStringProperty(diagnosis);
    }

    // Getters and setters
    public String getDate() { return date.get(); }
    public void setDate(String date) { this.date.set(date); }
    public StringProperty dateProperty() { return date; }

    public String getSpecialite() { return specialite.get(); }
    public void setSpecialite(String specialite) { this.specialite.set(specialite); }
    public StringProperty specialiteProperty() { return specialite; }

    public String getDoctor() { return doctor.get(); }
    public void setDoctor(String doctor) { this.doctor.set(doctor); }
    public StringProperty doctorProperty() { return doctor; }

    public String getDiagnosis() { return diagnosis.get(); }
    public void setDiagnosis(String diagnosis) { this.diagnosis.set(diagnosis); }
    public StringProperty diagnosisProperty() { return diagnosis; }

}




//public class RendezVous {
//    private int id;
//    private String type;
//    private long medecinId;
//    private int patientId;
//    private Date dateRendezVous;
//    private Time horaire;
//
//
//    public RendezVous() {
//
//    }
//
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public long getMedecinId() {
//        return medecinId;
//    }
//
//    public void setMedecinId(long medecinId) {
//        this.medecinId = medecinId;
//    }
//
//    public int getPatientId() {
//        return patientId;
//    }
//
//    public void setPatientId(int patientId) {
//        this.patientId = patientId;
//    }
//
//    public Date getDateRendezVous() {
//        return dateRendezVous;
//    }
//
//    public void setDateRendezVous(Date dateRendezVous) {
//        this.dateRendezVous = dateRendezVous;
//    }
//
//    public Time getHoraire() {
//        return horaire;
//    }
//
//    public void setHoraire(Time horaire) {
//        this.horaire = horaire;
//    }
//}
//
