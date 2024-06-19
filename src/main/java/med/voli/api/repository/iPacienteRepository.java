package med.voli.api.repository;
import med.voli.api.domain.paciente.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface iPacienteRepository extends JpaRepository<Paciente,Long> {
    Page<Paciente>findByActivoTrue(Pageable pageable);

    @Query("""
            SELECT p.activo
            FROM paciente p
            WHERE p.id=:idPaciente
            """)
    Boolean findActivoById(Long idPaciente);
}
