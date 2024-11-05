package husjp.api.asignacionCamasMicroservicio.repository;

import husjp.api.asignacionCamasMicroservicio.entity.Cama;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CamaRepository extends JpaRepository<Cama, Integer> {

    Optional<Cama> findByCodigo(String codigo);

}
