package med.voli.api.repository;
import med.voli.api.domain.usuarios.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface iUsuarioRepository extends JpaRepository<Usuarios,Long> {
    UserDetails findByLogin(String username);
}
