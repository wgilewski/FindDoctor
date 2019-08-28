package wg.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import wg.app.model.doctor.Doctor;
import wg.app.model.patient.Patient;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "visits")
public class Visit
{
    @Id
    @GeneratedValue
    private Long id;

    private BigDecimal price;
    private LocalDateTime visitDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    private Patient patient;
}
