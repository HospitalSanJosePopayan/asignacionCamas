package husjp.api.asignacionCamasMicroservicio.repository;

import husjp.api.asignacionCamasMicroservicio.entity.Diagnostico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DiagnosticoRepository extends JpaRepository<Diagnostico, String> {

    @Query("SELECT d FROM Diagnostico d WHERE d.id = :keyword OR d.nombre LIKE %:keyword%")
    Optional<List<Diagnostico>> findByIdOrNombre(@Param("keyword") String idOrNombre);
}
