package husjp.api.asignacionCamasMicroservicio.repository;

import husjp.api.asignacionCamasMicroservicio.entity.Cama;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CamaRepository extends JpaRepository<Cama, Integer> {

    Optional<Cama> findByCodigo(String codigo);
    Optional<List<Cama>> findByServicioId(Integer idServicio);

}
