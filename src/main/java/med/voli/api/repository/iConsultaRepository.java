package med.voli.api.repository;
import med.voli.api.domain.consulta.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface iConsultaRepository extends JpaRepository<Consulta,Long> {

    Boolean existsByPacienteIdAndDataBetween(Long idPaciente, LocalDateTime primerHorario, LocalDateTime ultimoHorario);

    Boolean existByMedicoIdAndData(Long aLong, LocalDateTime fecha);
}
