package med.voli.api.domain.consulta.validaciones;
import jakarta.validation.ValidationException;
import med.voli.api.domain.consulta.DatosAgendarConsulta;
import med.voli.api.repository.iPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteActivo implements iValidadorDeConsultas {

    @Autowired
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
