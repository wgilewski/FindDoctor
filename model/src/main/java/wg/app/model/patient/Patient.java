package wg.app.model.patient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import wg.app.model.Visit;
import wg.app.model.doctor.Doctor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder


@Entity
@Table(name = "patients")
public class Patient
{
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String surname;
    private LocalDate dateOfBirth;

    @ElementCollection
    @CollectionTable(joinColumns=@JoinColumn(name="patient_id"))
    private List<Doctor> favouriteDoctors;

    @OneToMany(mappedBy = "patient")
    private List<Visit> visits;
}
