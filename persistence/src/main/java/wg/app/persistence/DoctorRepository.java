package wg.app.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wg.app.model.doctor.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long>
{

}
