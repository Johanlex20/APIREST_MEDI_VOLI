package med.voli.api.domain.paciente;

import med.voli.api.domain.direccion.DatosDireccion;

public record DatosRespuestaPaciente(
        Long id,
        String nombre,
        String email,
        String telefono,
        DatosDireccion direccion
) {
}
