package wg.app.model.doctor;

import lombok.*;
import wg.app.model.Gender;
import wg.app.model.Role;
import wg.app.model.User;
import wg.app.model.Visit;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "doctors")
public class Doctor extends User
{

    @Builder
    public Doctor(Long id, String username, String password, Boolean enabled, Role role, String name, String surname, LocalDate dateOfBirth, String email, Gender gender, List<Specialization> specializations, Set<ServiceInfo> serviceInfos, List<Visit> visits) {
        super(id, username, password, enabled, role, name, surname, dateOfBirth, email, gender);
        this.specializations = specializations;
        this.serviceInfos = serviceInfos;
        this.visits = visits;
    }




    @ElementCollection(targetClass = Specialization.class)
    @Column(name = "specializations", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<Specialization> specializations;


    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<ServiceInfo> serviceInfos;

    @OneToMany(mappedBy = "doctor")
    private List<Visit> visits;


}
