package com.mediscreen.report;
import com.mediscreen.report.entity.*;
import com.mediscreen.report.service.PatientReportService;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PatientReportServiceTest {
    @Autowired
    PatientReportService patientReportService;


//    @Test
//    void generatePatientDiabetesReportTest(){
//        EnumRisk enumRisk = EnumRisk.NONE;
//        Patient patient= new Patient("John","Snow","03/12/1992","Homme","address","phone");
//        patient.setId(2);
//        PatientNote patientNote = new PatientNote(1, "Note");
//        Report Report = new Report("John", "Snow", "Homme", 29, enumRisk );
//        PatientWithNote patientWithNote = new PatientWithNote(patient, patientNote);
//        patientReportService.generatePatientDiabetesReport(patientWithNote.getPatient().getId());
//        assertEquals("John", Report.getFirstName());
//        assertEquals(EnumRisk.NONE, Report.getDiabetesPatientRiskLevel());
//    }

    @Test
    void  calculPatientDiabetesReportTest(){
        Patient patient= new Patient("Cersei","Lanister","03/12/1988","Femme","address","phone");
        PatientNote patientNote = new PatientNote(1, "Taille fumeur");
        EnumRisk enumRisk = EnumRisk.BORDERLINE;
        Report reportTest = patientReportService.calculPatientDiabetesReport(patient, patientNote);
        assertEquals("Cersei", reportTest.getFirstName());
        assertEquals(EnumRisk.BORDERLINE, reportTest.getDiabetesPatientRiskLevel());
    }

    @Test
    void calculPatientAgeFromDateOfBirthTest(){
        Patient patient= new Patient("Cersei","Lanister","03/12/1988","Femme","address","phone");
        int age = patientReportService.calculPatientAgeFromDateOfBirth(patient.getDateOfBirth());
        assertEquals(34, age);
    }

    @Test
    void numberOfTriggerWordTest(){
        PatientNote patientNote = new PatientNote(1, "Persone de grande taille fumeuse, souffre de cholestérol anormal. Elle a fait une rechute, pleins de réaction avec anticorps");
        int triggerWords = patientReportService.numberOfTriggerWord(patientNote);
        assertEquals(7, triggerWords);
    }

    @Test
    void manDiabetesReportWithNoneRiskTest(){
        Patient patient= new Patient("John","Snow","03/12/1992","Homme","address","phone");
        int age = 29;
        int triggerWords = 2;
        Report reportTest = patientReportService.manDiabetesReport(patient, age, triggerWords);
        assertEquals(EnumRisk.NONE, reportTest.getDiabetesPatientRiskLevel());
    }

    @Test
    void manDiabetesReportWithBorderlineRiskTest(){
        Patient patient= new Patient("John","Snow","03/12/1988","Homme","address","phone");
        int age = 33;
        int triggerWords = 4;
        Report reportTest = patientReportService.manDiabetesReport(patient, age, triggerWords);
        assertEquals(EnumRisk.BORDERLINE, reportTest.getDiabetesPatientRiskLevel());
    }

    @Test
    void manDiabetesReportWithInDangerRiskTest(){
        Patient patient= new Patient("John","Snow","03/12/1988","Homme","address","phone");
        int age = 33;
        int triggerWords = 6;
        Report reportTest = patientReportService.manDiabetesReport(patient, age, triggerWords);
        assertEquals(EnumRisk.IN_DANGER, reportTest.getDiabetesPatientRiskLevel());
    }

    @Test
    void manDiabetesReportWithEarlyOnsetRiskTest1(){
        Patient patient= new Patient("John","Snow","03/12/1992","Homme","address","phone");
        int age = 29;
        int triggerWords = 5;
        Report reportTest = patientReportService.manDiabetesReport(patient, age, triggerWords);
        assertEquals(EnumRisk.EARLY_ONSET, reportTest.getDiabetesPatientRiskLevel());
    }

    @Test
    void manDiabetesReportWithEarlyOnsetRiskTest2(){
        Patient patient= new Patient("John","Snow","03/12/1988","Homme","address","phone");
        int age = 33;
        int triggerWords = 9;
        Report reportTest = patientReportService.manDiabetesReport(patient, age, triggerWords);
        assertEquals(EnumRisk.EARLY_ONSET, reportTest.getDiabetesPatientRiskLevel());
    }

    @Test
    void womenDiabetesReportWithNoneRiskTest(){
        Patient patient= new Patient("Cersei","Lanister","03/12/1992","Femme","address","phone");
        int age = 29;
        int triggerWords = 3;
        Report reportTest = patientReportService.womenDiabetesReport(patient, age, triggerWords);
        assertEquals(EnumRisk.NONE, reportTest.getDiabetesPatientRiskLevel());
    }

    @Test
    void womenDiabetesReportWithBorderlineRiskTest(){
        Patient patient= new Patient("Cersei","Lanister","03/12/1988","Femme","address","phone");
        int age = 33;
        int triggerWords = 4;
        Report reportTest = patientReportService.womenDiabetesReport(patient, age, triggerWords);
        assertEquals(EnumRisk.BORDERLINE, reportTest.getDiabetesPatientRiskLevel());
    }

    @Test
    void womenDiabetesReportWithInDangerRiskTest(){
        Patient patient= new Patient("Cersei","Lanister","03/12/1988","Femme","address","phone");
        int age = 33;
        int triggerWords = 7;
        Report reportTest = patientReportService.womenDiabetesReport(patient, age, triggerWords);
        assertEquals(EnumRisk.IN_DANGER, reportTest.getDiabetesPatientRiskLevel());
    }

    @Test
    void womenDiabetesReportWithEarlyOnsetRiskTest1(){
        Patient patient= new Patient("Cersei","Lanister","03/12/1992","Femme","address","phone");
        int age = 29;
        int triggerWords = 10;
        Report reportTest = patientReportService.womenDiabetesReport(patient, age, triggerWords);
        assertEquals(EnumRisk.EARLY_ONSET, reportTest.getDiabetesPatientRiskLevel());
    }

    @Test
    void womenDiabetesReportWithEarlyOnsetRiskTest2(){
        Patient patient= new Patient("Cersei","Lanister","03/12/1988","Femme","address","phone");
        int age = 33;
        int triggerWords = 8;
        Report reportTest = patientReportService.womenDiabetesReport(patient, age, triggerWords);
        assertEquals(EnumRisk.EARLY_ONSET, reportTest.getDiabetesPatientRiskLevel());
    }
}
