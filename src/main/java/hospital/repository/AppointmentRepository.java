package hospital.repository;

import hospital.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    @Query("SELECT a FROM Appointment a")
    List<Appointment> findAll();

    @Query("Select a from Appointment a where a.patientId = ?1")
    List<Appointment> findAppointmentById(int id);

    @Modifying
    @Query("UPDATE Appointment a SET a.status = true WHERE a.id = ?1")
    int updateAppointmentStatus(int id);

    @Query("SELECT a FROM Appointment a WHERE a.procedure <> 'surgery'")
    List<Appointment> findByProcedureNot(String procedure);

}