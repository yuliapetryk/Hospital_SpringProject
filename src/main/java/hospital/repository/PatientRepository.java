package hospital.repository;


import hospital.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    @Query("SELECT p FROM Patient p")
    List<Patient> findAll();


    @Query("SELECT p.first_name, p.last_name, p.patronymic FROM Patient p WHERE p.id = :id")
    String findNameById(@Param("id") int id);
}