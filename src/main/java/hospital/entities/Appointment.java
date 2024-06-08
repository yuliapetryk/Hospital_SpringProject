package hospital.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @NonNull
    private int id;
    private String date;
    private int doctorId;
    private int patientId;
    private String diagnosis;
    private  String procedure;
    private  String details;
    private  boolean status;

   public Appointment( String date, int doctorId, int patientId, String diagnosis, String procedure, String details){
    this.date = date;
    this.doctorId = doctorId;
    this.patientId = patientId;
    this.diagnosis = diagnosis;
    this.procedure = procedure;
    this.details  = details;
    }

    public Appointment( int id, String date, int doctorId, int patientId, String diagnosis, String procedure, String details){
        this.id  = id;
        this.date = date;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.diagnosis = diagnosis;
        this.procedure = procedure;
        this.details = details;
    }

    public Appointment() {
    }

    public Appointment( int id, String date, int doctorId, int patientId, String diagnosis, String procedure, String details, boolean status){
        this.id  = id;
        this.date = date;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.diagnosis = diagnosis;
        this.procedure = procedure;
        this.details = details;
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
                Objects.equals(procedure, appointment.procedure) &&
                Objects.equals(details, appointment.details) ;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", doctorId=" + doctorId +
                ", patientId=" + patientId +
                ", diagnosis='" + diagnosis + '\'' +
                ", procedure='" + procedure + '\'' +
                ", details='" + details + '\'' +
                ", status=" + status +
                '}';
    }



}
