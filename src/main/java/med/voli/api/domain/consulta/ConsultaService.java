package med.voli.api.domain.consulta;
import med.voli.api.domain.consulta.validaciones.iValidadorDeConsultas;
import med.voli.api.domain.medico.Medico;
import med.voli.api.infra.errores.ValidacionDeIntegridad;
import med.voli.api.repository.iConsultaRepository;
import med.voli.api.repository.iMedicoRepository;
import med.voli.api.repository.iPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private iMedicoRepository medicoRepository;
    @Autowired
    private iPacienteRepository pacienteRepository;
    @Autowired
    private iConsultaRepository consultaRepository;
    @Autowired
    List<iValidadorDeConsultas> validadores;

    public void agendar(DatosAgendarConsulta datos){

        if(pacienteRepository.findById(datos.idPaciente()).isPresent()){
            throw new ValidacionDeIntegridad("Id para el paciente no fue encotrado");
        }

        if (datos.idMedico() != null && medicoRepository.existsById(datos.idMedico())){
            throw new ValidacionDeIntegridad("Id para el medico no fue encotrado");
        }

        //VALIDACIONES
        validadores.forEach(v->v.validar(datos));

        var paciente = pacienteRepository.findById(datos.idPaciente()).get();
        var medico = seleccionarMedico(datos);
        var consulta = new Consulta(null, medico, paciente, datos.fecha());

        consultaRepository.save(consulta);
    }

    private Medico seleccionarMedico(DatosAgendarConsulta datos) {
        if (datos.idMedico() != null){
            return medicoRepository.getReferenceById(datos.idMedico());
        }
        if (datos.especialidad() == null){
            throw new ValidacionDeIntegridad("debe selecionarce una especialidad para el medico");
        }
        return medicoRepository.seleccionarMedicoConEspecialidadEnFecha(datos.especialidad(), datos.fecha());
    }

}
