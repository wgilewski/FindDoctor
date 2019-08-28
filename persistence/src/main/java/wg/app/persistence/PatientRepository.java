package wg.app.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import wg.app.model.patient.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
