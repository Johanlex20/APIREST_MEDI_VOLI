package med.voli.api.repository;
import med.voli.api.domain.medico.Especialidad;
import med.voli.api.domain.medico.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDateTime;

public interface iMedicoRepository extends JpaRepository<Medico,Long> {
    Page<Medico> findByActivoTrue(Pageable pageable);

    @Query(value = "SELECT * FROM medicos m " +
            "WHERE m.activo = 1 AND " +
            "m.especialidad = :especialidad AND " +
            "m.id NOT IN ( " +
            "  SELECT c.medico_id FROM consultas c " +
            "  WHERE c.data = :fecha " +
            ") " +
            "ORDER BY RAND() " +
            "LIMIT 1",
            nativeQuery = true)
//        @Query(value = "SELECT * FROM Medicos" +
//                " WHERE Medico.activo = 1 AND Medicos.especialidad = especialidad AND Medicos.id", nativeQuery = true)
        Medico seleccionarMedicoConEspecialidadEnFecha(Especialidad especialidad, LocalDateTime fecha);

        @Query("""
            SELECT m.activo
            FROM medico m
            WHERE m.id=:idMedico
            """)
        Boolean findActivoById(Long idMedico);
}
