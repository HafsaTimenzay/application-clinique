package com.example.gestionclinique.model;

import java.sql.Date;
import java.time.LocalDate;

public class Consultation {
    private int idConsultation;
    private String symptoms;
    private String diagnosis;
    private String notes;
    private String date;
    private long medecinId;
    private int patientId;

    public int getIdConsultation() {
        return idConsultation;
    }

    public void setIdConsultation(int idConsultation) {
        this.idConsultation = idConsultation;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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


}
