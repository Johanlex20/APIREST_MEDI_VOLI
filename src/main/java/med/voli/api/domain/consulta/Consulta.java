package med.voli.api.domain.consulta;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import med.voli.api.domain.medico.Medico;
import med.voli.api.domain.paciente.Paciente;
import java.time.LocalDateTime;

@Entity(name = "consulta")
@Table(name = "consultas")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    @NotNull
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    @NotNull
    private Paciente paciente;

    private LocalDateTime data;


}
