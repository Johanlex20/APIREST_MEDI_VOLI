package med.voli.api.domain.paciente;

import jakarta.validation.constraints.NotNull;
import med.voli.api.domain.direccion.DatosDireccion;

public record DatosActualizarPaciente(

        @NotNull
        Long id,
        String nombre,
        String documento,
        DatosDireccion direccion

) {
}
