package wg.app.model.doctor;

import lombok.*;
import wg.app.model.Gender;
import wg.app.model.Visit;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "doctors")
public class Doctor
{
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String surname;

    @Enumerated(EnumType.STRING)
    private Gender gender;

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
