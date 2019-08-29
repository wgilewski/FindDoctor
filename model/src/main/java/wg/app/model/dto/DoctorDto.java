package wg.app.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import wg.app.model.user.Gender;
import wg.app.model.user.Role;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class DoctorDto
{
    private Long id;
    private String username;
    private String password;
    private Boolean enabled;
    private Role role;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String email;
    private Gender gender;
}
