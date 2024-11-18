package com.example.gestionclinique.model;

import java.time.LocalDate;

public class Week {
    private int id_week;
    private LocalDate startDate;
    private LocalDate endDate;

    // Constructeur
    public Week(int id_week, LocalDate startDate, LocalDate endDate) {
        this.id_week = id_week;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters et Setters
    public int getId_week() {
        return id_week;
    }

    public void setId_week(int id_week) {
        this.id_week = id_week;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}

