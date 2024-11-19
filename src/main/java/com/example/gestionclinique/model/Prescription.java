package com.example.gestionclinique.model;

public class Prescription {
    private int id_prescription;
    private int consultation_id;
    private String medication;
    private String dosage;
    private String duration;
    private String instructions;

    // Constructeur
    public Prescription(int id_prescription, int consultation_id, String medication, String dosage, String duration, String instructions) {
        this.id_prescription = id_prescription;
        this.consultation_id = consultation_id;
        this.medication = medication;
        this.dosage = dosage;
        this.duration = duration;
        this.instructions = instructions;
    }

    // Getters et Setters
    public int getId_prescription() {
        return id_prescription;
    }

    public void setId_prescription(int id_prescription) {
        this.id_prescription = id_prescription;
    }

    public int getConsultation_id() {
        return consultation_id;
    }

    public void setConsultation_id(int consultation_id) {
        this.consultation_id = consultation_id;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
