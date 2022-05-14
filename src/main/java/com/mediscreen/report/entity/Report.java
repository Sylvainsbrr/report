package com.mediscreen.report.entity;

public class Report {

    private String firstName;
    private String lastName;
    private String gender;
    private int age;
    private EnumRisk diabetesPatientRiskLevel;

    public Report(String firstName, String lastName, String gender, int age, EnumRisk diabetesPatientRiskLevel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.diabetesPatientRiskLevel = diabetesPatientRiskLevel;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public EnumRisk getDiabetesPatientRiskLevel() {
        return diabetesPatientRiskLevel;
    }

    public void setDiabetesPatientRiskLevel(EnumRisk diabetesPatientRiskLevel) {
        this.diabetesPatientRiskLevel = diabetesPatientRiskLevel;
    }
}
