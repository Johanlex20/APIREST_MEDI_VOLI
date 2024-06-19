package med.voli.api.controllers;
import jakarta.validation.Valid;
import med.voli.api.domain.consulta.ConsultaService;
import med.voli.api.domain.consulta.DatosAgendarConsulta;
import med.voli.api.domain.consulta.DatosDetalleConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping(value = "/consulta")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DatosAgendarConsulta datos){

       var response = consultaService.agendar(datos);

        return ResponseEntity.ok(response);
    }

}
