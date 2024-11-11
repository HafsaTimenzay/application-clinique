package com.example.gestionclinique.model;

import java.util.Date;

public class Consultation {
    private int idConsultation;
    private Date consultationDate;
    private Long medecinId;
    private Long patientId;
    private String symptoms;
    private String diagnosis;
    private String notes;

    public Consultation() {}

    public Consultation(int idConsultation, Date consultationDate, Long medecinId, Long patientId, String symptoms, String diagnosis, String notes) {
        this.idConsultation = idConsultation;
        this.consultationDate = consultationDate;
        this.medecinId = medecinId;
        this.patientId = patientId;
        this.symptoms = symptoms;
        this.diagnosis = diagnosis;
        this.notes = notes;
    }

    // Getters and Setters

    public int getIdConsultation() { return idConsultation; }
    public void setIdConsultation(int idConsultation) { this.idConsultation = idConsultation; }

    public Date getConsultationDate() { return consultationDate; }
    public void setConsultationDate(Date consultationDate) { this.consultationDate = consultationDate; }

    public Long getMedecinId() { return medecinId; }
    public void setMedecinId(Long medecinId) { this.medecinId = medecinId; }

    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }

    public String getSymptoms() { return symptoms; }
    public void setSymptoms(String symptoms) { this.symptoms = symptoms; }

    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
