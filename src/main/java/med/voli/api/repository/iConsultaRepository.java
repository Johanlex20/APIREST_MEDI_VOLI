package med.voli.api.repository;
import med.voli.api.domain.consulta.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iConsultaRepository extends JpaRepository<Consulta,Long> {
}
