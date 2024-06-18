package med.voli.api.controllers;
import jakarta.validation.Valid;
import med.voli.api.domain.direccion.DatosDireccion;
import med.voli.api.domain.medico.*;
import med.voli.api.repository.iMedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping(value = "/medicos")
public class MedicoController {

    @Autowired
    private iMedicoRepository medicoRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaMedico> registrarMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico,
                                                                UriComponentsBuilder uriComponentsBuilder){
       Medico medico = medicoRepository.save(new Medico(datosRegistroMedico));
       DatosRespuestaMedico datosRespuestaMedico = new DatosRespuestaMedico(
                medico.getId(),
                medico.getNombre(),
                medico.getEmail(),
                medico.getTelefono(),
                medico.getEspecialidad().toString(),
                new DatosDireccion(medico.getDireccion().getCalle(),
                        medico.getDireccion().getDistrito(),
                        medico.getDireccion().getCiudad(),
                        medico.getDireccion().getNumero(),
                        medico.getDireccion().getComplemento())
        );
        URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
       return ResponseEntity.created(url).body(datosRespuestaMedico);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DatosRespuestaMedico> retornaDatosMedico(@PathVariable(value = "id") Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        var datosMedicos = new DatosRespuestaMedico(
                medico.getId(),
                medico.getNombre(),
                medico.getEmail(),
                medico.getTelefono(),
                medico.getEspecialidad().toString(),
                new DatosDireccion(medico.getDireccion().getCalle(),
                        medico.getDireccion().getDistrito(),
                        medico.getDireccion().getCiudad(),
                        medico.getDireccion().getNumero(),
                        medico.getDireccion().getComplemento())
        );
        return ResponseEntity.ok(datosMedicos);
    }


    @GetMapping
    public ResponseEntity<Page<DatosListadoMedico>> listadoMedicos(@PageableDefault(size = 20) Pageable pageable){
        //return medicoRepository.findAll(pageable).map(DatosListadoMedico::new);
        return ResponseEntity.ok(medicoRepository.findByActivoTrue(pageable).map(DatosListadoMedico::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico){
        Medico medicoActualizado = medicoRepository.getReferenceById(datosActualizarMedico.id());
        medicoActualizado.actualizarDatos(datosActualizarMedico);
        return ResponseEntity.ok(new DatosRespuestaMedico(
                medicoActualizado.getId(),
                medicoActualizado.getNombre(),
                medicoActualizado.getEmail(),
                medicoActualizado.getTelefono(),
                medicoActualizado.getEspecialidad().toString(),
                new DatosDireccion(medicoActualizado.getDireccion().getCalle(),
                        medicoActualizado.getDireccion().getDistrito(),
                        medicoActualizado.getDireccion().getCiudad(),
                        medicoActualizado.getDireccion().getNumero(),
                        medicoActualizado.getDireccion().getComplemento())
        ));
    }

//    @DeleteMapping(value = "/{id}")
//    @Transactional
//    public void eliminarMedico(@PathVariable(value = "id") Long id){
//        Medico medicoEliminado = medicoRepository.getReferenceById(id);
//        medicoRepository.delete(medicoEliminado);
//    }

    @DeleteMapping(value = "/{id}")
    @Transactional
    public ResponseEntity eliminarMedico(@PathVariable(value = "id") Long id){
        Medico medicoDesactivado = medicoRepository.getReferenceById(id);
        medicoDesactivado.desactivarMedico();
        return ResponseEntity.noContent().build();
    }


}
