package med.voli.api.domain.paciente;

public record DatosListadoPaciente(

        Long id,
        String nombre,
        String documenteo,
        String email

) {

    public DatosListadoPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNombre(), paciente.getDocumento(), paciente.getEmail());
    }
}
