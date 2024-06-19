package med.voli.api.domain.consulta.validaciones;
import jakarta.validation.ValidationException;
import med.voli.api.domain.consulta.DatosAgendarConsulta;
import med.voli.api.repository.iPacienteRepository;


public class PacienteActivo {

    private iPacienteRepository pacienteRepository;

    public void validar(DatosAgendarConsulta datos){
        if (datos.idPaciente() == null){
            return;
        }

        var pacienteActivo = pacienteRepository.findActivoById(datos.idPaciente());

        if (!pacienteActivo){
            throw new ValidationException("No se permite agendar citas con pacientes inactivos en el sistema.");
        }


    }

}
