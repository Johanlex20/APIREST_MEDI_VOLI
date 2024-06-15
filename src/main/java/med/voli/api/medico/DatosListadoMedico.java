package med.voli.api.medico;

public record DatosListadoMedico(
        String nombre,
        String especialidad,
        String documenteo,
        String email
) {
    public DatosListadoMedico(Medico medico){
        this(medico.getNombre(), medico.getEspecialidad().toString(), medico.getDocumento(), medico.getEmail());
    }
}
