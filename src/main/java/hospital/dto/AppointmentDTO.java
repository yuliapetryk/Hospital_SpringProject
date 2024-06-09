package hospital.dto;

import hospital.entities.Appointment;

public class AppointmentDTO {
    private int id;
    private String date;
    private String  doctorId;
    private String diagnosis;
    private String procedure;
    private String details;
    private boolean status;
    private String patientName;

    // Getters and setters

    public AppointmentDTO(Appointment appointment, String patientName) {
        this.id = appointment.getId();
        this.date = appointment.getDate();
        this.doctorId = appointment.getDoctorId();
        this.diagnosis = appointment.getDiagnosis();
        this.procedure = appointment.getProcedure();
        this.details = appointment.getDetails();
        this.status = appointment.isStatus();
        this.patientName = patientName;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String  getDoctorId() {
        return doctorId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getProcedure() {
        return procedure;
    }

    public String getDetails() {
        return details;
    }

    public boolean isStatus() {
        return status;
    }

    public String getPatientName() {
        return patientName;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDoctorId(String  doctorId) {
        this.doctorId = doctorId;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
}
