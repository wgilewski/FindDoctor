package wg.app.model.patient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import wg.app.model.Gender;
import wg.app.model.Role;
import wg.app.model.User;
import wg.app.model.Visit;
import wg.app.model.doctor.Doctor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data


@Entity
@Table(name = "patients")
public class Patient extends User
{
    @Builder
    public Patient(Long id, String username, String password, Boolean enabled, Role role, String name, String surname, LocalDate dateOfBirth, String email, Gender gender, List<Doctor> favouriteDoctors, List<Visit> visits) {
        super(id, username, password, enabled, role, name, surname, dateOfBirth, email, gender);
        this.favouriteDoctors = favouriteDoctors;
        this.visits = visits;
    }



    @ElementCollection
    @CollectionTable(joinColumns=@JoinColumn(name="patient_id"))
    private List<Doctor> favouriteDoctors;

    @OneToMany(mappedBy = "patient")
    private List<Visit> visits;
}
