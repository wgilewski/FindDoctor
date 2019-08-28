package wg.app.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import wg.app.model.Gender;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class DoctorDto
{

    private Long id;
    private String name;
    private String surname;
    private Gender gender;


}
