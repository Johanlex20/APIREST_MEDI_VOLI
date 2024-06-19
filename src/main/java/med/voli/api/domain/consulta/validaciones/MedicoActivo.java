package med.voli.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voli.api.domain.consulta.DatosAgendarConsulta;
import med.voli.api.repository.iMedicoRepository;

public class MedicoActivo {

    private iMedicoRepository medicoRepository;

    public void validar(DatosAgendarConsulta datos){
        if(datos.idMedico() == null){
            return;
        }

        var medicoActivo = medicoRepository.findActivoById(datos.idMedico());

        if (!medicoActivo){
            throw new ValidationException("No se permite agendar citas con medicos inactivos en el sistema.");
        }


    }
}


