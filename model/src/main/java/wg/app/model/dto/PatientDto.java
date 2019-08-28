package wg.app.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PatientDto
{

    private Long id;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;

}
