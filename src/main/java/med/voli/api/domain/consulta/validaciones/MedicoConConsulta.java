package med.voli.api.domain.consulta.validaciones;
import jakarta.validation.ValidationException;
import med.voli.api.domain.consulta.DatosAgendarConsulta;
import med.voli.api.repository.iConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoConConsulta implements iValidadorDeConsultas {
    @Autowired
    private iConsultaRepository consultaRepository;
    public void validar(DatosAgendarConsulta datos){
        if (datos.idMedico() == null){
            return;
        }

        var medicoConConsulta = consultaRepository.existByMedicoIdAndData(datos.idMedico(), datos.fecha());
        if (medicoConConsulta){
            throw new ValidationException("este medico ya tiene  una consulta en ese horario");
        }
    }

}
