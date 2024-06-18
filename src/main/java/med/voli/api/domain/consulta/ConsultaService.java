package med.voli.api.domain.consulta;
import med.voli.api.domain.medico.Medico;
import med.voli.api.domain.paciente.Paciente;
import med.voli.api.repository.iConsultaRepository;
import med.voli.api.repository.iMedicoRepository;
import med.voli.api.repository.iPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {

    @Autowired
    private iMedicoRepository medicoRepository;
    @Autowired
    private iPacienteRepository pacienteRepository;
    @Autowired
    private iConsultaRepository consultaRepository;

    public void agendar(DatosAgendarConsulta datos){

        var paciente = pacienteRepository.findById(datos.idPaciente()).get();
        var medico = medicoRepository.findById(datos.idMedico()).get();

        var consulta = new Consulta(null, medico, paciente, datos.fecha());

        consultaRepository.save(consulta);
    }

}
