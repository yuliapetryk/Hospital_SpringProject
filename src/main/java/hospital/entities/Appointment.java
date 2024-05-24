package hospital.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    private int id;
     private String date;
    private int doctorId;
    private int patientId;
    private String diagnosis;
    private  String medication;
    private  String procedure;
    private  String surgery;
    private  boolean status;

   public Appointment( String date, int doctorId, int patientId, String diagnosis,String medication, String procedure, String surgery){
    this.date = date;
    this.doctorId = doctorId;
    this.patientId = patientId;
    this.diagnosis = diagnosis;
    this.procedure = procedure;
    this.medication = medication;
    this.surgery = surgery;
    }

    public Appointment( int id, String date, int doctorId, int patientId, String diagnosis,String medication, String procedure, String surgery){
        this.id  = id;
        this.date = date;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.diagnosis = diagnosis;
        this.procedure = procedure;
        this.medication = medication;
        this.surgery = surgery;
    }

    public Appointment() {
    }

    public Appointment( int id, String date, int doctorId, int patientId, String diagnosis,String medication, String procedure, String surgery, boolean status){
        this.id  = id;
        this.date = date;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.diagnosis = diagnosis;
        this.procedure = procedure;
        this.medication = medication;
        this.surgery = surgery;
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Appointment appointment = (Appointment) obj;
        return doctorId == appointment.doctorId &&
                patientId == appointment.patientId &&
                status == appointment.status &&
                Objects.equals(date, appointment.date) &&
                Objects.equals(diagnosis, appointment.diagnosis) &&
                Objects.equals(medication, appointment.medication) &&
                Objects.equals(procedure, appointment.procedure) &&
                Objects.equals(surgery, appointment.surgery);
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", doctorId=" + doctorId +
                ", patientId=" + patientId +
                ", diagnosis='" + diagnosis + '\'' +
                ", medication='" + medication + '\'' +
                ", procedure='" + procedure + '\'' +
                ", surgery='" + surgery + '\'' +
                ", status=" + status +
                '}';
    }



}
