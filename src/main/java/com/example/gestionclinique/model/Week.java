package com.example.gestionclinique.model;

public class Week {
    private int id;
    private String weekRange;

    // Constructor
    public Week(String weekRange) {
        this.weekRange = weekRange;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeekRange() {
        return weekRange;
    }

    public void setWeekRange(String weekRange) {
        this.weekRange = weekRange;
    }

    @Override
    public String toString() {
        return weekRange;
    }
}
