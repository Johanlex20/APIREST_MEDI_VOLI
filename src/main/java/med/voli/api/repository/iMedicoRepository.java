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

    @Query(""" 
            select m form Medico m
            where m.activo= 1 and
            m.especialidad=:especialidad and
            m.id not in(
            select c.medico.id from Consulta c
            c.data=:fecha
            )
            order by rand()
            limit 1
            """)
    Medico seleccionarMedicoConEspecialidadEnFecha(Especialidad especialidad, LocalDateTime fecha);
}
