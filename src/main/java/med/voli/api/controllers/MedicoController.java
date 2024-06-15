package med.voli.api.controllers;
import jakarta.validation.Valid;
import med.voli.api.medico.DatosListadoMedico;
import med.voli.api.medico.DatosRegistroMedico;
import med.voli.api.medico.Medico;
import med.voli.api.repository.iMedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/medicos")
public class MedicoController {

    @Autowired
    private iMedicoRepository medicoRepository;

    @PostMapping
    public void registrarMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico){
        medicoRepository.save(new Medico(datosRegistroMedico));
    }

//    @GetMapping
//    public List<DatosListadoMedico> listadoMedicos(){
//        return medicoRepository.findAll().stream().map(DatosListadoMedico::new).toList();
//    }

    @GetMapping
    public Page<DatosListadoMedico> listadoMedicos(@PageableDefault(size = 2) Pageable pageable){
        return medicoRepository.findAll(pageable).map(DatosListadoMedico::new);
    }

}
