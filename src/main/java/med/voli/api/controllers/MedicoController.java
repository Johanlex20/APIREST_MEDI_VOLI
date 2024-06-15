package med.voli.api.controllers;
import jakarta.validation.Valid;
import med.voli.api.medico.DatosActualizarMedico;
import med.voli.api.medico.DatosListadoMedico;
import med.voli.api.medico.DatosRegistroMedico;
import med.voli.api.medico.Medico;
import med.voli.api.repository.iMedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
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
    public Page<DatosListadoMedico> listadoMedicos(@PageableDefault(size = 20) Pageable pageable){
        //return medicoRepository.findAll(pageable).map(DatosListadoMedico::new);
        return medicoRepository.findByActivoTrue(pageable).map(DatosListadoMedico::new);
    }

    @PutMapping
    @Transactional
    public void actualizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico){
        Medico medicoActualizado = medicoRepository.getReferenceById(datosActualizarMedico.id());
        medicoActualizado.actualizarDatos(datosActualizarMedico);
    }

//    @DeleteMapping(value = "/{id}")
//    @Transactional
//    public void eliminarMedico(@PathVariable(value = "id") Long id){
//        Medico medicoEliminado = medicoRepository.getReferenceById(id);
//        medicoRepository.delete(medicoEliminado);
//    }

    @DeleteMapping(value = "/{id}")
    @Transactional
    public void eliminarMedico(@PathVariable(value = "id") Long id){
        Medico medicoDesactivado = medicoRepository.getReferenceById(id);
        medicoDesactivado.desactivarMedico();
    }


}
