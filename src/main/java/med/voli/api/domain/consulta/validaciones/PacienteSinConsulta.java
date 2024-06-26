package med.voli.api.domain.consulta.validaciones;
import jakarta.validation.ValidationException;
import med.voli.api.domain.consulta.DatosAgendarConsulta;
import med.voli.api.repository.iConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteSinConsulta implements iValidadorDeConsultas {

    @Autowired
    private iConsultaRepository consultaRepository;

    public void validar(DatosAgendarConsulta datos){
        var primerHorario = datos.fecha().withHour(7);
        var ultimoHorario = datos.fecha().withHour(18);
        var pacienteConConsulta = consultaRepository.existsByPacienteIdAndDataBetween(datos.idPaciente(), primerHorario,ultimoHorario);

        if (pacienteConConsulta){
            throw new ValidationException("El paciente ya tiene una consulta para ese dia");
        }
    }
}
