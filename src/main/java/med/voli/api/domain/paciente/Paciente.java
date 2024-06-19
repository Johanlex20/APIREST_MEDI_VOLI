package med.voli.api.domain.paciente;
import jakarta.persistence.*;
import lombok.*;
import med.voli.api.domain.direccion.Direccion;

@Entity(name = "paciente")
@Table(name = "pacientes")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String nombre;

    @Column(unique = true, length = 6)
    private String documento;

    @Column(unique = true, length = 100)
    private String email;

    @Column(length = 10)
    private String telefono;

    @Embedded
    private Direccion direccion;

    private Boolean activo;


    public Paciente(DatosRegistroPaciente datosRegistroPaciente) {

        this.nombre = datosRegistroPaciente.nombre();
        this.documento = datosRegistroPaciente.documento();
        this.email = datosRegistroPaciente.email();
        this.telefono = datosRegistroPaciente.telefono();
        this.direccion = new Direccion(datosRegistroPaciente.direccion());
        this.activo = true;
    }

    public void actualizarDatosPaciente(DatosActualizarPaciente datosActualizarPaciente) {

        if (datosActualizarPaciente.nombre() != null){
            this.nombre = datosActualizarPaciente.nombre();
        }
        if (datosActualizarPaciente.documento() != null){
            this.documento = datosActualizarPaciente.documento();
        }
        if (datosActualizarPaciente.direccion() != null){
            this.direccion = direccion.actualizarDatos(datosActualizarPaciente.direccion());
        }
    }

    public void desactivarPaciente() {
        this.activo = false;
    }
}
