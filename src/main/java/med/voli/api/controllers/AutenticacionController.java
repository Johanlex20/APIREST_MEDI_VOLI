package med.voli.api.controllers;
import jakarta.validation.Valid;
import med.voli.api.domain.usuarios.DatosAutenticacionUsuario;
import med.voli.api.domain.usuarios.Usuarios;
import med.voli.api.infra.Security.DatosJWTToken;
import med.voli.api.infra.Security.TokenService;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager autenticacionManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario){
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
                datosAutenticacionUsuario.login(),
                datosAutenticacionUsuario.clave());
        var usuarioAuthenticado = autenticacionManager.authenticate(authenticationToken);
        var jwtToken = tokenService.generarToken((Usuarios) usuarioAuthenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(jwtToken));
    }

}

