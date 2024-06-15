package med.voli.api.controllers;
import jakarta.validation.Valid;
import med.voli.api.medico.DatosRegistroMedico;
import med.voli.api.medico.Medico;
import med.voli.api.repository.iMedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/medicos")
public class MedicoController {

    @Autowired
    private iMedicoRepository medicoRepository;

    @PostMapping
    public void registrarMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico){
        medicoRepository.save(new Medico(datosRegistroMedico));
    }

}
