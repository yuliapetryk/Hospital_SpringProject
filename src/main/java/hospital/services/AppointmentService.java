package hospital.services;


import hospital.dto.AppointmentDTO;
import hospital.entities.Appointment;
import hospital.repository.AppointmentRepository;
import hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
    }

    public List<AppointmentDTO> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();

        return appointments.stream()
                .map(appointment -> {
                    String nameParts = patientRepository.findNameById(appointment.getPatientId());
                    String patientName = "";
                    if (nameParts != null) {
                        patientName = nameParts.replaceAll(",",  " ");
                    }
                    return new AppointmentDTO(appointment, patientName);
                })
                .collect(Collectors.toList());
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
