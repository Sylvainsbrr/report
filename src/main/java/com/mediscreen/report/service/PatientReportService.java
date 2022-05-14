package com.mediscreen.report.service;
import com.mediscreen.report.entity.*;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class PatientReportService {
    private EnumRisk diabetesPatientRiskLevel;

    public Report generatePatientDiabetesReport(PatientWithNote patientWithNote){
        Patient patient = patientWithNote.getPatient();
        PatientNote patientNote = patientWithNote.getPatientNote();
        return this.calculPatientDiabetesReport(patient, patientNote);
    }

    public Report calculPatientDiabetesReport(Patient patient, PatientNote patientNote){
        int patientAge = calculPatientAgeFromDateOfBirth(patient.getDateOfBirth());
        int triggerWords = this.numberOfTriggerWord(patientNote);
        Report patientDiabetesReport;
        if(Objects.equals(patient.getGender().toUpperCase(), "HOMME")){
            patientDiabetesReport  = manDiabetesReport(patient, patientAge, triggerWords);
        } else {
            patientDiabetesReport = womenDiabetesReport(patient, patientAge, triggerWords);
        }
        return patientDiabetesReport;
    }

    public int calculPatientAgeFromDateOfBirth(String dateOfBirtth){
        int patientAge;
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateNow = LocalDate.now();
        LocalDate localDateBirthDate = LocalDate.parse(dateOfBirtth, dateFormat);
        patientAge = dateNow.getYear() - localDateBirthDate.getYear();
        return patientAge;
    }

    public int numberOfTriggerWord(PatientNote patientNote){
        List<String> triggerWords = Arrays.asList("Hémoglobine A1C", "Microalbumine", "Taille", "Poids", "Fumeur","Anormal", "Cholestérol", "Vertige", "Rechute", "Réaction", "Anticorps");
        int words= 0;
        String notes = patientNote.getNotes();
        for (String triggerWord : triggerWords) {
            if (notes.toLowerCase().contains(triggerWord.toLowerCase())) {
                words++;
            }
        }
        return words;
    }

    public Report manDiabetesReport(Patient patient, int patientAge, int triggerWords){
        if(triggerWords <= 1 || patientAge < 30 && triggerWords <=2){
            diabetesPatientRiskLevel = EnumRisk.NONE;
        } else if(patientAge >= 30 && triggerWords >= 2 && triggerWords <= 5) {
            diabetesPatientRiskLevel = EnumRisk.BORDERLINE;
        } else if(patientAge < 30 && (triggerWords == 3 || triggerWords == 4)  || patientAge > 30 && (triggerWords == 6 || triggerWords == 7)) {
            diabetesPatientRiskLevel = EnumRisk.IN_DANGER;
        } else if(patientAge < 30 && triggerWords == 5 || patientAge >= 30 && triggerWords >= 8) {
            diabetesPatientRiskLevel = EnumRisk.EARLY_ONSET;
        }
        return new Report(patient.getFirstName(), patient.getLastName(), patient.getGender(), patientAge, diabetesPatientRiskLevel);
    }

    public Report womenDiabetesReport(Patient patient, int patientAge, int triggerWords){
        if(triggerWords <= 1 || patientAge < 30 && triggerWords <=3){
            diabetesPatientRiskLevel = EnumRisk.NONE;
        } else if(patientAge >= 30 && triggerWords >= 2 && triggerWords <= 5) {
            diabetesPatientRiskLevel = EnumRisk.BORDERLINE;
        } else if(patientAge < 30 && (triggerWords >= 4 && triggerWords <= 6)  || patientAge >= 30 && (triggerWords == 6 || triggerWords == 7)) {
            diabetesPatientRiskLevel = EnumRisk.IN_DANGER;
        } else if(patientAge < 30 && triggerWords >= 7 || patientAge >= 30 && triggerWords >= 8) {
            diabetesPatientRiskLevel = EnumRisk.EARLY_ONSET;
        }
        return new Report(patient.getFirstName(), patient.getLastName(), patient.getGender(), patientAge, diabetesPatientRiskLevel);
    }
}
