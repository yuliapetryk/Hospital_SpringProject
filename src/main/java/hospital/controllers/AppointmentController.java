package hospital.controllers;


import hospital.entities.Appointment;
import hospital.services.AppointmentService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @RolesAllowed("doctor")
    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @RolesAllowed({"doctor", "nurse"})
    @GetMapping("/patient/{patientId}")
    public List<Appointment> getAppointmentsByPatientId(@PathVariable int patientId) {
        return appointmentService.getAppointmentsByPatientId(patientId);
    }

    @RolesAllowed("doctor")
    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        return appointmentService.createAppointment(appointment);
    }

    @RolesAllowed("doctor")
    @PutMapping("/{id}/status")
    public int updateAppointmentStatus(@PathVariable int id) {
        return appointmentService.updateAppointmentStatus(id);
    }

    @RolesAllowed("doctor")
    @PutMapping("/{id}")
    public Appointment updateAppointment(@PathVariable int id, @RequestBody Appointment appointment) {
        return appointmentService.updateAppointment(id, appointment);
    }

    @RolesAllowed("doctor")
    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable int id) {
        appointmentService.deleteAppointment(id);
    }

    @RolesAllowed("nurse")
    @GetMapping("/non-surgery")
    public List<Appointment> getAppointmentsWithoutSurgery() {
        return appointmentService.getAppointmentsWithoutSurgery();
    }
}
