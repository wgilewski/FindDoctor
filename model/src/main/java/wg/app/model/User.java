package wg.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String password;
    private Boolean enabled;
    private Role role;

    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

}
