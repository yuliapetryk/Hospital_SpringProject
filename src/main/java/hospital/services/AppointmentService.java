package hospital.services;


import hospital.entities.Appointment;
import hospital.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> getAppointmentsByPatientId(int patientId) {
        return appointmentRepository.findAppointmentById(patientId);
    }

    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Transactional
    public int updateAppointmentStatus(int appointmentId) {
        return appointmentRepository.updateAppointmentStatus(appointmentId);
    }

    public Appointment updateAppointment(int id, Appointment updatedAppointment) {
        return appointmentRepository.findById(id).map(appointment -> {
            appointment.setDate(updatedAppointment.getDate());
            appointment.setDoctorId(updatedAppointment.getDoctorId());
            appointment.setPatientId(updatedAppointment.getPatientId());
            appointment.setDiagnosis(updatedAppointment.getDiagnosis());
            appointment.setProcedure(updatedAppointment.getProcedure());
            appointment.setDetails(updatedAppointment.getDetails());
            appointment.setStatus(updatedAppointment.isStatus());
            return appointmentRepository.save(appointment);
        }).orElseGet(() -> {
            updatedAppointment.setId(id);
            return appointmentRepository.save(updatedAppointment);
        });
    }

    public void deleteAppointment(int id) {
        appointmentRepository.deleteById(id);
    }

    public List<Appointment> getAppointmentsWithoutSurgery() {
        return appointmentRepository.findByProcedureNot("surgery");
    }
}
