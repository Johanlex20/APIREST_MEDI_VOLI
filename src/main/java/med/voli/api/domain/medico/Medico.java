package med.voli.api.domain.medico;
import jakarta.persistence.*;
import lombok.*;
import med.voli.api.domain.direccion.Direccion;

@Entity(name = "medico")
@Table(name = "medicos")
@Getter
@Setter
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

    @Column(length = 10)
    private String telefono;

    private Boolean activo;

    public Medico(DatosRegistroMedico datosRegistroMedico) {
        this.activo = true;
        this.nombre = datosRegistroMedico.nombre();
        this.email = datosRegistroMedico.email();
        this.documento = datosRegistroMedico.documento();
        this.especialidad = datosRegistroMedico.especialidad();
        this.direccion = new Direccion(datosRegistroMedico.direccion());
        this.telefono = datosRegistroMedico.telefono();
    }

    public void actualizarDatos(DatosActualizarMedico datosActualizarMedico) {

        if (datosActualizarMedico.nombre() != null){
            this.nombre = datosActualizarMedico.nombre();
        }
        if (datosActualizarMedico.documento() != null){
            this.documento = datosActualizarMedico.documento();
        }
        if (datosActualizarMedico.direccion() != null){
            this.direccion = direccion.actualizarDatos(datosActualizarMedico.direccion());
        }
    }

    public void desactivarMedico() {
        this.activo = false;
    }
}
