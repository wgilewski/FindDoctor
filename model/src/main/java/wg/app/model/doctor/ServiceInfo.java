package wg.app.model.doctor;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "service")
public class ServiceInfo
{
    @Id
    @GeneratedValue
    private Long id;

    private String serviceName;
    private BigDecimal price;
    private String description;

    @Enumerated(EnumType.STRING)
    private Specialization specialization;

    @ManyToOne(cascade = CascadeType.MERGE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Doctor doctor;
}
