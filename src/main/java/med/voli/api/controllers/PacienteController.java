package med.voli.api.controllers;
import jakarta.validation.Valid;
import med.voli.api.domain.direccion.DatosDireccion;
import med.voli.api.domain.paciente.*;
import med.voli.api.repository.iPacienteRepository;
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
@RequestMapping(value = "/pacientes")
public class PacienteController {

    @Autowired
    private iPacienteRepository pacienteRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaPaciente> registrarPaciente(@RequestBody @Valid DatosRegistroPaciente datosRegistroPaciente,
                                                                    UriComponentsBuilder uriComponentsBuilder){
        Paciente paciente = pacienteRepository.save(new Paciente(datosRegistroPaciente));
        DatosRespuestaPaciente datosRespuestaPaciente = new DatosRespuestaPaciente(
                paciente.getId(),
                paciente.getNombre(),
                paciente.getEmail(),
                paciente.getTelefono(),
                new DatosDireccion(paciente.getDireccion().getCalle(),
                        paciente.getDireccion().getDistrito(),
                        paciente.getDireccion().getCiudad(),
                        paciente.getDireccion().getNumero(),
                        paciente.getDireccion().getComplemento())
        );
        URI url = uriComponentsBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaPaciente);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DatosRespuestaPaciente> retornaDatosPaciente(@PathVariable(value = "id") Long id){
        Paciente paciente = pacienteRepository.getReferenceById(id);
        var datosPaciente = new DatosRespuestaPaciente(
                paciente.getId(),
                paciente.getNombre(),
                paciente.getEmail(),
                paciente.getTelefono(),
                new DatosDireccion(paciente.getDireccion().getCalle(),
                        paciente.getDireccion().getDistrito(),
                        paciente.getDireccion().getCiudad(),
                        paciente.getDireccion().getNumero(),
                        paciente.getDireccion().getComplemento())
        );
        return ResponseEntity.ok(datosPaciente);
    }


    @GetMapping
    public ResponseEntity<Page<DatosListadoPaciente>> listadoPaciente(@PageableDefault(size = 20) Pageable pageable){
        return ResponseEntity.ok(pacienteRepository.findByActivoTrue(pageable).map(DatosListadoPaciente::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarPaciente(@RequestBody @Valid DatosActualizarPaciente datosActualizarPaciente){
        Paciente pacienteActualizado = pacienteRepository.getReferenceById(datosActualizarPaciente.id());
        pacienteActualizado.actualizarDatosPaciente(datosActualizarPaciente);
        return ResponseEntity.ok(new DatosRespuestaPaciente(
                pacienteActualizado.getId(),
                pacienteActualizado.getNombre(),
                pacienteActualizado.getEmail(),
                pacienteActualizado.getTelefono(),
                new DatosDireccion(pacienteActualizado.getDireccion().getCalle(),
                        pacienteActualizado.getDireccion().getDistrito(),
                        pacienteActualizado.getDireccion().getCiudad(),
                        pacienteActualizado.getDireccion().getNumero(),
                        pacienteActualizado.getDireccion().getComplemento())
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
        Paciente pacienteDesactivado = pacienteRepository.getReferenceById(id);
        pacienteDesactivado.desactivarPaciente();
        return ResponseEntity.noContent().build();
    }

}
