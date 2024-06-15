package med.voli.api.medico;
import jakarta.persistence.*;
import lombok.*;
import med.voli.api.direccion.Direccion;

@Entity(name = "medico")
@Table(name = "medicos")
@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String nombre;

    @Column(unique = true, length = 100)
    private String email;

    @Column(unique = true, length = 6)

    private String documento;

    @Enumerated(EnumType.STRING)
    @Column(length = 100)
    private Especialidad especialidad;

    @Embedded
    private Direccion direccion;

    private String telefono;

    public Medico(DatosRegistroMedico datosRegistroMedico) {
        this.nombre = datosRegistroMedico.nombre();
        this.email = datosRegistroMedico.email();
        this.documento = datosRegistroMedico.documento();
        this.especialidad = datosRegistroMedico.especialidad();
        this.direccion = new Direccion(datosRegistroMedico.direccion());
        this.telefono = datosRegistroMedico.telefono();
    }
}
