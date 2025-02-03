package com.evernorth.ecalender.service;

import java.util.List;

public class AttendanceOverview {
    private List<String> presentEmployees;
    private List<String> absentEmployees;

    public AttendanceOverview(List<String> presentEmployees, List<String> absentEmployees) {
        this.presentEmployees = presentEmployees;
        this.absentEmployees = absentEmployees;
    }

    // Getters and setters
    public List<String> getPresentEmployees() {
        return presentEmployees;
    }

    public void setPresentEmployees(List<String> presentEmployees) {
        this.presentEmployees = presentEmployees;
    }

    public List<String> getAbsentEmployees() {
        return absentEmployees;
    }

    public void setAbsentEmployees(List<String> absentEmployees) {
        this.absentEmployees = absentEmployees;
    }
}
