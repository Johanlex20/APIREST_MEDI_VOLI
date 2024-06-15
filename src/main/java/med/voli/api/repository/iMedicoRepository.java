package med.voli.api.repository;
import med.voli.api.medico.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface iMedicoRepository extends JpaRepository<Medico,Long> {
}
