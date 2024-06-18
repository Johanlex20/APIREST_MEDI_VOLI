package med.voli.api.repository;
import med.voli.api.domain.paciente.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface iPacienteRepository extends JpaRepository<Paciente,Long> {
    Page<Paciente>findByActivoTrue(Pageable pageable);
}
